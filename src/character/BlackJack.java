/*
 * TITLE: Project 3 Bang The Dice Game
 * AUTHOR: Stephen C. Devaney
 * FOR: CS 2365 Object Oriented Programming Section 001 Spring 2020
 */
package character;

/**
 * class for black jack
 * @author Stephen C. Devaney
 */
public class BlackJack extends BangCharacter {
    private static final String NAME = "BLACK JACK";
    private static final String SPECIAL = "You may re-roll (not if you roll three or more!).\n" +
                                          "If you roll three or more Dynamite at once (or in\n" +
                                          "total if you didn’t re-roll them), follow the usual\n" +
                                          "rules (your turn ends, etc.).";
    private static final String IMAGEFILENAME = "/Images/Black Jack.jpg";
    
    /**
     * Constructor for Black Jack calls the super constructor from BangCharacter Class
     */
    public BlackJack(){
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
     * This Method checks to see if the character may re-roll dynamite
     * @return overrides to true
     */
    @Override
    public boolean canRerollDynamite(){
        return true;
    }
}
