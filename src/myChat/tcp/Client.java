package myChat.tcp;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by Hodei Eceiza
 * Date: 11/6/2020
 * Time: 09:21
 * Project: myChat
 * Copyright: MIT
 */
public class Client {

    String host;
    int port;
    String out;
    Client(){
        Scanner scn=new Scanner(System.in);
        out=scn.nextLine();
       host="172.20.201.122";
       port=12345;
    }


    {
        try (
            Socket address = new Socket(host,port);
            PrintWriter printOut=new PrintWriter(address.getOutputStream(),true);

        ){
            while(true){
                printOut.println(out);
                Thread.sleep(1000);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Client c=new Client();
    }
}
