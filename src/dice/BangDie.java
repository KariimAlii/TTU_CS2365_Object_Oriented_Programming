/*
 * TITLE: Die Class for Project 3 Bang The Dice Game
 * AUTHOR: Stephen C. Devaney
 * FOR: CS 2365 Object Oriented Programming Section 001 Spring 2020
 */
package dice;
/**
 *
 * @author Stephen C. Devaney
 */
public class BangDie {
    //Anamol Acharya- Added new dice face for Saloon Dice

    /**Constants to designate the sides for the die*/ public final static int ARROW = 1, DYNAMITE = 2, BULLSEYE1 = 3, BULLSEYE2 = 4, BEER = 5, GATLING = 6;
    /** value to hold which side/symbol of the die is showing */ protected int symbol;
    /** value to hold weather or not the die has been processed by the player */ protected boolean processedState;
    /** value to hold if the die is able to be rerolled*/ protected boolean rerollable;
    /** value to hold if the die is a beer or bulls eye 1 or two and requires the player to make a choice about the die*/ protected boolean requireschooseableaction;
    /** value to hold what kind of game die the dice is*/ protected DieType type;
    
    
    /** BangDie Base Constructor
     * test case 1: always sets symbol to 1 and processedState to false
     */
    public BangDie(){
        this.symbol = ARROW;
        this.processedState = false; 
        this.rerollable = true;
        this.requireschooseableaction = false;
        this.type = DieType.BASIC;
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
    
    
    
    /** isEqual method
     * DESCRIPTION: compares this die with the second die if this die is equal to the second die true is returned otherwise false is returned
     * test case 1: first die equals second die returns true
     * test case 2: first die does not equal the second die returns false
     * @param die: BangDie
     * @return boolean
     */
    public boolean equals(BangDie die){
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
    
    /**
     * checks if the die is rerollable
     * @return
     */
    public boolean isRerollable(){
        return this.rerollable;
    }
    
    /** setDieState method
     * DESCRIPTION: process the die
     * test case 1: sets the value of the state to true
     */
    public void processDie(){
        this.processedState = true;
    }
    
    /**
     * checks to see if a die requires a action to be chosen by the player
     * @return true of false boolean values if the die does require a action to be chosen by the player
     */
    public boolean doesRequireChooseableAction(){
        return this.requireschooseableaction;
    }
    

    /** setDieSymbol method
     * DESCRIPTION: sets symbol to the value given
     * test case 1: sets the value of the state to false and the symbol to a proper symbol
     * test case 2: improper value is given default value is arrow
     * @param symbol
     */
    public void setDie(int symbol){
        if (ARROW <= symbol && symbol <= GATLING){
            this.symbol = symbol;
            if (symbol == DYNAMITE){
                this.rerollable = false;
                this.requireschooseableaction = false;
            }
            else if(symbol == BULLSEYE1 || symbol == BULLSEYE2 || symbol == BEER){
                this.rerollable = true;
                this.requireschooseableaction = true;
            }
            else{
                this.rerollable = true;
                this.requireschooseableaction = false;
            }
        }
        else{
            this.symbol = ARROW;
            this.rerollable = true;
            this.requireschooseableaction = false;
        }
        this.processedState = false;
    }
    
    /**
     * makes the die rerollable used for special abilities that allow non rerollable dice to be rerolled
     */
    public void makeRerollable(){
        this.rerollable = true;
    }
    
    /**
     * give what kind of die it is
     * @return object DieType
     */
    public DieType getDieType(){
        return this.type;
    }
}
