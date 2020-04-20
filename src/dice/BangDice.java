/*
 * TITLE: Dice Class for Project 3 Bang The Dice Game
 * AUTHOR: Stephen C. Devaney
 * FOR: CS 2365 Object Oriented Programming Section 001 Spring 2020
 */
package dice;
import java.util.Random;

/**
 *
 * @author Stephen C. Devaney
 */
public class BangDice {
    private final BangDie dice[]; // an array of Dice to hold all of the dice
    private final Random rand;
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

    public int getNumberOfDice(){
        return dice.length - 1;
    }
    
    /**
     * DESCRIPTION: This method rolls a die at the index given in a dice array
     * test case 1: randomize the die at the given index
     * test case 2: index is improper nothing happens
     * @param index     index at which the card is to be inserted
     */
    public void rollDieAtIndex(int index) {
        if(0 <= index && index < dice.length) {this.dice[index].setDie(rand.nextInt(6)+1);}
        else if(0 - dice.length <= index && index <=-1) {this.dice[dice.length - (0 - index)].setDie(rand.nextInt(6)+1);}
    }
    
    
    /**
     * DESCRIPTION: This method rolls a die at the index given in a dice array
     * test case 1: randomize all of the dice in a dice array
     */
    public void rollDice() {
        for(int i = 0; i < dice.length; i++){
            this.dice[i].setDie(rand.nextInt(6)+1);
        }
    }
    
    /**
    * DESCRIPTION: This method returns a string the rendering of this dice and also note how individual dice are rendered. A for loop is performed to return string if the die.
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
    
    
    /** DESCRIPTION: This method gets the value of the symbol at a specified index if the value is outside the range of the dice then 0 will be return if the value 
     *               if the index is negative will do reverse array indexing.
     * @param index: int
     * @return returnvalue: int
     */
    public int getDieAtIndex(int index){
        int returnvalue = 0;
        if (0 <= index && index < dice.length) {returnvalue = dice[index].getDieSymbol();}
        else if (0 - dice.length <= index && index <=-1) {returnvalue = dice[dice.length - (0 - index)].getDieSymbol();}
        return returnvalue;
    }
    
    public boolean getRerollableAtIndex(int index){
        boolean returnvalue = true;
        if (0 <= index && index < dice.length) {returnvalue = dice[index].isRerollable();}
        else if (0 - dice.length <= index && index <=-1) {returnvalue = dice[dice.length - (0 - index)].isRerollable();}
        return returnvalue;
    }
    
    public void makeRerollableAtIndex(int index){
        if (0 <= index && index < dice.length) {this.dice[index].makeRerollable();}
        else if (0 - dice.length <= index && index <=-1) {dice[dice.length - (0 - index)].makeRerollable();}
    }
}