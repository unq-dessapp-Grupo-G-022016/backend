import model.*;
import model.Category;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * Created by leog on 05/09/16.
 */
public class GeneralTest {

    @Test
    public void cheapTripTestSeq(){

        List<Event> allEvents = new ArrayList<>();
        BundleGenerator bundleGenerator = new BundleGenerator();

        Price priceEventMock = Mockito.mock(Price.class);
        Price cheapPrice = Mockito.mock(Price.class);
        Price zeroPrice = Mockito.mock(Price.class);
        //Event eventMock = Mockito.mock(Event.class);

        Profile profile = Mockito.mock(Profile.class);
        FoodEvent foodEventMock = Mockito.mock(FoodEvent.class);
        MovieEvent movieEventMock = Mockito.mock(MovieEvent.class);

        allEvents.add(foodEventMock);
        allEvents.add(movieEventMock);
        allEvents.add(foodEventMock);
        allEvents.add(movieEventMock);
        allEvents.add(foodEventMock);
        allEvents.add(movieEventMock);

        when(foodEventMock.getPrice()).thenReturn(priceEventMock);
        when(movieEventMock.getPrice()).thenReturn(zeroPrice);

        when(foodEventMock.isFoodEvent()).thenReturn(true);
        when(movieEventMock.isFoodEvent()).thenReturn(false);

        when(foodEventMock.hasCategory()).thenReturn(false);

        when(zeroPrice.getAmmount()).thenReturn(0);
        when(priceEventMock.getAmmount()).thenReturn(10);
        when(cheapPrice.getAmmount()).thenReturn(30);

        // not a mock!!
        Set<Category> catSet = new HashSet<Category>();
        when(profile.getFoodTypes()).thenReturn(catSet);
        //Category frula = Mockito.mock(Category.class);
        //catSet.add(frula);
        //Assert.assertEquals(9, bundleGenerator.cheap(allEvents,cheapPrice,profile).size());
        //Assert.assertTrue(true);
    }



    @Test
    public void cheapTripTestSeqWithMatcher(){
        List<Event> allEvents = new ArrayList<>();
        BundleGenerator bundleG = new BundleGenerator();

        Price zeroPrice = Mockito.mock(Price.class);

        Profile profile = Mockito.mock(Profile.class);
        MovieEvent movieEventMock = Mockito.mock(MovieEvent.class);

        Category veganFood = Mockito.mock(Category.class);
        Category fastFood = Mockito.mock(Category.class);
        Category mexicanFood = Mockito.mock(Category.class);

        FoodEvent veganFoodEventMock = Mockito.mock(FoodEvent.class);
        FoodEvent mexicanFoodEventMock = Mockito.mock(FoodEvent.class);
        FoodEvent fastFoodEventMock = Mockito.mock(FoodEvent.class);

        when(veganFoodEventMock.hasCategory()).thenReturn(true);
        when(fastFoodEventMock.hasCategory()).thenReturn(true);
        when(mexicanFoodEventMock.hasCategory()).thenReturn(true);

        when(veganFoodEventMock.getPrice()).thenReturn(zeroPrice);
        when(fastFoodEventMock.getPrice()).thenReturn(zeroPrice);
        when(mexicanFoodEventMock.getPrice()).thenReturn(zeroPrice);

        when(veganFoodEventMock.getCategory()).thenReturn(veganFood);
        when(fastFoodEventMock.getCategory()).thenReturn(fastFood);
        when(mexicanFoodEventMock.getCategory()).thenReturn(mexicanFood);


        allEvents.add(fastFoodEventMock);
        allEvents.add(movieEventMock);
        allEvents.add(veganFoodEventMock);
        allEvents.add(movieEventMock);
        allEvents.add(mexicanFoodEventMock);
        allEvents.add(movieEventMock);

        when(movieEventMock.getPrice()).thenReturn(zeroPrice);

        when(movieEventMock.isFoodEvent()).thenReturn(false);
        when(fastFoodEventMock.isFoodEvent()).thenReturn(true);
        when(veganFoodEventMock.isFoodEvent()).thenReturn(true);
        when(mexicanFoodEventMock.isFoodEvent()).thenReturn(true);

        // not a mock!!
        Set<Category> catSet = new HashSet<Category>();
        catSet.add(veganFood);
        catSet.add(mexicanFood);
        //catSet.add(fastFood);

        when(profile.getFoodTypes()).thenReturn(catSet);

        when(fastFood.getName()).thenReturn("fast");
        when(mexicanFood.getName()).thenReturn("mexican");
        when(veganFood.getName()).thenReturn("vegan");


        when(zeroPrice.getAmmount()).thenReturn(0);

        //Assert.assertEquals(6, bundleG.cheap(allEvents,zeroPrice,profile).size());
        //Assert.assertTrue(true);
    }




