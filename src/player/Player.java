/*
 * TITLE: Project 3 Bang The Dice Game
 * AUTHOR: Stephen C. Devaney
 * FOR: CS 2365 Object Oriented Programming Section 001 Spring 2020
 */
package player;

import Game.BangGame;
import character.BangCharacter;

/**
 *
 * @author Stephen C. Devaney
 */
public abstract class Player {
    private PlayerType playertype;
    private BangCharacter character;
    private Role role;
    private int arrows;
    private Target pointsystem[];
    private Player nextPlayer;
    private Player previousPlayer;
    
    Player(PlayerType playertype, BangCharacter character, Role role){
        this.playertype = playertype;
        this.character = character;
        this.role = role;
    }
    
    public void rollDice(BangGame game){
        game.getDice().rollDice();
        if(this.character.canRerollDynamite()){
            for(int i = 0; i <= game.getDice().getNumberOfDice(); i++){
                if(game.getDice().getDieAtIndex(i) == 2){
                    game.getDice().makeRerollableAtIndex(i);
                }
            }
        }
    }
    
    public int getRerollCount(){
        if(this.character.canHaveExtraReroll()){
            return 3;
        }
        else {return 2;}
    }
    
    public String getcharactername(){
        return character.getName();
    }
    
    public int getCurLife(){
        return this.character.getCurLifePoints();
    }
    
    public int getMaxLife(){
        return this.character.getMaxLifePoints();
    }
    
    public double getLifeProgress(){
        double curlife = this.character.getCurLifePoints();
        double maxlife = this.character.getMaxLifePoints();
        return curlife / maxlife;
    }
    
    private void takeDamage(BangGame game){
        this.character.takeDamage();
        if(this.character.isDead()){
            game.returnArrows(this.arrows);
            this.arrows = 0;
        }
    }
    
    private void gainHealth(){
        this.character.gainLife();
    }
    
    public boolean isPlayerDead(){
        return character.isDead();
    }
    
    public Player getPreviousPlayer(){
        return this.previousPlayer;
    }
    
    public Player getNextPlayer(){
        return this.nextPlayer;
    }
    
    
    public int getArrows(){
        return arrows;
    }
    
    public void takeArrow(BangGame game){
        this.arrows++;
        game.takeArrow();
    }
    
    public int individualIndianAttack(){
        int returnvalue = this.arrows;
        for (; this.arrows > 0; this.arrows--){
            if(this.character.canReducedIndianAttack()){
                this.arrows = 1;
            }
            this.character.takeDamage();
        }
        return returnvalue;
    }
    
    public Player[] getTargetsB1(){
        Player targets[] = {this.getPreviousPlayer(), this.getNextPlayer()};
        return targets;
    }
    
    public Player[] getTargetsB2(){
        Player targets[] = {this.getPreviousPlayer().getPreviousPlayer(), this.getNextPlayer().getNextPlayer()};
        return targets;
    }
    
    public abstract Player getSelectedB1();
    
    public abstract Player getSelectedB2();
    
    public void shootTarget(Player target, BangGame game){
        target.takeDamage(game);
    }
    
    public Player[] getTargetBeer(BangGame game){
        Player targets[] = new Player[game.getCurNumPlayers()];
        targets[0] = this;
        Player temp = this.getNextPlayer();
        int i = 1;
        while (temp != this){
            if (!temp.isPlayerDead()){targets[i] = temp;}
            temp = temp.getNextPlayer();
            i++;
        }
        return targets;
    }
    
    public Player getSelectedBeer(){ // Needs to be overwritten by computer players
        return this;
    }
    
    public void giveBeer(Player target, BangGame game){
        target.takeBeer();
        if(target == this && this.character.canLowLifeDoubleBeer() && this.character.gainLife() <= 4){
            target.takeBeer();
        }
    }
    
    public void takeBeer(){
        this.gainHealth();
    }
    
    public void individualGatlingGunShot(){
        if(!this.character.canHaveGatlingDamageImmunity()){
            this.character.takeDamage();
        }
    }
    
    public int individualGatlingGunShoot(){
        int returnvalue = this.arrows;
        this.arrows = 0;
        return returnvalue;
    }
}