package model;

import org.jooq.lambda.Seq;
import org.jooq.lambda.tuple.Tuple2;

import java.util.ArrayList;
import java.util.List;

import static org.jooq.lambda.Seq.seq;

/**
 * Created by alejandroK on 10/9/2016.
 *
 * Exclude from TripManager for cobertura purposes
 */
public class JoolUse {
    /**
     * Return combinations between two events list.
     */
    public List<Bundle> eventListCrossJoin(List<Event> listA, List<Event> listB){

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
}
