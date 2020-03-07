/*
 * TITLE: Hand Class for Project 2 UNO Workout
 * AUTHOR: Anamol Acharya
 * FOR: CS 2365 Object Oriented Programming Section 001 Spring 2020
 * CLASS DESCRIPTION: class is used to hold an hand of UNO cards for an UNO game
                      or workout.
 * TEST CASES: test cases for this class are held in the workoutclassclient class
 */
package unoworkout;

/**
 *  Hand of UNOCards, held by a particular player. A Hand object is
 * responsible for playing a Card-choosing a card to play-
 * when the player's turn comes up.
 * To do this, it implements the
 * strategy pattern by which this choice can be delegated to an arbitrary
 * implementer of the UnoPlayer class.
 * @author Anamol Acharya
 */
public class UNOHand {

    private UNOCard hand[]; // an array of UNOCard to hold a hand of Uno Cards

    /** UNOHand Constructor
     * Declare a UNOHand object to be played by the UnoPlayer class, and
     * the player name, passed as arguments. This implements a strategy
     * pattern whereby the constructor accepts various strategies that
     * implement the UnoPlayer interface. Creating an array of 7 cards from the 
     * UNOCard using a for loop.
     */
    public UNOHand() {
        hand = new UNOCard[7];
        for(int i = 0; i < 7; i++){
            hand[i] = new UNOCard();
        }

    }

    
    /** insertCard Method
     * DESCRIPTION: This method add a card to the hand at the index given
     * test case 1: inserts a card into the hand at the given index
     * test case 2: index is improper nothing happens
     * @param index     index at which the card is to be inserted
     * @param card      card to be inserted in to the hand
     */
    public void insertCard(int index, UNOCard card) {
        if(0<= index && index < 7) {this.hand[index].copyCard(card);}
        else if(index == -1) {this.hand[6].copyCard(card);}
    }

    
     /** removeCard Method
     * DESCRIPTION: This method removes a card in the sorted hands. 
     * test case 1: removes a card into the hand at the given index
     * test case 2: index is improper nothing happens
     * @param index at which the card is to be removed
     * @return UNOCard that was removed
     */
    public UNOCard removeCard(int index){
        UNOCard temp = new UNOCard();
        UNOCard temp2 = new UNOCard();
        if(0<= index && index < 7){
            temp.copyCard(this.hand[index]);
            for(int i = index; i < 7; i++){
                if(i < 6) {
                    this.hand[i] = this.hand[i+1];
                    if(this.hand[i+1].isNullCard()) break;
                }
                else {this.hand[i].copyCard(temp2);}
            }
        }
        else if (index == -1){
            temp.copyCard(this.hand[6]);
            this.hand[6].copyCard(temp2);
        }
        return temp;
    }
   
    
    /** discardHand Method
     * DESCRIPTION: This method loops through the hand discarding all of the 
     * cards in the hand.
     * test case 1: always discards a hand
     */
    public void discardHand(){
        UNOCard temp = new UNOCard();
        for(int i = 0; i < 7; i++)
        {
            this.hand[i].copyCard(temp);
        }
    }
    
    
    /** isHandEmpty Method
     * DESCRIPTION: This method returns true only if this Hand has no cards, 
     * which should trigger a winning condition. For loop is performed to check 
     * if hand is empty of not.
     * test case 1: hand is empty return true
     * test case 2: hand is not empty return false
     * @return boolean value to let program know if hand is empty
     */
    public boolean isHandEmpty() {
        boolean returnvalue = true;
        for(int i = 0; i < 7; i++){
            if(!this.hand[i].isNullCard()) returnvalue = false;
        }
        return returnvalue;
    }
    

    /** isHandFull Method
     * DISCRIPTION: This boolean method checks if the hand is full or not. 
     * It performs a for loop to check if the  hand is null just to check is 
     * hand is full.
     * test case 1: hand is full returns true
     * test case 2: hand is not full returns false
     * @return boolean value to let program know if hand is full
     */
    public boolean isHandFull(){
      boolean returnvalue = true;
        for(int i = 0; i < 7; i++){
            if(this.hand[i].isNullCard()) returnvalue = false;
        }
        return returnvalue;
    }
    
    
    /** sortHand Method
     * DESCRIPTION: To sort the hand calling from the UNOCard class. The use of 
     * selection sort is performed to sort the cards. Since the total number of 
     * cards is 7, the time complexity does not make a huge difference to look 
     * for other better sorting algorithm
     * test case 1: sorts the hand by color then by number in ascending order
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
    
    
    /** handToString Method 
    * A for loop is performed to return string if the hand is not null.
    * DESCRIPTION: This method return a string the rendering of this hand and also note how individual cards are rendered.
    * test case 1: outputs the hand to string
    * @return the hand as a string
    */
    public String handToString() {
        String returnString = "";
        for (int i=0; i < 7; i++) {
            returnString += this.hand[i].cardToString();
            if (i<6 && !this.hand[i].isNullCard()) {
                if(!this.hand[i + 1].isNullCard()){
                    returnString += ", ";
                }
            }
        }
        return returnString;
    }


    /** getCardAtIndex Method
     * DISCRIPTION: This method gets the card from UNOCard class to return the index of the card.
     * test case 1: good index returns the card at the given index
     * test case 2: bad index returns a null card
     * @param index of the card to be removed
     * @return the object at the given index
     */
    public UNOCard getCardAtIndex(int index){
        UNOCard returnvalue = new UNOCard();
        if (0 <= index ||index < 7) {returnvalue.copyCard(this.hand[index]);}
        else if (index == -1) returnvalue.copyCard(this.hand[6]);
        return returnvalue;
    }
}
