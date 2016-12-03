package model;

import model.persistents.Price;
import model.persistents.User;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;

/**
 * Created by alejandrok on 9/12/16.
 */
public class PriceTest {
    @Test
    public void isCheapTest(){
        Price price = new Price(10);

        User mockUser = Mockito.mock(User.class);
        when(mockUser.getLowCostTrip()).thenReturn(price);

        Assert.assertTrue(price.isCheap(mockUser));

    }
    @Test
    public void isNotCheapTest(){
        Price price = new Price(10);
        Price userCheapPrice = new Price(5);

        User mockUser = Mockito.mock(User.class);
        when(mockUser.getLowCostTrip()).thenReturn(userCheapPrice);

        Assert.assertFalse(price.isCheap(mockUser));

    }

    @Test
    public void isNotFreeTest(){
        Price price = new Price(10);
        Assert.assertFalse(price.isFree());
    }

    @Test
    public void isFreeTest(){
        Price price = new Price(0);
        Assert.assertTrue(price.isFree());
    }
}
