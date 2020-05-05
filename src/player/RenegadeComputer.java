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
    
    public RenegadeComputer(BangCharacter character, int startingnumberofplayers){
        super(PlayerType.COMPUTER,character,Role.RENEGADE,startingnumberofplayers);
    }
    
    @Override
    public void notifySheriff(Player sheriff){
        this.getTargetValue(sheriff);
        this.pointsystem[this.highesttargetindex].setMinValue();
    }
    
    @Override
    public void notifySheriffHelped(Player helper){
        increaseTarget(helper);
    }
}
