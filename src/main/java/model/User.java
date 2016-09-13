package model;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by leog on 30/08/16.
 */
public class User {


    private String userName;
    private Profile profile;
    private Price lowCostTrip;
    //StartPoint;
    private Friends friends;
    private Set<Event> personalEvent;
    private Set<String> vehicles;
    private Set<Event> attendedEvents;

    public User(String userName, Profile profile, Price lowCostTrip, Friends friends, Set<Event> personalEvent, Set<String> vehicles, Set<Event> attendedEvents) {
        this.userName = userName;
        this.profile = profile;
        this.lowCostTrip = lowCostTrip;
        this.friends = friends;
        this.personalEvent = personalEvent;
        this.vehicles = vehicles;
        this.attendedEvents = attendedEvents;
    }

    public void createPersonalEvent(Event event){
        this.personalEvent.add(event);
    }

    public Set<Event> friendsEvent(){
        Set<Event>friendsEvents = new HashSet<>();
        this.friends.getFriends().forEach(f -> friendsEvents.addAll(f.getPersonalEvent()));
        return friendsEvents;
    }


    public void attend(Event event){
        this.attendedEvents.add(event);
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

    public Price getLowCostTrip() {
        return lowCostTrip;
    }

    public String getUserName() {return userName; }

    public Set<Event> getAttendedEvents() { return attendedEvents;}

    public Set<Event> getPersonalEvent() {return this.personalEvent;}

    public void addFriend(User friend){
        friends.add(friend);
    }
}
