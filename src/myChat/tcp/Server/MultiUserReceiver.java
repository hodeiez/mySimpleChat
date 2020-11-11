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
    ServerPrinter serverPrinter;
    Object objIn;
    DAO database;


    public String getReadToPrint() {
        return readToPrint;
    }

    Socket clientSocket;

    MultiUserReceiver(Socket clientSocket, ServerPrinter serverPrinter, DAO database) {
        this.clientSocket = clientSocket;
        this.serverPrinter = serverPrinter;
        this.database = database;
    }

    @Override
    public void run() {
        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter printOut = new PrintWriter(clientSocket.getOutputStream(), true);
            ObjectOutputStream objectOut = new ObjectOutputStream(clientSocket.getOutputStream());
            ObjectInputStream objectIn = new ObjectInputStream(clientSocket.getInputStream());


            serverPrinter.addWriter(printOut);
            serverPrinter.addWriteObjects(objectOut);

            while (true) {
                objIn = objectIn.readObject();
                if (objIn instanceof Init) {
                    objectOut.writeObject(new Init());
                } else if (objIn instanceof String) {

                    if (!nameExists((String) objIn)) {

                        UserData userdata = new UserData();
                        userdata.setName((String) objIn);
                        database.addUserData(userdata);
                        objectOut.writeObject(userdata);

                    } else if (nameExists((String) objIn)) {

                        objectOut.writeObject(new Init(true));
                    }
                } else if (objIn instanceof UserData) {
                    sendToAllClients((UserData) objIn);
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();


        }

    }

    public void sendToAllClients(UserData messages) {
        serverPrinter.getWriteObjects().forEach(pw -> {
            try {
                pw.writeObject(messages);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public boolean nameExists(String name) {
        boolean exists = false;
        System.out.println(database.getUserdataList());
        for (UserData user : database.getUserdataList()) {
            if (user.getName().equals(name)) {
                exists = true;
                break;
            }
        }
        System.out.println("returning nameExists");
        return exists;
    }

    public void createSphere(String sphere) {
        String[] split = sphere.split(" ");

        double radius = Double.parseDouble(split[1]);
        Color colored = Color.valueOf(split[2]);

    }
/*
    public void textToPrint() {
        textArea.appendText(getReadToPrint() + "\n");
    }

 */
}
