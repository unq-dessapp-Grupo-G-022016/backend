package model;

import model.persistents.Category;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by alejandroK on 10/9/2016.
 */
public class CategoryTest {

    @Test
    public void isEqualTest(){
        Category vegan = new Category("vegan");
        Assert.assertTrue(vegan.isEqual(vegan));
    }
    @Test
    public void isNotEqualTest(){
        Category vegan = new Category("vegan");
        Category mexican = new Category("mexican");

        Assert.assertFalse(vegan.isEqual(mexican));
    }
}
