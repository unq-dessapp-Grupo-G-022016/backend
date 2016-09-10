package model;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * Created by leog on 30/08/16.
 */
public class TripManager {

    public List<Bundle> cheapTrip(List<Event>allEvents, User user){
        List<Event> cheapFoodEvents = cheapFood(allEvents,user.getLowCostTrip());
        List<Event> freeEvents = freeEvents(allEvents);
        //reminder: match the events with the profile
        //cheapFoodEvents = profileFoodMatcher(cheapFoodEvents,user.getProfile());
        return new JoolUse().eventListCrossJoin(cheapFoodEvents,freeEvents);
    }

    private List<Event> cheapFood(List<Event> allEvents, Price price){
        Stream<Event> events=
                allEvents.stream().filter(e ->
                        e.getPrice().ammount() <= price.ammount() &&
                                e.isFoodEvent());
        return events.collect(Collectors.toList());
    }

    private List<Event> freeEvents(List<Event> allEvents){
        // todos los eventos gratis excluyendo los de comida
        Stream<Event> events= allEvents.stream().filter(e -> e.getPrice().ammount() == 0 && !e.isFoodEvent());
        return events.collect(Collectors.toList());
    }

/*

    public List<Bundle> friendlyTrip(){

    }

    public List<Bundle> feverTrip(){

    }

    public List<Bundle> coupleTrip(){

    }

    public List<Bundle> surpriceTrip(){

    }

*/


}
