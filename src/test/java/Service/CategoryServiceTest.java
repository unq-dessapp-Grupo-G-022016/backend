package Service;

import model.Category;
import model.Event;
import model.Price;
import model.User;
import model.creation.EventBuilder;
import org.junit.After;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import service.CategoryService;
import service.EventService;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by alejandrok on 26/11/16.
 */


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/META-INF/spring-persistence-context.xml", "/META-INF/spring-services-context.xml" })
public class CategoryServiceTest {

    @Autowired
    private CategoryService categoryService;

    @After
    public void eraseData() {
        //categoryService.retriveAll().forEach(each -> categoryService.delete(each));
    }

    @Ignore
    @Test
    public void testDelete() {
        categoryService.save(new Category("test"));
        categoryService.delete(categoryService.retriveAll().get(0));
        Assert.assertEquals(0, categoryService.retriveAll().size());
    }
    //@Transactional
    @Test
    public void testSave() {
        //eventService.save(eventBuilder.anyEvent().buildGeneralEvent());
        //Category category = new Category("test");
        categoryService.create("test");

        Assert.assertEquals(1, categoryService.retriveAll().size());
    }


}
