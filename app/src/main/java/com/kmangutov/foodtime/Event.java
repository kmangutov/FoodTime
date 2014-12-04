package com.kmangutov.foodtime;

import java.util.ArrayList;
import java.util.Date;
/**
 * Created by Chihiro on 11/12/2014.
 */
public class Event {
    private String title;
    private String location;
    private Date startTime;
    private Date endTime;
    private ArrayList<User> waitingFriends = new ArrayList<User>();
    private ArrayList<User> acceptedFriends = new ArrayList<User>();

    public Event(String title, String location){
        this.title = title;
        this.location = location;
    }

    public Event(String title, String location, Date startTime, Date endTime ){
        this.title = title;
        this.location = location;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Event(String title, String location, Date startTime, Date endTime, ArrayList<User> waitingFriends, ArrayList<User> acceptedFriends){
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

    public void setStartTime(Date startTime){
        this.startTime  = startTime;
    }

    public Date getStartTime(){
        return  this.startTime;
    }

    public void setEndTime(Date endTime){
        this.endTime = endTime;
    }

    public Date getEndTime(){
        return  this.endTime;
    }

    public void  setWaitingFriends(ArrayList<User> waitingFriends){
        this.waitingFriends = waitingFriends;
    }

    public ArrayList<User> getWaitingFriends(){
        return this. waitingFriends;
    }

    public void addWaitingFriends(User user) {waitingFriends.add(user);}

    public void setAcceptedFriends(ArrayList<User> acceptedFriends){
        this.acceptedFriends = acceptedFriends;
    }

    public ArrayList<User> getAcceptedFriends(){
        return  this.acceptedFriends;
    }

    public void addAcceptedFriends(User user) {acceptedFriends.add(user);}

    //Move from accepted to waiting
    public void moveToWaitingFriends(User user) {
        if (acceptedFriends.contains(user)){
            acceptedFriends.remove(user);
            waitingFriends.add(user);
        }

    }

    //Move from waiting to accepted
    public void moveToAcceptedFriends(User user) {
        if (waitingFriends.contains(user)){
            waitingFriends.remove(user);
            acceptedFriends.add(user);
        }
    }





}
