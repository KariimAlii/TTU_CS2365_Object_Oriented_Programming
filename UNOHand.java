/*
 * TITLE: Deck Class for Project 2 UNO Workout
 * AUTHOR: Anamol Acharya
 * FOR: CS 2365 Object Oriented Programming Section 001 Spring 2020
 */
package unoworkout;

/**
 *  Hand of UNOCards, held by a particular player. A Hand object is
 * responsible for playing a Card-choosing a card to play-
 * when the player's turn comes up.
 * To do this, it implements the
 * strategy pattern by which this choice can be delegated to an arbitrary
 * implementer of the UnoPlayer class.
 */
public class UNOHand {

    private UNOCard hand[];
   // private lastcardindex 

    /**
     * Declare a UNOHand object to be played by the UnoPlayer class, and
     * the player name, passed as arguments. This implements a strategy
     * pattern whereby the constructor accepts various strategies that
     * implement the UnoPlayer interface.
     */
    public UNOHand() {
        hand = new UNOCard[7];
        for(int i = 0; i < 7; i++){
            hand[i] = new UNOCard();
        }

    }

    /**
     * Add a card to the hand.
     */
    void insertCard(int index, UNOCard card) {
        this.hand[index] = card;
    }

     /**
     * Remove a card to the hand.
     */
    void removeCard(int index){
        for(int i = index; i < 7; i++){
            if(i < 6) {
                this.hand[i] = this.hand[i+1];
                if(this.hand[i+1].isNullCard()) break;
            }
            else this.hand[i].setCardNull();
        }
        
    }
   
    void discardHand(int index){
        for(int i = 0; i < 7; i++)
        {
            this.hand[i].setCardNull();
        }
    }
    /**
     * Return true only if this Hand has no cards, which should trigger a
     * winning condition.
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
    Boolean to check if hand if full or not
    */
    public boolean isHandFull(){
      boolean returnvalue = true;
        for(int i = 0; i < 7; i++){
            if(this.hand[i].isNullCard()) returnvalue = false;
        }
        return returnvalue;
    }
    
//     /**
//     * Return the number of cards in the hand.
//     * @return 
//     */
//    public int size() {
//        return cards.size();
//    }

    
    /**
   To sort the hand calling from the UNOCard class
     */
    void sortHand() {
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
     * Return a string rendering of this Hand. See Card::toString() for
     * notes about how individual cards are rendered.
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

    public UNOCard getCardAtIndex(int index){
        UNOCard returnvalue = new UNOCard();
        if (index < 7) returnvalue = this.hand[index];
        return returnvalue;
    }
}
