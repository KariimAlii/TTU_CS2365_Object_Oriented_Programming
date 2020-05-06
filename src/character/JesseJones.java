/*
 * TITLE: Project 3 Bang The Dice Game
 * AUTHOR: Stephen C. Devaney
 * FOR: CS 2365 Object Oriented Programming Section 001 Spring 2020
 */
package character;

/**
 * class for jesse jones
 * @author Stephen C. Devaney
 */
public class JesseJones extends BangCharacter{
    private static final String NAME = "JESSE JONES";
    private static final String SPECIAL = "If you have four life points or less, you gain two if you\n" +
                                          "use a beer for yourself. For example, if you have four life\n" +
                                          "points and use two beers, you get four life points.";
    private static final String IMAGEFILENAME = "/Images/Jesse Jones.jpg";
    
    /**
     * Constructor for Jesse Jones calls the super constructor from BangCharacter Class
     */
    public JesseJones(){
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
     * This Method checks to see if the character gains double health if the character has four or less life points
     * @return overrides to true
     */
    @Override
    public boolean canLowLifeDoubleBeer(){
        return true;
    }
}
