package model;

/**
 * Created by leog on 30/08/16.
 */
public class Price {

    int ammount;



    public boolean isFree(){
        return (this.ammount==0);
    }

    public boolean isCheap(User user){
        return this.ammount<user.getLowCostTrip().getAmmount();
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


