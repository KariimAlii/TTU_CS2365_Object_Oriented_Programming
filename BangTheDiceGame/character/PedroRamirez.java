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
public class PedroRamirez extends BangCharacter{
    private static final String NAME = "PEDRO RAMIREZ";
    private static final String SPECIAL = "Each time you lose a life point, you may discard\n" +
                                          "one of your arrows. (You still lose the life point\n" +
                                          " when you use this ability.)";
    
    /**
     * Constructor for Pedro Ramirez calls the super constructor from BangCharacter Class
     */
    public PedroRamirez(){
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
     * This method checks to see if the character may discard one of their arrows each time they lose a life point
     * @return  overrides to true
     */
    @Override
    public boolean canDiscardArrowIfLoseLife(){
        return true;
    }
}
