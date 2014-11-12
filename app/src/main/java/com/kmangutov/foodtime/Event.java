package com.kmangutov.foodtime;

import java.lang.reflect.Array;
import java.util.Calendar;
import java.util.ArrayList;
/**
 * Created by Chihiro on 11/12/2014.
 */
public class Event {
    private String title;
    private String location;
    private Calendar startTime;
    private Calendar endTime;
    private ArrayList<User> waitingFriends;
    private ArrayList<User> acceptedFriends;

    public Event(String title, String location){
        this.title = title;
        this.location = location;
    }

    public Event(String title, String location, Calendar startTime, Calendar endTime ){
        this.title = title;
        this.location = location;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Event(String title, String location, Calendar startTime, Calendar endTime, ArrayList<User> waitingFriends, ArrayList<User> acceptedFriends){
        this.title = title;
        this.location = location;
        this.startTime = startTime;
        this.endTime = endTime;
        this.waitingFriends = waitingFriends;
        this.acceptedFriends = acceptedFriends;
    }
    public void setTitle(String title){
       this. title = title;
    }

    public String getTitle(){
        return this.title;
    }

    public void setLocation(String location){
        this.location = location;
    }

    public String getLocation(){
        return  this.location;
    }

    public void setStartTime(Calendar startTime){
        this.startTime  = startTime;
    }

    public Calendar getStartTime(){
        return  this.startTime;
    }

    public void setEndTime(Calendar endTime){
        this.endTime = endTime;
    }

    public Calendar getEndTime(){
        return  this.endTime;
    }

    public void  setWaitingFriends(ArrayList<User> waitingFriends){
        this.waitingFriends = waitingFriends;
    }

    public ArrayList<User> getWaitingFriends(){
        return this. waitingFriends;
    }

    public void setAcceptedFriends(ArrayList<User> acceptedFriends){
        this.acceptedFriends = acceptedFriends;
    }

    public ArrayList<User> getAcceptedFriends(){
        return  this.acceptedFriends;
    }


}
