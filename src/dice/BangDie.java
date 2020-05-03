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
    public final static int ARROW = 1, DYNAMITE = 2, BULLSEYE1 = 3, BULLSEYE2 = 4, BEER = 5, GATLING = 6, BULLET = 7, RETURN_ARROW =8; // constants for the die symbols
    private int symbol; // symbol of the die
    private boolean processedState;
    private boolean rerollable;
    private boolean requireschooseableaction;
    
    
    /** BangDie Base Constructor
     * test case 1: always sets symbol to 1 and processedState to false
     */
    public BangDie(){
        this.symbol = ARROW;
        this.processedState = false; 
        this.rerollable = true;
        this.requireschooseableaction = false;
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
    
    /**Anamol Acharya 
     * added a method dietoStringOldSaloon coward dice for the expansion 1
     * dieToString method
     * new Dice faces- Return arrow and Bullet
     * DESCRIPTION: the method takes a die and outputs the proper wording for the die in a string to the expansion for oldsaloon
     * test case 1: returns the proper string for the die
     * @return String
     */
     public String dieToStringOldSaloonCoward(){
        String returnString = "";
        switch (this.symbol) {
            case RETURN_ARROW:
                returnString += "Return Arrow";
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
                returnString += "Double Beer";
                break;
            case GATLING:
                returnString += "Gatling";
                break;
            default:
                break;
        }
        return returnString;
    }
    
    
    /**Anamol Acharya 
     * added a method dietoStringOldSaloon loudmouth dice for the expansion 1
     * dieToString method
     * new Dice faces- Return arrow and Bullet
     * DESCRIPTION: the method takes a die and outputs the proper wording for the die in a string to the expansion for oldsaloon
     * test case 1: returns the proper string for the die
     * @return String
     */
     public String dieToStringOldSaloonLoudMouth(){
        String returnString = "";
        switch (this.symbol) {
            case BULLET:
                returnString += "Bullet";
                break;
            case DYNAMITE:
                returnString += "Dynamite";
                break;
            case BULLSEYE1:
                returnString += "Double Bull's Eye 1";
                break;
            case BULLSEYE2:
                returnString += "Double Bull's Eye 2";
                break;
            case BEER:
                returnString += "Beer";
                break;
            case GATLING:
                returnString += " Double Gatling";
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
    
    public void makeRerollable(){
        this.rerollable = true;
    }
}