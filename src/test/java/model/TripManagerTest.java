package model;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

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
    public void cheapTrip(){
        TripManager tripManager = new TripManager();

        List<Event> eventsList = new ArrayList<>();
        User mockUser = Mockito.mock(User.class);
        Event mockFoodEvent = Mockito.mock(Event.class);
        Event mockMovieEvent = Mockito.mock(Event.class);
        eventsList.add(mockFoodEvent);
        eventsList.add(mockMovieEvent);
        Profile mockProfile = Mockito.mock(Profile.class);

        when(mockFoodEvent.isFoodEvent()).thenReturn(true);
        when(mockMovieEvent.isFoodEvent()).thenReturn(false);
        when(mockFoodEvent.isCheap(mockUser)).thenReturn(true);
        when(mockMovieEvent.isCheap(mockUser)).thenReturn(true);
        when(mockMovieEvent.isFree()).thenReturn(true);
        when(mockFoodEvent.hasTheSameCategory(new HashSet<Category>())).thenReturn(true);
        when(mockMovieEvent.hasTheSameCategory(new HashSet<Category>())).thenReturn(true);
        when(mockUser.getProfile()).thenReturn(mockProfile);
        when(mockProfile.allCategories()).thenReturn(new HashSet<Category>());

        Assert.assertEquals(1,tripManager.cheapTrip(eventsList,mockUser).size());
    }

    @Test
    public void friendlyTrip(){
        TripManager tripManager = new TripManager();
        List<Event> eventsList = new ArrayList<>();
        Set<Category> userCategories = new HashSet<Category>();
        Set<Category> friendsCategories = new HashSet<Category>();
        User mockUser = Mockito.mock(User.class);
        Friends mockFriends = Mockito.mock(Friends.class);
        Profile mockUserProfile = Mockito.mock(Profile.class);
        when(mockUser.getProfile()).thenReturn(mockUserProfile);
        when(mockUserProfile.allCategories()).thenReturn(userCategories);
        when(mockUser.getFriends()).thenReturn(mockFriends);
        when(mockFriends.allCategories()).thenReturn(friendsCategories);

        Assert.assertEquals(0,tripManager.friendlyTrip(eventsList,mockUser).size());
    }

    @Test
    public void surpriseTrip(){
        TripManager tripManager = new TripManager();
        Set<Event> allEvents = new HashSet<Event>();
        //user //mock
        //user.getFriends().categoriesOfUsersThatHaveAnyOfThis  //mock answer
        //user.getProfile().allCategories()  //mock answer

    }
}
