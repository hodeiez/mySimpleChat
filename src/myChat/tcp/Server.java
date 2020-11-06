package myChat.tcp;

import javafx.scene.control.TextArea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Hodei Eceiza
 * Date: 11/6/2020
 * Time: 09:22
 * Project: myChat
 * Copyright: MIT
 */
public class Server implements Runnable {
    private int port=12345;
    String readToPrint;
    TextArea textArea;

    public String getReadToPrint() {
        return readToPrint;
    }

    public Server(TextArea textArea){
        this.textArea=textArea;
    }


    @Override
    public void run() {
        try (
                ServerSocket serverSocket=new ServerSocket(port);
                Socket clientSocket =serverSocket.accept();
                BufferedReader input =new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))
        ){
            while(true){
                //create all the receiving methods

                readToPrint=input.readLine();
                System.out.println(readToPrint);
                InetSocketAddress sockaddr=(InetSocketAddress)clientSocket.getRemoteSocketAddress();
                if(readToPrint.equals("info"))

                textToPrint();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void textToPrint(){
        textArea.appendText(getReadToPrint()+"\n");
    }
}
