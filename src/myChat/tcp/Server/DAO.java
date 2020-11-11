package myChat.tcp.Server;

import myChat.tcp.Common.UserData;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hodei Eceiza
 * Date: 11/11/2020
 * Time: 10:01
 * Project: myChat
 * Copyright: MIT
 */
public class DAO {
    List<UserData> userDataList=new ArrayList<>();
    public void addUserData(UserData userData){ userDataList.add(userData);
    }
    public void removeData(UserData userData){userDataList.remove(userData);}
    public List<UserData> getUserdataList(){
        return userDataList;
    }
}
