/*
 * TITLE: Project 3 Bang The Dice Game
 * AUTHOR: Stephen C. Devaney
 * FOR: CS 2365 Object Oriented Programming Section 001 Spring 2020
 */
package player;

import Game.BangGame;
import character.BangCharacter;
import static dice.BangDie.*;
import javafx.scene.image.Image;

/**
 *
 * @author Stephen C. Devaney
 */
public abstract class Player {
    private PlayerType playertype;
    private BangCharacter character;
    private int rerollcount;
    private Role role;
    private int arrows;
    private int dynamitecount;
    private boolean dynamiteexploded;
    private Target pointsystem[];
    private Player nextPlayer;
    private Player previousPlayer;
    
    Player(PlayerType playertype, BangCharacter character, Role setuprole){
        this.playertype = playertype;
        this.character = character;
        this.role = setuprole;
        if (this.role == Role.SHERIFF){character.setSheriff();}
        this.dynamitecount = 0;
        dynamiteexploded = false;
        if (this.character.canHaveExtraReroll()) {this.rerollcount = 3;}
        else {this.rerollcount = 2;}
    }
    
    public void startTurn(){
        if (this.character.canGiveAnyPlayerLife()){
            this.gainHealth();
        }
        if (this.character.canHaveExtraReroll()) {this.rerollcount = 3;}
        else {this.rerollcount = 2;}
        this.dynamitecount = 0;
        dynamiteexploded = false;
    }
    
    public void endTurn(BangGame game){
        game.endTurn();
    }
    
    public Role getRole(){
        return this.role;
    }
    
    public void decreaseRerollCount(){
        this.rerollcount--;
    }
    
    public int getRoleindex(){
        int returnvalue = 0;
        if(this.role == Role.SHERIFF){returnvalue = 1;}
        else if (this.role == Role.RENEGADE && (this.isPlayerDead() || this.playertype == PlayerType.Human)){returnvalue = 2;}
        else if (this.role == Role.OUTLAW && (this.isPlayerDead() || this.playertype == PlayerType.Human)){returnvalue = 3;}
        else if (this.role == Role.DEPUTY && (this.isPlayerDead() || this.playertype == PlayerType.Human)){returnvalue = 4;}
        return returnvalue;
    }
    
    public void setNextPlayer(Player player){
        this.nextPlayer = player;
    }
    
    
    public void setPreviousPlayer(Player player){
        this.previousPlayer = player;
    }
    
    public void rollDice(BangGame game){
        game.getDice().rollDice();
        if(this.character.canRerollDynamite()){
            for(int i = 0; i < game.getDice().getNumberOfDice(); i++){
                if(game.getDice().getDieAtIndex(i) == DYNAMITE){
                    game.getDice().makeRerollableAtIndex(i);
                }
            }
        }
        processArrowsOrDynamite(game);
    }
    
    public void rollDieAtIndex(BangGame game, int index){
        if(game.getDice().getDieAtIndex(index) == DYNAMITE){
            this.dynamitecount--;
        }
        game.getDice().rollDieAtIndex(index);
        if(this.character.canRerollDynamite()){
            for(int i = 0; i < game.getDice().getNumberOfDice(); i++){
                if(game.getDice().getDieAtIndex(i) == DYNAMITE){
                    game.getDice().makeRerollableAtIndex(i);
                }
            }
        }
        processArrowsOrDynamite(game);
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
    
    public Image getCharacterImage(){
        return character.getImage();
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
    
    private void takeArrow(BangGame game){
            if(!this.character.isDead()){
            this.arrows++;
            game.takeArrow();
        }
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
    
    private void processArrowsOrDynamite(BangGame game){
        for (int i = 0; i < game.getDice().getNumberOfDice();i++){
            if(game.getDice().getDieAtIndex(i) == ARROW && !game.getDice().isProcessedDieAtIndex(i)){
                this.takeArrow(game);
                game.getDice().processDieAtIndex(i);
            }
            else if (game.getDice().getDieAtIndex(i) == DYNAMITE && !game.getDice().isProcessedDieAtIndex(i)){
                this.dynamitecount++;
                game.getDice().processDieAtIndex(i);
            }
        }
        checkDynamiteExplodes(game);
    }
    
    private void checkDynamiteExplodes(BangGame game){
        if(this.dynamitecount >= 3 && !dynamiteexploded){
            this.rerollcount = 0;
            this.takeDamage(game);
            dynamiteexploded = true;
        }
    }
    
    public Player[] getTargetsB1(BangGame game){
        Player curtargets[] = {this.getPreviousPlayer(), this.getNextPlayer()};
        return curtargets;
    }
    
    public Player[] getTargetsB2(BangGame game){
        Player curtargets[];
        if(game.getCurNumPlayers() > 2) {
            curtargets = new Player[2];
            curtargets[0] = this.getPreviousPlayer().getPreviousPlayer();
            curtargets[1] = this.getNextPlayer().getNextPlayer();
        }
        else{
            curtargets = new Player[1];
            curtargets[0] = this.getNextPlayer();
        }
        return curtargets;
    }
    
    public abstract Player getSelectedB1(BangGame game);
    
    public abstract Player getSelectedB2(BangGame game);
    
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
    
    private void giveBeer(Player target, BangGame game){
        target.takeBeer();
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
    
    public boolean canContinueReroll(){
        boolean returnvalue = false;
        if(this.rerollcount > 0 && !this.isPlayerDead()) returnvalue = true;
        return returnvalue;
    }
}