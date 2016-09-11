package model;

import model.creation.UserBuilder;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.when;

/**
 * Created by alejandroK on 11/9/2016.
 */
public class FriendsTest {

    @Test
    public void allCategories(){

        Friends friends = new Friends();
        User mockUser = Mockito.mock(User.class);
        friends.add(mockUser);
        Set<Category> categoriesSet = new HashSet<Category>();
        Category mockCategory = Mockito.mock(Category.class);
        categoriesSet.add(mockCategory);
        Profile mockProfile = Mockito.mock(Profile.class);
        when(mockUser.getProfile()).thenReturn(mockProfile);
        when(mockProfile.allCategories()).thenReturn(categoriesSet);

        Assert.assertEquals(1,friends.allCategories().size());
    }
}
