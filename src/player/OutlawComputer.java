/*
 * TITLE: Project 3 Bang The Dice Game
 * AUTHOR: Andrew Sena
 * FOR: CS 2365 Object Oriented Programming Section 001 Spring 2020
 */
package player;

import Game.BangGame;
import character.BangCharacter;

/**
 *
 * @author raime
 */
public class OutlawComputer extends Player {
    
    /**
     * constructor for OutlawComputer
     * @param character character of the game
     * @param startingnumberofplayers number of players in the game
     */
    public OutlawComputer(BangCharacter character, int startingnumberofplayers){
        super(PlayerType.COMPUTER,character,Role.OUTLAW,startingnumberofplayers);
    }
    
    /**
     * DESCRIPTION: override method that notifies the sheriff of players threat level
     * @param sheriff sheriff of the game
     */
    @Override
    public void notifySheriff(Player sheriff){
        this.getTargetValue(sheriff);
        this.pointsystem[this.highesttargetindex].setMaxValue();
    }
    
    /**
     * DESCRIPTION: override method that notifies the sheriff of who shot him
     * @param shooter player who shot the sheriff
     */
    @Override
    public void notifySheriffShot(Player shooter){
        decreaseTarget(shooter);
    }
    
    /**
     * DESCRIPTION: override method that notifies the sheriff of who helped him
     * @param helper player who helped the sheriff
     */
    @Override
    public void notifySheriffHelped(Player helper){
        increaseTarget(helper);
    }
}
