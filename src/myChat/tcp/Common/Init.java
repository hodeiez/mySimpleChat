package myChat.tcp.Common;

import java.io.Serializable;

/**
 * Created by Hodei Eceiza
 * Date: 11/10/2020
 * Time: 09:36
 * Project: myChat
 * Copyright: MIT
 */
public class Init implements Serializable {
    boolean restarted;

    public Init(){
        restarted=false;
    }
    public Init(boolean restarted){
        this.restarted=restarted;
    }

    public boolean isRestarted() {
        return restarted;
    }

    public void setRestarted(boolean restarted) {
        this.restarted = restarted;
    }
}
