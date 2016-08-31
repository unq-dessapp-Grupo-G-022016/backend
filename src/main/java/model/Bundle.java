package model;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by leog on 31/08/16.
 */
public class Bundle {



    public List<Event> cheapTrip(List<Event>allEvents, Price price){
        Stream<Event> events= allEvents.stream().filter(e -> e.price().ammount > price.ammount);
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
