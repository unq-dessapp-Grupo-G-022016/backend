package model;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;

/**
 * Created by alejandrok on 9/12/16.
 */
public class PriceTest {
    @Test
    public void isCheap(){
        Price price = new Price(10);

        User mockUser = Mockito.mock(User.class);
        when(mockUser.getLowCostTrip()).thenReturn(price);

        Assert.assertTrue(price.isCheap(mockUser));

    }
}