    @Test
    public void friendlyTripTestSeqWithMatcher(){
        List<Event> allEvents = new ArrayList<>();
        BundleGenerator bundleG = new BundleGenerator();

        Price zeroPrice = Mockito.mock(Price.class);

        Profile profile = Mockito.mock(Profile.class);
        Profile profileFriend = Mockito.mock(Profile.class);
        MovieEvent movieEventMock = Mockito.mock(MovieEvent.class);

        Category veganFood = Mockito.mock(Category.class);
        Category fastFood = Mockito.mock(Category.class);
        Category mexicanFood = Mockito.mock(Category.class);

        FoodEvent veganFoodEventMock = Mockito.mock(FoodEvent.class);
        FoodEvent mexicanFoodEventMock = Mockito.mock(FoodEvent.class);
        FoodEvent fastFoodEventMock = Mockito.mock(FoodEvent.class);

        when(veganFoodEventMock.hasCategory()).thenReturn(true);
        when(fastFoodEventMock.hasCategory()).thenReturn(true);
        when(mexicanFoodEventMock.hasCategory()).thenReturn(true);

        when(veganFoodEventMock.getPrice()).thenReturn(zeroPrice);
        when(fastFoodEventMock.getPrice()).thenReturn(zeroPrice);
        when(mexicanFoodEventMock.getPrice()).thenReturn(zeroPrice);

        when(veganFoodEventMock.getCategory()).thenReturn(veganFood);
        when(fastFoodEventMock.getCategory()).thenReturn(fastFood);
        when(mexicanFoodEventMock.getCategory()).thenReturn(mexicanFood);


        allEvents.add(fastFoodEventMock);
        allEvents.add(movieEventMock);
        allEvents.add(veganFoodEventMock);
        allEvents.add(movieEventMock);
        allEvents.add(mexicanFoodEventMock);
        allEvents.add(movieEventMock);

        when(movieEventMock.getPrice()).thenReturn(zeroPrice);

        when(movieEventMock.isFoodEvent()).thenReturn(false);
        when(fastFoodEventMock.isFoodEvent()).thenReturn(true);
        when(veganFoodEventMock.isFoodEvent()).thenReturn(true);
        when(mexicanFoodEventMock.isFoodEvent()).thenReturn(true);

        Set<Category> catSet = new HashSet<Category>();
        catSet.add(veganFood);
        catSet.add(mexicanFood);
        catSet.add(fastFood);

        Set<Category> catSetFriend = new HashSet<Category>();
        catSetFriend.add(veganFood);
        //catSet.add(mexicanFood);
        //catSet.add(fastFood);

        //when(profile.getFoodTypes()).thenReturn(catSet);

        when(fastFood.getName()).thenReturn("fast");
        when(mexicanFood.getName()).thenReturn("mexican");
        when(veganFood.getName()).thenReturn("vegan");
        when(profileFriend.allCategories()).thenReturn(catSetFriend);
        when(profile.allCategories()).thenReturn(catSet);
        when(zeroPrice.getAmmount()).thenReturn(0);

        //Assert.assertEquals(3, bundleG.friendlyTrip(allEvents,profile,profileFriend).size());

    }

    @Test
    public void categoriesMatcherTest(){
        Category veganFood = Mockito.mock(Category.class);
        Category fastFood = Mockito.mock(Category.class);
        Category mexicanFood = Mockito.mock(Category.class);

        BundleGenerator bundleG = new BundleGenerator();

        Set<Category> categoriesSet = new HashSet<Category>();
        categoriesSet.add(veganFood);
        categoriesSet.add(fastFood);
        categoriesSet.add(mexicanFood);

        Assert.assertEquals(3,bundleG.categoriesMatcher(categoriesSet,categoriesSet).size());
    }

