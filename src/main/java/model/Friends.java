package model;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by alejandroK on 10/9/2016.
 */
public class Friends {

    Set<User> friends = new HashSet<>();

    public void add(User friend) {
        this.friends.add(friend);
    }

    public void remove(User friend) {
        this.friends.remove(friend);
    }

    public Set<Category> allCategories() {
        Set<Category> allCategories = new HashSet<Category>();
        friends.forEach(friend -> allCategories.addAll(friend.getProfile().allCategories()));
        return allCategories;
    }

    public Set<Profile> friendsProfiles() {
        Set<Profile>friendsProfiles = new HashSet<Profile>();
        friends.forEach(f -> friendsProfiles.add(f.getProfile()));
        return friendsProfiles;
    }

    public Set<User> getFriends() { return friends; }

}
