package model;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * Created by leog on 30/08/16.
 */
public class TripManager {

    public List<Bundle> cheapTrip(List<Event>allEvents, User user){
        List<Event> cheapFoodEvents = cheapFood(allEvents,user);
        List<Event> notFoodEvents = notFoodEvent(allEvents);
        List<Event> notFoodFreeEvents = freeEvents(notFoodEvents);
        //reminder: match the events with the profile
        //cheapFoodEvents = profileFoodMatcher(cheapFoodEvents,user.getProfile());
        return new JoolUse().eventListCrossJoin(cheapFoodEvents,notFoodFreeEvents);
    }

    private List<Event> cheapFood(List<Event> allEvents, User user){
        List<Event> foodEvents = foodEvents(allEvents);
        List<Event> cheapFoodEvents = cheapEvents(foodEvents,user);

        return cheapFoodEvents;
    }

    private List<Event> cheapEvents(List<Event> allEvents, User user){
        Stream<Event> events= allEvents.stream().filter(event -> event.isCheap(user));
        return events.collect(Collectors.toList());
    }

    private List<Event> foodEvents(List<Event> allEvents){
        Stream<Event> events= allEvents.stream().filter(event -> event.isFoodEvent());
        return events.collect(Collectors.toList());
    }

    private List<Event> notFoodEvent(List<Event> allEvents){
        Stream<Event> events= allEvents.stream().filter(event -> !(event.isFoodEvent()));
        return events.collect(Collectors.toList());
    }

    private List<Event> freeEvents(List<Event> allEvents){
        Stream<Event> events= allEvents.stream().filter(event -> event.isFree() );
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
