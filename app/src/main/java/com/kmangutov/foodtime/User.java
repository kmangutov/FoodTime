package com.kmangutov.foodtime;

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

    public User(String name)
    {
        this.name = name;
        this.username = name;
    }
    public User(String username, String password, String name, String location) {
        super();
        this.username = username;
        this.password = password;
        this.name = name;
        this.location = location;
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
}
