import model.User;
import model.creation.UserBuilder;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Leonardo on 10/9/2016.
 */
public class UserBuilderTest {

    @Test
    public void anyUserTest(){
        UserBuilder userBuilder = new UserBuilder();

        //ej
        //User anyUserA = userBuilder.anyUser().create();

        //User anyUserB = userBuilder.anyUser().withCheapAmmountOf(new Price(2)).create();


        User userWithUsername = userBuilder.withUserName("userName").build();



        Assert.assertEquals("userName",userWithUsername.getUserName());

    }
}
