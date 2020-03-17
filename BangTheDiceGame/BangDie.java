/*
 * TITLE: Die Class for Project 3 Bang The Dice Game
 * AUTHOR: Stephen C. Devaney
 * FOR: CS 2365 Object Oriented Programming Section 001 Spring 2020
 */
package bangthedicegame;
import java.util.Random;
/**
 *
 * @author Stephen C. Devaney
 */
public class BangDie {
    public final static int ARROW = 1, DYNAMITE = 2, BULLSEYE1 = 3, BULLSEYE2 = 4, BEER = 5, GATLING = 6; // constants for the die symbols
    private int symbol; // symbol of the die
    private boolean processedState;
    
    
    /** BangDie Base Constructor
     * test case 1: always sets symbol to 1 and processedState to false
     */
    public BangDie(){
        this.symbol = ARROW;
        this.processedState = false;
    }
    
    
    /** dieToString method
     * DESCRIPTION: the method takes a die and outputs the proper wording for the die in a string
     * test case 1: returns the proper string for the die
     * @return String
     */
    public String dieToString(){
        String returnString = "";
        switch (this.symbol) {
            case ARROW:
                returnString += "Arrow";
                break;
            case DYNAMITE:
                returnString += "Dynamite";
                break;
            case BULLSEYE1:
                returnString += "Bull's Eye 1";
                break;
            case BULLSEYE2:
                returnString += "Bull's Eye 2";
                break;
            case BEER:
                returnString += "Beer";
                break;
            case GATLING:
                returnString += "Gatling";
                break;
            default:
                break;
        }
        return returnString;
    }
    
    
    /** compareDie method
     * DESCRIPTION: compares this die with the second die 
     *                  if this die goes before the second die -1 is returned
     *                  if this die goes after the second die 1 is returned
     *                  if this die is equal to the second die 0 is returned
     * test case 1: first die goes before second die returns -1
     * test case 2: first die equals second die returns 0
     * test case 3: first die goes after second die returns 1
     * @param die: BangDie
     * @return int
     */
    public int compareDie(BangDie die){
        int result;
        if (this.isEqual(die)) {result = 0;}
        else if (this.symbol < die.symbol) {result = -1;}
        else{result = 1;}
        return result;
    }
    
    
    /** isEqual method
     * DESCRIPTION: compares this die with the second die if this die is equal to the second die true is returned otherwise false is returned
     * test case 1: first die equals second die returns true
     * test case 2: first die does not equal the second die returns false
     * @param die: BangDie
     * @return boolean
     */
    public boolean isEqual(BangDie die){
        return(this.symbol == die.symbol);
    }
    
    
    /** getDieSymbol method
     * DESCRIPTION: get the symbol value of the die
     * test case 1: returns the value of the symbol of the die
     * @return int
     */
    public int getDieSymbol(){
        return this.symbol;
    }
    
    
    /** getDieState method
     * DESCRIPTION: get the state of the die
     * test case 1: returns the value of the state of the die
     * @return boolean
     */
    public boolean getDieState(){
        return this.processedState;
    }
    
    
    /** setDieState method
     * DESCRIPTION: process the die
     * test case 1: sets the value of the state to true
     */
    public void processDie(){
        this.processedState = true;
    }
    

    /** setDieSymbol method
     * DESCRIPTION: rolls the die to a random symbol
     * test case 1: sets the value of the state to false and the symbol to a proper symbol
     */
    public void rollDie(){
        Random rand = new Random(System.currentTimeMillis());
        BangDie newdie = new BangDie();
        int random = rand.nextInt(GATLING - ARROW + 1) + ARROW;
        this.symbol = random;
        this.processedState = false;
    }
    
    
    /** copyDie method
     * DESCRIPTION: copies a dice value onto another die best use in the a dice class.
     * test case 1: die is copied exactly
     * @param die is the die to be copied from
     */
    public void copyDie(BangDie die){
        this.symbol = die.symbol;
        this.processedState = die.processedState;
    }
}
