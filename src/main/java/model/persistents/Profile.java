package model.persistents;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

/**
 * Created by leog on 30/08/16.
 */
@Entity
public class Profile {
	
	public Profile(){
		
	}
	
	@Id()
    @GeneratedValue()
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Set<Category> getCategories() {
		return categories;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}

	@ManyToMany (fetch = FetchType.EAGER, targetEntity=Category.class)
	@org.hibernate.annotations.Cascade(value=org.hibernate.annotations.CascadeType.SAVE_UPDATE)
	@JoinTable(
		      name="categoriesSet",
		      joinColumns=@JoinColumn(name="userProfileId", referencedColumnName="id"),
		      inverseJoinColumns=@JoinColumn(name="category", referencedColumnName="name"))
  
	/*
	@ManyToMany(fetch = FetchType.LAZY,
    cascade =
    {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.PERSIST
    },
    targetEntity = Category.class)
@JoinTable(name = "ProfileCategories",
    inverseJoinColumns = @JoinColumn(name = "profileId",
            nullable = false,
            updatable = false),
    joinColumns = @JoinColumn(name = "categoryName",
            nullable = false,
            updatable = false),
    foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT),
    inverseForeignKey = @ForeignKey(ConstraintMode.CONSTRAINT))
    */
	private Set<Category> categories = new HashSet<>();

    public void addCategory(Category category){this.categories.add(category);}

    public void removeCategory(Category category){this.categories.remove(category);}

    public Set<Category> allCategories(){
        return categories;
    }

    public boolean hasCategory(String categoryName){

		Stream<Category> cat = getCategories().stream().filter(c -> (c.getName().equals(categoryName)));
		return (cat.toArray().length>0);
	}

}
