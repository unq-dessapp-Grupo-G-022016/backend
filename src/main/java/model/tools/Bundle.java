package model.tools;

import model.persistents.Event;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alejandrok on 9/8/16.
 */
public class Bundle {

    private List<Event> bundle = new ArrayList<>();
    private boolean isOrdered = false;

    public Bundle(){
    	
    }
    
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
