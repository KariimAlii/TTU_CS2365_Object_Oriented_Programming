/*
 * TITLE: 
 * AUTHOR: Stephen C. Devaney
 * FOR: CS 2365 Object Oriented Programming Section 001 Spring 2020
 */
package dice;

/**
 * @author Stephen C. Devaney
 */
public class DuelDie extends BangDie {
    public final static int ARROW = 1, DYNAMITE = 2, BULLSEYE1 = 3, BULLSEYE2 = 4, BEER = 5, GATLING = 6; // constants for the die symbols
    
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
            case WHISKEY_BOTTLE:
                returnString += "Whiskey Bottle";
                break;
            case FIGHT_A_DUEL:   //In this case I assume each dice has two fight a duel face
                returnString += "Fight a duel";
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
}
