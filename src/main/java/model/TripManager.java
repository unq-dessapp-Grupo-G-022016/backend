package model;

import org.jooq.lambda.Seq;
import org.jooq.lambda.tuple.Tuple2;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.jooq.lambda.Seq.seq;

/**
 * Created by leog on 30/08/16.
 */
public class TripManager {

    public List<Bundle> cheapTrip(List<Event>allEvents, User user){
        List<Event> cheapFoodEvents = cheapFood(allEvents,user.getLowCostTrip());
        List<Event> freeEvents = freeEvents(allEvents);
        //reminder: match the events with the profile
        //cheapFoodEvents = profileFoodMatcher(cheapFoodEvents,user.getProfile());

        return eventListCrossJoin(cheapFoodEvents,freeEvents);
    }

    /**
     * Return combinations between two events list.
     */
    private List<Bundle> eventListCrossJoin(List<Event> listA, List<Event> listB){

        Seq<Event> seq1 = seq(listA.stream());
        Seq<Event> seq2 = seq(listB.stream());

        List<Tuple2<Event, Event>> res = seq1.crossJoin(seq2).toList();

        List<Bundle> bList;
        bList = new ArrayList<>();

        res.forEach(a -> toBundleAndAddingToList(a,bList));

        return bList;
    }
    private void toBundleAndAddingToList(Tuple2<Event, Event> t, List<Bundle> bList) {

        Event a = t.v1;
        Event b = t.v2;
        Bundle bundle = new Bundle();
        bundle.add(a);
        bundle.add(b);
        bList.add(bundle);
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

    public List<BundleGenerator> cheapTrip(){

    }

    public List<BundleGenerator> friendlyTrip(){

    }

    public List<BundleGenerator> feverTrip(){

    }

    public List<BundleGenerator> coupleTrip(){

    }

    public List<BundleGenerator> surpriceTrip(){

    }

*/


}
