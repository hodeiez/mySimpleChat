package myChat.tcp;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hodei Eceiza
 * Date: 11/10/2020
 * Time: 14:42
 * Project: myChat
 * Copyright: MIT
 */
public class ServerPrinter {
    private static List<PrintWriter> writing=new ArrayList<>();

    public List<PrintWriter> getWritings(){
        return writing;
    }
    public void addWriter(PrintWriter writer){
        writing.add(writer);
    }


}
