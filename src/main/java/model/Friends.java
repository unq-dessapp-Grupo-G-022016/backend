package model;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by alejandroK on 10/9/2016.
 */
public class Friends {

    private Set<User> friends = new HashSet<>();

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

    private Set<Profile> friendsProfilesWithInterests(Set<Category> categories){
        Set<Profile> profiles = new HashSet<Profile>();
        profiles.addAll(this.friendsProfiles());
        Iterator<Profile> it = profiles.iterator();
        while (it.hasNext()){
            Profile p = it.next();
            if (new JoolUse().categoriesSetsIntersection(p.allCategories(),categories).size()==0) {
                it.remove();
            }
        }
        return profiles;
    }

    public Set<Category> categoriesOfUsersThatHaveAnyOfThis(Set<Category> categories) {
        Set<Category> friendsCategories = new HashSet<Category>();
        this.friendsProfilesWithInterests(categories).forEach(profile -> friendsCategories.addAll(profile.allCategories()));
        return  friendsCategories;
    }
}
