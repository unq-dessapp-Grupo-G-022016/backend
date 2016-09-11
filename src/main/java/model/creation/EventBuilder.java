package model.creation;

import model.*;
import model.Category;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Leonardo on 5/9/2016.
 */
public class EventBuilder {

    String name;
    String address;
    String details;
    Price price;
    LocalDateTime startTime;
    LocalDateTime endTime;
    Set<User> attenders = new HashSet<User>();
    boolean hasCategory = false;
    Category category;




    public EventBuilder withName(String name){
        this.name=name;
        return this;
    }

    public EventBuilder withAddres(String address){
        this.address=address;
        return this;
    }

    public EventBuilder withDetails(String details){
        this.details=details;
        return this;
    }

    public EventBuilder withPrice(Price price){
        this.price=price;
        return this;
    }

    public EventBuilder withStartTime(LocalDateTime startTime){
        this.startTime=startTime;
        return this;
    }

    public EventBuilder withEndTime(LocalDateTime endTime){
        this.endTime=endTime;
        return this;
    }

    public EventBuilder withAttender(User attender){
        this.attenders.add(attender);
        return this;
    }

    public EventBuilder anyEvent(){
        name = "anyName";
        address = "anyAddres";
        details = "anyDetails";
        price = new Price(40);
        startTime = startTime.now().plusDays(9);
        endTime = startTime.plusHours(3);
        return this;
    }

    public Event build(){
        return new Event(name,address,details,price, startTime, endTime,attenders);
    }

    public FoodEvent createFoodEvent(){
            return new FoodEvent(name,address,details,price, startTime, endTime,attenders,category);}

    public MovieEvent createMovieEvent(){
            return new MovieEvent(name,address,details,price, startTime, endTime,attenders,category);
    }

    public MusicEvent createMusicEvent(){
            return new MusicEvent(name,address,details,price, startTime, endTime,attenders,category);
    }

    public EventBuilder withCategory(Category category){
        this.hasCategory = true;
        this.category = category;
        return this;
    }

}
