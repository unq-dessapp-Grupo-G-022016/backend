package model;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.when;

/**
 * Created by alejandroK on 10/9/2016.
 */
public class TripManagerTest {

    @Test
    public void matchForADayTest(){
        TripManager tripManager = new TripManager();
        LocalDateTime dayToTrip = LocalDateTime.now();
        Event mockEvent1 = Mockito.mock(Event.class);
        Event mockEvent2 = Mockito.mock(Event.class);
        List<Event> events = new ArrayList<>();

        events.add(mockEvent1);
        events.add(mockEvent2);

        when(mockEvent1.matchForDay(dayToTrip)).thenReturn(true);
        when(mockEvent2.matchForDay(dayToTrip)).thenReturn(false);

        Assert.assertEquals(1,tripManager.matchForDay(events,dayToTrip).size());
    }

    @Test
    public void cheapTriTest(){
        TripManager tripManager = new TripManager();

        List<Event> eventsList = new ArrayList<>();
        User mockUser = Mockito.mock(User.class);
        Event mockFoodEvent = Mockito.mock(Event.class);
        Event mockMovieEvent = Mockito.mock(Event.class);
        eventsList.add(mockFoodEvent);
        eventsList.add(mockMovieEvent);
        Profile mockProfile = Mockito.mock(Profile.class);

        when(mockFoodEvent.isFoodEvent()).thenReturn(true);
        when(mockFoodEvent.isCheap(mockUser)).thenReturn(true);
        when(mockMovieEvent.isCheap(mockUser)).thenReturn(true);
        when(mockMovieEvent.isFree()).thenReturn(true);
        //when(mockFoodEvent.hasTheSameCategory(new HashSet<Category>())).thenReturn(true);
        //when(mockMovieEvent.hasTheSameCategory(new HashSet<Category>())).thenReturn(true);
        when(mockUser.getProfile()).thenReturn(mockProfile);
        when(mockProfile.allCategories()).thenReturn(new HashSet<Category>());

        Assert.assertEquals(1,tripManager.cheapTrip(eventsList,mockUser).size());
    }

    @Test
    public void friendlyTripTest(){
        TripManager tripManager = new TripManager();
        List<Event> eventsList = new ArrayList<>();

        Set<Category> userCategories = new HashSet<Category>();
        Set<Category> friendsCategories = new HashSet<Category>();
        Set<Category> matchedCategories = new HashSet<Category>();

        User mockUser = Mockito.mock(User.class);
        Friends mockFriends = Mockito.mock(Friends.class);
        Profile mockUserProfile = Mockito.mock(Profile.class);
        Category mockMexicanCategory = Mockito.mock(Category.class);
        Category mockVeganCategory = Mockito.mock(Category.class);
        Category mockTerrorCategory = Mockito.mock(Category.class);
        Category mockSciFiCategory = Mockito.mock(Category.class);
        Category mockAdventureCategory = Mockito.mock(Category.class);
        Event mockFoodEvent1 = Mockito.mock(Event.class);
        Event mockFoodEvent2 = Mockito.mock(Event.class);
        Event mockMovieEvent1 = Mockito.mock(Event.class);
        Event mockMovieEvent2 = Mockito.mock(Event.class);
        Event mockMovieEvent3 = Mockito.mock(Event.class);

        userCategories.add(mockMexicanCategory);
        userCategories.add(mockTerrorCategory);
        userCategories.add(mockAdventureCategory);
        friendsCategories.add(mockMexicanCategory);
        friendsCategories.add(mockVeganCategory);
        friendsCategories.add(mockTerrorCategory);
        friendsCategories.add(mockSciFiCategory);
        eventsList.add(mockFoodEvent1);
        eventsList.add(mockFoodEvent2);
        eventsList.add(mockMovieEvent1);
        eventsList.add(mockMovieEvent2);
        eventsList.add(mockMovieEvent3);
        matchedCategories.add(mockMexicanCategory);
        matchedCategories.add(mockTerrorCategory);

        when(mockUser.getProfile()).thenReturn(mockUserProfile);
        when(mockUserProfile.allCategories()).thenReturn(userCategories);
        when(mockUser.getFriends()).thenReturn(mockFriends);
        when(mockFriends.allCategories()).thenReturn(friendsCategories);
        when(mockFoodEvent1.isFoodEvent()).thenReturn(true);
        //when(mockFoodEvent1.hasTheSameCategory(matchedCategories)).thenReturn(true);
        //when(mockMovieEvent1.hasTheSameCategory(matchedCategories)).thenReturn(true);

        Assert.assertEquals(1,tripManager.friendlyTrip(eventsList,mockUser).size());
    }


    @Test
    public void surpriseTripTest(){
        TripManager tripManager = new TripManager();
        List<Event> allEvents = new ArrayList<>();
        User mockUser = Mockito.mock(User.class);
        Set<Category> categorySet = new HashSet<Category>();

        Friends mockFriends = Mockito.mock(Friends.class);
        Profile mockUserProfile = Mockito.mock(Profile.class);
        Event mockFoodEvent1 = Mockito.mock(Event.class);
        Event mockMovieEvent1 = Mockito.mock(Event.class);
        Category mockVeganCategory = Mockito.mock(Category.class);
        Category mockTerrorCategory = Mockito.mock(Category.class);

        categorySet.add(mockVeganCategory);
        categorySet.add(mockTerrorCategory);
        allEvents.add(mockFoodEvent1);
        allEvents.add(mockMovieEvent1);

        when(mockUser.getProfile()).thenReturn(mockUserProfile);
        when(mockUserProfile.allCategories()).thenReturn(new HashSet<Category>());
        when(mockUser.getFriends()).thenReturn(mockFriends);
        when(mockFriends.allCategories()).thenReturn(new HashSet<Category>());
        when(mockFriends.categoriesOfUsersThatHaveAnyOfThis(new HashSet<Category>())).thenReturn(categorySet);
        when(mockFoodEvent1.isFoodEvent()).thenReturn(true);
        //when(mockFoodEvent1.hasTheSameCategory(categorySet)).thenReturn(true);
        //when(mockMovieEvent1.hasTheSameCategory(categorySet)).thenReturn(true);

        Assert.assertEquals(1,tripManager.surpriseTrip(allEvents,mockUser).size());
    }

    @Test
    public void eventSearchTest(){
        TripManager tripManager = new TripManager();
        List<Event> eventsList = new ArrayList<>();

        Event mockEvent1 = Mockito.mock(Event.class);
        Event mockEvent2 = Mockito.mock(Event.class);
        Event mockEvent3 = Mockito.mock(Event.class);

        when(mockEvent1.getName()).thenReturn("event1");
        when(mockEvent2.getName()).thenReturn("event2");
        when(mockEvent3.getName()).thenReturn("event3");
        when(mockEvent1.getDetails()).thenReturn("event1Details");
        when(mockEvent2.getDetails()).thenReturn("event2Details");
        when(mockEvent3.getDetails()).thenReturn("event3Details");

        eventsList.add(mockEvent1);
        eventsList.add(mockEvent2);
        eventsList.add(mockEvent3);

        Assert.assertTrue(tripManager.eventSearch(eventsList,"event1Details").contains(mockEvent1));
    }

    @Test
    public void feverTripTest(){
        TripManager tripManager = new TripManager();
        List<Event> allEvents = new ArrayList<>();
        User mockUser = Mockito.mock(User.class);

        Assert.assertEquals(0,tripManager.feverTrip(allEvents,mockUser).size());
    }


}
