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
public class BillNoface extends BangCharacter{
    
    private static final String NAME = "BILL NOFACE";
    private static final String SPECIAL = "Apply arrow results only after your last roll.\n" +
                                          "(Your last roll isn’t necessarily the third one,\n" +
                                          "you may stop earlier, as normal).";
    private static final String IMAGEFILENAME = "/Images/BillNoface.jpg";

    
    /**
     * Constructor for BelleStar calls the super constructor from BangCharacter Class
     */
    public BillNoface(){
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
     * This method checks to see if the character bill noface's special ability
     * @return
     */
    @Override
    public boolean canApplyArrowResultsAfterRerolls(){
        return true;
    }
}
