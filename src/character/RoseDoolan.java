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
public class RoseDoolan extends BangCharacter{
    private static final String NAME = "ROSE DOOLAN";
    private static final String SPECIAL = "You may use a bull's eye 1 or a bull's eye 2 for players\n" +
                                          "sitting one place further. (With bull's eye 1 you may hit\n" +
                                          "a player sitting up to two places away, and with a bull's\n" +
                                          "eye 2 you may hit a playersitting two or three places away).";
    
    /**
     * Constructor for Rose Doolan calls the super constructor from BangCharacter Class
     */
    public RoseDoolan(){
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
     * This Method checks to see if the character has a different range value for bull's eye 1
     * @return overrides to 2
     */
    @Override
    public int getB1Range(){ // Calmity Janet & Rose Doolan Only
        return 2;
    }
    
    /**
     * This Method checks to see if the character has a different range value for bull's eye 1
     * @return overrides to 3
     */
    @Override
    public int getB2Range(){ // Calmity Janet & Rose Doolan Only
        return 3;
    }
}
