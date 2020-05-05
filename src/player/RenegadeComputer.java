/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package player;

import character.BangCharacter;

/**
 *
 * @author raime
 */
public class RenegadeComputer extends Player {
    
    /**
     * constructor for RenegadeComputer
     * @param character the character assigned by the game
     * @param startingnumberofplayers number of players selected for the game
     */
    public RenegadeComputer(BangCharacter character, int startingnumberofplayers){
        super(PlayerType.COMPUTER,character,Role.RENEGADE,startingnumberofplayers);
    }
    
    /**
     * DESCRIPTION: override method used to determine threat level to the sheriff
     * @param sheriff the sheriff of the game
     */
    @Override
    public void notifySheriff(Player sheriff){
        this.getTargetValue(sheriff);
        this.pointsystem[this.highesttargetindex].setMinValue();
    }
    
    /**
     * DESCRIPTION: override method used to determine if the sheriff was helped
     * @param helper player who helped the sheriff
     */
    @Override
    public void notifySheriffHelped(Player helper){
        increaseTarget(helper);
    }
}
