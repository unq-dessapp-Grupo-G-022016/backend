package model;

/**
 * Created by leog on 30/08/16.
 */
public class Price {

    int ammount;



    public boolean isFree(){
        return true;
    }

    public boolean isCheap(){
        return false;
    }

    public int ammount(){
        return ammount;
    }

    public void setAmmount(int ammount){
        this.ammount = ammount;
    }

    public Price(int ammount){
        setAmmount(ammount);
    }

}


