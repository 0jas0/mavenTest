package com.jas.multicast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class multicastServer {
    public static void main(String[] args) {
        try {
            MulticastSocket server = new MulticastSocket();
            String message = "say lei lei";
            InetAddress inetAddress =  InetAddress.getByName("127.0.0.1");
            while (true){
                server.send(new DatagramPacket(message.getBytes(), message.length(), inetAddress, 8888));
                Thread.sleep(1000*3);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
