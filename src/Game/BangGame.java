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
    Player[] players;
    private Player sheriff;
    private Player curplayer;
    private BangDice dice;
    
    public BangGame(BangSetup setup){
        Random rand;
        rand = new Random(System.currentTimeMillis());
        startingnumberofplayers = currentnumberofplayers = setup.getNumberOfPlayers();
        players = new Player[startingnumberofplayers];
        sheriff = curplayer = new HumanPlayer(setup.getCharacter(),setup.getRole());
        players[0] = curplayer;
        Role temp;
        Player tempplayer;
        for(int i = 1; i < startingnumberofplayers; i++){
            temp = setup.getRole();
            if(temp == Role.SHERIFF){tempplayer = new RandomComputer(setup.getCharacter(),temp);}
            else if(temp == Role.RENEGADE){tempplayer = new RandomComputer(setup.getCharacter(),temp);}
            else if(temp == Role.DEPUTY){tempplayer = new RandomComputer(setup.getCharacter(),temp);}
            else {tempplayer = new RandomComputer(setup.getCharacter(),temp);}
            players[i] = tempplayer;
            curplayer.setNextPlayer(tempplayer);
            tempplayer.setPreviousPlayer(curplayer);
            tempplayer.setNextPlayer(sheriff);
            sheriff.setPreviousPlayer(tempplayer);
        }
        setSheriff();
        dice = new BangDice();
        arrowpile = 9;
    }
    
    private void setSheriff(){
        if(this.sheriff.getRole() == Role.SHERIFF) this.curplayer = this.sheriff;
        else{
            Player temp = this.sheriff.getNextPlayer();
            while (temp.getRole() != Role.SHERIFF && temp != this.sheriff){
                temp = temp.getNextPlayer();
            }
           this.curplayer = this.sheriff = temp;
        }
        this.sheriff.setSheriff();
    }
    
    public BangDice getDice(){
        return this.dice;
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
        this.curplayer.individualIndianAttack();
        Player temp = this.curplayer.getNextPlayer();
        while(temp != this.curplayer){
            temp.individualIndianAttack();
            temp.getNextPlayer();
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
}
