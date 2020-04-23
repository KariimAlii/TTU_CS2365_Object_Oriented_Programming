/*
 * TITLE: Project 3 Bang The Dice Game
 * AUTHOR: Stephen Devaney
 * FOR: CS 2365 Object Oriented Programming Section 001 Spring 2020
 */
package Game;

import dice.BangDice;
import java.util.LinkedList;
import java.util.Random;
import player.*;

/**
 *
 * @author Stephen Devaney
 */
public class BangGame {
    private int startingnumberofplayers;
    private int currentnumberofplayers;
    private int numberofbadguys;
    private int arrowpile;
    private Player[] players;
    private Player sheriff;
    private Player curplayer;
    private BangDice dice;
    
    public BangGame(BangSetup setup){
        Random rand;
        rand = new Random(System.currentTimeMillis());
        startingnumberofplayers = currentnumberofplayers = setup.getNumberOfPlayers();
        players = new Player[startingnumberofplayers];
        Player templayer2 = this.sheriff = this.curplayer = new HumanPlayer(setup.getCharacter(),setup.getRole());
        players[0] = curplayer;
        Role temp;
        Player tempplayer;
        for(int i = 1; i < startingnumberofplayers; i++){
            temp = setup.getRole();
            if(temp == Role.SHERIFF){
                tempplayer = new RandomComputer(setup.getCharacter(),temp);
                this.sheriff = tempplayer;
            }
            else if(temp == Role.RENEGADE){tempplayer = new RandomComputer(setup.getCharacter(),temp);}
            else if(temp == Role.DEPUTY){tempplayer = new RandomComputer(setup.getCharacter(),temp);}
            else {tempplayer = new RandomComputer(setup.getCharacter(),temp);}
            //check this iw working properly
            players[i] = tempplayer;
            curplayer.setNextPlayer(tempplayer);
            tempplayer.setPreviousPlayer(curplayer);
            tempplayer.setNextPlayer(templayer2);
            templayer2.setPreviousPlayer(tempplayer);
            curplayer = curplayer.getNextPlayer();
        }
        curplayer = sheriff;
        dice = new BangDice();
        arrowpile = 9;
    }
    
    public BangDice getDice(){
        return this.dice;
    }
    
    public int getArrowPile(){
        return this.arrowpile;
    }
    
    public int getCurNumPlayers(){
        return this.currentnumberofplayers;
    }
    
    public int getStartingNumPlayers(){
        return this.startingnumberofplayers;
    }
    
    public void takeArrow(){
        this.arrowpile--;
        if (this.arrowpile == 0){indianAttack();}
    }
    
    public void returnArrows(int arrows){
        this.arrowpile += arrows;
    }
    
    private void indianAttack(){
        this.arrowpile += this.curplayer.individualIndianAttack();
        Player temp = this.curplayer.getNextPlayer();
        while(temp != this.curplayer){
            this.arrowpile += temp.individualIndianAttack();
            temp = temp.getNextPlayer();
        }
    }
    
    private void shootGatlingGun(){
        this.arrowpile = this.curplayer.individualGatlingGunShoot();
        Player temp = this.curplayer.getNextPlayer();
        while(temp != this.curplayer){
            temp.individualGatlingGunShot();
            temp.getNextPlayer();
        }
    }
    
    public Player getPlayerAtIndex(int index){
        return players[index];
    }
    
    public int getCurPlayerIndex(){
        int returnvalue = 0;
        for(int i = 0; i < this.players.length; i++){
            if(this.curplayer == this.players[i]) {
                returnvalue = i;
                break;
            }
        }
        return returnvalue;
    }
    
    public Player getCurPlayer(){
        return this.curplayer;
    }
    
    public Player getSheriff(){
        return this.sheriff;
    }
    
    public void endTurn(){
        this.curplayer = this.curplayer.getNextPlayer();
        this.curplayer.startTurn();
   }
}
