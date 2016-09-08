package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alejandrok on 9/8/16.
 */
public class Bundle {

    List<Event> bundle = new ArrayList<>();

    public void add(Event anEvent){
        bundle.add(anEvent);
    }
}
