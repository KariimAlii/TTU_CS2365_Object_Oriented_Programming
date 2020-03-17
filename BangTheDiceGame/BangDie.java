/*
 * TITLE: Die Class for Project 3 Bang The Dice Game
 * AUTHOR: Stephen C. Devaney
 * FOR: CS 2365 Object Oriented Programming Section 001 Spring 2020
 */
package bangthedicegame;

/**
 *
 * @author Stephen C. Devaney
 */
public class BangDie {
    public final static int ARROW = 1, DYNAMITE = 2, BULLSEYE1 = 3, BULLSEYE2 = 4, BEER = 5, GATLING = 6; // constants for the die symbols
    private int symbol; // symbol of the die
    private boolean processedState;
    
    
    /** BangDie Base Constructor
     * test case 1: always sets symbol to -1 and processedState to true
     */
    public BangDie(){
        this.symbol = -1;
        this.processedState = true;
    }
    
    
    
    /** isNullDie method
     * DESCRIPTION: checks to see if the die is NULL
     * test case 1: die is group designated null returns true
     * test case 2: die is not group designated null returns false
     * @return boolean
     */
    public boolean isNullDie(){
        return(this.symbol == -1);
    }
    
    
    /** dieToString method
     * DESCRIPTION: the method takes a die and outputs the proper wording for the die in a string
     * test case 1: non null die returns the proper string
     * test case 2: null die returns a null string
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
        if(!this.isNullDie()){
            if (this.isEqual(die)) {result = 0;}
            else if (this.symbol < die.symbol) {result = -1;}
            else{result = 1;}
        }
        else return 1;
        return result;
    }
    
    
    /** isEqual method
     * DESCRIPTION: compares this die with the second die if this die is equal to the second die true is returned otherwise false is returned
     * test case 2: first die equals second die returns true
     * test case 3: first die does not equal the second die returns false
     * @param die: BangDie
     * @return boolean
     */
    public boolean isEqual(BangDie die){
        return(this.symbol == die.symbol);
    }
    
    
    /** getDieSymbol method
     * DESCRIPTION: returns the color value of the die
     * test case 1: non null die returns the value of the color
     * test case 2: null die returns -1
     * @return int
     */
    public int getDieSymbol(){
        return this.symbol;
    }
    
    
    /** getDieState method
     * DESCRIPTION: returns the state of the die
     * test case 1: non null die returns the value of the state
     * test case 2: null die returns true
     * @return boolean
     */
    public boolean getDieState(){
        return this.processedState;
    }
    
    
    /** setDieState method
     * DESCRIPTION: returns the color state of the die
     * test case 1: sets the value of the state
     * @param state state to be changed to
     */
    public void setDieState(boolean state){
        this.processedState = state;
    }
    

    /** setDieSymbol method
     * DESCRIPTION: returns the color state of the die
     * test case 1: sets the value of the state if have a proper value
     * test case 2: other wise set to a null dice
     * @param value value to be changed to
     */
    public void setDieSymbol(int value){
        if ((ARROW <= value && value <= GATLING)){
            this.symbol = value;
            this.processedState = false;
        }
        else{
            this.symbol = -1;
            this.processedState = true;
        }
    }
    
    
    /** copyDie method
     * DESCRIPTION: copies a dice value onto another die best use in the a dice class.
     * test case 1: non null die is copied exactly
     * test case 2: null die is copied exactly
     * @param die is the die to be copied from
     */
    public void copyDie(BangDie die){
        this.symbol = die.symbol;
        this.processedState = die.processedState;
    }
}
