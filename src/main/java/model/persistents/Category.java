package model.persistents;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Leonardo on 8/9/2016.
 */
@Entity
public class Category {

	@Id
    private String name;
	
	public void setName(String name) {
		this.name = name;
	}

	public Category(){
		
	}

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
