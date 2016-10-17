package Service;

import org.junit.After;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import model.Event;
import model.creation.EventBuilder;
import service.EventService;

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
    @Ignore
    @Test
    public void testSave() {
    	eventService.save(eventBuilder.anyEvent().buildGeneralEvent());
        Assert.assertEquals(1, eventService.retriveAll().size());
    }
    

}
