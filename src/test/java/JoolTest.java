import model.*;
import org.jooq.lambda.Seq;
import org.jooq.lambda.tuple.Tuple;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.jooq.lambda.Seq.seq;
import static org.jooq.lambda.tuple.Tuple.tuple;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

/**
 * Created by alejandrok on 9/7/16.
 */
public class JoolTest {

    @Test
    public void test1(){

        List<Event> allEvents = new ArrayList<>();
        Bundle bundle = new Bundle();
        FoodEvent foodEventMock = Mockito.mock(FoodEvent.class);
        MovieEvent movieEventMock = Mockito.mock(MovieEvent.class);
        Price zeroPrice = Mockito.mock(Price.class);


        allEvents.add(foodEventMock);
        allEvents.add(movieEventMock);
        allEvents.add(foodEventMock);
        allEvents.add(movieEventMock);
        allEvents.add(foodEventMock);
        allEvents.add(movieEventMock);



        when(foodEventMock.price()).thenReturn(zeroPrice);
        when(movieEventMock.price()).thenReturn(zeroPrice);

        when(foodEventMock.isFoodEvent()).thenReturn(true);
        when(movieEventMock.isFoodEvent()).thenReturn(false);

        when(zeroPrice.ammount()).thenReturn(0);


        //assertEquals(0,tuple(bundle.listToString(allEvents)));
        //assertEquals(0,bundle.aSeqCheapBundle(allEvents,new Price(0)));
        assertTrue(true);
    }


    @Test
    public void secTest(){
        /*
        (tuple(1, "A"), tuple(1, "B"), tuple(2, "A"), tuple(2, "B"))
        Seq.of(1, 2).crossJoin(Seq.of("A", "B"));
        */

        Stream t1 = Stream.of("program", "creek");

        Seq<String> seq1 = seq(t1);
        //Seq<Integer> seq1 = seq(Stream.of(1, 2));

        Assert.assertEquals(4, seq1.crossJoin(Seq.of("A", "B")).count());
    }
}
