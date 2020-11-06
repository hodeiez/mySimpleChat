package myChat.udp;

/**
 * Created by Hodei Eceiza
 * Date: 11/5/2020
 * Time: 23:25
 * Project: myChat
 * Copyright: MIT
 */
public class ConnectionData {
    int port;
    String group;
    String iF;

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getiF() {
        return iF;
    }

    public void setiF(String iF) {
        this.iF = iF;
    }
}
