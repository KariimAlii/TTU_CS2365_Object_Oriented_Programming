/*
 * TITLE: Dice Class for Project 3 Bang The Dice Game
 * AUTHOR: Stephen C. Devaney
 * FOR: CS 2365 Object Oriented Programming Section 001 Spring 2020
 */
package bangthedicegame;
/**
 *
 * @author Stephen C. Devaney
 */
public class BangDice {
    private final BangDie dice[]; // an array of Dice to hold all of the dice
    /** BangDice Constructor
     * Declare a BangDice object to be used by the Game class.
     */
    public BangDice() {
        dice = new BangDie[5];
        for(int i = 0; i < 5; i++){
            dice[i] = new BangDie();
        }

    }

    
    /** rollDieAtIndex Method
     * DESCRIPTION: This method rolls a die at the index given in a dice array
     * test case 1: randomize the die at the given index
     * test case 2: index is improper nothing happens
     * @param index     index at which the card is to be inserted
     */
    public void rollDieAtIndex(int index) {
        if(0<= index && index < 5) {this.dice[index].rollDie();}
        else if(index == -1) {this.dice[4].rollDie();}
    }
    
    
    /** rollDice Method
     * DESCRIPTION: This method rolls a die at the index given in a dice array
     * test case 1: randomize all of the dice in a dice array
     */
    public void rollDice() {
        for(int i = 0; i < 5; i++){
            this.dice[i].rollDie();
        }
    }
    
    
    /** sortDice Method
     * DESCRIPTION: To sort the dice calling from the BangDie class. The use of 
     * selection sort is performed to sort the dice. Since the total number of 
     * dice is 5, the time complexity does not make a huge difference to look 
     * for other better sorting algorithm
     * test case 1: sorts the dice by symbol
     */
    public void sortDice() {
        for(int i = 0; i < 4; i++){
            int minIndex = i;
            int j = i+1;
            for(; j < 5; j++){
                if(this.dice[j].compareDie(this.dice[minIndex]) < 0){
                    minIndex = j;
                }
            }
            BangDie temp = new BangDie();
            temp.copyDie(this.dice[i]);
            this.dice[i].copyDie(this.dice[minIndex]);
            this.dice[minIndex].copyDie(temp);
        }
    }
    
    
    /** diceToString Method 
    * A for loop is performed to return string if the dice is not null.
    * DESCRIPTION: This method return a string the rendering of this dice and also note how individual dice are rendered.
    * test case 1: outputs the dice to string
    * @return the dice as a string
    */
    public String diceToString() {
        String returnString = "";
        for (int i = 0; i < 5; i++) {
            returnString += this.dice[i].dieToString();
            if (i < 4) {
                returnString += ", ";
            }
        }
        return returnString;
    }
}
