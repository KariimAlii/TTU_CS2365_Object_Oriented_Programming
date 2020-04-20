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
public class ElGringo extends BangCharacter{
    private static final String NAME = "EL GRINGO";
    private static final String SPECIAL = "When a player makes you lose one or more life\n" +
                                          "points, he must take an arrow. Life points lost\n" +
                                          "to Indians or Dynamite are not affected.";
    
    /**
     * Constructor for El Gringo calls the super constructor from BangCharacter Class
     */
    public ElGringo(){
        super(7);
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
     * This Method checks to see if the character makes a player take an arrow when he loses one or more life points
     * @return overrides to true
     */
    @Override
    public boolean canShooterTakeArrow(){
        return true;
    }
}
