package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by leog on 30/08/16.
 */
public class User {


    String userName;
    Profile profile;
    Price lowCostTrip;
    //StartPoint;
    Friends friends;
    Set<Event> personalEvent;
    Set<String> vehicles;

    public User(String userName, Profile profile, Price lowCostTrip, Friends friends, Set<Event> personalEvent, Set<String> vehicles) {
        this.userName = userName;
        this.profile = profile;
        this.lowCostTrip = lowCostTrip;
        this.friends = friends;
        this.personalEvent = personalEvent;
        this.vehicles = vehicles;
    }

    public User(){}

    public void createPersonalEvent(Event event){
        this.personalEvent.add(event);
    }

    public Set<Event> friendsEvent(){
        Set<Event>friendsEvents = new HashSet<>();
        this.friends.getFriends().forEach(f -> friendsEvents.addAll(f.getPersonalEvent()));
        return friendsEvents;
    }



    public void addFriend(User friend){
        friends.add(friend);
    }

    public void removeFriend(User friend){
        friends.remove(friend);
    }

    public Friends getFriends(){
        return this.friends;
    }

    public Profile getProfile(){
        return this.profile;
    }

    public Set<String> getVehicles() {
        return vehicles;
    }

    public Price getLowCostTrip() {
        return lowCostTrip;
    }

    public String getUserName() {return userName; }

    public Set<Event> getPersonalEvent() {return this.personalEvent;}
}
