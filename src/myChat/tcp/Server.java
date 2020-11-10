package myChat.tcp;


import javafx.scene.control.TextArea;

import javafx.scene.layout.StackPane;

import java.io.IOException;

import java.net.ServerSocket;


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
    StackPane imagePane;

    public String getReadToPrint() {
        return readToPrint;
    }

    public Server(TextArea textArea, StackPane imagePane){
        this.textArea=textArea;
        this.imagePane=imagePane;
    }


    @Override
    public void run() {
        try (
                ServerSocket serverSocket=new ServerSocket(port)

        ){
            while(true) {
                MultiUserReceiver user = new MultiUserReceiver(serverSocket.accept(), textArea, imagePane); //<-only start an accept, multiuser will SEND the info to client, an client will print!
                textArea = user.getTextArea();
                imagePane = user.getImagePane();
                user.start();
            }



        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    public void textToPrint(){
        textArea.appendText(getReadToPrint()+"\n");
    }
}
