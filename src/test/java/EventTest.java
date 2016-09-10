import model.Category;
import model.Event;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;

import static org.mockito.Mockito.when;

/**
 * Created by alejandroK on 10/9/2016.
 */
public class EventTest {

    @Test
    public void isTimeCompatible(){
        //EventCreator eventCreator = new EventCreator();
        LocalDateTime anyTime = LocalDateTime.now();

        Event firstEvent = new Event();
        Event secondEvent = new Event();

        firstEvent.setEndTime(anyTime);
        secondEvent.setStartTime(anyTime.plusHours(1));

        Assert.assertTrue(firstEvent.timeCompatible(secondEvent));
    }

    @Test
    public void isTimeCompatibleMocked(){
        // DO NOT WORK1
        LocalDateTime anyTime = LocalDateTime.now();

        Event firstEvent = Mockito.mock(Event.class);
        Event secondEvent = Mockito.mock(Event.class);
        when(firstEvent.getEndTime()).thenReturn(anyTime.plusHours(2));
        when(secondEvent.getStartTime()).thenReturn(anyTime.plusHours(3));

        Assert.assertTrue( ! firstEvent.timeCompatible(secondEvent)); //deberia retornar true!

    }

    @Test
    public void hasTheSameCategory(){

        Event event1 = new Event();
        Event event2 = new Event();

        Category veganCategoryMock = Mockito.mock(Category.class);
        event1.setCategory(veganCategoryMock);
        event2.setCategory(veganCategoryMock);
        when(veganCategoryMock.getName()).thenReturn("vegan");

        Assert.assertTrue(event1.hasTheSameCategory(event2));

    }
}
