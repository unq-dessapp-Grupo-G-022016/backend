package model.creation;

import model.persistents.*;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by leog on 31/08/16.
 */
public class UserBuilder {

    private String userName;
    private Profile profile = new Profile();
    private Price lowCostTrip = new Price(100);
    //StartPoint;
    private Friends friends =  new Friends();
    private Set<Event> personalEvents = new HashSet<>();
    private Set<String> vehicles= new HashSet<>();
    private Set<Event> attendedEvents = new HashSet<>();

    public UserBuilder withUserName(String userName){
        this.userName=userName;
        return this;
    }

    public UserBuilder withCheapAmmountOf(Price price){
        this.lowCostTrip = price;
        return this;
    }

    public UserBuilder withProfile(Profile profile){
        this.profile = profile;
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


