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
public class SlabTheKiller extends BangCharacter{
    private static final String NAME = "SLAB THE KILLER";
    private static final String SPECIAL = "Once per turn, you can use a beer to double a bull's eye 1 or a bull's eye 2.\n" +
                                          "The dice you double takes two life points away from\n" +
                                          "the same player (you can’t choose two different\n" +
                                          "players). The beer in this case does not provide any\n" +
                                          "life points.";
    
    /**
     * Constructor for Slab The Killer calls the super constructor from BangCharacter Class
     */
    public SlabTheKiller(){
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
     * This method checks to see if the character may double shots with a beer instead of healing
     * @return  overrides to true
     */
    @Override
    public boolean canUseBeerToDoubleDamage(){
        return true;
    }
}
