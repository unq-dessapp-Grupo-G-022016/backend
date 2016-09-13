package model;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * Created by Leonardo on 13/9/2016.
 */
public class ScoreTest {

    @Test
    public void isBadScoreTest(){
        Event eventMock = Mockito.mock(Event.class);
        User userMock = Mockito.mock(User.class);
        Score score = new Score(eventMock,userMock,2);

        Assert.assertTrue(score.isBad());
    }

    @Test
    public void isNotBadScoreTest(){
        Event eventMock = Mockito.mock(Event.class);
        User userMock = Mockito.mock(User.class);
        Score score = new Score(eventMock,userMock,5);

        Assert.assertFalse(score.isBad());
    }

    @Test
    public void isGoodScoreTest(){
        Event eventMock = Mockito.mock(Event.class);
        User userMock = Mockito.mock(User.class);
        Score score = new Score(eventMock,userMock,4);

        Assert.assertTrue(score.isGood());
    }

    @Test
    public void isNotGoodScoreTest(){
        Event eventMock = Mockito.mock(Event.class);
        User userMock = Mockito.mock(User.class);
        Score score = new Score(eventMock,userMock,2);

        Assert.assertFalse(score.isGood());
    }


}
