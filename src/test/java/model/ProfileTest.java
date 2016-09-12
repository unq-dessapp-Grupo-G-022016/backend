package model;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * Created by alejandrok on 9/12/16.
 */
public class ProfileTest {

    @Test
    public void allCategories(){
        Profile profile = new Profile();
        Category mockCategory = Mockito.mock(Category.class);
        profile.addFoodCategory(mockCategory);
        Assert.assertEquals(1,profile.allCategories().size());
    }
}
