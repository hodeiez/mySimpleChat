package myChat.tcp.Server;

import java.io.ObjectOutputStream;
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
    private static List<ObjectOutputStream> writeObject=new ArrayList<>();

    public List<PrintWriter> getWritings(){
        return writing;
    }
    public void addWriter(PrintWriter writer){
        writing.add(writer);
    }
    public void removeWriter(PrintWriter writer){writing.remove(writer);}

    public List<ObjectOutputStream> getWriteObjects(){return writeObject;}
    public void addWriteObjects(ObjectOutputStream write){writeObject.add(write);}
    public void removeWriteObjects(ObjectOutputStream write){writeObject.remove(write);}

}
