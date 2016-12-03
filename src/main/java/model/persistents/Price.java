package model.persistents;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by leog on 30/08/16.
 */
@Entity
public class Price {

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Id()
    @GeneratedValue()
	private int id;
	
	@Column
    private int ammount;
	
	public Price(){
		
	}

    public boolean isFree(){
        return (this.ammount==0);
    }

    public boolean isCheap(User user){
        return this.getAmmount()<=user.getLowCostTrip().getAmmount();
    }

    public int getAmmount(){
        return ammount;
    }

    public void setAmmount(int ammount){
        this.ammount = ammount;
    }

    public Price(int ammount){
        setAmmount(ammount);
    }

}


