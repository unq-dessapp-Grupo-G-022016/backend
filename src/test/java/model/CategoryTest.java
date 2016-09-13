package model;

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
}
