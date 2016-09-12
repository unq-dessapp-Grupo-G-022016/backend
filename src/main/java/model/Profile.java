package model;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by leog on 30/08/16.
 */
public class Profile {




    Set<Category>musicalGenres = new HashSet<Category>();
    Set<Category>movieTypes = new HashSet<Category>();
    Set<Category>foodTypes = new HashSet<Category>();


    public Profile() {
    }

    public void addMusicCategory(Category category){
        this.musicalGenres.add(category);
    }

    public void removeMusicCategory(Category category){
        this.musicalGenres.remove(category);
    }

    public void addMovieCategory(Category category){
        this.movieTypes.add(category);
    }

    public void removeMovieCategory(Category category){
        this.movieTypes.remove(category);
    }

    public void addFoodCategory(Category category){
        this.foodTypes.add(category);
    }

    public void removeFoodCategory(Category category){
        this.foodTypes.remove(category);
    }
/*
    public boolean eventCompatibility(Event event){
        //only for specific events, aka food, movie, music
        if(event.hasCategory() && event instanceof SpecificEvent){
            return allCategories().contains(event.category);
        }
        return false;// ¿¿
    }
*/
    public Set<Category> allCategories(){
        Set<Category>categories = new HashSet<Category>();
        categories.addAll(movieTypes);
        categories.addAll(musicalGenres);
        categories.addAll(foodTypes);
        return categories;
    }
/*
    public Set<Category> categoriesCompatibility(Profile profile){
        Set<Category>categories = this.allCategories();
        categories.retainAll(profile.allCategories());
        return categories;
    }
*/
    public Set<Category> getFoodTypes(){
        return this.foodTypes;
    }
}
