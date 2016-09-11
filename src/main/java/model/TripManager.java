package model;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * Created by leog on 30/08/16.
 */
public class TripManager {

    public List<Bundle> cheapTrip(List<Event>allEvents, User user){
        List<Event> cheapFoodEvents = cheapFood(allEvents,user);
        List<Event> notFoodEvents = notFoodEvents(allEvents);
        List<Event> notFoodFreeEvents = freeEvents(notFoodEvents);
        cheapFoodEvents = eventsAndCategoriesMatcher(cheapFoodEvents, user.getProfile().allCategories());
        notFoodFreeEvents = eventsAndCategoriesMatcher(notFoodFreeEvents, user.getProfile().allCategories());
        return new JoolUse().eventListCrossJoin(cheapFoodEvents,notFoodFreeEvents);
    }

    public List<Bundle> friendlyTrip(List<Event>allEvents, User user){

        Set<Category> userCategories = user.getProfile().allCategories();
        Set<Category> friendsCategories = user.getFriends().allCategories();
        Set<Category> friendlyCategories = new JoolUse().categoriesSetsIntersection(userCategories,friendsCategories);

        List<Event> matchingEvents = eventsAndCategoriesMatcher(allEvents, friendlyCategories);

        List<Event> foodEvents = foodEvents(matchingEvents);
        List<Event> notFoodEvents = notFoodEvents(matchingEvents);

        return new JoolUse().eventListCrossJoin(foodEvents,notFoodEvents);

    }

    private List<Event> eventsAndCategoriesMatcher(List<Event> events, Set<Category> friendlyCategories) {
        Iterator<Event> it = events.iterator();
        while (it.hasNext()){
            Event e = it.next();
            if (!e.hasTheSameCategory(friendlyCategories)) {
                it.remove();
            }
        }
        return events;
    }

    private List<Event> profileMatcher(List<Event> events, User user) {
        Iterator<Event> it = events.iterator();
        while (it.hasNext()){
            Event e = it.next();
            if (!e.hasTheSameCategory(user.getProfile().allCategories())) {
                it.remove();
            }
        }
        return events;
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

    private List<Event> notFoodEvents(List<Event> allEvents){
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
