/*
 * TITLE: Project 3 Bang The Dice Game
 * AUTHOR: Anamol Acharya
 * FOR: CS 2365 Object Oriented Programming Section 001 Spring 2020
 */
package character;


public class GregDigger extends BangCharacter{
    
    private static final String NAME = "GregDigger";
    private static final String SPECIAL = "You may use each  whisket bottle to roll twice";
    // Todo
    private static final String IMAGEFILENAME = "   ";

    
    /**
     * Constructor for GregDigger calls the super constructor from BangCharacter Class
     */
    public GregDigger(){
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
    
    @Override
    public String getImageFileName(){
        return IMAGEFILENAME;
    }
    
    
}
