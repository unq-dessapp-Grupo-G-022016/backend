package model;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * Created by alejandrok on 9/12/16.
 */
public class ProfileTest {

    @Test
    public void allCategoriesTest(){
        Profile profile = new Profile();
        Category mockCategory = Mockito.mock(Category.class);

        profile.addCategory(mockCategory);

        Assert.assertEquals(1,profile.allCategories().size());
    }

    @Test
    public void removeCategoryTest(){
        Profile profile = new Profile();
        Category mockCategory = Mockito.mock(Category.class);

        profile.addCategory(mockCategory);
        profile.removeCategory(mockCategory);

        Assert.assertTrue(profile.allCategories().isEmpty());
    }
}
