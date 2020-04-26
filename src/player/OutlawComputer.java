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
    
    public OutlawComputer(BangCharacter character, int startingnumberofplayers){
        super(PlayerType.COMPUTER,character,Role.OUTLAW,startingnumberofplayers);
    }
    
    @Override
    public void notifySheriff(Player sheriff){
        this.getTargetValue(sheriff);
        this.pointsystem[this.highesttargetindex].setMaxValue();
    }
    
    @Override
    public void notifySheriffShot(Player shooter){
        decreaseTarget(shooter);
    }
    
    @Override
    public void notifySheriffHelped(Player helper){
        increaseTarget(helper);
    }
}
