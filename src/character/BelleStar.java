/*
 * TITLE: Project 3 Bang The Dice Game
 * AUTHOR: Anamol Acharya
 * FOR: CS 2365 Object Oriented Programming Section 001 Spring 2020
 */
package character;

import javafx.scene.image.Image;

/**
 * class for belle star
 */
public class BelleStar extends BangCharacter {
    
    private static final String NAME = "BELLE STAR";
    private static final String SPECIAL = "After each of your dice rolls, you can change one Dynamite to Gatling";
    private static final String IMAGEFILENAME = "/Images/Belle Star.jpg";

    
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
    
    /**
     * Method to get the image file name
     * @return
     */
    @Override
    public String getImageFileName(){
        return IMAGEFILENAME;
    }
    
    /**
     * This method checks to see if the character is able to excange one dynamite for a gatling after each roll
     * @return
     */
     @Override
    public boolean canExchangeOneDynamiteforGatling(){
        return true;
    }
}
