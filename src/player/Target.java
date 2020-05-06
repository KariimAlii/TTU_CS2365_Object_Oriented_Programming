/*
 * TITLE: Project 3 Bang The Dice Game
 * AUTHOR: Stephen C. Devaney
 * FOR: CS 2365 Object Oriented Programming Section 001 Spring 2020
 */
package player;


/**
 * DESCRIPTION: class to hold targets and their threat levels
 * @author Stephen C. Devaney
 */
public class Target {
    private final Player player;
    private int threatlevel;
    private boolean isdead;
    
    /**
     * constructor for target class
     * @param targetplayer the player being targeted
     * @param increase increases total threatlevel
     */
    public Target(Player targetplayer, boolean increase){
        isdead = false;
        player = targetplayer;
        if(increase) {threatlevel = 1;}
        else {threatlevel = -1;}
    }
    
    /**
     * DESCRIPTION: selected target in the target array 
     * @param player player being targeted
     * @return
     */
    public boolean isTarget(Player player){
        return (this.player == player);
    }
    
    /**
     * DESCRIPTION: gets the targets threatlevel (used as threat indicator)
     * @return
     */
    public int getTargetPoints(){
        return this.threatlevel;
    }
    
    /**
     * DESCRIPTION: allows for target to gain threatlevel increasing threat level
     */
    public void increaseTargetPoints(){
        if(threatlevel != Integer.MAX_VALUE) threatlevel++;
    }
    
    /**
     * DESCRIPTION: allows for target to lose threatlevel decreasing threat level
     */
    public void decreaseTargetPoints(){
        if(threatlevel != Integer.MIN_VALUE) threatlevel--;
    }
    
    /**
     * DESCRIPTION: a check if the target is dead or not
     * @return
     */
    public boolean isTargetDead(){
        return isdead;
    }
    
    /**
     * DESCRIPTION: sets the threat level for individual roles pointing to the 
     * sheriff sets to highest amount (outlaws)
     */
    public void setMaxValue(){
        threatlevel = Integer.MAX_VALUE;
    }
    
    /**
     * DESCRIPTION: sets the threat level for individual roles pointing to the 
     * sheriff sets to lowest amount (deputies)
     */
    public void setMinValue(){
        threatlevel = Integer.MIN_VALUE;
    }
}
