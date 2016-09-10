import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.jooq.lambda.Seq;
import org.junit.Test;
import org.mockito.Mockito;

public class MockitoTest{

/*
@Test
    public void test1()  {
            class MyClass {
                public int getUniqueId(){
                        return 0;
                }
            }
            //  create mock
            MyClass test = Mockito.mock(MyClass.class);


            // define return value for method getUniqueId()
            when(test.getUniqueId()).thenReturn(43);

            // use mock in test....
            assertEquals(test.getUniqueId(), 43);
            
    }
*/
/*

    @Test public void crossJoinTest(){


        Seq.of(1, 2).crossJoin(Seq.of("A", "B"));

        Seq.of(1, 2).crossJoin(Seq.of("A", "B"));
        Seq.of(1, 2, 3).crossJoin(Seq.of("A", "B"));
        Seq.of(1, 2,3).crossJoin(Seq.of("A", "B","C","D"));
        Seq.of(1, 2).crossJoin(Seq.of("A", "B")).crossJoin(Seq.of(6,3));

        //assertEquals(0,Seq.of(1, 2).crossJoin(Seq.of("A", "B")));

        //assertEquals(0,Seq.of(1, 2).crossJoin(Seq.of("A", "B")).crossJoin(Seq.of(6,3)));

        assertEquals(0,0);

    }
*/
/*
    // Demonstrates the return of multiple values
    @Test
    public void testMoreThanOneReturnValue()  {
            Iterator i= mock(Iterator.class);
            when(i.next()).thenReturn("Mockito").thenReturn("rocks");
            String result=i.next()+" "+i.next();
            //assert
            assertEquals("Mockito rocks", result);
    }

    // this test demonstrates how to return values based on the input
    @Test
    public void testReturnValueDependentOnMethodParameter()  {
            Comparable c= mock(Comparable.class);
            when(c.compareTo("Mockito")).thenReturn(1);
            when(c.compareTo("Eclipse")).thenReturn(2);
            //assert
            assertEquals(1,c.compareTo("Mockito"));
    }

    // this test demonstrates how to return values independent of the input value

    @Test
    public void testReturnValueInDependentOnMethodParameter()  {
            Comparable c= mock(Comparable.class);
            when(c.compareTo(anyInt())).thenReturn(-1);
            //assert
            assertEquals(-1 ,c.compareTo(9));
    }

    // return a value based on the type of the provide parameter

    @Test
    public void testReturnValueInDependentOnMethodParameter()  {
            Comparable c= mock(Comparable.class);
            when(c.compareTo(isA(Todo.class))).thenReturn(0);
            //assert
            Todo todo = new Todo(5);
            assertEquals(todo ,c.compareTo(new Todo(1)));
    }


    // this test demonstrates how use doThrow

    @Test(expected=IOException.class)
    public void testForIOException() {
            // create an configure mock
            OutputStream mockStream = mock(OutputStream.class);
            doThrow(new IOException()).when(mockStream).close();

            // use mock
            OutputStreamWriter streamWriter= new OutputStreamWriter(mockStream);
            streamWriter.close();
    }
*/
}
    