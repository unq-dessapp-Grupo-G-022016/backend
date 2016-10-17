package Service;

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

    @Test
    public void testDelete() {
    	userService.save(userBuilder.anyUser().build());
    	userService.delete(userService.retriveAll().get(0));
        Assert.assertEquals(0, userService.retriveAll().size());
    }
    @Test
    public void testSave() {
    	User user = new User();
    	user.userName = "a";
    	userService.save(user);
        Assert.assertEquals(1, userService.retriveAll().size());
    }
    @Test
    public void testSaveUsingBuilder() {
    	User user = userBuilder.withUserName("builded").build();
    	userService.save(user);
        Assert.assertEquals(1, userService.retriveAll().size());
    }
    

}
