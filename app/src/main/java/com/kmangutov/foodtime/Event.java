package com.kmangutov.foodtime;

import java.util.Calendar;
import java.util.List;
/**
 * Created by Chihiro on 11/12/2014.
 */
public class Event {
    private String title;
    private String location;
    private Calendar startTime;
    private Calendar endTime;
    private List<User> waitingFriends;
    private List<User> acceptedFriends;

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

    public void  setWaitingFriends(List<User> waitingFriends){
        this.waitingFriends = waitingFriends;
    }

    public List<User> getWaitingFriends(){
        return this. waitingFriends;
    }

    public void setAcceptedFriends(List<User> acceptedFriends){
        this.acceptedFriends = acceptedFriends;
    }

    public List<User> getAcceptedFriends(){
        return  this.acceptedFriends;
    }


}
