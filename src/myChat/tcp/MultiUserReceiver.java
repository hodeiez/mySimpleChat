package myChat.tcp;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.application.Platform;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URL;
import java.util.Random;

/**
 * Created by Hodei Eceiza
 * Date: 11/7/2020
 * Time: 17:41
 * Project: myChat
 * Copyright: MIT
 */
public class MultiUserReceiver extends Thread{
    String readToPrint;
    TextArea textArea;
    StackPane imagePane;

    public TextArea getTextArea() {
        return textArea;
    }

    public StackPane getImagePane() {
        return imagePane;
    }

    public Socket getClientSocket() {
        return clientSocket;
    }

    public String getReadToPrint() {
        return readToPrint;
    }
    Socket clientSocket ;
    MultiUserReceiver(Socket clientSocket,TextArea textArea, StackPane imagePane){
        this.clientSocket=clientSocket;
        this.imagePane=imagePane;
        this.textArea=textArea;
    }
    @Override
    public void run(){
        try {
            BufferedReader input =new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            while(true){
                //create all the receiving methods

                readToPrint=input.readLine();
                System.out.println(readToPrint);
                InetSocketAddress sockaddr=(InetSocketAddress)clientSocket.getRemoteSocketAddress();
                textToPrint();
                if(readToPrint.equals("info")) {
                    readToPrint= sockaddr.getHostString();
                    textToPrint();

                }
                if(readToPrint.equals("sphere")) {
                    readToPrint="sphere created";
                    textToPrint();
                    //creating a sphere
                    Random rnd=new Random();
                    Sphere mySphere=new Sphere();
                    mySphere.setRadius(50);
                    PhongMaterial color=new PhongMaterial();
                    color.setDiffuseColor(Color.BEIGE);
                    color.setDiffuseMap(new Image(String.valueOf(new URL("https://i.stack.imgur.com/9VQJu.jpg"))));
                    mySphere.setTranslateX(rnd.nextInt(100));
                    mySphere.setTranslateY(rnd.nextInt(100));
                    mySphere.setMaterial(color);

                    //animation test
                    RotateTransition rt4 = new RotateTransition();
                    rt4.setNode(mySphere);
                    rt4.setDuration(Duration.millis(9000));
                    rt4.setAxis(Rotate.Y_AXIS);
                    rt4.setByAngle(360);
                    rt4.setCycleCount(Animation.INDEFINITE);
                    rt4.setInterpolator(Interpolator.LINEAR);
                    rt4.play();
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
