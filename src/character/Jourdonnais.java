/*
 * TITLE: Project 3 Bang The Dice Game
 * AUTHOR: Stephen C. Devaney
 * FOR: CS 2365 Object Oriented Programming Section 001 Spring 2020
 */
package character;

/**
 * class for jourdonnais
 * @author Stephen C. Devaney
 */
public class Jourdonnais extends BangCharacter{
    private static final String NAME = "JOURDONNAIS";
    private static final String SPECIAL = "You never lose more than one life point to Indians.";
    private static final String IMAGEFILENAME = "/Images/Jourdonnais.jpg";
    
    /**
     * Constructor for Jourdonnais calls the super constructor from BangCharacter Class
     */
    public Jourdonnais(){
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
     * Method to get the image file name
     * @return
     */
    @Override
    public String getImageFileName(){
        return IMAGEFILENAME;
    }
    
    /**
     * This method checks to see if the character never lose more than one life point to Indians.
     * @return  overrides to true
     */
    @Override
    public boolean canReducedIndianAttack(){
        return true;
    }
}
