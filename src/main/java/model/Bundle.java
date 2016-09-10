package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by alejandrok on 9/8/16.
 */
public class Bundle {

    List<Event> bundle = new ArrayList<>();

    public void add(Event anEvent){
        bundle.add(anEvent);
    }

    public void orderByStartTime(){
        bundle.sort((o1, o2) -> o1.getStartTime().compareTo(o2.getStartTime()));
    }

    public List<Event> getBundle() {
        return bundle;
    }

}
