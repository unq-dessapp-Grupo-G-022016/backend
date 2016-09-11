package model.creation;

import model.*;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by leog on 31/08/16.
 */
public class UserCreator {

    String userName;
    Profile profile = new Profile();
    Price lowCostTrip;
    //StartPoint;
    Friends friends =  new Friends();
    Set<Event> personalEvents = new HashSet<>();
    Set<String> vehicles= new HashSet<>();

    public UserCreator withUserName(String userName){
        this.userName=userName;
        return this;
    }

    public UserCreator withCheapAmmountOf(Price price){
        this.lowCostTrip = price;
        return this;
    }

    public UserCreator anyUser(){
        this.userName = "anyUsername";
        this.lowCostTrip= new Price(50);
        return this;
    }

    public User create(){
        return new User(userName,profile,lowCostTrip,friends,personalEvents,vehicles);
    }

}


