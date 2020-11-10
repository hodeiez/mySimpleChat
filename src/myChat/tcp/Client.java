package myChat.tcp;

import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;


/**
 * Created by Hodei Eceiza
 * Date: 11/6/2020
 * Time: 09:21
 * Project: myChat
 * Copyright: MIT
 */
public class Client implements Runnable {

    String host = "127.0.0.1";
    int port = 12345;
    String out;
    InetAddress test;
    String name;
    TextArea textArea;
    StackPane imagePane;
    TextField myTextField;
    PrintWriter printOut;
    BufferedReader stringIn;
    ObjectInputStream objectIn;
    String readToPrint;
    Object inObj;


    public void setTextArea() {
        textArea.appendText(readToPrint + "\n");
    }


    public Client(TextArea textArea, StackPane imagePane, TextField myTextField) {
        this.textArea = textArea;
        this.imagePane = imagePane;
        this.myTextField = myTextField;

        try {
            Socket address = new Socket(host, port);
            printOut = new PrintWriter(address.getOutputStream(), true);
            stringIn = new BufferedReader(new InputStreamReader(address.getInputStream()));
            objectIn = new ObjectInputStream(address.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void run() {


        try {
            test = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

//LOG IN LOGIC
        try {
            inObj = objectIn.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (inObj instanceof Init) {
            readToPrint = "Write your name";
            setTextArea();
            try {
                name=stringIn.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            readToPrint =name + " is connected";
            printOut.println(readToPrint);
        }
        //END LOG IN LOGIC, STARTS THE CHAT INTERACTION LOGIC
        while (true) {
            try {
                readToPrint = stringIn.readLine();
                setTextArea();
                System.out.println(readToPrint);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * takes the text from textfield and sends to the server
     * @param message string from textfield
     */
    public void sendText(String message) {
        if(name==null){
       printOut.println(message);}
        else
            printOut.println(name +": " + message);

    }

    public String getName() {
        return name;
    }
}
