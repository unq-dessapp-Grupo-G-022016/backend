package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by leog on 30/08/16.
 */
public class User {

    String userName;
    Profile profile;
    Price lowCostTrip;
    //StartPoint;
    List<User> friends =  new ArrayList<User>();
    Set<String> vehicles= new HashSet<String>();





}
