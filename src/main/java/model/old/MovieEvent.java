package model.old;

import model.persistents.Category;
import model.persistents.Event;
import model.persistents.Price;
import model.persistents.User;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * Created by leog on 30/08/16.
 */
public class MovieEvent extends Event {
    //String imdb/rottenTomatoes ratting;

    public MovieEvent(String name, String address, String details, Price price, LocalDateTime date, LocalDateTime duration, Set<User> attenders, Category category) {
        //super(name, address, details, price, date, duration, attenders, category);
    }

    public boolean isMovieEvent(){return true;}

}

