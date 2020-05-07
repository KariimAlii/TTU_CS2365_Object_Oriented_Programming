/*
 * TITLE: Project 3 Bang The Dice Game
 * AUTHOR: Anamol Acharya
 * COLLABORATOR: Shree Shrestha
 * FOR: CS 2365 Object Oriented Programming Section 001 Spring 2020
 */
package dice;

/**
 *  DESCRIPTION: Die class for the duel die
 */
public class DuelDie extends BangDie {
    public final static int FIGHTADUEL = 3, WHISKEYBOTTLE = 5; // constants for the die symbols
    
    /**
     * constructor for a duel die
     */
    public DuelDie(){
        super();
        this.type = DieType.DUEL;
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
            case FIGHTADUEL:   //In this case I assume each dice has two fight a duel face
                returnString += "Fight a Duel";
                break;
            case WHISKEYBOTTLE:
                returnString += "Whiskey Bottle";
                break;
            case GATLING:
                returnString += "Gatling";
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
            
            if (symbol == BULLSEYE2){this.symbol = FIGHTADUEL;}
            else {this.symbol = symbol;}
            
            if (this.symbol == DYNAMITE){
                this.rerollable = false;
                this.requireschooseableaction = false;
            }
            else if(this.symbol == FIGHTADUEL){
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
