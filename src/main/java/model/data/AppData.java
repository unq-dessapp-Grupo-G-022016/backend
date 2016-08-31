package model.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by leog on 31/08/16.
 */
public class AppData {

    public List<String> musicCategories = new ArrayList<String>();
    public List<String> moviesCategories = new ArrayList<String>();
    public List<String> foodTypes = new ArrayList<String>();



    private List<String> add(List<String>cateroriesList, String category){
        cateroriesList.add(category);
        return cateroriesList;
    }

    private AppData addMusicType(String category){
        musicCategories.add(category);
        return this;
    }

    private AppData addMovieCategory(String category){
        moviesCategories.add(category);
        return this;
    }

    private AppData addFoodType(String category){
        foodTypes.add(category);
        return this;
    }

    public void fillCategories(){
        addMusicType("Rock").addMusicType("Punk").addMusicType("Indie").addMusicType("Grunge");
        addFoodType("Meat").addFoodType("Vegan").addFoodType("Celiac").addFoodType("GlutenFree").addFoodType("Mexican");
        addMovieCategory("Action").addMovieCategory("Drama").addMovieCategory("Comedy");
    }

}


