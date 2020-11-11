package myChat.tcp.Common;

import java.io.Serializable;

/**
 * Created by Hodei Eceiza
 * Date: 11/11/2020
 * Time: 09:44
 * Project: myChat
 * Copyright: MIT
 */
public class UserData implements Serializable {
   private String name;
  private  String message;
  public UserData(){

  }
    public UserData(String name){
this.name=name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
