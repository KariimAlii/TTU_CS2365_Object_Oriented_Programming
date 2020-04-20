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
public class BartCassidy extends BangCharacter {
    private static final String NAME = "BART CASSIDY";
    private static final String SPECIAL = "You may take an arrow instead of losing a life\n" +
                                          "point (except to Indians or Dynamite). You cannot\n" +
                                          "use this ability if you lose a life point to Indian\n" +
                                          "or Dynamite, only for Bull's Eye 1, Bull's Eye 2,\n" +
                                          "or Gatling Guns. You may not use this ability to take\n" +
                                          "the last arrow remaining in the pile.";
    
    /**
     * Constructor for Bart Cassidy calls the super constructor from BangCharacter Class
     */
    public BartCassidy(){
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
     * This Method checks to see if the character may take an arrow instead of losing a life point
     * @return overrides to true
     */
    @Override
    public boolean canTakeArrowForDamage(){
        return true;
    }
}