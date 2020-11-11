package myChat.tcp.Server;

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
    ServerPrinter serverPrinter;
    Object objIn;
    DAO database;
    Protocol protocol;


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
            protocol=new Protocol();
            while (true) {
               objIn = objectIn.readObject();
               objectOut.writeObject(protocol.clientServerInteraction(objIn, database));
               protocol.sendToAllClients(objIn,serverPrinter);
            }
        }
        catch (IOException | ClassNotFoundException e) {

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
        for (UserData user : database.getUserdataList()) {
            if (user.getName().equals(name)) {
                exists = true;
                break;
            }
        }
        return exists;
    }

    public void createSphere(String sphere) {
        String[] split = sphere.split(" ");

        double radius = Double.parseDouble(split[1]);
        Color colored = Color.valueOf(split[2]);

    }

}
