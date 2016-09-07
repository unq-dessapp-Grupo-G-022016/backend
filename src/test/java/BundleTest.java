import model.Bundle;
import model.Event;
import model.Price;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

/**
 * Created by leog on 05/09/16.
 */
public class BundleTest {

    @Test
    public void cheapTripTest(){

        List<Event> allEvents = new ArrayList<>();
        Bundle bundle = new Bundle();

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

    @Test
    public void cheapTripTestSeq(){

        List<Event> allEvents = new ArrayList<>();
        Bundle bundle = new Bundle();

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

}
