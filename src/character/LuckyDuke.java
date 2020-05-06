/*
 * TITLE: Project 3 Bang The Dice Game
 * AUTHOR: Stephen C. Devaney
 * FOR: CS 2365 Object Oriented Programming Section 001 Spring 2020
 */
package character;

/**
 * class for lucky duke
 * @author Stephen C. Devaney
 */
public class LuckyDuke extends BangCharacter{
    private static final String NAME = "LUCKY DUKE";
    private static final String SPECIAL = "You may make one extra re-roll. (You may roll the \n" +
                                          "dice a total of four times on your turn.)";
    private static final String IMAGEFILENAME = "Images/Lucky Duke.jpg";
    
    /**
     * Constructor for Lucky Die calls the super constructor from BangCharacter Class
     */
    public LuckyDuke(){
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
     * This method checks to see if the character may make one extra re-roll
     * @return  overrides to true
     */
    @Override
    public boolean canHaveExtraReroll(){
        return true;
    }
}
