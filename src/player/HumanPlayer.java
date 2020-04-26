/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package player;

import Game.BangGame;
import character.*;

/**
 *
 * @author anamo
 */
public class HumanPlayer extends Player {
    
    public HumanPlayer(BangCharacter character, Role role, int numberofplayers){
        super(PlayerType.HUMAN, character, role, numberofplayers); // This is constructor logic can keep this
    }
    
    @Override
    public void startTurn(BangGame game){
        if (this.character.canHaveExtraReroll()) {this.rerollcount = 3;}
        else {this.rerollcount = 2;}
        this.dynamitecount = 0;
        dynamiteexploded = false;
        turnoutput = "";
    }
}
