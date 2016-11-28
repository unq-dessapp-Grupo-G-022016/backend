package Service;

import org.apache.log4j.BasicConfigurator;
import org.junit.Assert;
import org.junit.Test;
import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

import javax.persistence.Entity;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created by Leonardo on 28/11/2016.
 */
public class ArqTest {


    @Test
    public void testReflect() {


        Reflections reflections = new Reflections("model");

        //config
        List<ClassLoader> classLoadersList = new LinkedList<ClassLoader>();
        classLoadersList.add(ClasspathHelper.contextClassLoader());
        classLoadersList.add(ClasspathHelper.staticClassLoader());

        Reflections reflectionsb = new Reflections(new ConfigurationBuilder()
                .setScanners(new SubTypesScanner(false /* don't exclude Object.class */), new ResourcesScanner())
                .setUrls(ClasspathHelper.forClassLoader(classLoadersList.toArray(new ClassLoader[0])))
                .filterInputsBy(new FilterBuilder().include(FilterBuilder.prefix("model"))));
        //

        Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(Entity.class);

        Set<Class<? extends Object>> allClasses = reflectionsb.getSubTypesOf(Object.class);


        /*
//
        //nope
        System.out.println("annotation classes");
        System.out.println(annotated.size());
        for (Class<?>c:annotated) {
            System.out.println(c.toString());
        }

        System.out.println("all classes");
        System.out.println(allClasses.size());
        for (Class<?>ac:allClasses) {
            System.out.println(ac.toString());
        }
*/

/*
        Assert.assertEquals(11,annotated.size());

        Assert.assertEquals(11,allClasses.size());

        */

        Assert.assertTrue(allClasses.size() > annotated.size());



    }

}
