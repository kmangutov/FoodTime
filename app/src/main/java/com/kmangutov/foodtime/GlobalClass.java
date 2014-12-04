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
    private  ArrayList<Event> eventList = new ArrayList<Event>();
    private  ArrayList<Event> pendingEventList = new ArrayList<Event>();


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

        /*Event event1 = new Event("Domino Lunch", "Domino" );
        Event event2 = new Event("Evo Lunch", "Evo" );

        User friend1 = new User("Kirill");
        User friend2 = new User("Jon");
        User friend3 = new User("Isra");
        User friend4 = new User("Borg");

        ArrayList<User> acceptedFriends1 = new ArrayList<User>();
        ArrayList<User> acceptedFriends2 = new ArrayList<User>();

        acceptedFriends1.add(friend1);
        acceptedFriends1.add(friend2);
        acceptedFriends1.add(friend3);
        acceptedFriends2.add(friend2);
        acceptedFriends2.add(friend4);
        acceptedFriends2.add(friend1);

        event1.setAcceptedFriends(acceptedFriends1);
        eventList.add(event1);
        eventList.add(event2);*/
    }

    public void setEventList(ArrayList<Event> eventList) {
        this.eventList = eventList;
    }

    public ArrayList<User> getUserList() {
        return userList;
    }

    public ArrayList<User> getFriendList() {
        return friendList;
    }

    public ArrayList<Event> getEventList(){
        return  eventList;
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
