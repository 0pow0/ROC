package test;

import ROCBuilder.ROCBuilder;
import java.net.Socket;
import java.lang.Exception;
import java.lang.System;
import java.net.InetAddress;
import java.io.OutputStream;


public class Test {
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

        byte[] buf1 = rocb.buildCreationInfo("123", "123", "123", 3);
        try {
			os.write(buf1);
		}
		catch (Exception e) {
			System.out.println(e); 
		}
        byte[] buf2 = rocb.buildActionInfo("123", "123", "123");
        try {
			os.write(buf2);
		}
		catch (Exception e) {
			System.out.println(e); 
		}
    }
}
