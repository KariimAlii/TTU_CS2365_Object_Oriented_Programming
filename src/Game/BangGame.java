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
    private Player sheriff;
    private Player curplayer;
    private Player humanplayer;
    private BangDice dice;
    
    public BangGame(BangSetup setup){
        Random rand;
        rand = new Random(System.currentTimeMillis());
        startingnumberofplayers = currentnumberofplayers = setup.getNumberOfPlayers();
        humanplayer = curplayer = new HumanPlayer(setup.getCharacter(),setup.getRole());
        Role temp;
        for(int i = 1; i < startingnumberofplayers; i++){
            temp = setup.getRole();
            if(temp == Role.SHERIFF){sheriff = new RandomComputer(setup.getCharacter(),temp);}
            else if(temp == Role.RENEGADE){sheriff = new RandomComputer(setup.getCharacter(),temp);}
            else if(temp == Role.DEPUTY){sheriff = new RandomComputer(setup.getCharacter(),temp);}
            else {sheriff = new RandomComputer(setup.getCharacter(),temp);}
            curplayer.setNextPlayer(sheriff);
            sheriff.setPreviousPlayer(curplayer);
            sheriff.setNextPlayer(humanplayer);
            humanplayer.setPreviousPlayer(sheriff);
        }
        setSheriff();
        sheriff.setSheriff();
        dice = new BangDice();
        arrowpile = 9;
    }
    
    private void setSheriff(){
        if(humanplayer.getRole() == Role.SHERIFF) this.curplayer = this.sheriff = humanplayer;
        else{
            Player temp = humanplayer.getNextPlayer();
            while (temp.getRole() != Role.SHERIFF && temp != humanplayer){
                temp = temp.getNextPlayer();
            }
           this.curplayer = this.sheriff = temp;
        }
    }
    
    public BangDice getDice(){
        return dice;
    }
    
    public int getCurNumPlayers(){
        return currentnumberofplayers;
    }
    
    public void takeArrow(){
        arrowpile--;
        if (arrowpile == 0){indianAttack();}
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
        arrowpile = this.curplayer.individualGatlingGunShoot();
        Player temp = this.curplayer.getNextPlayer();
        while(temp != this.curplayer){
            temp.individualGatlingGunShot();
            temp.getNextPlayer();
        }
    }
}
