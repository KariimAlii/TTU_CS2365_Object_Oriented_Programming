/*
 * TITLE: Project 3 Bang The Dice Game
 * AUTHOR: Stephen C. Devaney
 * FOR: CS 2365 Object Oriented Programming Section 001 Spring 2020
 */
package character;

/**
 * class for paul regret
 * @author Stephen C. Devaney
 */
public class PaulRegret extends BangCharacter{
    private static final String NAME = "PAUL REGRET";
    private static final String SPECIAL = "You never lose life points to the Gatling Gun.";
    private static final String IMAGEFILENAME = "/Images/Paul Regret.jpg";

    /**
     * Constructor for Paul Regret calls the super constructor from BangCharacter Class
     */
    public PaulRegret(){
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
     * Method to get the image file name
     * @return
     */
    @Override
    public String getImageFileName(){
        return IMAGEFILENAME;
    }
    
    /**
     * This method checks to see if the character never lose life points to the Gatling Gun.
     * @return  overrides to true
     */
    @Override
    public boolean canHaveGatlingDamageImmunity(){
        return true;
    }
}
