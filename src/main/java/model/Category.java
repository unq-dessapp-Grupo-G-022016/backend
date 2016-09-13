package model;

/**
 * Created by Leonardo on 8/9/2016.
 */
public class Category {

    private String name;

    public Category(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public boolean isEqual(Category category){

        return this.getName() == category.getName() && !this.getName().contentEquals("undefined");
    }
}
