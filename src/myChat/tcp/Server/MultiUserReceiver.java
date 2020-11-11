package myChat.tcp.Server;

import javafx.scene.control.TextArea;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import myChat.tcp.Common.Init;
import myChat.tcp.Common.UserData;

import java.io.*;
import java.net.Socket;

/**
 * Created by Hodei Eceiza
 * Date: 11/7/2020
 * Time: 17:41
 * Project: myChat
 * Copyright: MIT
 */
public class MultiUserReceiver extends Thread {
    String readToPrint;
    TextArea textArea;
    StackPane imagePane;
    ServerPrinter serverPrinter;
    Object objIn;

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

    Socket clientSocket;

    MultiUserReceiver(Socket clientSocket, ServerPrinter serverPrinter) {
        this.clientSocket = clientSocket;
        this.serverPrinter = serverPrinter;
    }

    @Override
    public void run() {
        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter printOut = new PrintWriter(clientSocket.getOutputStream(), true);
           ObjectOutputStream objectOut=new ObjectOutputStream(clientSocket.getOutputStream());
            ObjectInputStream objectIn = new ObjectInputStream(clientSocket.getInputStream());
            serverPrinter.addWriter(printOut);
            serverPrinter.addWriteObjects(objectOut);

            while(true){
                objIn=objectIn.readObject();
                if(objIn instanceof Init){
                    objectOut.writeObject(new Init());
                }
                else if(objIn instanceof String){
                    UserData userdata=new UserData();
                    userdata.setName((String)objIn);
                    objectOut.writeObject(userdata);
                }
                else if(objIn instanceof UserData){
                    sendToAllClients((UserData)objIn);
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();


        }

    }

    public void sendToAllClients(UserData message){
        serverPrinter.getWriteObjects().forEach(pw -> {
            try {
                pw.writeObject(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
    public void createSphere(String sphere) {
        String[] split = sphere.split(" ");

        double radius = Double.parseDouble(split[1]);
        Color colored = Color.valueOf(split[2]);

    }

    public void textToPrint() {
        textArea.appendText(getReadToPrint() + "\n");
    }
}
