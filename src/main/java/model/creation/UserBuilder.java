package model.creation;

import model.*;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by leog on 31/08/16.
 */
public class UserBuilder {

    String userName;
    Profile profile = new Profile();
    Price lowCostTrip;
    //StartPoint;
    Friends friends =  new Friends();
    Set<Event> personalEvents = new HashSet<>();
    Set<String> vehicles= new HashSet<>();
    Set<Event> attendedEvents = new HashSet<>();

    public UserBuilder withUserName(String userName){
        this.userName=userName;
        return this;
    }

    public UserBuilder withCheapAmmountOf(Price price){
        this.lowCostTrip = price;
        return this;
    }

    public UserBuilder anyUser(){
        this.userName = "anyUsername";
        this.lowCostTrip= new Price(50);
        return this;
    }

    public User build(){
        return new User(userName,profile,lowCostTrip,friends,personalEvents,vehicles, attendedEvents);
    }

}


