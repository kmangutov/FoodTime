package com.kmangutov.foodtime;

import android.app.Application;

import java.util.ArrayList;

/**
 * Sort of like a substitute DB for now.
 * Created by Jonathan on 11/9/2014.
 */
public class GlobalClass extends Application {
    private ArrayList<User> userList = new ArrayList<User>();
    private ArrayList<User> friendList = new ArrayList<User>();

    public ArrayList<User> getUserList() {
        return userList;
    }

    public ArrayList<User> getFriendList() {
        return friendList;
    }

    public void clearFriendList() {
        friendList.clear();
    }

    public void unsetAll() {
        for(int i=0; i<userList.size(); ++i) {
            //All Unset all User objects
            userList.get(i).setSelected(false);
        }
    }
}
