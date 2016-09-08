package model;


import java.time.LocalDateTime;
import java.util.Set;

/**
 * Created by leog on 30/08/16.
 */
public class Event {

    String name;
    String address;
    String details;
    Price price;
    LocalDateTime startTime;
    LocalDateTime endTime;
    Set<User>attenders;

    public Event(String name, String address, String details, Price price, LocalDateTime date, LocalDateTime duration, Set<User> attenders) {
        this.name = name;
        this.address = address;
        this.details = details;
        this.price = price;
        this.startTime = date;
        this.endTime = duration;
        this.attenders = attenders;
    }

    public Event(){}


    public Price price(){
        return price;
    }

    public boolean isFoodEvent(){
        return false;
    }

    public boolean isMovieEvent(){return false;}

    public boolean isMusicEvent(){return false;}

    public boolean timeCompatible(Event event){
        return this.endTime.isBefore(event.startTime);
    }

    public boolean hasCategory(){return false;}

}



