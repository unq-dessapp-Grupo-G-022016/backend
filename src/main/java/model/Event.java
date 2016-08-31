package model;


import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by leog on 30/08/16.
 */
public class Event {

    String name;
    String addres;
    String details;
    Price price;
    LocalDateTime date = LocalDateTime.now();

    Set<User>attenders = new HashSet<User>();


    public Price price(){
        return price;
    }



}



