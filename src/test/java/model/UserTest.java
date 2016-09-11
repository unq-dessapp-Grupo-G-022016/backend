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



}
