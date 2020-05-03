/*
 * TITLE: Project 3 Bang The Dice Game
 * AUTHOR: Anamol Acharya
 * FOR: CS 2365 Object Oriented Programming Section 001 Spring 2020
 */
package character;

import javafx.scene.image.Image;


public class BelleStar extends BangCharacter {
    
     private static final String NAME = "BelleStar";
    private static final String SPECIAL = "After each of your dice rolls, you can change one Dynamite to Gatlings";
    // Todo
    private static final String IMAGEFILENAME = "   ";

    
    /**
     * Constructor for BelleStar calls the super constructor from BangCharacter Class
     */
    public BelleStar(){
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
    
    @Override
    public String getImageFileName(){
        return IMAGEFILENAME;
    }
    
  
    
}
