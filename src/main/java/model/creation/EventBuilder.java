package model.creation;

import model.old.FoodEvent;
import model.old.GeneralEvent;
import model.old.MovieEvent;
import model.old.MusicEvent;
import model.persistents.Category;
import model.persistents.Price;
import model.persistents.User;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Leonardo on 5/9/2016.
 */
public class EventBuilder {

    private String name;
    private String address;
    private String details;
    private Price price;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Set<User> attenders = new HashSet<User>();
    private Category category;


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
        category = new Category("undefined");
        return this;
    }

    public FoodEvent buildFoodEvent(){
            return new FoodEvent(name,address,details,price, startTime, endTime,attenders,category);}

    public MovieEvent buildMovieEvent(){
            return new MovieEvent(name,address,details,price, startTime, endTime,attenders,category);
    }

    public MusicEvent buildMusicEvent(){
            return new MusicEvent(name,address,details,price, startTime, endTime,attenders,category);
    }

    public GeneralEvent buildGeneralEvent(){
        return new GeneralEvent(name,address,details,price, startTime, endTime,attenders,category);

    }

    public EventBuilder withCategory(Category category){
        this.category = category;
        return this;
    }

}
