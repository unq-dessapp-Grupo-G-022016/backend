package model;

import model.data.Category;
import org.jooq.lambda.Seq;
import org.jooq.lambda.tuple.Tuple2;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.jooq.lambda.Seq.seq;


/**
 * Created by leog on 31/08/16.
 */
public class BundleGenerator {




    public List<Event> cheapTrip(List<Event>allEvents, Price price){
        Stream<Event> events= allEvents.stream().filter(e -> e.price().ammount() <= price.ammount());
        return events.collect(Collectors.toList());
    }

    public List<Bundle> cheap(List<Event>allEvents, Price price, Profile profile){
        List<Event> cheapFoodEvents = cheapFood(allEvents,price);
        List<Event> freeEvents = freeEvents(allEvents);

        cheapFoodEvents = profileFoodMatcher(cheapFoodEvents,profile);

        Seq<Event> seq1 = seq(cheapFoodEvents.stream());
        Seq<Event> seq2 = seq(freeEvents.stream());

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
                        e.price().ammount() <= price.ammount() &&
                                e.isFoodEvent());
        return events.collect(Collectors.toList());
    }

    private List<Event> freeEvents(List<Event> allEvents){
        // todos los eventos gratis excluyendo los de comida
        Stream<Event> events= allEvents.stream().filter(e -> e.price().ammount() == 0 && !e.isFoodEvent());
        return events.collect(Collectors.toList());
    }

    private List<Event> profileFoodMatcher(List<Event> eventList, Profile profile){
        return profileMatcher(eventList,profile.getFoodTypes());
    }

    private List<Event> profileMatcher(List<Event> eventList, Set<Category> categorySet){
        Iterator<Event> it = eventList.iterator();

        while (it.hasNext()){
            Event e = it.next();
            if (!eventMatch(e,categorySet)) {
                it.remove();
            }
        }

        return eventList;
    }

    public boolean eventMatch(Event event, Set<Category> categorySet){
        boolean res = false;
        if (event.hasCategory()) {
            Iterator<Category> it = categorySet.iterator();
            while (it.hasNext() && res == false){
                Category category = it.next();
                String s1 = category.getName();
                String s2 = ((SpecificEvent) event).getCategory().getName();
                res = s1.contentEquals(s2);
                //res = getCategory.isEqual(((SpecificEvent) event).getCategory());
            }
        }
        else{
            res = true; // events without category are valid
        }
        return res;
    }
    /*

    //profile filter
    public List<List<Event>> aaCheapBundle(List<Event>allEvents, Price price){
        List<List<Event>>bundle = new ArrayList();

        bundle.add(this.cheapFood(allEvents,price));
        //orderByTimeFood
        bundle.add(this.freeEvents(allEvents));

        this.permutations(bundle);

        return this.checkTime(bundle);;
    }

    /*
    private List<List<Event>> checkTime(List<List<Event>>bundleNotChecked){
        for (List<Event>list: bundleNotChecked){
            //e.checkTime
        }

    }*/





    /*
    private List<Event> profileMatchingEvents(List<Event>events){
        return
    }
    */




    public  List<List<Event>> permutations(List<List<Event>> collections) {
        if (collections == null || collections.isEmpty()) {
            return Collections.emptyList();
        } else {
            List<List<Event>> res = new LinkedList<>();
            permutationsImpl(collections, res, 0, new LinkedList<Event>());
            return res;
        }
    }

    private  void permutationsImpl(List<List<Event>> ori, List<List<Event>> res, int d, List<Event> current) {
        // if depth equals number of original collections, final reached, add and return
        if (d == ori.size()) {
            res.add(current);
            return;
        }

        // iterate from current collection and copy 'current' element N times, one for each element
        Collection<Event> currentCollection = ori.get(d);
        for (Event element : currentCollection) {
            List<Event> copy = new LinkedList<>();
            copy.addAll(current);
            copy.add(element);

            permutationsImpl(ori, res, d + 1, copy);
        }
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
