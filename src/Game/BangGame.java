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
    public int numberofbadguys;
    private int arrowpile;
    private Player[] players;
    private Player sheriff;
    private Player curplayer;
    private BangDice dice;
    public  boolean hasindianCheifArrow;
    private Boolean undeadoralivemodule;
    private Boolean outbreak ;
    
    public BangGame(BangSetup setup){
        Random rand;
        rand = new Random(System.currentTimeMillis());
        startingnumberofplayers = currentnumberofplayers = setup.getNumberOfPlayers();
        numberofbadguys = 0;
        players = new Player[startingnumberofplayers];
        Player templayer2 = this.sheriff = this.curplayer = new HumanPlayer(setup.getCharacter(),setup.getRole(), startingnumberofplayers);
        if(templayer2.getRole() == Role.OUTLAW || templayer2.getRole() == Role.RENEGADE) numberofbadguys++;
        players[0] = curplayer;
        Role temp;
        Player tempplayer;
        for(int i = 1; i < startingnumberofplayers; i++){
            temp = setup.getRole();
            if(temp == Role.SHERIFF){
                tempplayer = new SheriffComputer(setup.getCharacter(),startingnumberofplayers);
                this.sheriff = tempplayer;
            }
            else if(temp == Role.RENEGADE){
                tempplayer = new RenegadeComputer(setup.getCharacter(), startingnumberofplayers);
                numberofbadguys++;
            }
            else if(temp == Role.DEPUTY){tempplayer = new DeputyComputer(setup.getCharacter(),startingnumberofplayers);}
            else {
                tempplayer = new OutlawComputer(setup.getCharacter(), startingnumberofplayers);
                numberofbadguys++;
            }
            //check this iw working properly
            players[i] = tempplayer;
            curplayer.setNextPlayer(tempplayer);
            tempplayer.setPreviousPlayer(curplayer);
            tempplayer.setNextPlayer(templayer2);
            templayer2.setPreviousPlayer(tempplayer);
            curplayer = curplayer.getNextPlayer();
        }
        for(int i = 0; i < startingnumberofplayers; i++){
            players[i].notifySheriff(sheriff);
        }
        curplayer = sheriff;
        dice = new BangDice();
        arrowpile = 9;
        outbreak = false;
    }
    
    public void reduceCurrentNumberOfPlayers(){
        this.currentnumberofplayers--;
    }
    
    public void reduceNumberOfBadGuys(){
        this.numberofbadguys--;
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
    
    public boolean takeArrow(){
        boolean returnvalue = false;
        this.arrowpile--;
        if (this.arrowpile == 0){
            indianAttack();
            returnvalue = true;
        }
        return returnvalue;
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
    
    public void shootGatlingGun(){
        this.arrowpile += this.curplayer.individualGatlingGunShoot();
        for (int i = 0; i < players.length; i++){
            if(!players[i].isPlayerDead() && players[i] != this.curplayer){
                players[i].individualGatlingGunShot();
            }
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
    
    public void sheriffShot(){
        for(int i = 0; i <  players.length; i++){
            players[i].notifySheriffShot(sheriff);
        }
    }
    
    public void sheriffHelped(){
        for(int i = 0; i <  players.length; i++){
            players[i].notifySheriffHelped(sheriff);
        }
    }
    
    public void endTurn(){
        this.curplayer = this.curplayer.getNextPlayer();
        this.curplayer.startTurn(this);
   }
    
    public boolean isEndCondition(){
        boolean returnvalue = false;
        if(this.sheriff.isPlayerDead()) returnvalue = true;
        else if(this.numberofbadguys == 0) returnvalue = true;
        return returnvalue;
    }
    
    public int getEndCondition(){
        int returnvalue = 0;
        if(this.currentnumberofplayers <= 0) returnvalue = 0;
        else if (this.sheriff.isPlayerDead() && this.currentnumberofplayers > 1) returnvalue = 0;
        else if (this.sheriff.isPlayerDead() && this.currentnumberofplayers == 1 && this.curplayer.getRole() == Role.RENEGADE) returnvalue = 3;
        else if (!this.sheriff.isPlayerDead() && this.startingnumberofplayers > 4 && this.numberofbadguys <= 0) returnvalue = 1;
        else if (!this.sheriff.isPlayerDead() && this.numberofbadguys <= 0) returnvalue = 2;
        return returnvalue;
    }
  
  
    /**
     * Anamol Acharya
     * Created a boolean method for Indian chief arrow 
     * @return return value
     */
     public boolean hasIndianChiefArrow(){
         boolean returnvalue = false;
         this.arrowpile--;
         if(this.hasindianCheifArrow){
            returnvalue = true; 
         }    
        return returnvalue;
    }
     
    /**
     *Returns a boolean for if the module is selected or not
     * @return
     */
    public boolean getUndeadOrAlive(){
         return undeadoralivemodule;
     }
     
    /**
     *Returns a boolean for if the outbreak has occurred or not
     * @return
     */
    public boolean getOutbreak(){
         return outbreak;
     }
}