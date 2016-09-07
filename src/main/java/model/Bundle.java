package model;

import org.jooq.lambda.Seq;
import org.jooq.lambda.tuple.Tuple2;

import java.util.*;
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

    public List<Tuple2<List<Event>, List<Event>>> aSeqCheapBundle(List<Event>allEvents, Price price){
        List<Event> cheapFoodEvents = cheapFood(allEvents,price);
        List<Event> freeEvents = freeEvents(allEvents);

        return Seq.of(cheapFoodEvents).crossJoin(Seq.of(freeEvents)).toList();
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



    private List<Event> cheapFood(List<Event>allEvents, Price price){
        Stream<Event> events=
        allEvents.stream().filter(e -> 
            e.price().ammount() <= price.ammount() &&
            e.isFoodEvent());
        return events.collect(Collectors.toList());
    }

    /*
    private List<Event> profileMatchingEvents(List<Event>events){
        return
    }
    */

    private List<Event> freeEvents(List<Event>allEvents){
        Stream<Event> events= allEvents.stream().filter(e -> e.price().ammount() == 0);
        return events.collect(Collectors.toList());
    }


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
