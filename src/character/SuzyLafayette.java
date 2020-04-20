/*
 * TITLE: Project 3 Bang The Dice Game
 * AUTHOR: Stephen C. Devaney
 * FOR: CS 2365 Object Oriented Programming Section 001 Spring 2020
 */
package character;

/**
 *
 * @author Stephen C. Devaney
 */
public class SuzyLafayette extends BangCharacter{
    private static final String NAME = "SUZY LAFAYETTE";
    private static final String SPECIAL = "If you didn’t roll bull's eye 1s any or bull's eye 2s,\n" +
                                          "you gain two life points. This only applies at the end of\n" +
                                          "your turn, not during your re-rolls.";
    
    /**
     * Constructor for Suzy Lafayette calls the super constructor from BangCharacter Class
     */
    public SuzyLafayette(){
        super(8);
    }
    
    /**
     * Method to get the name of the character.
     * @return
     */
    @Override
    public String getName(){
        return NAME;
    }
    
    /**
     * Method to get the character's special ability in the form of a string so that
     * it may be displayed
     * @return
     */
    @Override
    public String getSpecial(){
        return SPECIAL;
    }
    
    /**
     * This method checks to see if the character can gain life at the end of there turn if they didn't roll any bull's eye 1 or bull's eye 2
     * @return  overrides to true
     */
    @Override
    public boolean canGainLifeAtEnd(){
        return true;
    }
}