    @Test
    public void surpriseTripTestSeqWithMatcher(){
        List<Event> allEvents = new ArrayList<>();
        BundleGenerator bundleG = new BundleGenerator();

        Price zeroPrice = Mockito.mock(Price.class);

        Profile profile = Mockito.mock(Profile.class);
        Profile profileFriend = Mockito.mock(Profile.class,"profileFriend");
        MovieEvent movieEventMock = Mockito.mock(MovieEvent.class);

        Category veganFood = Mockito.mock(Category.class);
        Category fastFood = Mockito.mock(Category.class);
        Category mexicanFood = Mockito.mock(Category.class);

        FoodEvent veganFoodEventMock = Mockito.mock(FoodEvent.class);
        FoodEvent mexicanFoodEventMock = Mockito.mock(FoodEvent.class);
        FoodEvent fastFoodEventMock = Mockito.mock(FoodEvent.class);

        when(veganFoodEventMock.hasCategory()).thenReturn(true);
        when(fastFoodEventMock.hasCategory()).thenReturn(true);
        when(mexicanFoodEventMock.hasCategory()).thenReturn(true);

        when(veganFoodEventMock.getPrice()).thenReturn(zeroPrice);
        when(fastFoodEventMock.getPrice()).thenReturn(zeroPrice);
        when(mexicanFoodEventMock.getPrice()).thenReturn(zeroPrice);

        when(veganFoodEventMock.getCategory()).thenReturn(veganFood);
        when(fastFoodEventMock.getCategory()).thenReturn(fastFood);
        when(mexicanFoodEventMock.getCategory()).thenReturn(mexicanFood);


        allEvents.add(fastFoodEventMock);
        allEvents.add(movieEventMock);
        allEvents.add(veganFoodEventMock);
        allEvents.add(movieEventMock);
        allEvents.add(mexicanFoodEventMock);
        allEvents.add(movieEventMock);

        when(movieEventMock.getPrice()).thenReturn(zeroPrice);

        when(movieEventMock.isFoodEvent()).thenReturn(false);
        when(fastFoodEventMock.isFoodEvent()).thenReturn(true);
        when(veganFoodEventMock.isFoodEvent()).thenReturn(true);
        when(mexicanFoodEventMock.isFoodEvent()).thenReturn(true);

        Set<Category> catSet = new HashSet<Category>();
        catSet.add(veganFood);
        //catSet.add(mexicanFood);
        //catSet.add(fastFood);

        Set<Category> catSetFriend = new HashSet<Category>();
        catSetFriend.add(veganFood);
        catSetFriend.add(mexicanFood);
        //catSetFriend.add(fastFood);

        //when(profile.getFoodTypes()).thenReturn(catSet);

        when(fastFood.getName()).thenReturn("fast");
        when(mexicanFood.getName()).thenReturn("mexican");
        when(veganFood.getName()).thenReturn("vegan");
        when(zeroPrice.getAmmount()).thenReturn(0);

        User mockUser = Mockito.mock(User.class);
        User mockFriendUser = Mockito.mock(User.class);

        when(mockUser.getProfile()).thenReturn(profile);
        when(mockFriendUser.getProfile()).thenReturn(profileFriend);

        Set<User> friends = new HashSet<User>();
        friends.add(mockFriendUser);
        //when(mockUser.getFriends()).thenReturn(friends);

        when(profileFriend.allCategories()).thenReturn(catSetFriend);
        when(profile.allCategories()).thenReturn(catSet);

        //Assert.assertEquals(3, bundleG.surpriseTrip(allEvents,mockUser).size());

    }

    @Test
    public void allUsersCategoriesTest(){
        BundleGenerator bundleG = new BundleGenerator();
        Set<Profile> profiles = new HashSet<Profile>();

        Profile profile = Mockito.mock(Profile.class);
        Profile profileFriend = Mockito.mock(Profile.class);

        profiles.add(profile);
        profiles.add(profileFriend);

        Category veganFood = Mockito.mock(Category.class);
        Category fastFood = Mockito.mock(Category.class);
        Category mexicanFood = Mockito.mock(Category.class);

        Set<Category> catSet = new HashSet<Category>();
        catSet.add(veganFood);
        catSet.add(mexicanFood);
        //catSet.add(fastFood);

        Set<Category> catSetFriend = new HashSet<Category>();
        //catSetFriend.add(veganFood);
        //catSet.add(mexicanFood);
        catSetFriend.add(fastFood);

        when(profileFriend.allCategories()).thenReturn(catSetFriend);
        when(profile.allCategories()).thenReturn(catSet);

        Assert.assertEquals(3, bundleG.allFriendsCategories(profiles).size());
    }
}
