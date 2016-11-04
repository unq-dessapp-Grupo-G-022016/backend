package model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
public class Attenders {
   
    /* TODO
     * check capacity and attenders!!
     */
   
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
   
    @ManyToMany (fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
              name="attendersSet",
              joinColumns=@javax.persistence.JoinColumn(name="attendersId", referencedColumnName="id"),
              inverseJoinColumns=@javax.persistence.JoinColumn(name="userName", referencedColumnName="userName"))
    private Set<User>users = new HashSet<>();
   
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public int getRecommendedMinGroup() {
        return recommendedMinGroup;
    }

    public void setRecommendedMinGroup(int recommendedMinGroup) {
        this.recommendedMinGroup = recommendedMinGroup;
    }

    public int getRecommendedMaxGroup() {
        return recommendedMaxGroup;
    }

    public void setRecommendedMaxGroup(int recommendedMaxGroup) {
        this.recommendedMaxGroup = recommendedMaxGroup;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Column
    private int maxCapacity;
    @Column
    private int recommendedMinGroup;
    @Column
    private int recommendedMaxGroup;

    public Attenders(){}
   
    public void add(User user) {
        // TODO Auto-generated method stub
        getUsers().add(user);
    }

    @JsonIgnore
    public Set<User> getUsers() {
        // TODO Auto-generated method stub
        return this.users;
    }


}
