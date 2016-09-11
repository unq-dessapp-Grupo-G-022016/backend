package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alejandrok on 9/8/16.
 */
public class Bundle {

    List<Event> bundle = new ArrayList<>();
    boolean isOrdered = false;

    public void add(Event anEvent){
        bundle.add(anEvent);
    }

    public void orderByStartTime(){
        bundle.sort((o1, o2) -> o1.getStartTime().compareTo(o2.getStartTime()));
        isOrdered = true;
    }

    public List<Event> getBundle() {
        return bundle;
    }

    public boolean isValidBundle(){
        if (!this.isOrdered){
            this.orderByStartTime();
        }
        boolean res = true;

        for(int i = 0; i < bundle.size()-1; i ++) {
            Event e1 = bundle.get(i);
            Event e2 = bundle.get(i+1);
            if (!e1.timeCompatible(e2)){
                res = false;
            }
        }

        return res;
    }

}
