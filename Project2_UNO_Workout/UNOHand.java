/*
 * TITLE: Deck Class for Project 2 UNO Workout
 * AUTHOR: Anamol Acharya
 * FOR: CS 2365 Object Oriented Programming Section 001 Spring 2020
 */
package unoworkout;

/**
 * It is responsible for playing a Card-choosing a card to play
 * To do this, it implements the Hand class of this project. A Hand  is responsible for creating a hand using array from 
 * the UNOCard class, sort the created hand according to the project instruction,
 * insert/remove a card in a hand, check if the hand is empty/full, Return a string rendering of this Hand.
 * This class interacts most of the time to UNOCards.java class for the operation.
 
 */
public class UNOHand {

    private UNOCard hand[];
   
    /**
     * Declare a UNOHand object to be played by the UNOHand class, and
     * passed as arguments. This implements a strategy
     * pattern whereby the constructor accepts various strategies that
     * implement the UNOCards interface.
     
    Creating an array of 7 cards from the UNOCard using a for loop.
    */
    public UNOHand() {
        hand = new UNOCard[7];
        for(int i = 0; i < 7; i++){
            hand[i] = new UNOCard();
        }

    }

    /**
     *This method add a card in the hand
     */
    public void insertCard(int index, UNOCard card) {
        this.hand[index] = card;
    }

     /**
     * This method removes a card in the sorted hands. 
     */
    public UNOCard removeCard(int index){
        UNOCard temp = new UNOCard();
        temp.copyCard(this.hand[index]);
        for(int i = index; i < 7; i++){
            if(i < 6) {
                this.hand[i] = this.hand[i+1];
                if(this.hand[i+1].isNullCard()) break;
            }
            else {this.hand[i]= new UNOCard();}
        }
        return temp;
    }
    
    
   
    public void discardHand(){
        for(int i = 0; i < 7; i++)
        {
            this.hand[i]= new UNOCard();
        }
    }
    /**
     * This method returns true only if this Hand has no cards, which should trigger a 
     * winning condition.
     * For loop is performed to check if hand is empty of not.
     * @return 
     */
    public boolean isHandEmpty() {
        boolean returnvalue = true;
        for(int i = 0; i < 7; i++){
            if(!this.hand[i].isNullCard()) returnvalue = false;
        }
        return returnvalue;
    }

    /*
    This boolean method chechs if the hand is full or not. 
    It performs a for loop to check if the  hand is null just to check is hand is full. 
    */
    public boolean isHandFull(){
      boolean returnvalue = true;
        for(int i = 0; i < 7; i++){
            if(this.hand[i].isNullCard()) returnvalue = false;
        }
        return returnvalue;
    }
    


    
    /**
   To sort the hand calling from the UNOCard class
   * The use of selection sort is performed to sort the cards as the total number of cards
   * is 7, I believe, the time complexity does not make a huge difference to look for other better sorting algorithm
  
   */
    public void sortHand() {
        for(int i = 0; i < 6; i++){
            int minIndex = i;
            int j = i+1;
            for(; j < 7; j++){
                if(this.hand[j].compareCard(this.hand[minIndex]) < 0){
                    minIndex = j;
                }
            }
            UNOCard temp = this.hand[i];
            this.hand[i] = this.hand[minIndex];
            this.hand[minIndex] = temp;
        }
    }
    /**
    *This method return a string the rendering of this hand and also note how individual cards are rendered. 
    * A for loop is performed to return string if the hand is not null.
     * @return 
     */
    public String handToString() {
        String returnString = "";
        for (int i=0; i < 7; i++) {
            returnString += this.hand[i].cardToString();
            if (i<6 && !hand[i].isNullCard()) {
                if(!hand[i + 1].isNullCard()){
                    returnString += ", ";
                }
            }
        }
        return returnString;
    }
/*
    *This method gets the card from UNOCard class to return the index of the card.
    */
    public UNOCard getCardAtIndex(int index){
        UNOCard returnvalue = new UNOCard();
        if (index < 7) returnvalue = this.hand[index];
        return returnvalue;
    }
}
