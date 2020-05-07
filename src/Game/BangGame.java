/*
 * TITLE: Project 3 Bang The Dice Game
 * AUTHOR: Stephen Devaney
 * COLLABORATORS: Andrew Sena, Anamol Acharya
 * FOR: CS 2365 Object Oriented Programming Section 001 Spring 2020
 */

package Game;

import dice.*;
import java.util.LinkedList;
import java.util.Random;
import player.*;

/**
 * The game class for bang the dice game
 * @author Stephen Devaney
 * @author Andrew Sena
 * @author Anamol Acharya
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
    public  boolean hasindianChiefArrow = false;
    private boolean undeadoralivemodule;
    private boolean outbreak ;
    private boolean hasreset;
    public int boneyardcard[] = {0,0,1,1,1,1,1,1,2,2,2};
    public int frontindex = 0;
    public int topOfDeck;
    
    /**
     * base constructor for bang the dice game
     */
    public BangGame(BangSetup setup){
        Random rand;
        rand = new Random(System.currentTimeMillis());
        this.hasindianChiefArrow = setup.getIndianChiefArrow();
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
        dice = new BangDice(setup.getdueldice());
        arrowpile = 9;
        outbreak = false;
        hasreset = false;
    }
    
    /**
     * reduces the current number of players for after a player dies
     */
    public void reduceCurrentNumberOfPlayers(){
        this.currentnumberofplayers--;
    }
    
    /**
     * reduces the current number of bad guys for after an outlaw or rengade dies if not in exansion zombie if in expansion
     */
    public void reduceNumberOfBadGuys(){
        this.numberofbadguys--;
    }
    
    /**
     * gets the dice for the other classes
     */
    public BangDice getDice(){
        return this.dice;
    }
    
    /**
     * gets the value of the arrow pile
     */
    public int getArrowPile(){
        return this.arrowpile;
    }
    
    /**
     * gets the value of the current number of players
     */
    public int getCurNumPlayers(){
        return this.currentnumberofplayers;
    }
    
    /**
     * gets the starting number of players
     */
    public int getStartingNumPlayers(){
        return this.startingnumberofplayers;
    }
    
    /**
     * takes an arrow from the arrow pile and sets up for indian attack
     */
    public boolean takeArrow(){
        boolean returnvalue = false;
        this.arrowpile--;
        if (this.arrowpile == 0){
            indianAttack();
            returnvalue = true;
        }
        return returnvalue;
    }
    
    /**
     * returns a number of arrows to the arrow pile
     */
    public void returnArrows(int arrows){
        this.arrowpile += arrows;
    }
    
    /**
     * preforms an indian attack on all players
     */
    private void indianAttack(){
        this.arrowpile += this.curplayer.individualIndianAttack(this);
        Player temp = this.curplayer.getNextPlayer();
        while(temp != this.curplayer){
            this.arrowpile += temp.individualIndianAttack(this);
            temp = temp.getNextPlayer();
        }
    }
    
    /**
     * allows a player to shoot the gatling gun damging all other players and discarding his arrows
     */
    public void shootGatlingGun(){
        this.arrowpile += this.curplayer.individualGatlingGunShoot();
        for (int i = 0; i < players.length; i++){
            if(!players[i].isPlayerDead() && players[i] != this.curplayer){
                players[i].individualGatlingGunShot();
            }
        }
    }
    
    /**
     * Gets the player a specified index in the player array
     * @param index
     * @return
     */
    public Player getPlayerAtIndex(int index){
        return players[index];
    }
    
    /**
     * gets the index of the current player in the player array
     * @return
     */
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
    
    /**
     * gets the current player
     * @return
     */
    public Player getCurPlayer(){
        return this.curplayer;
    }
    
    /**
     * gets the sheriff player
     * @return
     */
    public Player getSheriff(){
        return this.sheriff;
    }
    
    /**
     * notified all players that the sheriff has been shot
     */
    public void sheriffShot(){
        for(int i = 0; i <  players.length; i++){
            players[i].notifySheriffShot(sheriff);
        }
    }
    
    /**
     * notified all players that the sheriff has been helped
     */
    public void sheriffHelped(){
        for(int i = 0; i <  players.length; i++){
            players[i].notifySheriffHelped(sheriff);
        }
    }
    
    /**
     * gets the next player and setups his next turn
     */
    public void endTurn(){
        this.resetPlayerPointers();
        this.curplayer = this.curplayer.getNextPlayer();
        this.curplayer.startTurn(this);
   }
    
    /**
     * checks to see if the game has ended
     * @return
     */
    public boolean isEndCondition(){
        boolean returnvalue = false;
        if(this.sheriff.isPlayerDead()) returnvalue = true;
        else if(this.numberofbadguys <= 0) returnvalue = true;
        else if(this.currentnumberofplayers <= 1) returnvalue = true;
        return returnvalue;
    }
    
    /**
     * gets the end condition for the GUI
     * @return
     */
    public int getEndCondition(){
        this.resetPlayerPointers();
        System.out.println("Current number of players: " +  this.currentnumberofplayers);
        System.out.println("number of bad guys: " + this.numberofbadguys);
        int returnvalue = 0;
        if(this.currentnumberofplayers <= 0) returnvalue = 0;
        else if (this.sheriff.isPlayerDead() && this.currentnumberofplayers > 1) returnvalue = 0;
        else if (this.sheriff.isPlayerDead() && this.currentnumberofplayers == 1 && this.curplayer.getRole() == Role.RENEGADE) returnvalue = 3;
        else if (!this.sheriff.isPlayerDead() && this.startingnumberofplayers > 4 && this.numberofbadguys <= 0) returnvalue = 1;
        else if (!this.sheriff.isPlayerDead() && this.numberofbadguys <= 0) returnvalue = 2;
        return returnvalue;
    }
  
     
    /**
     *Returns a boolean for if the module is selected or not
     * @author Anamol Archarya
     * @return
     */
    public boolean getUndeadOrAlive(){
         return undeadoralivemodule;
     }
     
    /**
     *Returns a boolean for if the outbreak has occurred or not
     * @return
     * @author Anamol Archarya
     */
    public boolean getOutbreak(){
         return outbreak;
     }
    
    /**
     * shuffles the bone yard deck
     * @author Andrew Sena
     */
    public void shuffleBoneyardDeck(){
        
        for(int i = frontindex; i < boneyardcard.length; i++){
            Random ran = new Random();
            int random = ran.nextInt(boneyardcard.length-frontindex)+frontindex;
            int temp = boneyardcard[i];
            boneyardcard[i] = boneyardcard[random];
            boneyardcard[random] = temp;
        }
    }
    
    /**
     * draws a bone yard card
     * @author Andrew Sena
     * @return
     */
    public int drawBoneyardCard(){
        int draw = 0;
        if (frontindex != 11){
            draw = boneyardcard[frontindex];
            if(boneyardcard[frontindex] != 0)
                frontindex++;
            else
                shuffleBoneyardDeck();
        }
        return draw;
    }
    
    public void resetPlayerPointers(){
        if(this.currentnumberofplayers >= 0){
            if(this.undeadoralivemodule && this.outbreak && !this.hasreset)
                for(int i = 0; i < this.getStartingNumPlayers(); i++){
                    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////TODO zombie outbreak
                }
            else {
                Player firstplayer = this.players[0];
                Player lastplayer = this.players[0];
                this.currentnumberofplayers = 0;
                this.numberofbadguys = 0;
                for(int i = 0;  i < this.getStartingNumPlayers(); i++){
                    if(firstplayer.isPlayerDead()){
                        if(!this.players[i].isPlayerDead()){
                            firstplayer = lastplayer = this.players[i];
                            this.currentnumberofplayers++;
                            if(this.players[i].getRole() == Role.RENEGADE || this.players[i].getRole() == Role.OUTLAW){
                                this.numberofbadguys++;
                            }
                        }
                    }
                    else{
                        if(!players[i].isPlayerDead()){
                            lastplayer.setNextPlayer(players[i]);
                            players[i].setPreviousPlayer(lastplayer);
                            lastplayer = players[i];
                            this.currentnumberofplayers++;
                            if(this.players[i].getRole() == Role.RENEGADE || this.players[i].getRole() == Role.OUTLAW){
                                this.numberofbadguys++;
                            }
                        }
                    }
                }
                lastplayer.setNextPlayer(firstplayer);
                firstplayer.setPreviousPlayer(lastplayer);
            }
        }
    }
}