package model;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

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
}
