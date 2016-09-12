package model;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * Created by leog on 30/08/16.
 */
public class MusicEvent extends Event {

    public MusicEvent(String name, String address, String details, Price price, LocalDateTime date, LocalDateTime duration, Set<User> attenders, Category category) {
        super(name, address, details, price, date, duration, attenders,category);
    }

    public boolean isMusicEvent(){return true;}

}
