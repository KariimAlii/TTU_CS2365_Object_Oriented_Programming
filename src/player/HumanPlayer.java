/*
 * TITLE: Project 3 Bang The Dice Game
 * AUTHOR: Anamol Archarya
 * FOR: CS 2365 Object Oriented Programming Section 001 Spring 2020
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
