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
    public void isTimeCompatibleTest(){
        LocalDateTime anyTime = LocalDateTime.now();
        EventBuilder eventCreator = new EventBuilder();
        Event firstEvent = eventCreator.anyEvent().buildGeneralEvent();
        Event secondEvent = eventCreator.anyEvent().buildGeneralEvent();

        firstEvent.setEndTime(anyTime);
        secondEvent.setStartTime(anyTime.plusHours(1));

        Assert.assertTrue(firstEvent.timeCompatible(secondEvent));
    }

    @Test
    public void hasTheSameCategoryTest(){

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
    public void hasCategoryTest(){

        EventBuilder eventCreator = new EventBuilder();
        Event event1 = eventCreator.anyEvent().buildGeneralEvent();

        Category categoryMock = Mockito.mock(Category.class);
        event1.setCategory(categoryMock);
        when(categoryMock.getName()).thenReturn("undefined");

        Assert.assertFalse(event1.hasCategory());

    }

    @Test
    public void eventHasACategoryThatIsInASetOfCategoriesTest(){

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
    public void eventDoNotHasACategoryThatIsInASetOfCategoriesTest(){

        Category veganFood = Mockito.mock(Category.class);
        Category fastFood = Mockito.mock(Category.class);
        Category mexicanFood = Mockito.mock(Category.class);

        EventBuilder eventCreator = new EventBuilder();
        Event event = eventCreator.withCategory(veganFood).buildGeneralEvent();

        when(veganFood.isEqual(fastFood)).thenReturn(false);
        when(veganFood.isEqual(mexicanFood)).thenReturn(false);

        Set<Category> catSet = new HashSet<Category>();
        catSet.add(mexicanFood);
        catSet.add(fastFood);

        Assert.assertFalse(event.hasTheSameCategory(catSet));

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
    public void eventWithoutCategoryTest(){
        EventBuilder eventBuilder = new EventBuilder();
        Event event = eventBuilder.anyEvent().buildGeneralEvent();

        Assert.assertFalse(event.hasCategory());
    }

    @Test
    public void eventWithCategoryTest(){
        EventBuilder eventBuilder = new EventBuilder();
        Category mockCategory = Mockito.mock(Category.class);
        when(mockCategory.getName()).thenReturn("Cool");
        Event event = eventBuilder.withCategory(mockCategory).buildGeneralEvent();

        Assert.assertTrue(event.hasCategory());
    }


    @Test
    public void eventSuggestionsTest(){
        EventBuilder eventBuilder = new EventBuilder();
        Set<Event> attendedEvents = new HashSet<>();

        User userMock = Mockito.mock(User.class);
        Event eventA = eventBuilder.anyEvent().buildGeneralEvent();
        Event eventB = eventBuilder.anyEvent().buildGeneralEvent();
        Event eventC = eventBuilder.anyEvent().buildGeneralEvent();

        when(userMock.getAttendedEvents()).thenReturn(attendedEvents);
        eventA.attend(userMock);
        attendedEvents.add(eventC);

        Assert.assertTrue(eventA.eventSuggestions(eventB).contains(eventC));
    }

    @Test
    public void isFreeTest(){
        EventBuilder eventBuilder = new EventBuilder();
        Event event = eventBuilder.withPrice(new Price(0)).buildGeneralEvent();

        Assert.assertTrue(event.isFree());
    }

    /*
    @Test
    public void isCheapTest(){
        EventBuilder eventBuilder = new EventBuilder();
        User userMock = Mockito.mock(User.class);
        Price priceMock = Mockito.mock(Price.class);

        Event event = eventBuilder.withPrice(new Price(0)).buildGeneralEvent();
        when(priceMock.isCheap(userMock)).thenReturn(true);

        Assert.assertTrue(event.isCheap(userMock));
    }
*/

    @Test
    public void getNameAndDetailsTest(){
        EventBuilder eventBuilder = new EventBuilder();
        Event event =eventBuilder.withName("name").withDetails("details").buildGeneralEvent();

        Assert.assertEquals("namedetails",event.getName()+event.getDetails());
    }

    @Test
    public void eventPriceTest(){
        EventBuilder eventBuilder = new EventBuilder();
        Price priceMock = Mockito.mock(Price.class);
        Event event =eventBuilder.withPrice(priceMock).buildGeneralEvent();

        Assert.assertEquals(priceMock,event.getPrice());
    }

    @Test
    public void isFoodEventTest(){
        EventBuilder eventBuilder = new EventBuilder();
        Event foodEvent =eventBuilder.buildFoodEvent();

        Assert.assertTrue(foodEvent.isFoodEvent());
    }

    @Test
    public void isMovieEventTest(){
        EventBuilder eventBuilder = new EventBuilder();
        Event movieEvent =eventBuilder.buildMovieEvent();

        Assert.assertTrue(movieEvent.isMovieEvent());
    }

    @Test
    public void isMusicEventTest(){
        EventBuilder eventBuilder = new EventBuilder();
        Event foodEvent =eventBuilder.buildFoodEvent();

        Assert.assertFalse(foodEvent.isMusicEvent());
    }

    @Test
    public void isNotFoodEventTest(){
        EventBuilder eventBuilder = new EventBuilder();
        Event musicEvent = eventBuilder.buildMusicEvent();

        Assert.assertFalse(musicEvent.isFoodEvent());
    }

    @Test
    public void isNotMovieEventTest(){
        EventBuilder eventBuilder = new EventBuilder();
        Event foodEvent =eventBuilder.buildFoodEvent();

        Assert.assertFalse(foodEvent.isMovieEvent());
    }

    @Test
    public void isNotMusicEventTest(){
        EventBuilder eventBuilder = new EventBuilder();
        Event musicEvent =eventBuilder.buildMusicEvent();

        Assert.assertTrue(musicEvent.isMusicEvent());
    }



    @Test
    public void FoodEventIsTimeCompatibleTest(){
        EventBuilder eventBuilder = new EventBuilder();
        LocalDateTime date = LocalDateTime.now();
        LocalDateTime afterDate = date.plusDays(1);

        Event foodEvent = eventBuilder.withStartTime(date).withEndTime(date.plusHours(6)).buildFoodEvent();
        Event generalEvent = eventBuilder.withStartTime(afterDate).buildGeneralEvent();

        Assert.assertTrue(foodEvent.timeCompatible(generalEvent));
    }




}
