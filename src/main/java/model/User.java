package model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;


/**
 * Created by leog on 30/08/16.
 */

@Entity  
@Table(name = "Users")  
public class User {

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public void setLowCostTrip(Price lowCostTrip) {
		this.lowCostTrip = lowCostTrip;
	}

	public void setFriends(Friends friends) {
		this.friends = friends;
	}

	public void setPersonalEvent(Set<Event> personalEvent) {
		this.personalEvent = personalEvent;
	}

	public void setAttendedEvents(Set<Event> attendedEvents) {
		this.attendedEvents = attendedEvents;
	}

	@Id
    public String userName;
	@JsonIgnore
	@OneToOne (cascade = CascadeType.ALL)
    private Profile profile;
	@JsonIgnore
    @OneToOne (cascade = CascadeType.ALL)
	private Price lowCostTrip;
    //StartPoint;
	@JsonIgnore
    @OneToOne (cascade = CascadeType.ALL)
    private Friends friends;
	@JsonIgnore
    @ManyToMany (fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Event> personalEvent;
    //private Set<String> vehicles;
	@JsonIgnore
	@ManyToMany (fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Event> attendedEvents;

    public User(){
    	
    }
    
    public User(String userName, Profile profile, Price lowCostTrip, Friends friends, Set<Event> personalEvent, Set<String> vehicles, Set<Event> attendedEvents) {
        this.userName = userName;
        this.profile = profile;
        this.lowCostTrip = lowCostTrip;
        this.friends = friends;
        this.personalEvent = personalEvent;
        //this.vehicles = vehicles;
        this.attendedEvents = attendedEvents;
    }

    public void createPersonalEvent(Event event){
       personalEvent.add(event);
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
