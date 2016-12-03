package model.tools;

import model.persistents.Category;
import model.persistents.Event;
import org.jooq.lambda.Seq;
import org.jooq.lambda.tuple.Tuple2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.jooq.lambda.Seq.seq;

/**
 * Created by alejandroK on 10/9/2016.
 * Exclude from TripManager for mvn cobertura purposes
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

        res.forEach(a -> tupleOfEventsToListOfBundles(a,bList));

        return bList;
    }

    public Set<Category> categoriesSetsIntersection(Set<Category> categoriesA, Set<Category> categoriesB){

        Set<String> cat1 = new HashSet<String>();
        categoriesA.forEach(cn -> cat1.add(cn.getName()));
        Set<String> cat2 = new HashSet<String>();
        categoriesB.forEach(cn -> cat2.add(cn.getName()));

        Seq<String> seq1 = seq(cat1.stream());
        Seq<String> seq2 = seq(cat2.stream());

        Set<String> result = seq1.retainAll(seq2).toSet();

        Set<Category> catResult = new HashSet<Category>();
        result.forEach(catname -> catResult.add(new Category(catname)));

        return  catResult;
    }

    private void tupleOfEventsToListOfBundles(Tuple2<Event, Event> t, List<Bundle> bList) {

        Event a = t.v1;
        Event b = t.v2;
        Bundle bundle = new Bundle();
        bundle.add(a);
        bundle.add(b);
        bList.add(bundle);
    }

    private void tupleOfBundleEventToListOfBundles(Tuple2<Bundle, Event> t, List<Bundle> bList) {

        Bundle a = t.v1;
        Event b = t.v2;
        a.add(b);
        bList.add(a);
    }

    public List<Bundle> bundleListAndEventListCrossJoin(List<Bundle> bundleListA, List<Event> listB){

        Seq<Bundle> seq1 = seq(bundleListA.stream());
        Seq<Event> seq2 = seq(listB.stream());

        List<Tuple2<Bundle, Event>> res = seq1.crossJoin(seq2).toList();

        List<Bundle> bList;
        bList = new ArrayList<>();

        res.forEach(a -> tupleOfBundleEventToListOfBundles(a,bList));

        return bList;
    }



}
