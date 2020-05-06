/*
 * TITLE: Dice Class for Project 3 Bang The Dice Game
 * AUTHOR: Andrew Sena
 * FOR: CS 2365 Object Oriented Programming Section 001 Spring 2020
 */
package character;

/**
 * zombie character class is there to remove all of the characters special abilities
 * @author raime
 */
public class Zombie extends BangCharacter{
    private static final String NAME = "Zombie";
    private static final String SPECIAL = "No special ability.";
    private static final String IMAGEFILENAME = "";
    
    
    /**
     * zombie constructor
     * @param numaliveplayers
     */
    public Zombie(int numaliveplayers){
        super(numaliveplayers);
    }
    
    
    /**
     * name will be changed to zombie
     */
    @Override
    public String getName(){
        return NAME;
    }
    
    
    /**
     * special ability changed to no special ability
     */
    @Override
    public String getSpecial(){
        return SPECIAL;
    }
    
    
    /**
     * image file for zombies since they discard their character card
     */
    @Override
    public String getImageFileName(){
        return IMAGEFILENAME;
    }
    
    
    /**
     * modifies take damage so that the zombies current life points and max life points stay and the same level and so they can not be healed
     */
    @Override
    public void takeDamage(){
        if(!this.isDead()){
            this.curlifepoints--;
            this.maxlifepoints--;
        }
    }
    
    
    /**
     * overides gainlife so that the zombie is not eligable to gain life
     */
    @Override
    public int gainLife(){
        int count = 0;
        return count;
    }
}
