/*
 * TITLE: Project 3 Bang The Dice Game
 * AUTHOR: Stephen C. Devaney
 * FOR: CS 2365 Object Oriented Programming Section 001 Spring 2020
 */
package player;


/**
 *
 * @author Stephen C. Devaney
 */
public class Target {
    private final Player player;
    private int points;
    private boolean isdead;
    
    /**
     * constructor for target class
     * @param targetplayer the player being targeted
     * @param increase increases total points
     */
    public Target(Player targetplayer, boolean increase){
        isdead = false;
        player = targetplayer;
        if(increase) {points = 1;}
        else {points = -1;}
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
     * DESCRIPTION: gets the targets points (used as threat indicator)
     * @return
     */
    public int getTargetPoints(){
        return this.points;
    }
    
    /**
     * DESCRIPTION: allows for target to gain points increasing threat level
     */
    public void increaseTargetPoints(){
        if(points != Integer.MAX_VALUE) points++;
    }
    
    /**
     * DESCRIPTION: allows for target to lose points decreasing threat level
     */
    public void decreaseTargetPoints(){
        if(points != Integer.MIN_VALUE) points--;
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
        points = Integer.MAX_VALUE;
    }
    
    /**
     * DESCRIPTION: sets the threat level for individual roles pointing to the 
     * sheriff sets to lowest amount (deputies)
     */
    public void setMinValue(){
        points = Integer.MIN_VALUE;
    }
}
