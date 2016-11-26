package Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import model.Category;
import model.Event;
import model.Price;
import model.User;
import model.creation.EventBuilder;
import service.EventService;

import javax.transaction.Transactional;

/**
 * TODO: description
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/META-INF/spring-persistence-context.xml", "/META-INF/spring-services-context.xml" })
public class EventServiceTest {

    @Autowired
    private EventService eventService;
    private EventBuilder eventBuilder;

    @After
    public void eraseData() {
    	eventService.retriveAll().forEach(each -> eventService.delete(each));
    }
    
    @Ignore
    @Test
    public void testDelete() {
    	eventService.save(eventBuilder.anyEvent().buildGeneralEvent());
    	eventService.delete(eventService.retriveAll().get(0));
        Assert.assertEquals(0, eventService.retriveAll().size());
    }
    @Transactional
    @Test
    public void testSave() {
    	//eventService.save(eventBuilder.anyEvent().buildGeneralEvent());
    	Event e = new Event();
    	e.setName("goingToHell");
    	e.setAddress("666");
    	e.setDetails("Devils house");
    	e.setPrice(new Price(20));
    	e.setStartTime(LocalDateTime.now());
    	e.setEndTime(LocalDateTime.now());
    	Set<User> uset = new HashSet<User>();
    	//e.setAttenders(uset);
    	//e.setCategory(new Category("warm places"));
    	eventService.save(e);

        Assert.assertEquals(1, eventService.retriveAll().size());
    }
    

}
