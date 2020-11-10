package myChat.tcp;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
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
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
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
    MultiUserReceiver(Socket clientSocket){
        this.clientSocket=clientSocket;
    }
    @Override
    public void run(){
        try {
            BufferedReader input =new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter printOut = new PrintWriter(clientSocket.getOutputStream(), true);
            InetSocketAddress sockaddr=(InetSocketAddress)clientSocket.getRemoteSocketAddress();
            printOut.println("you are connected " + sockaddr.getAddress() + " write 'sphere' to create a sphere, write info to get some info");
            while(true){
                //create all the receiving methods

               // System.out.println("Welcome " + sockaddr.getAddress());
                readToPrint=input.readLine();
                System.out.println(readToPrint);
              //
                textToPrint();
                if(readToPrint.equals("info")) {
                    printOut.println("you ask for info");
                    readToPrint= sockaddr.getHostString();
                    textToPrint();

                }
                if(readToPrint.contains("sphere")) {
                    printOut.println("you created a sphere");

                    textToPrint();
                    //creating a sphere
                    createSphere(readToPrint);
                    readToPrint="sphere created";
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void createSphere(String sphere){
        String [] split=sphere.split(" ");

        double radius=Double.parseDouble(split[1]);
        Color colored=Color.valueOf(split[2]);

        Random rnd=new Random();
        Sphere mySphere=new Sphere();
        mySphere.setRadius(radius);
        PhongMaterial color=new PhongMaterial();
        color.setDiffuseColor(colored);
        try {
            color.setDiffuseMap(new Image(String.valueOf(new URL("https://i.stack.imgur.com/9VQJu.jpg"))));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        mySphere.setTranslateX(rnd.nextInt(300)-200);
        mySphere.setTranslateY(rnd.nextInt(300)-200);
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
        TranslateTransition tt4=new TranslateTransition();
        tt4.setNode(mySphere);
        tt4.setFromX(23);
        tt4.setToX(200);
        tt4.setDuration(Duration.millis(9000));
        tt4.setCycleCount(Animation.INDEFINITE);
        tt4.setInterpolator(Interpolator.LINEAR);
        tt4.play();
        //add sphere to imagePane

        Platform.runLater(() -> imagePane.getChildren().add(mySphere));
    }
    public void textToPrint(){
        textArea.appendText(getReadToPrint()+"\n");
    }
}
