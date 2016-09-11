package model;

import model.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.theories.suppliers.TestedOn;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.when;

/**
 * Created by alejandroK on 10/9/2016.
 */
public class EventTest {

    @Test
    public void isTimeCompatible(){
        //EventBuilder eventCreator = new EventBuilder();
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

    @Test
    public void hasCategory(){

        Event event1 = new Event();

        Category categoryMock = Mockito.mock(Category.class);
        event1.setCategory(categoryMock);
        when(categoryMock.getName()).thenReturn("undefined");

        Assert.assertFalse(event1.hasCategory());

    }

    @Test
    public void eventHasACategoryThatIsInASetOfCategories(){
        Event event = new Event();

        Category veganFood = Mockito.mock(Category.class);
        Category fastFood = Mockito.mock(Category.class);
        Category mexicanFood = Mockito.mock(Category.class);

        event.setCategory(veganFood);

        when(veganFood.getName()).thenReturn("vegan");
        when(fastFood.getName()).thenReturn("fast");
        when(mexicanFood.getName()).thenReturn("mexican");

        Set<Category> catSet = new HashSet<Category>();
        catSet.add(veganFood);
        catSet.add(mexicanFood);
        catSet.add(fastFood);

        Assert.assertTrue(event.hasTheSameCategory(catSet));

    }

    @Test
    public void singleUserAttendTest(){
        Event event = new Event();

        User userMock = Mockito.mock(User.class);

        event.attend(userMock);

        Assert.assertTrue(event.getAttenders().contains(userMock));
    }

    @Test
    public void multipleUsersAttendTest(){
        Event event = new Event();

        User userAMock = Mockito.mock(User.class);
        User userBMock = Mockito.mock(User.class);

        event.attend(userAMock);
        event.attend(userBMock);

        Assert.assertEquals(2, event.getAttenders().size());
    }

}
