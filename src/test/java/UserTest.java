import model.Event;
import model.User;
import model.creation.UserCreator;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * Created by Leonardo on 11/9/2016.
 */
public class UserTest {

    @Test
    public void createPersonalEventTest(){
        UserCreator userBuilder = new UserCreator();

        User user = userBuilder.anyUser().create();
        Event eventMock = Mockito.mock(Event.class);

        user.createPersonalEvent(eventMock);

        Assert.assertTrue(user.getPersonalEvent().contains(eventMock));
    }

    @Test
    public void friendsEventsTest(){
        UserCreator userBuilder = new UserCreator();

        User userWithEvent = userBuilder.anyUser().create();
        User friendUser = userBuilder.anyUser().create();
        Event eventMock = Mockito.mock(Event.class);

        userWithEvent.createPersonalEvent(eventMock);
        friendUser.addFriend(userWithEvent);

        Assert.assertTrue(friendUser.friendsEvent().contains(eventMock));
    }



}
