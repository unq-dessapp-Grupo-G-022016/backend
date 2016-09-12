package model;

import model.creation.EventBuilder;
import org.junit.Assert;
import org.junit.Test;
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
        LocalDateTime anyTime = LocalDateTime.now();
        EventBuilder eventCreator = new EventBuilder();
        Event firstEvent = eventCreator.anyEvent().buildGeneralEvent();
        Event secondEvent = eventCreator.anyEvent().buildGeneralEvent();

        firstEvent.setEndTime(anyTime);
        secondEvent.setStartTime(anyTime.plusHours(1));

        Assert.assertTrue(firstEvent.timeCompatible(secondEvent));
    }

    @Test
    public void hasTheSameCategory(){

        EventBuilder eventCreator = new EventBuilder();
        Event event1 = eventCreator.anyEvent().buildGeneralEvent();
        Event event2 = eventCreator.anyEvent().buildGeneralEvent();

        Category veganCategoryMock = Mockito.mock(Category.class);
        event1.setCategory(veganCategoryMock);
        event2.setCategory(veganCategoryMock);
        when(veganCategoryMock.getName()).thenReturn("vegan");

        Assert.assertTrue(event1.hasTheSameCategory(event2));

    }

    @Test
    public void hasCategory(){

        EventBuilder eventCreator = new EventBuilder();
        Event event1 = eventCreator.anyEvent().buildGeneralEvent();

        Category categoryMock = Mockito.mock(Category.class);
        event1.setCategory(categoryMock);
        when(categoryMock.getName()).thenReturn("undefined");

        Assert.assertFalse(event1.hasCategory());

    }

    @Test
    public void eventHasACategoryThatIsInASetOfCategories(){

        Category veganFood = Mockito.mock(Category.class);
        Category fastFood = Mockito.mock(Category.class);
        Category mexicanFood = Mockito.mock(Category.class);

        EventBuilder eventCreator = new EventBuilder();
        Event event = eventCreator.withCategory(veganFood).buildGeneralEvent();

        when(veganFood.isEqual(veganFood)).thenReturn(true);
        when(veganFood.isEqual(fastFood)).thenReturn(false);
        when(veganFood.isEqual(mexicanFood)).thenReturn(false);

        Set<Category> catSet = new HashSet<Category>();
        catSet.add(veganFood);
        catSet.add(mexicanFood);
        catSet.add(fastFood);

        Assert.assertTrue(event.hasTheSameCategory(catSet));

    }

    @Test
    public void singleUserAttendTest(){

        EventBuilder eventCreator = new EventBuilder();
        Event event = eventCreator.anyEvent().buildGeneralEvent();

        User userMock = Mockito.mock(User.class);

        event.attend(userMock);

        Assert.assertTrue(event.getAttenders().contains(userMock));
    }

    @Test
    public void multipleUsersAttendTest(){

        EventBuilder eventCreator = new EventBuilder();
        Event event = eventCreator.anyEvent().buildGeneralEvent();

        User userAMock = Mockito.mock(User.class);
        User userBMock = Mockito.mock(User.class);

        event.attend(userAMock);
        event.attend(userBMock);

        Assert.assertEquals(2, event.getAttenders().size());
    }

    @Test
    public void eventWithoutCategory(){
        EventBuilder eventBuilder = new EventBuilder();
        Event event = eventBuilder.anyEvent().buildGeneralEvent();

        Assert.assertFalse(event.hasCategory());
    }

    @Test
    public void eventWithCategory(){
        EventBuilder eventBuilder = new EventBuilder();
        Category mockCategory = Mockito.mock(Category.class);
        when(mockCategory.getName()).thenReturn("Cool");
        Event event = eventBuilder.withCategory(mockCategory).buildGeneralEvent();

        Assert.assertTrue(event.hasCategory());
    }

    @Test
    public void isFoodEvent(){

        EventBuilder eventCreator = new EventBuilder();
        Event foodEvent = eventCreator.anyEvent().buildFoodEvent();
        Assert.assertTrue(foodEvent.isFoodEvent());
    }

}
