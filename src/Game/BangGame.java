/*
 * TITLE: Project 3 Bang The Dice Game
 * AUTHOR: Stephen Devaney
 * FOR: CS 2365 Object Oriented Programming Section 001 Spring 2020
 */
package Game;

import dice.BangDice;
import java.util.LinkedList;
import java.util.Random;
import player.Player;

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
        

        dice = new BangDice();
        arrowpile = 9;
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
