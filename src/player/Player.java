/*
 * TITLE: Project 3 Bang The Dice Game
 * AUTHOR: Stephen C. Devaney
 * FOR: CS 2365 Object Oriented Programming Section 001 Spring 2020
 */
package player;

import Game.BangGame;
import character.BangCharacter;
import javafx.scene.image.Image;

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
    
    Player(PlayerType playertype, BangCharacter character, Role setuprole){
        this.playertype = playertype;
        this.character = character;
        this.role = setuprole;
        if (this.role == Role.SHERIFF){character.setSheriff();}
    }
    
    public Role getRole(){
        return this.role;
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