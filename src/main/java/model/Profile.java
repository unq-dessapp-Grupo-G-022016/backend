package model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.codehaus.jackson.annotate.JsonIgnore;

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

	@JsonIgnore
	@ManyToMany (fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Category> categories = new HashSet<>();

    public void addCategory(Category category){this.categories.add(category);}

    public void removeCategory(Category category){this.categories.remove(category);}

    public Set<Category> allCategories(){
        return categories;
    }

}
