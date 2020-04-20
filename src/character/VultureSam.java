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
public class VultureSam extends BangCharacter{
    private static final String NAME = "VULTURE SAM";
    private static final String SPECIAL = "Each time another player is eliminated, you gain\n" +
                                          "two life points.";
    
    /**
     * Constructor for Vulture Sam calls the super constructor from BangCharacter Class
     */
    public VultureSam(){
        super(9);
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
     * This method checks to see if the character gains two life points each time another player is eliminated
     * @return  overrides to true
     */
    @Override
    public boolean canGainLifeForDeadPlayers(){
        return true;
    }
}
