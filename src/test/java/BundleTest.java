import model.*;
import model.data.Category;
import org.jooq.lambda.Seq;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.when;

/**
 * Created by leog on 05/09/16.
 */
public class BundleTest {
/*
    @Test
    public void cheapTripTest(){

        List<Event> allEvents = new ArrayList<>();
        BundleGenerator bundle = new BundleGenerator();

        Price priceEventMock = Mockito.mock(Price.class);
        Price cheapPrice = Mockito.mock(Price.class);
        Event eventMock = Mockito.mock(Event.class);

        allEvents.add(eventMock);
        allEvents.add(eventMock);

        when(eventMock.price()).thenReturn(priceEventMock);
        when(priceEventMock.ammount()).thenReturn(10);
        when(cheapPrice.ammount()).thenReturn(30);

        Assert.assertEquals(2, bundle.cheapTrip(allEvents,cheapPrice).size());
    }
*/
    @Test
    public void cheapTripTestSeq(){

        List<Event> allEvents = new ArrayList<>();
        BundleGenerator bundleG = new BundleGenerator();

        Price priceEventMock = Mockito.mock(Price.class);
        Price cheapPrice = Mockito.mock(Price.class);
        Price zeroPrice = Mockito.mock(Price.class);
        //Event eventMock = Mockito.mock(Event.class);

        Profile profile = Mockito.mock(Profile.class);
        FoodEvent foodEventMock = Mockito.mock(FoodEvent.class);
        MovieEvent movieEventMock = Mockito.mock(MovieEvent.class);

        allEvents.add(foodEventMock);
        allEvents.add(movieEventMock);
        allEvents.add(foodEventMock);
        allEvents.add(movieEventMock);
        allEvents.add(foodEventMock);
        allEvents.add(movieEventMock);

        when(foodEventMock.price()).thenReturn(priceEventMock);
        when(movieEventMock.price()).thenReturn(zeroPrice);

        when(foodEventMock.isFoodEvent()).thenReturn(true);
        when(movieEventMock.isFoodEvent()).thenReturn(false);

        when(foodEventMock.hasCategory()).thenReturn(false);

        when(zeroPrice.ammount()).thenReturn(0);
        when(priceEventMock.ammount()).thenReturn(10);
        when(cheapPrice.ammount()).thenReturn(30);

        Assert.assertEquals(9, bundleG.cheap(allEvents,cheapPrice,profile).size());
        //Assert.assertTrue(true);
    }

    @Test
    public void secTest(){
        // (tuple(1, "A"), tuple(1, "B"), tuple(2, "A"), tuple(2, "B"))
        // Seq.of(1, 2).crossJoin(Seq.of("A", "B"));

        Assert.assertEquals(4,Seq.of(1, 2).crossJoin(Seq.of("A", "B")).count());
    }

    @Test
    public void cheapTripTestSeqWithMatcher(){
// UNFINISHED11
        List<Event> allEvents = new ArrayList<>();
        BundleGenerator bundleG = new BundleGenerator();

        Price priceEventMock = Mockito.mock(Price.class);
        Price cheapPrice = Mockito.mock(Price.class);
        Price zeroPrice = Mockito.mock(Price.class);
        //Event eventMock = Mockito.mock(Event.class);

        Profile profile = Mockito.mock(Profile.class);
        FoodEvent foodEventMock = Mockito.mock(FoodEvent.class);
        MovieEvent movieEventMock = Mockito.mock(MovieEvent.class);

        Category veganFood = Mockito.mock(Category.class);
        Category fastFood = Mockito.mock(Category.class);
        Category mexicanfood = Mockito.mock(Category.class);


        FoodEvent veganFoodEventMock = Mockito.mock(FoodEvent.class);
        FoodEvent mexicanFoodEventMock = Mockito.mock(FoodEvent.class);
        FoodEvent fastFoodEventMock = Mockito.mock(FoodEvent.class);

        when(veganFoodEventMock.hasCategory()).thenReturn(true);
        when(fastFoodEventMock.hasCategory()).thenReturn(true);
        when(mexicanFoodEventMock.hasCategory()).thenReturn(true);

        when(veganFoodEventMock.price()).thenReturn(priceEventMock);
        when(fastFoodEventMock.price()).thenReturn(priceEventMock);
        when(mexicanFoodEventMock.price()).thenReturn(priceEventMock);


        allEvents.add(fastFoodEventMock);
        allEvents.add(movieEventMock);
        allEvents.add(veganFoodEventMock);
        allEvents.add(movieEventMock);
        allEvents.add(mexicanFoodEventMock);
        allEvents.add(movieEventMock);

        when(foodEventMock.price()).thenReturn(priceEventMock);
        when(movieEventMock.price()).thenReturn(zeroPrice);

        when(foodEventMock.isFoodEvent()).thenReturn(true);
        when(movieEventMock.isFoodEvent()).thenReturn(false);

        when(foodEventMock.hasCategory()).thenReturn(false);

        when(zeroPrice.ammount()).thenReturn(0);
        when(priceEventMock.ammount()).thenReturn(10);
        when(cheapPrice.ammount()).thenReturn(30);

        Assert.assertEquals(0, bundleG.cheap(allEvents,cheapPrice,profile).size());
        //Assert.assertTrue(true);
    }

}
