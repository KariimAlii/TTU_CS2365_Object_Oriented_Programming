/*
 * TITLE: Project 3 Bang The Dice Game
 * AUTHOR: Stephen C. Devaney
 * FOR: CS 2365 Object Oriented Programming Section 001 Spring 2020
 */
package character;

/**
 * class for willy the kid
 * @author Stephen C. Devaney
 */
public class WillyTheKid extends BangCharacter{
    private static final String NAME = "WILLY THE KID";
    private static final String SPECIAL = "You only need to use the Gatling Gun. You can\n" +
                                          "activate the Gatling Gun only once per turn,\n" +
                                          "even if you roll more than two results.";
    private static final String IMAGEFILENAME = "/Images/Willy the Kid.jpg";
    
    /**
     * Constructor for Willy The Kid calls the super constructor from BangCharacter Class
     */
    public WillyTheKid(){
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
     * Method to get the image file name
     * @return
     */
    @Override
    public String getImageFileName(){
        return IMAGEFILENAME;
    }
    
    /**
     * This method checks to see if the character only needs to gatling guns to set off the gatling
     * @return  overrides to true
     */
    @Override
    public int getGatlingNeed(){
        return 2;
    }
}