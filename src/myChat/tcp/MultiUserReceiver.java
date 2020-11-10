package myChat.tcp;

import javafx.scene.control.TextArea;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

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
            serverPrinter.addWriter(printOut);

//LOG IN LOGIC
            objectOut.writeObject(new Init());
            String name=input.readLine();
            //check the name
            printOut.println(name);
            String connected=input.readLine();
            sendToAllClients(connected);

            while (true) {


                String inputs=input.readLine();
                sendToAllClients(inputs);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
public void sendToAllClients(String message){
    for (PrintWriter pw : serverPrinter.getWritings()) {
        pw.println(message);
    }
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
