package model;

import model.data.Category;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * Created by Leonardo on 8/9/2016.
 */
public class SpecificEvent extends Event {

    Category category = null;

    public SpecificEvent(String name, String address, String details, Price price, LocalDateTime date, LocalDateTime duration, Set<User> attenders) {
        super(name, address, details, price, date, duration, attenders);
    }

    public SpecificEvent(String name, String address, String details, Price price, LocalDateTime date, LocalDateTime duration, Set<User> attenders, Category category) {
        super(name, address, details, price, date, duration, attenders);
        this.category = category;
    }

    public boolean hasCategory(){return this.category != null;}

    public Category category(){
        return category;
    }

}
