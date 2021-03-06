package model.persistents;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import model.tools.JoolUse;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * Created by alejandroK on 10/9/2016.
 */
@Entity
//@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
public class Friends {

	@Id()
    @GeneratedValue()
	private int id;
	
	@ManyToMany (fetch = FetchType.EAGER, targetEntity=User.class)//, cascade=CascadeType.ALL)
	@org.hibernate.annotations.Cascade(value=org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinTable(
		      name="friendsSet",
		      joinColumns=@JoinColumn(name="userFriendId", referencedColumnName="id"),
		      inverseJoinColumns=@JoinColumn(name="friendName", referencedColumnName="userName"))
    private Set<User> friends = new HashSet<>();

    @JsonIgnore
    public Set<User> getFriends() { return friends; }
    
	public Friends(){
		
	}
	
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
    
    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setFriends(Set<User> friends) {
		this.friends = friends;
	}

    public Set<Category> categoriesOfUsersThatHaveAnyOfThis(Set<Category> categories) {
        Set<Category> friendsCategories = new HashSet<Category>();
        this.friendsProfilesWithInterests(categories).forEach(profile -> friendsCategories.addAll(profile.allCategories()));
        return  friendsCategories;
    }
}
