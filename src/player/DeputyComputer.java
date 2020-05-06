/*
 * TITLE: Project 3 Bang The Dice Game
 * AUTHOR: Andrew Sena
 * FOR: CS 2365 Object Oriented Programming Section 001 Spring 2020
 */
package player;

import Game.BangGame;
import character.BangCharacter;

/**
 * DESCRIPTION: A class for a computer player with the role of a outlaw
 * @author raime
 */
public class DeputyComputer extends Player{
    
    /**
     * constructor for the DeputyComputer
     * @param character character for the game
     * @param startingnumberofplayers number of players in the game
     */
    public DeputyComputer(BangCharacter character, int startingnumberofplayers){
        super(PlayerType.COMPUTER,character,Role.DEPUTY,startingnumberofplayers);
    }
    
    /**
     * DESCRIPTION: override method to check on statues of the sheriff and to help the sheriff
     * @param game game functionality
     */
    @Override
    public void startTurn(BangGame game){
        if (this.character.canGiveAnyPlayerLife()){
            int deputyscurlife = this.getCurLife();
            int sheriffcurlife = game.getSheriff().getCurLife();
            int sheriffmaxlife = game.getSheriff().getMaxLife();
            if(deputyscurlife <= sheriffcurlife && sheriffcurlife < sheriffmaxlife){
                game.getSheriff().gainHealth();
            }
            else {this.gainHealth();}
        }
        if (this.character.canHaveExtraReroll()) {this.rerollcount = 3;}
        else {this.rerollcount = 2;}
        this.dynamitecount = 0;
        dynamiteexploded = false;
    }
    
    /**
     * DESCRIPTION: override method that notifies sheriff of players threat level to the sheriff
     * @param sheriff the sheriff of the game
     */
    @Override
    public void notifySheriff(Player sheriff){
        this.getTargetValue(sheriff);
        this.pointsystem[this.highesttargetindex].setMinValue();
    }
    
    /**
     * DESCRIPTION: override method to prioritize sheriffs survival over own survival
     * @param game game functionality
     * @return
     */
    @Override
    public Player getSelectedBeer(BangGame game){ 
        Player returnvalue;
        int deputyscurlife = this.getCurLife();
        int sheriffcurlife = game.getSheriff().getCurLife();
        int sheriffmaxlife = game.getSheriff().getMaxLife();
        if(deputyscurlife <= sheriffcurlife && sheriffcurlife < sheriffmaxlife){
            returnvalue = game.getSheriff();
        }
        else {returnvalue = this;}
        return returnvalue;
    }
}
