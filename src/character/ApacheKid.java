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
public class ApacheKid extends BangCharacter {
    
    private static final String NAME = "APACHE KID";
    private static final String SPECIAL = "If you roll an arrow , you may take the Indian\n" +
                                          "Chief’s Arrow from another player";
    private static final String IMAGEFILENAME = "/Images/ApacheKid.jpg";

    
    /**
     * Constructor for BelleStar calls the super constructor from BangCharacter Class
     */
    public ApacheKid(){
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
     * This method checks to see if the character has apache kids special ability
     * @return
     */
    @Override
    public boolean canTakeChiefsArrowFromPlayerWhenRollArrow(){
        return true;
    }
}
