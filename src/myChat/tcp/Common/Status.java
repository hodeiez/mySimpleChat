package myChat.tcp.Common;

/**
 * Created by Hodei Eceiza
 * Date: 11/10/2020
 * Time: 09:36
 * Project: myChat
 * Copyright: MIT
 */
public class Status extends UserData {
    private boolean online=false;

    public Status(boolean online){
        this.online=online;
    }
    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }
}
