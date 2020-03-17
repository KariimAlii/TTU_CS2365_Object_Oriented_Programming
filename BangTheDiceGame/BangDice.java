/*
 * TITLE: Dice Class for Project 3 Bang The Dice Game
 * AUTHOR: Stephen C. Devaney
 * FOR: CS 2365 Object Oriented Programming Section 001 Spring 2020
 */
package bangthedicegame;

import java.util.Random;

/**
 *
 * @author Stephen C. Devaney
 */
public class BangDice {
    private final BangDie dice[]; // an array of Dice to hold all of the dice
    private Random rand;
    /** BangDice Constructor
     * Declare a BangDice object to be used by the Game class.
     */
    public BangDice() {
        rand = new Random(System.currentTimeMillis());
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
        if(0<= index && index < 5) {this.dice[index].setDie(rand.nextInt(6)+1);}
        else if(index == -1) {this.dice[4].setDie(rand.nextInt(6)+1);}
    }
    
    
    /** rollDice Method
     * DESCRIPTION: This method rolls a die at the index given in a dice array
     * test case 1: randomize all of the dice in a dice array
     */
    public void rollDice() {
        for(int i = 0; i < 5; i++){
            this.dice[i].setDie(rand.nextInt(6)+1);
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
