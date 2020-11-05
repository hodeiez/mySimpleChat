package myChat;

import javafx.scene.control.TextArea;

import javax.swing.*;
import java.io.IOException;
import java.net.*;

/**
 * Created by Hodei Eceiza
 * Date: 11/5/2020
 * Time: 16:01
 * Project: myChat
 * Copyright: MIT
 */
public class Receiver implements Runnable {
    int port = 12540;
    String group = "234.235.236.237";
    String zerotier = "eth15";
    MulticastSocket recSocket;
    String received;
    InetSocketAddress groupSocket;
    NetworkInterface netIf;
    NetworkInterface netIfZ;
    TextArea textArea;

    Receiver(TextArea textArea) {
        this.textArea = textArea;
    }

    @Override
    public void run() {
        try {
            InetAddress socketAd = InetAddress.getByName(group);
            groupSocket = new InetSocketAddress(socketAd, port);
            netIf = NetworkInterface.getByName("wlan0");
            netIfZ = NetworkInterface.getByName("eth15");
            //  DatagramSocket socket=new DatagramSocket(receivePort);

            recSocket = new MulticastSocket(port);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            recSocket.joinGroup(groupSocket, netIf);
            byte[] bytesReceive = new byte[2048];
            while (true) {
                DatagramPacket packet = new DatagramPacket(bytesReceive, bytesReceive.length);
                recSocket.receive(packet);
                String text = new String(packet.getData(), 0, packet.getLength(), "ISO-8859-1");
                received = text;
                receivedToTextArea();

            }
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    public synchronized void receivedToTextArea() {
        textArea.appendText(received+"\n");
    }
}
