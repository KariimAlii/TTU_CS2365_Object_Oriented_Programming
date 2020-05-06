/*
 * TITLE: Project 3 Bang The Dice Game
 * AUTHOR: Andrew Sena
 * FOR: CS 2365 Object Oriented Programming Section 001 Spring 2020
 */
package player;

import Game.BangGame;
import character.BangCharacter;
import static dice.BangDie.ARROW;
import static dice.BangDie.BEER;
import static dice.BangDie.BULLSEYE1;
import static dice.BangDie.BULLSEYE2;
import static dice.BangDie.DYNAMITE;
import static dice.BangDie.GATLING;
import static dice.CowardDie.BROKENARROW;
import static dice.CowardDie.DOUBLEBEER;
import dice.DieType;
import static dice.DuelDie.FIGHTADUEL;
import static dice.DuelDie.WHISKEYBOTTLE;
import static dice.LoudmouthDie.BULLET;

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
    
    /**
     * DESCRIPTION: override method to decide reroll strategy for deputy
     * @param game
     * @param index
     * @param beercount
     * @param gatcount
     * @return
     */
    @Override
    protected boolean rerollStrategy(BangGame game, int index, int beercount, int gatcount){
        boolean returnvalue = false;
        int diesymbol = game.getDice().getDieAtIndex(index);
        DieType dietype = game.getDice().getDieTypeAtIndex(index);
        
        if(dietype == DieType.LOUDMOUTH && diesymbol == BULLET){returnvalue = true;}
        
        else if (dietype == DieType.COWARD && (diesymbol == BROKENARROW || diesymbol == DOUBLEBEER)){
            if(diesymbol == BROKENARROW) {returnvalue = true;}
            else{
                if(game.getCurPlayer().getCurLife() + beercount >= game.getCurPlayer().getMaxLife()){returnvalue = true;}
            }
        }
        
        else if(dietype == DieType.DUEL && (diesymbol == WHISKEYBOTTLE || diesymbol == FIGHTADUEL)){
            if(diesymbol == WHISKEYBOTTLE){
                if(game.getCurPlayer().getCurLife() + beercount >= game.getCurPlayer().getMaxLife()){returnvalue = true;}
                else{
                    if(game.getCurNumPlayers() < 4 && this.getCurLife() < this.getMaxLife()/3 ) {returnvalue = true;}
                    else if(game.getCurNumPlayers() >= 4 && this.getCurLife() < this.getMaxLife()/2 ) {returnvalue = true;}
                }
            }
        }
        
        else{
            if(diesymbol == ARROW || (diesymbol == DYNAMITE && this.character.canRerollDynamite())) {returnvalue = true;}
            else if (diesymbol == BULLSEYE1){
                if(this.getNextPlayer() == game.getSheriff()  || this.getPreviousPlayer() == game.getSheriff()) {returnvalue = true;}
            }
            else if(diesymbol == BULLSEYE2){
                if(this.getNextPlayer().getNextPlayer() == game.getSheriff() || this.getPreviousPlayer().getPreviousPlayer() == game.getSheriff()) {returnvalue = true;}
            }
            else if (diesymbol == BEER){
                if(game.getCurPlayer().getCurLife() + beercount >= game.getCurPlayer().getMaxLife() && game.getSheriff().getCurLife() + beercount >= game.getSheriff().getMaxLife()){returnvalue = true;}
            }
            else if(diesymbol == GATLING){
                if(gatcount < 2 || gatcount > 3){returnvalue = true;}
                else if(game.getCurNumPlayers() <= 3 && game.getCurPlayer().getArrows() < 3){returnvalue = true;}
            }
        }
        return returnvalue;
    }
}
