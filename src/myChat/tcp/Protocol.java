package myChat.tcp;

import myChat.tcp.Common.Init;
import myChat.tcp.Common.Response;

/**
 * Created by Hodei Eceiza
 * Date: 11/9/2020
 * Time: 22:49
 * Project: myChat
 * Copyright: MIT
 */
public class Protocol {
    private final int INIT=0;
    private final int WAITING=1;
    private int status=INIT;

    public Object clientServerInteraction(String clientsIn){
        //clients connects, and receives message to send its name and sends
        if(status==INIT){
            status=WAITING;
            return new Init(); //asks to write the name and client sends string with her name
        }
        else if(status==WAITING){
            return new Response();
        }
        return null;
    }
    /*
   1.-Client connected message Server sends to client start object->client receives
   2.-Client writes name ->client receive message to write his name
            -Check if name exists
            -check if name is blank
   3.-Client starts.
            -add her name to StatusField
            -send her text to tcpTextArea
   4.-If client writes special commands server sends requested object.
            -Object is printed in imagePane.
            -In tcpTextArea shows who send the object
     */
}
