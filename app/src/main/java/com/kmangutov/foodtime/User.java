package com.kmangutov.foodtime;

import java.util.ArrayList;

/**
 * Created by Jonathan on 11/8/2014.
 */
public class User {

    String name = null;
    String username = null;
    private String password;
    String location = null;
    //Used for checkbox lists?
    Boolean selected= false;
    public ArrayList<User> friendList;

    public User(String name)
    {
        this.name = name;
        this.username = name;
        friendList = new ArrayList<User>();
    }
    public User(String username, String password, String name, String location) {
        super();
        this.username = username;
        this.password = password;
        this.name = name;
        this.location = location;
        friendList = new ArrayList<User>();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username=username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean checkPassword(String query) {
        if (password.compareTo(query)==0)
            return true;
        return false;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean b) {
        this.selected = b;
    }

    public void addFriend(User user) {
        friendList.add(user);
    }

    public void removeFriend(User user) {
        friendList.remove(user);
    }

    public ArrayList<User> getFriendList() {
        return friendList;
    }
}
