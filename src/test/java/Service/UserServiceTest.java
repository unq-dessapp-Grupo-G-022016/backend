package Service;

import javax.transaction.Transactional;

import org.junit.After;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import model.User;
import model.creation.EventBuilder;
import model.creation.UserBuilder;
import service.EventService;
import service.UserService;

/**
 * TODO: description
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/META-INF/spring-persistence-context.xml", "/META-INF/spring-services-context.xml" })
public class UserServiceTest {

    @Autowired
    private UserService userService;
    private UserBuilder userBuilder = new UserBuilder();

    @After
    public void eraseData() {
    	userService.retriveAll().forEach(each -> userService.delete(each));
    }

    @Transactional
    @Test
    public void testDelete() {  	
    	userService.save(userBuilder.anyUser().build());
    	userService.delete(userService.retriveAll().get(0));
        Assert.assertEquals(0, userService.retriveAll().size());
    }
    @Transactional
    @Test
    public void testSave() {
    	User user = new User();
    	user.userName = "a";
    	userService.save(user);
        Assert.assertEquals(1, userService.retriveAll().size());
    }
    @Transactional
    @Test
    public void testSaveUsingBuilder() {
    	User user = userBuilder.withUserName("builded").build();
    	userService.save(user);
        Assert.assertEquals(1, userService.retriveAll().size());
    }
    @Transactional
    @Test
    public void testAddFriendAndDeleteFriend() {  	
    	userService.save(userBuilder.withUserName("momo1").build());
    	userService.save(userBuilder.withUserName("momo2").build());
    	User momo1 = userService.findById("momo1"); 
    	User momo2 = userService.findById("momo2");
    	
    	momo1.addFriend(momo2);
    	userService.save(momo1);
    	//userService.delete(momo2);
    	
        //Assert.assertEquals(1, userService.retriveAll().size());
    	Assert.assertEquals(2, userService.retriveAll().size());
    }

}
