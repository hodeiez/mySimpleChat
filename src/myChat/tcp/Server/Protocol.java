package myChat.tcp.Server;

import myChat.tcp.Common.Init;
import myChat.tcp.Common.Response;
import myChat.tcp.Common.UserData;

import java.io.IOException;

/**
 * Created by Hodei Eceiza
 * Date: 11/9/2020
 * Time: 22:49
 * Project: myChat
 * Copyright: MIT
 */
public class Protocol {
    private final int INIT = 0;
    private final int WAITING = 1;
    private int status = INIT;

    public Object clientServerInteraction(Object clientsIn, DAO database) {

        if (clientsIn instanceof Init) {
            return new Init();
        } else if (clientsIn instanceof String) {

            if (!nameExists((String) clientsIn, database)) {

                UserData userdata = new UserData();
                userdata.setName((String) clientsIn);
                database.addUserData(userdata);
                return userdata;

            } else if (nameExists((String) clientsIn, database)) {

                return new Init(true);
            }
        }
        return null;
    }

    public void sendToAllClients(Object clientsIn, ServerPrinter serverPrinter) {
        if (clientsIn instanceof UserData) {
            sendToAllClients((UserData) clientsIn, serverPrinter);
        }

    }

    public boolean nameExists(String name, DAO database) {
        boolean exists = false;
        for (UserData user : database.getUserdataList()) {
            if (user.getName().equals(name)) {
                exists = true;
                break;
            }
        }
        return exists;
    }

    public void sendToAllClients(UserData messages, ServerPrinter serverPrinter) {
        serverPrinter.getWriteObjects().forEach(pw -> {
            try {
                pw.writeObject(messages);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
