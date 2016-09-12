package model;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;

import static org.mockito.Mockito.when;

/**
 * Created by alejandroK on 10/9/2016.
 */
public class BundleTest {

    @Test
    public void orderByStartTimeTest(){
        Bundle bundle = new Bundle();
        LocalDateTime anyTime = LocalDateTime.now();

        Event firstEvent = Mockito.mock(Event.class);
        Event secondEvent = Mockito.mock(Event.class);
        Event thirdEvent = Mockito.mock(Event.class);

        when(firstEvent.getStartTime()).thenReturn(anyTime.plusHours(1));
        when(secondEvent.getStartTime()).thenReturn(anyTime.plusHours(2));
        when(thirdEvent.getStartTime()).thenReturn(anyTime.plusHours(3));

        bundle.add(secondEvent);
        bundle.add(thirdEvent);
        bundle.add(firstEvent);

        bundle.orderByStartTime();

        Assert.assertEquals(firstEvent,bundle.getBundle().get(0));
    }
    @Test
    public void isValidBundle(){
        Bundle bundle = new Bundle();
        LocalDateTime anyTime = LocalDateTime.now();

        Event firstEvent = Mockito.mock(Event.class);
        Event secondEvent = Mockito.mock(Event.class);

        when(firstEvent.timeCompatible(secondEvent)).thenReturn(true);

        when(firstEvent.getStartTime()).thenReturn(anyTime.plusHours(1));
        when(secondEvent.getStartTime()).thenReturn(anyTime.plusHours(2));

        bundle.add(secondEvent);
        bundle.add(firstEvent);

        Assert.assertTrue(bundle.isValidBundle());
    }
    @Test
    public void isNotAValidBundle(){
        Bundle bundle = new Bundle();
        LocalDateTime anyTime = LocalDateTime.now();

        Event firstEvent = Mockito.mock(Event.class);
        Event secondEvent = Mockito.mock(Event.class);

        when(firstEvent.timeCompatible(secondEvent)).thenReturn(false);

        when(firstEvent.getStartTime()).thenReturn(anyTime.plusHours(1));
        when(secondEvent.getStartTime()).thenReturn(anyTime.plusHours(2));

        bundle.add(secondEvent);
        bundle.add(firstEvent);

        bundle.orderByStartTime();

        Assert.assertFalse(bundle.isValidBundle());
    }


}
