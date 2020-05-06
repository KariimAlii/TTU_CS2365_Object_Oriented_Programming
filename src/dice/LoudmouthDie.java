/*
 * TITLE: Project 3 Bang The Dice Game
 * AUTHOR: Anamol Acharya
 * COLLABORATOR: Shree Shrestha
 * FOR: CS 2365 Object Oriented Programming Section 001 Spring 2020
 */
package dice;

/**
 * DESCRIPTION: Die class for the loudmouth die
 */
public class LoudmouthDie extends BangDie {
    public final static int BULLET = 5; // constants for the die symbols
    
    /**
     * construtctor for loudmouth die
     */
    public LoudmouthDie(){
        super();
        this.type = DieType.LOUDMOUTH;
    }
    
    /** dieToString method
     * DESCRIPTION: the method takes a die and outputs the proper wording for the die in a string
     * test case 1: returns the proper string for the die
     * @return String
     * @author Anamol Acharya
     */
    @Override
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
                returnString += "Double Bull's Eye 1";
                break;
            case BULLSEYE2:
                returnString += "Double Bull's Eye 2";
                break;
            case BULLET:
                returnString += "Bullet";
                break;
            case GATLING:
                returnString += " Double Gatling";
                break;
            default:
                break;
        }
        return returnString;
    }
    
    
    /** setDieSymbol method
     * DESCRIPTION: sets symbol to the value given
     * test case 1: sets the value of the state to false and the symbol to a proper symbol
     * test case 2: improper value is given default value is arrow
     * @param symbol
     */
    @Override
    public void setDie(int symbol){
        if (ARROW <= symbol && symbol <= GATLING){
            this.symbol = symbol;
            if (symbol == DYNAMITE){
                this.rerollable = false;
                this.requireschooseableaction = false;
            }
            else if(symbol == BULLSEYE1 || symbol == BULLSEYE2){
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
}
