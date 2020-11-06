package myChat.udp;

import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.*;

/**
 * Created by Hodei Eceiza
 * Date: 11/5/2020
 * Time: 22:51
 * Project: myChat
 * Copyright: MIT
 */
public class Sender {
    int port = 12540;
    String group = "234.235.236.237";
    String zerotier = "eth15";
    MulticastSocket socket;
    String received;
    InetSocketAddress groupSocket;
    NetworkInterface netIf;
    NetworkInterface netIfZ;
    TextArea textArea;
    TextField textField;

   // Sender(TextArea textArea,TextField textField){
       // this.textArea=textArea;
       // this.textField=textField;
   // }
   public Sender(){}
   public Sender(int port,String group,String netiF){
       this.port=port;
       this.group=group;
       try {
           this.netIf=NetworkInterface.getByName(netiF);
       } catch (SocketException e) {
           e.printStackTrace();
       }
   }
    public void send(String message) {
        try {
            InetAddress socketAd = InetAddress.getByName(group);
            groupSocket = new InetSocketAddress(socketAd, port);
            netIf = NetworkInterface.getByName("wlan0");
            netIfZ = NetworkInterface.getByName("eth15");
            //  DatagramSocket socket=new DatagramSocket(receivePort);

            socket = new MulticastSocket(port);
            socket.joinGroup(groupSocket, netIf);
            byte[] bytesToSend = message.getBytes("ISO-8859-1");
            DatagramPacket packet = new DatagramPacket(bytesToSend, bytesToSend.length, InetAddress.getByName(group), port);
            socket.send(packet);


        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }


}
