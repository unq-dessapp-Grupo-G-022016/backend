package model.old;

import model.persistents.Event;
import model.persistents.User;

/**
 * Created by alejandrok on 9/12/16.
 */
public class Score {

    private Event event;
    private User user;
    private int points; //from 1 to 5

    Score(Event event, User user, int points){
        this.event = event;
        this.user = user;
        this.points = points;
    }


    public boolean isGood(){
        return this.points>3;
    }
    public boolean isBad(){
        return this.points<3;
    }



}
