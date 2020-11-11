package myChat.tcp.Server;


import java.io.IOException;

import java.net.ServerSocket;


/**
 * Created by Hodei Eceiza
 * Date: 11/6/2020
 * Time: 09:22
 * Project: myChat
 * Copyright: MIT
 */
public class Server implements Runnable {
    private int port=12345;
    private static ServerPrinter serverPrinter =new ServerPrinter();
    private static DAO database=new DAO();
    public Server(){

    }

    @Override
    public void run() {
        try (
                ServerSocket serverSocket=new ServerSocket(port)

        ){
            while(true) {

                MultiUserReceiver user = new MultiUserReceiver(serverSocket.accept(), serverPrinter,database);
                user.start();
            }



        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static void main(String[] args) {
        Server s=new Server();
        Thread th=new Thread(s);
        th.start();
    }

}
