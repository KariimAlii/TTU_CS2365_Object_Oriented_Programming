/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Card;

/**
 *
 * @author raime
 */
public class BoneyardCard {
    //public final static int NOHANDS = 0, ONEHAND = 1, TWOHANDS = 2;
    private int number;
    
    /**
     *Constructor for BoneyardCard 
     * @param number
     */
    public BoneyardCard(int number){
        this.number = number;
    }
    
    /**
     *Overload constructor
     */
    public BoneyardCard(){
        this(-1);
    }
    
    /**
     *Method to check if card is null
     * @return
     */
    public Boolean isNullCard(){
        return(this.number == -1);
    }
    
    /**
     *Method to set a card to null value
     */
    public void setCardNull(){
        this.number = -1;
    }
    
    /**
     *Method to get and return card's value
     * @return
     */
    public int getCardNumber(){
        return this.number;
    }
}
