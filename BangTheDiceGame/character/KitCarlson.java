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
public class KitCarlson extends BangCharacter{
    private static final String NAME = "KIT CARLSON";
    private static final String SPECIAL = "For each gatling die you roll you may discard one arrow from\n" +
                                          "any player. You may choose to discard your own arrows. If you\n" +
                                          "roll three gatlings, you discard all your own arrows, plus three\n" +
                                          "from any player(s) (of course, you still deal one damage to each\n" +
                                          "other player).";
    
    /**
     * Constructor for Kit Carlson calls the super constructor from BangCharacter Class
     */
    public KitCarlson(){
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
     * This method checks to see if the character may discard one arrow from any player for each gatling gun that is rolled
     * @return  overrides to true
     */
    @Override
    public boolean canDiscardArrowForGattling(){
        return true;
    }
}
