package model;

import model.persistents.Category;
import model.persistents.Friends;
import model.persistents.Profile;
import model.persistents.User;
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
    public void allCategoriesTest(){
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

    @Test
    public void categoriesOfUsersThatHaveAnyOfThisTest(){
        Friends friends = new Friends();
        Set<Category> categorySet = new HashSet<Category>();
        Set<Category> categorySet1 = new HashSet<Category>();
        Set<Category> categorySet2 = new HashSet<Category>();
        Category mockTerrorCategory = Mockito.mock(Category.class);
        Category mockSciFiCategory = Mockito.mock(Category.class);
        Category mockAdventureCategory = Mockito.mock(Category.class);
        categorySet.add(mockSciFiCategory);
        User mockUser1 = Mockito.mock(User.class);
        User mockUser2 = Mockito.mock(User.class);
        Profile mockProfile1 = Mockito.mock(Profile.class);
        Profile mockProfile2 = Mockito.mock(Profile.class);
        friends.add(mockUser1);
        friends.add(mockUser2);
        categorySet1.add(mockSciFiCategory);
        categorySet1.add(mockTerrorCategory);
        categorySet2.add(mockAdventureCategory);
        when(mockUser1.getProfile()).thenReturn(mockProfile1);
        when(mockProfile1.allCategories()).thenReturn(categorySet1);
        when(mockUser2.getProfile()).thenReturn(mockProfile2);
        when(mockProfile2.allCategories()).thenReturn(categorySet2);

        Assert.assertEquals(2,friends.categoriesOfUsersThatHaveAnyOfThis(categorySet).size());
    }

    @Test
    public void removeTest(){
        Friends friends = new Friends();
        User mockUser1 = Mockito.mock(User.class);
        friends.add(mockUser1);
        friends.remove(mockUser1);

        Assert.assertEquals(0,friends.getFriends().size());
    }
}
