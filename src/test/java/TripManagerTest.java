import model.Event;
import model.TripManager;
import model.User;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import sun.swing.BakedArrayList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alejandroK on 10/9/2016.
 */
public class TripManagerTest {

    @Test
    public void cheapTrip(){
        TripManager tripManager = new TripManager();

        List<Event> eventsList = new ArrayList<>();
        User userMock = Mockito.mock(User.class);

        Assert.assertEquals(0,tripManager.cheapTrip(eventsList,userMock).size());
    }
}
