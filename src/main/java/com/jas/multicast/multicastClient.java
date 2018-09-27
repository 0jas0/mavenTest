package com.jas.multicast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class multicastClient {
    public static void main(String[] args) {
        try {
            InetAddress inetAddress =  InetAddress.getByName("127.0.0.1");
            MulticastSocket client = new MulticastSocket(8888);
            //client.joinGroup(inetAddress);
            byte[] bytes = new byte[256];
            DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length);
            client.receive(datagramPacket);
            System.out.println(new String(datagramPacket.getData(), 0, datagramPacket.getLength()));
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
