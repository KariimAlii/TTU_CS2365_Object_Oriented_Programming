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
        points++;
    }
    
    public void decreaseTargetPoints(){
        points--;
    }
    
    public boolean isTargetDead(){
        return isdead;
    }
}
