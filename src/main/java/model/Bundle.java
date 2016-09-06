package model;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by leog on 31/08/16.
 */
public class Bundle {



    public List<Event> cheapTrip(List<Event>allEvents, Price price){
        Stream<Event> events= allEvents.stream().filter(e -> e.price().ammount() <= price.ammount());
        return events.collect(Collectors.toList());
    }
/*
    public List<Bundle> aCheapBundle(List<Event>allEvents, Price price){
        cheapFoodEvents = cheapFood();
        freeEvents = freeEvent();
        return ... a list of pair of a cheap food event and a free event
    }
*/
    private List<Event> cheapFood(List<Event>allEvents, Price price){
        Stream<Event> events= 
        allEvents.stream().filter(e -> 
            e.price().ammount() <= price.ammount() &&
            e.isFoodEvent());
        return events.collect(Collectors.toList());
    }

    private List<Event> freeEvents(List<Event>allEvents){
        Stream<Event> events= allEvents.stream().filter(e -> e.price().ammount() == 0);
        return events.collect(Collectors.toList());
    }
/*

    public List<Event> friendlyTrip(){

    }

    public List<Event> feverTrip(){

    }

    public List<Event> coupleTrip(){

    }

    public List<Event> surpriceTrip(){

    }
*/




}
