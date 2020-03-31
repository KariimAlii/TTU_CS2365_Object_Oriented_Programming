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
public class CalamityJanet extends BangCharacter {
    private static final String NAME = "CALAMITY JANET";
    private static final String SPECIAL = "You can use Bull's Eye 1 as Bull's Eye 2 and \n"
                                        + "vice-versa.";
    
    /**
     * Constructor for Calamity Janet calls the super constructor from BangCharacter Class
     */
    public CalamityJanet(){
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
     * This Method checks to see if the character has a different range value for bull's eye 1
     * @return overrides to 2
     */
    @Override
    public int getB1Range(){ // Calmity Janet & Rose Doolan Only
        return 2;
    }
    
    /**
     * This Method checks to see if the character has a different range value for bull's eye 2
     * @return overrides to 1
     */
    @Override
    public int getB2Range(){ // Calmity Janet & Rose Doolan Only
        return 1;
    }
}
