package model;

import model.persistents.*;
import model.tools.Bundle;
import model.tools.TripManager;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by alejandrok on 03/12/16.
 */
public class TripManagerITest {

    @Test
    public void cheap(){
        List<List<Event>> bundles = new ArrayList<List<Event>>();

        List<Event> eventsOfADay = addEvents();
        User aUser = addUser();

        List<Bundle> cheaptrips = new TripManager().cheapTrip(eventsOfADay,aUser);
        cheaptrips.forEach(bundle -> bundles.add(bundle.getBundle()));

        Assert.assertEquals(3,bundles.size());

    }

    @Test
    public void friendly(){
        List<List<Event>> bundles = new ArrayList<List<Event>>();

        List<Event> eventsOfADay = addEvents();
        User aUser = addUser();
        aUser.addFriend(addFriend());

        List<Bundle> cheaptrips = new TripManager().friendlyTrip(eventsOfADay,aUser);
        cheaptrips.forEach(bundle -> bundles.add(bundle.getBundle()));

        Assert.assertEquals(2,bundles.size());
    }

    @Test
    public void surprise(){
        List<List<Event>> bundles = new ArrayList<List<Event>>();

        List<Event> eventsOfADay = addEvents();
        User aUser = addUser();
        aUser.addFriend(addFriend());

        List<Bundle> cheaptrips = new TripManager().surpriseTrip(eventsOfADay,aUser);
        cheaptrips.forEach(bundle -> bundles.add(bundle.getBundle()));

        Assert.assertEquals(2,bundles.size());
    }

    public List<Event> addEvents(){
        List<Event> eventsOfADay = new ArrayList<Event>();


        //e.setCategory(new Category("warm places"));
        //e.setHour(LocalTime.of(12, 50));
        for(int i=1;i<20;i++){
            Event e = new Event();
            e.setName("goingToHell");
            e.setAddress("666");
            e.setDetails("Devils house");
            e.setPrice(new Price(0));
            e.setStartTime(LocalDateTime.now());
            e.setEndTime(LocalDateTime.now());
            Set<User> uset = new HashSet<User>();
            Attenders attenders = new Attenders();
            attenders.setUsers(uset);
            attenders.setMaxCapacity(0);
            attenders.setRecommendedMaxGroup(0);
            attenders.setRecommendedMinGroup(0);
            e.setAttenders(attenders);
            Profile p = new Profile();
            p.addCategory(new Category("warm places"));
            e.setProfile(p);
            String newname = "goingToHell"+i;
            e.setName(newname);
            Set<User> uset2 = new HashSet<User>();
            Attenders attenders2 = new Attenders();
            attenders.setUsers(uset2);
            e.setAttenders(attenders2);
            Profile p1 = new Profile();
            e.setProfile(p1);
            p1.addCategory(new Category("warm places"));

            eventsOfADay.add(e);
        }

        eventsOfADay.get(0).getProfile().addCategory(new Category("Termonuclear"));
        eventsOfADay.get(1).getProfile().addCategory(new Category("Termonuclear"));
        eventsOfADay.get(2).getProfile().addCategory(new Category("Suprafantastico"));
        eventsOfADay.get(3).getProfile().addCategory(new Category("Atomico"));
        eventsOfADay.get(4).getProfile().addCategory(new Category("Atomico"));

        Event e1 = new Event();
        e1.setName("Guerrin");
        e1.setAddress("Av. Corrientes 1368, C1043ABN CABA");
        e1.setDetails("La pizzería Guerrin está entre las más antiguas y tradicionales de la calle corrientes. " +
                "Fué fundada hacia el año 1932 por el Sr. Arturo Malvezzi y el Sr. Guido Grondona, emigrantes " +
                "Genoveses que habían llegado de Italia en el año 1927.");

        e1.setPrice(new Price(20));
        e1.setStartTime(LocalDateTime.now());
        e1.setEndTime(LocalDateTime.now());
        Set<User> users1 = new HashSet<User>();
        Attenders attenders1 = new Attenders();
        attenders1.setUsers(users1);
        attenders1.setMaxCapacity(1000);
        attenders1.setRecommendedMaxGroup(100);
        attenders1.setRecommendedMinGroup(1);
        e1.setAttenders(attenders1);
        Profile p1 = new Profile();
        p1.addCategory(new Category("Food"));
        p1.addCategory(new Category("Termonuclear"));
        p1.addCategory(new Category("Atomico"));
        //p.addCategory(new Category(""));
        e1.setProfile(p1);
        //e.setCategory(new Category("warm places"));
        //e.setHour(LocalTime.of(12, 50));
        eventsOfADay.add(e1);

        return eventsOfADay;
    }

    public User addUser(){
        User u = new User();
        String newname = "momo1";
        u.setUserName(newname);
        u.setAttendedEvents(new HashSet<Event>());
        u.setFriends(new Friends());
        u.setLowCostTrip(new Price(100));
        u.setPersonalEvent(new HashSet<Event>());
        u.setProfile(new Profile());
        u.getProfile().addCategory(new Category("Termonuclear"));
        u.getProfile().addCategory(new Category("Suprafantastico"));

        return u;
    }


    public User addFriend(){
        User u = new User();
        String newname = "momo2";
        u.setUserName(newname);
        u.setAttendedEvents(new HashSet<Event>());
        u.setFriends(new Friends());
        u.setLowCostTrip(new Price(10));
        u.setPersonalEvent(new HashSet<Event>());
        u.setProfile(new Profile());
        u.getProfile().addCategory(new Category("Termonuclear"));
        u.getProfile().addCategory(new Category("Atomico"));
        return u;
    }
}
