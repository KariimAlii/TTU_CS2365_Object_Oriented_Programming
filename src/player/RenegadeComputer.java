/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
 * DESCRIPTION: A class for a computer player with the role of a renegade
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
    
    /**
     * DESCRIPTION: override method used to determine the reroll strategy for the renegade
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
                if((this.getNextPlayer() == game.getSheriff() && game.getCurNumPlayers() > 2) || (this.getPreviousPlayer() == game.getSheriff() && game.getCurNumPlayers() > 2)) {returnvalue = true;}
            }
            else if(diesymbol == BULLSEYE2){
                if((this.getNextPlayer().getNextPlayer() == game.getSheriff() && game.getCurNumPlayers() > 2) || (this.getPreviousPlayer().getPreviousPlayer() == game.getSheriff() && game.getCurNumPlayers() > 2)) {returnvalue = true;}
            }
            else if (diesymbol == BEER){
                if(game.getCurPlayer().getCurLife() + beercount >= game.getCurPlayer().getMaxLife() && game.getSheriff().getCurLife() + beercount >= game.getSheriff().getMaxLife()){returnvalue = true;}
            }
            else if(diesymbol == GATLING){
                if(gatcount < 2 || gatcount > 3){returnvalue = true;}
                else if(game.getCurNumPlayers() <= 3 && game.getCurPlayer().getArrows() < 4){returnvalue = true;}
            }
        }
        return returnvalue;
    }
}
