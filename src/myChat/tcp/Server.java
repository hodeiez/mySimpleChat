package myChat.tcp;

import javafx.application.Platform;
import javafx.scene.control.TextArea;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Sphere;

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
                ServerSocket serverSocket=new ServerSocket(port);
                Socket clientSocket =serverSocket.accept();
                BufferedReader input =new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))
        ){
            while(true){
                //create all the receiving methods

                readToPrint=input.readLine();
                System.out.println(readToPrint);
                InetSocketAddress sockaddr=(InetSocketAddress)clientSocket.getRemoteSocketAddress();
                textToPrint();
                if(readToPrint.equals("info"))
                    textToPrint();
                if(readToPrint.equals("sphere")) {
                    Sphere mySphere=new Sphere();
                    mySphere.setRadius(50);
                    mySphere.setStyle("-fx-fill: red");
                    //add sphere to imagePane
                    Platform.runLater(() -> imagePane.getChildren().add(mySphere));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void textToPrint(){
        textArea.appendText(getReadToPrint()+"\n");
    }
}
