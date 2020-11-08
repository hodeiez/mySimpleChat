package myChat.tcp;

import com.sun.javafx.sg.prism.NGPhongMaterial;
import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.application.Platform;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Material;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.Random;

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
               // Socket clientSocket =serverSocket.accept();
             //  BufferedReader input =new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))

        ){
            while(true) {
                MultiUserReceiver user = new MultiUserReceiver(serverSocket.accept(), textArea, imagePane);
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
