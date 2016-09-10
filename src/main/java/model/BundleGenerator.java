package model;

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

    public BundleGenerator() {
    }

    public List<Bundle> surpriseTrip(List<Event>allEvents, User user){
        // assume that friends interest whit common interest can interest to the user
        Set<Profile>friendsProfiles = user.getFriends().friendsProfiles();

        //Remove friends without common interest
        Iterator<Profile> it = friendsProfiles.iterator();
        while (it.hasNext()){
            Profile p = it.next();
            if ((categoriesMatcher(p.allCategories(),user.getProfile().allCategories())).size()==0) {
                it.remove();
            }
        }

        Set<Category> categories = allFriendsCategories(friendsProfiles);

        //Remove user categories
        Iterator<Category> it2 = categories.iterator();
        while (it2.hasNext()){
            Category c = it2.next();
            if (user.getProfile().allCategories().contains(c)) {
                it2.remove();
            }
        }


        List<Event> userMatching = profileMatcher(allEvents,categories);

        List<Event> foodEvents = foodEvents(userMatching);
        List<Event> notFoodEvents = notFoodEvents(userMatching);

        return eventListCrossJoin(foodEvents,notFoodEvents);
    }

    public List<Bundle> cheap(List<Event>allEvents, User user){
        List<Event> cheapFoodEvents = cheapFood(allEvents,user.getLowCostTrip());
        List<Event> freeEvents = freeEvents(allEvents);

        cheapFoodEvents = profileFoodMatcher(cheapFoodEvents,user.getProfile());

        return eventListCrossJoin(cheapFoodEvents,freeEvents);
    }



    public List<Bundle> friendlyTrip(List<Event>allEvents, User user){

        //List<Event> userMatching = profileMatcher(allEvents,userProfile.allCategories());
        //List<Event> friendMatching = profileMatcher(allEvents, friendProfile.allCategories());
        //return eventListCrossJoin(userMatching, friendMatching);
        Profile userProfile = user.getProfile();
        Friends friends = user.getFriends();

        Set<Category> friendlyCategories = categoriesMatcher(userProfile.allCategories(),friends.allCategories());
        List<Event> userMatching = profileMatcher(allEvents,friendlyCategories);

        List<Event> foodEvents = foodEvents(userMatching);
        List<Event> notFoodEvents = notFoodEvents(userMatching);

        return eventListCrossJoin(foodEvents,notFoodEvents);

    }

    private List<Event> foodEvents(List<Event> allEvents){
        Stream<Event> foodEvents = allEvents.stream().filter(e -> e.isFoodEvent());
        return foodEvents.collect(Collectors.toList());
    }

    private List<Event> notFoodEvents(List<Event> allEvents){
        Stream<Event> foodEvents = allEvents.stream().filter(e -> !e.isFoodEvent());
        return foodEvents.collect(Collectors.toList());
    }

    public Set<Category> categoriesMatcher(Set<Category> catList1,Set<Category> catList2){
        Stream<Category> categories = catList1.stream().filter(c -> catList2.contains(c));
        return categories.collect(Collectors.toSet());
    }

    public Set<Category> allFriendsCategories(Set<Profile> profiles){
        Set<Category> allFriendsCategories = new HashSet<Category>();
        profiles.forEach(p -> allFriendsCategories.addAll(p.allCategories()));
        return allFriendsCategories;
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

    private List<Event> profileFoodMatcher(List<Event> eventList, Profile profile){
        return profileMatcher(eventList,profile.getFoodTypes());
    }

    private List<Event> profileMatcher(List<Event> eventList, Set<Category> categorySet){
        Iterator<Event> it = eventList.iterator();
        while (it.hasNext()){
            Event e = it.next();
            if (!e.hasTheSameCategory(categorySet)) {
                it.remove();
            }
        }

        return eventList;
    }

    /*

    //profile filter
    public List<List<Event>> aaCheapBundle(List<Event>allEvents, Price getPrice){
        List<List<Event>>bundle = new ArrayList();

        bundle.add(this.cheapFood(allEvents,getPrice));
        //orderByTimeFood
        bundle.add(this.freeEvents(allEvents));

        this.permutations(bundle);

        return this.checkTime(bundle);;
    }

    private List<List<Event>> checkTime(List<List<Event>>bundleNotChecked){
        for (List<Event>list: bundleNotChecked){
            //e.checkTime
        }

    }

    private List<Event> profileMatchingEvents(List<Event>events){
        return
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


    public List<Event> feverTrip(){

    }

    public List<Event> coupleTrip(){

    }

    public List<Event> surpriceTrip(){

    }
*/

}
