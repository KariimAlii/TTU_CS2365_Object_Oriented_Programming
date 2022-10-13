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
    
    /**
     * DESCRIPTION: override method that decides reroll strategy for outlaw
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
                if(this.getNextPlayer().getNextPlayer() == game.getSheriff() || this.getPreviousPlayer().getPreviousPlayer() == game.getSheriff()){returnvalue = true;}
            }
            else if(diesymbol == BULLSEYE2){
                if(this.getNextPlayer() == game.getSheriff() || this.getPreviousPlayer() == game.getSheriff()) {returnvalue = true;}
            }
            else if (diesymbol == BEER){
                if(game.getCurPlayer().getCurLife() + beercount >= game.getCurPlayer().getMaxLife()){returnvalue = true;}
            }
            else if(diesymbol == GATLING){
                if(gatcount < 2 || gatcount > 3){returnvalue = true;}
                else if(game.getCurNumPlayers() <= 3 && game.getCurPlayer().getArrows() < 3){returnvalue = true;}
            }
        }
        return returnvalue;
    }
}
