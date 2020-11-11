package myChat.tcp;

import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import myChat.tcp.Common.Init;
import myChat.tcp.Common.UserData;

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
    ObjectOutputStream objectOut;
    String readToPrint;
    Object inObj;
    UserData userData;


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
            objectOut = new ObjectOutputStream(address.getOutputStream());
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

        try {
            objectOut.writeObject(new Init()); //send start message
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (true) {
            try {
                inObj = objectIn.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            if (inObj instanceof Init) {
                readToPrint = "Write your name";//ADD new message if user name exists
                setTextArea();
            } else if (inObj instanceof UserData) {

                if (userData == null) {
                    userData = (UserData) inObj;
                    readToPrint = userData.getName() + " is connected";
                    setTextArea();
                } else {
                    readToPrint = (((UserData) inObj).getName()) + " :" + (((UserData) inObj).getMessage());
                    setTextArea();
                }
            }

        }
    }

    public void sendText(String message) {
        if (userData == null) {
            try {
                objectOut.writeObject(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
            // printOut.println(message);
        } else {
            userData.setMessage(message);
            try {
                objectOut.writeObject(userData);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String getName() {
        return name;
    }
}
