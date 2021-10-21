package test;

import ROCBuilder.ROCBuilder;
import ROC.*;
import com.google.flatbuffers.FlatBufferBuilder;
import java.net.Socket;
import java.net.SocketException;
import java.lang.Exception;
import java.lang.System;
import java.util.Scanner;
import java.net.InetAddress;
import java.io.InputStream;
import java.io.OutputStream;

public class TestSINR {
    /**
     * UE should be less than 50 NS3 only has 50 in the pool
     */
    private ROCBuilder rocb;
    private Socket socket;
    private OutputStream os;
    private InputStream is;

    public TestSINR() {
        rocb = new ROCBuilder();
        socket = new Socket();
        try {
            socket = new Socket("127.0.0.1", 62700);
            InetAddress addr = socket.getInetAddress();
            System.out.println(addr);
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println(socket.getInetAddress());

        os = null;
        try {
            os = socket.getOutputStream();
        } catch (Exception e) {
            System.out.println(e);
        }

        is = null;
        try {
            is = socket.getInputStream();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void readFromSocket() {
        try {
            byte[] len = this.is.readNBytes(4);
            java.nio.ByteBuffer buf = java.nio.ByteBuffer.wrap(len);
            int N = buf.getInt();
            System.out.println("Message length is " + N);
            byte[] bytes = new byte[N];
            int n = is.read(bytes);
            if (n == -1)
                throw new SocketException("No bytes is available");
            else
                System.out.println("Reading " + n + "Bytes from socket");
            buf = java.nio.ByteBuffer.wrap(bytes);
            ROCInfo roc = ROCInfo.getRootAsROCInfo(buf);
            int rocType = roc.infoType();
            if (rocType == ROCType.SINRResp) {
                SINRResp resp = (SINRResp) roc.info(new SINRResp());
                System.out.println("Uav id = " + resp.uavId());
                System.out.println("SINR = " + resp.sinr());
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void sendSINRRequest(String delay, String uavID) {
        if (delay.length() == 0)
            delay = "0s";
        byte[] buf = this.rocb.buildSINRReq(delay, uavID);
        try {
            this.os.write(buf);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void sendDeletionInfo(String delay, String uavID) {
        if (delay.length() == 0)
            delay = "0s";
        byte[] buf = this.rocb.buildDeletionInfo(delay, uavID);
        try {
            this.os.write(buf);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void sendActionInfo(String delay, String uavID, String lat, String lng) {
        if (delay.length() == 0)
            delay = "0s";
        byte[] buf = this.rocb.buildActionInfo(delay, uavID, lat, lng);
        try {
            os.write(buf);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void sendCreationInfo(String delay, String uavID, String lat, String lng, String masterID) {
        if (delay.length() == 0)
            delay = "0s";
        byte[] buf = this.rocb.buildCreationInfo(delay, uavID, lat, lng, Integer.parseInt(masterID));
        try {
            os.write(buf);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        TestSINR test = new TestSINR();
        int numUE = 25;
        String lat = "47.980315";
        String lng = "-102.939365";
        String masterID = "5";
        // initialize UE
        for (int i = 0; i < numUE; i++) {
            test.sendCreationInfo("0s", Integer.toString(i), lat, lng, masterID); 
            test.sendSINRRequest("1s", Integer.toString(i));
            test.readFromSocket();
        }
    }
}
