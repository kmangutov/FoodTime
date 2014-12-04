package com.kmangutov.foodtime;

import android.app.Application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * Sort of like a substitute DB for now.
 * Created by Jonathan on 11/9/2014.
 */
public class GlobalClass extends Application {
    private ArrayList<User> userList = new ArrayList<User>();
    //private ArrayList<User> friendList = new ArrayList<User>();
    private  ArrayList<Event> eventList = new ArrayList<Event>();
    private User currentUser;


    //Simple initialize just for testing
    public GlobalClass(){
        //Generate fake pre-existing user db (temporary)
        if (userList.isEmpty()) {
            userList.add(new User("test", "test", "Jon", "Behind You, IL"));
            userList.add(new User("test1", "test", "Borg", "Urbana, IL"));
            userList.add(new User("test2", "test", "Harrison", "Champaign, IL"));
            userList.add(new User("smthn1", "pass", "Kirill", "Urbana, IL"));
            userList.add(new User("florida", "pass", "Isra", "FLORIDAAAAAA"));
            userList.add(new User("something2", "pass", "Xi", "Champaign, IL"));
        }


        //Generate fake pre-existing events
        String str1 = "2014.December.13 07:00 PM";
        String str2 = "2014.December.13 09:00 PM";
        Date date = null;
        Date date2 = null;
        try {
            date = new SimpleDateFormat("yyyy.MMMM.dd hh:mm aaa", Locale.ENGLISH).parse(str1);
            date2 = new SimpleDateFormat("yyyy.MMMM.dd hh:mm aaa", Locale.ENGLISH).parse(str2);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        ArrayList<User> acceptedFriends = new ArrayList<User>();
        ArrayList<User> invitedFriends = new ArrayList<User>();

        acceptedFriends.add(userList.get(0));
        acceptedFriends.add(userList.get(1));
        acceptedFriends.add(userList.get(2));
        invitedFriends.add(userList.get(3));
        invitedFriends.add(userList.get(4));
        invitedFriends.add(userList.get(5));

        Event event1 = new Event("Papa Johns Dinner", "Papa Johns" , date, date2, invitedFriends, acceptedFriends);

        str1 = "2014.December.31 11:50 AM";
        str2 = "2014.December.31 1:00 PM";
        try {
            date = new SimpleDateFormat("yyyy.MMMM.dd hh:mm aaa", Locale.ENGLISH).parse(str1);
            date2 = new SimpleDateFormat("yyyy.MMMM.dd hh:mm aaa", Locale.ENGLISH).parse(str2);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        acceptedFriends = new ArrayList<User>();
        invitedFriends = new ArrayList<User>();

        invitedFriends.add(userList.get(0));
        invitedFriends.add(userList.get(1));
        invitedFriends.add(userList.get(2));
        invitedFriends.add(userList.get(3));
        invitedFriends.add(userList.get(4));
        invitedFriends.add(userList.get(5));

        Event event2 = new Event("#FREELUNCH", "Sakari Sushi", date, date2, invitedFriends, acceptedFriends);

        eventList.add(event1);
        eventList.add(event2);
    }

    public void setEventList(ArrayList<Event> eventList) {
        this.eventList = eventList;
    }

    public ArrayList<User> getUserList() {
        return userList;
    }

    /*public ArrayList<User> getFriendList() {
        return friendList;
    }*/

    public ArrayList<Event> getEventList(){
        return  eventList;
    }

    /*public void clearFriendList() {
        friendList.clear();
    }*/

    public void unsetAll() {
        for(int i=0; i<userList.size(); ++i) {
            //All Unset all User objects
            userList.get(i).setSelected(false);
        }
    }

    public void setUser(User user){
        currentUser=user;
    }

    public User getUser(){
        return currentUser;
    }
}
