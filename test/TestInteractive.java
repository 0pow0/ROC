package test;

import ROCBuilder.ROCBuilder;
import java.net.Socket;
import java.lang.Exception;
import java.lang.System;
import java.util.Scanner;
import java.net.InetAddress;
import java.io.OutputStream;


public class TestInteractive {
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

        System.out.println("0: action info; 1: creation info; 2: deletion info"); 
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
                default: break;
            }
            System.out.println();
            System.out.println("0: action info; 1: creation info; 2: deletion info");
        }
    }

    private static void requireForDeletionInfo(OutputStream os, ROCBuilder rocb, Scanner in) {
        System.out.println("uav_id"); 
        String uav_id = in.next();
        byte[] buf = rocb.buildDeletionInfo(uav_id);
        try {
			os.write(buf);
		}
		catch (Exception e) {
			System.out.println(e); 
		}
    }

    private static void requireForActionInfo(OutputStream os, ROCBuilder rocb, Scanner in) {
        System.out.println("uav_id lat lng"); 
        String uav_id = in.next();
        String lat = in.next();
        String lng = in.next();
        byte[] buf = rocb.buildActionInfo(uav_id, lat, lng);
        try {
			os.write(buf);
		}
		catch (Exception e) {
			System.out.println(e); 
		}
    }

    private static void requireForCreationInfo(OutputStream os, ROCBuilder rocb, Scanner in) {
        System.out.println("uav_id lat lng master_id"); 
        String uav_id = in.next();
        String lat = in.next();
        String lng = in.next();
        String s_master_id = in.next();
        int master_id = Integer.parseInt(s_master_id);

        byte[] buf = rocb.buildCreationInfo(uav_id, lat, lng, master_id);
        try {
			os.write(buf);
		}
		catch (Exception e) {
			System.out.println(e); 
		}
    }
}
