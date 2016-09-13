package model;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by leog on 30/08/16.
 */
public class Profile {

    private Set<Category> categories = new HashSet<>();

    public void addCategory(Category category){this.categories.add(category);}

    public void removeCategory(Category category){this.categories.remove(category);}

    public Set<Category> allCategories(){
        return categories;
    }

}
