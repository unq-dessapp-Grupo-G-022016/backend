package model;

import model.creation.UserBuilder;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * Created by Leonardo on 11/9/2016.
 */
public class UserTest {

    @Test
    public void createPersonalEventTest(){
        UserBuilder userBuilder = new UserBuilder();

        User user = userBuilder.anyUser().build();
        Event eventMock = Mockito.mock(Event.class);

        user.createPersonalEvent(eventMock);

        Assert.assertTrue(user.getPersonalEvent().contains(eventMock));
    }

    @Test
    public void friendsEventsTest(){
        UserBuilder userBuilder = new UserBuilder();

        User userWithEvent = userBuilder.anyUser().build();
        User friendUser = userBuilder.anyUser().build();
        Event eventMock = Mockito.mock(Event.class);

        userWithEvent.createPersonalEvent(eventMock);
        friendUser.addFriend(userWithEvent);

        Assert.assertTrue(friendUser.friendsEvent().contains(eventMock));
    }

    @Test
    public void attendTest(){
        UserBuilder userBuilder = new UserBuilder();
        User user = userBuilder.anyUser().build();
        Event eventMock = Mockito.mock(Event.class);

        user.attend(eventMock);

        Assert.assertTrue(user.getAttendedEvents().contains(eventMock));
    }

    @Test
    public void removeFriendTest(){
        UserBuilder userBuilder = new UserBuilder();
        User user = userBuilder.anyUser().build();
        User friend = userBuilder.anyUser().build();

        user.addFriend(friend);
        user.removeFriend(friend);

        Assert.assertTrue(user.getFriends().getFriends().isEmpty());
    }

    @Test
    public void getLowCostTripTest(){
        UserBuilder userBuilder = new UserBuilder();
        Price priceMock = Mockito.mock(Price.class);
        User user = userBuilder.withCheapAmmountOf(priceMock).build();

        Assert.assertEquals(priceMock, user.getLowCostTrip());
    }

    @Test
    public void getUserNameTest(){
        UserBuilder userBuilder = new UserBuilder();
        User user = userBuilder.withUserName("username").build();

        Assert.assertEquals("username", user.getUserName());
    }

    @Test
    public void getProfileTest(){
        UserBuilder userBuilder = new UserBuilder();
        Profile mockProfile = Mockito.mock(Profile.class);
        User user = userBuilder.withProfile(mockProfile).build();

        Assert.assertSame(mockProfile,user.getProfile());
    }

}
