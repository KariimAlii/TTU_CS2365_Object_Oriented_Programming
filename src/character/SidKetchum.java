/*
 * TITLE: Project 3 Bang The Dice Game
 * AUTHOR: Stephen C. Devaney
 * FOR: CS 2365 Object Oriented Programming Section 001 Spring 2020
 */
package character;

/**
 * class to for sidketchum
 * @author Stephen C. Devaney
 */
public class SidKetchum extends BangCharacter{
    private static final String NAME = "SID KETCHUM";
    private static final String SPECIAL = "At the beginning of your turn, any player of your\n" +
                                          "choice gains one life point. (You may also choose yourself.)";
    private static final String IMAGEFILENAME = "/Images/Sid Ketchum.jpg";
    
    /**
     * Constructor for Sid Ketchum calls the super constructor from BangCharacter Class
     */
    public SidKetchum(){
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
     * This method checks to see if the character can give any player of their choice a life point
     * @return  overrides to true
     */
    @Override
    public boolean canGiveAnyPlayerLife(){
        return true;
    }
}
