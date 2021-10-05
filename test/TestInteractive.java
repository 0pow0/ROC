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


public class TestInteractive {
    private static String leading = "0: action info; 1: creation info; 2: deletion info; 3: SINRReq 4: ReadFromSocket";
    public static void main(String[] args) {
        ROCBuilder rocb = new ROCBuilder();
        Socket socket = new Socket();
        try {
            socket = new Socket("127.0.0.1", 62700);
            InetAddress addr = socket.getInetAddress();
            System.out.println(addr);
        }
        catch (Exception e) {
            System.out.println(e);  
        }
        System.out.println(socket.getInetAddress());

        OutputStream os = null;
		try {
			os = socket.getOutputStream();	
		}catch (Exception e) {
            System.out.println(e); 
		}

        InputStream is = null;
		try {
			is = socket.getInputStream();	
		}catch (Exception e) {
            System.out.println(e); 
		}

        System.out.println(leading);
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            int type = in.nextInt();
            switch(type) {
                case 0: requireForActionInfo(os, rocb, in);
                        break;
                case 1: requireForCreationInfo(os, rocb, in);
                        break;
                case 2: requireForDeletionInfo(os, rocb, in); 
                        break;
                case 3: requireForSINRRequest(os, rocb, in);
                        break;
                case 4: readFromSocket(is);
                        break;
                default: break;
            }
            System.out.println();
            System.out.println(leading);
        }
    }
    
    private static void readFromSocket(InputStream is) {
        try {
            byte[] len = is.readNBytes(4);
            java.nio.ByteBuffer buf = java.nio.ByteBuffer.wrap(len);
            int N = buf.getInt();
            System.out.println("Message length is " + N);
            byte[] bytes = new byte[N];
            int n = is.read(bytes);
            if (n == -1) throw new SocketException("No bytes is available");
            else System.out.println("Reading " + n + "Bytes from socket");
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

    private static void requireForSINRRequest(OutputStream os, ROCBuilder rocb, Scanner in) {
        System.out.println("delay");
        String delay = in.next();
        if (delay.length() == 0) delay = "0.0";

        System.out.println("uav_id");
        String uav_id = in.next();
        byte[] buf = rocb.buildSINRReq(delay, uav_id);
        try {
			os.write(buf);
		}
		catch (Exception e) {
			System.out.println(e); 
		}
    }

    private static void requireForDeletionInfo(OutputStream os, ROCBuilder rocb, Scanner in) {
        System.out.println("delay"); 
        String delay = in.next();
        if (delay.length() == 0) delay = "0.0";

        System.out.println("uav_id"); 
        String uav_id = in.next();
        byte[] buf = rocb.buildDeletionInfo(delay, uav_id);
        try {
			os.write(buf);
		}
		catch (Exception e) {
			System.out.println(e); 
		}
    }

    private static void requireForActionInfo(OutputStream os, ROCBuilder rocb, Scanner in) {
        System.out.println("delay"); 
        String delay = in.next();
        if (delay.length() == 0) delay = "0.0";

        System.out.println("uav_id lat lng"); 
        String uav_id = in.next();
        String lat = in.next();
        String lng = in.next();
        byte[] buf = rocb.buildActionInfo(delay, uav_id, lat, lng);
        try {
			os.write(buf);
		}
		catch (Exception e) {
			System.out.println(e); 
		}
    }

    private static void requireForCreationInfo(OutputStream os, ROCBuilder rocb, Scanner in) {
        System.out.println("delay"); 
        String delay = in.next();
        if (delay.length() == 0) delay = "0.0";

        System.out.println("uav_id lat lng master_id"); 
        String uav_id = in.next();
        String lat = in.next();
        String lng = in.next();
        String s_master_id = in.next();
        int master_id = Integer.parseInt(s_master_id);

        byte[] buf = rocb.buildCreationInfo(delay, uav_id, lat, lng, master_id);
        System.out.println(buf.length);
        try {
			os.write(buf);
		}
		catch (Exception e) {
			System.out.println(e); 
		}
    }
}
