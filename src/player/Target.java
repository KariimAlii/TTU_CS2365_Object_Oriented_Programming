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
    
    
    public Target(Player targetplayer, boolean increase){
        isdead = false;
        player = targetplayer;
        if(increase) {points = 1;}
        else {points = -1;}
    }
    
    public boolean isTarget(Player player){
        return (this.player == player);
    }
    
    public int getTargetPoints(){
        return this.points;
    }
    
    public void increaseTargetPoints(){
        if(points != Integer.MAX_VALUE) points++;
    }
    
    public void decreaseTargetPoints(){
        if(points != Integer.MIN_VALUE) points--;
    }
    
    public boolean isTargetDead(){
        return isdead;
    }
    
    public void setMaxValue(){
        points = Integer.MAX_VALUE;
    }
    
    public void setMinValue(){
        points = Integer.MIN_VALUE;
    }
}
