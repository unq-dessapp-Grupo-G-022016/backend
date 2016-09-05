package model;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * Created by leog on 30/08/16.
 */
public class FoodEvent extends Event {

    public FoodEvent(String name, String address, String details, Price price, LocalDateTime date, LocalDateTime duration, Set<User> attenders) {
        this.name = name;
        this.address = address;
        this.details = details;
        this.price = price;
        this.startTime = date;
        this.endTime = duration;
        this.attenders = attenders;
    }
}


