/*
 * TITLE: Project 3 Bang The Dice Game
 * AUTHOR: Anamol Acharya
 * COLLABORATOR: Shree Shrestha
 * FOR: CS 2365 Object Oriented Programming Section 001 Spring 2020
 */
package dice;

/**
 *  DESCRIPTION: Die class for the coward die
 * @author Anamol Acharya
 */
public class CowardDie extends BangDie {
    public final static int BROKENARROW = 4, DOUBLEBEER = 6; // constants for the die symbols
    
    /**
     * constructor for coward die
     */
    public CowardDie(){
        super();
        this.type = DieType.COWARD;
    }
    
    /** dieToString method
     * DESCRIPTION: the method takes a die and outputs the proper wording for the die in a string
     * test case 1: returns the proper string for the die
     * @return String
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
                returnString += "Bull's Eye 1";
                break;
            case BROKENARROW:
                returnString += "Broken Arrow";
                break;
            case BEER:
                returnString += "Beer";
                break;
            case DOUBLEBEER:
                returnString += "Double Beer";
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
        if (ARROW <= symbol && symbol <= DOUBLEBEER){
            this.symbol = symbol;
            if (symbol == DYNAMITE){
                this.rerollable = false;
                this.requireschooseableaction = false;
            }
            else if(symbol == BULLSEYE1 || symbol == DOUBLEBEER || symbol == BEER){
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