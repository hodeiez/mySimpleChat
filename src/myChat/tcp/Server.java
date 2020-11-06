package myChat.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Hodei Eceiza
 * Date: 11/6/2020
 * Time: 09:22
 * Project: myChat
 * Copyright: MIT
 */
public class Server {
    private int port=12345;
    Server(){
        try (
            ServerSocket serverSocket=new ServerSocket(port);
            Socket clientSocket =serverSocket.accept();
            BufferedReader input =new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))
        ){
            while(true){
                System.out.println(input.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        Server s=new Server();
    }

}
