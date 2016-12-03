package model.old;

import model.persistents.Category;
import model.persistents.Event;
import model.persistents.Price;
import model.persistents.User;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * Created by alejandrok on 9/12/16.
 */
public class GeneralEvent extends Event {

    public GeneralEvent(String name, String address, String details, Price price, LocalDateTime date, LocalDateTime duration, Set<User> attenders, Category category) {
        //super(name, address, details, price, date, duration, attenders, category);
    }

}