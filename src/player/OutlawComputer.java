/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
