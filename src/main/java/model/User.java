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
    Set<String> vehicles;

    public User(String userName, Profile profile, Price lowCostTrip, Friends friends, Set<String> vehicles) {
        this.userName = userName;
        this.profile = profile;
        this.lowCostTrip = lowCostTrip;
        this.friends = friends;
        this.vehicles = vehicles;
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


    public Price getLowCostTrip() {
        return lowCostTrip;
    }
}
