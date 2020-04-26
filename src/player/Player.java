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
    protected BangCharacter character;
    protected int rerollcount;
    private Role role;
    private int arrows;
    protected int dynamitecount;
    protected boolean dynamiteexploded;
    protected Target pointsystem[];
    protected int highesttargetindex;
    private Player nextPlayer;
    private Player previousPlayer;
    private boolean setdead;
    protected String turnoutput;
    
    Player(PlayerType playertype, BangCharacter character, Role setuprole, int startingnumberofplayers){
        this.playertype = playertype;
        this.character = character;
        this.role = setuprole;
        if (this.role == Role.SHERIFF){character.setSheriff();}
        this.dynamitecount = 0;
        dynamiteexploded = false;
        this.setdead = false;
        if (this.character.canHaveExtraReroll()) {this.rerollcount = 3;}
        else {this.rerollcount = 2;}
        pointsystem = new Target[startingnumberofplayers];
        highesttargetindex = -1;
    }
    
    public void startTurn(BangGame game){
        if (this.character.canGiveAnyPlayerLife()){
            if(this.getCurLife() >= this.getMaxLife()){
                Player targets[] = this.getTargetBeer(game);
                Player lowestvalue = targets[0];
                for(int i = 0; i < targets.length; i++){
                    if(getTargetValue(targets[i]) < getTargetValue(lowestvalue)){
                        lowestvalue = targets[i];
                    }
                }
                lowestvalue.gainHealth();
            }
            else{this.gainHealth();}
        }
        if (this.character.canHaveExtraReroll()) {this.rerollcount = 3;}
        else {this.rerollcount = 2;}
        this.dynamitecount = 0;
        dynamiteexploded = false;
        turnoutput = "";
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
    
    public int getRoleindex(BangGame game){
        int returnvalue = 0;
        if(!game.isEndCondition()){
            if(this.role == Role.SHERIFF){returnvalue = 1;}
            else if (this.role == Role.RENEGADE && (this.isPlayerDead() || this.playertype == PlayerType.HUMAN)){returnvalue = 2;}
            else if (this.role == Role.OUTLAW && (this.isPlayerDead() || this.playertype == PlayerType.HUMAN)){returnvalue = 3;}
            else if (this.role == Role.DEPUTY && (this.isPlayerDead() || this.playertype == PlayerType.HUMAN)){returnvalue = 4;}
        }
        else{
            if(this.role == Role.SHERIFF){returnvalue = 1;}
            else if (this.role == Role.RENEGADE){returnvalue = 2;}
            else if (this.role == Role.OUTLAW){returnvalue = 3;}
            else if (this.role == Role.DEPUTY){returnvalue = 4;}
        }
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
        
        if(game.getDice().getDieAtIndex(index) == DYNAMITE && this.character.canRerollDynamite()){
            game.getDice().makeRerollableAtIndex(index);
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
    
    protected void takeDamage(BangGame game){
        this.character.takeDamage();
        if(this.character.isDead() && !this.setdead){
            setDead(game);
        }
    }
    
    private void setDead(BangGame game){
        this.setdead = true;
        this.getPreviousPlayer().setNextPlayer(this.nextPlayer);
        this.getNextPlayer().setPreviousPlayer(this.previousPlayer);
        game.returnArrows(this.arrows);
        this.arrows = 0;
        game.reduceCurrentNumberOfPlayers();
        if(this.role == Role.OUTLAW || this.role == Role.RENEGADE){game.reduceNumberOfBadGuys();}
    }
    
    protected void gainHealth(){
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
    
    public Player getSelectedB1(BangGame game){
        Player targets[] = this.getTargetsB1(game);
        Player highesttarget = targets[0];
        for(int i = 0; i < targets.length; i++){
            if(getTargetValue(targets[i]) > getTargetValue(highesttarget)){
                highesttarget = targets[i];
            }
        }
        return highesttarget;
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
    
    public Player getSelectedB2(BangGame game){
        Player targets[] = this.getTargetsB2(game);
        Player highesttarget = targets[0];
        for(int i = 0; i < targets.length; i++){
            if(getTargetValue(targets[i]) > getTargetValue(highesttarget)){
                highesttarget = targets[i];
            }
        }
        return highesttarget;
    }
    
    public void shootTarget(Player target, BangGame game){
        target.takeShot(this, game);
    }
    
    protected void takeShot(Player shooter, BangGame game){
        this.takeDamage(game);
        this.increaseTarget(shooter);
    }
    
    public Player[] getTargetBeer(BangGame game){
        Player targets[] = new Player[game.getCurNumPlayers()];
        int targetsindex = 0;
        for(int i = 0; i < game.getStartingNumPlayers(); i++){
            if(!game.getPlayerAtIndex(i).isPlayerDead()){
                targets[targetsindex++] = game.getPlayerAtIndex(i);
            }
        }
        return targets;
    }
    
    public Player getSelectedBeer(BangGame game){
        Player returnvalue;
        if(this.getCurLife() >= this.getMaxLife()){
            Player targets[] = this.getTargetBeer(game);
            returnvalue = targets[0];
            for(int i = 0; i < targets.length; i++){
                if(getTargetValue(targets[i]) < getTargetValue(returnvalue)){
                    returnvalue = targets[i];
                }
            }
        }
        else{returnvalue = this;}
        return returnvalue;
    }
    
    private void giveBeer(Player target, BangGame game){
        target.takeBeer(this, game);
    }
    
    protected void takeBeer(Player giver, BangGame game){
        this.gainHealth();
        if(giver != this) decreaseTarget(giver);
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
    
    public boolean canRoll(BangGame game){
        boolean returnvalue = false;
        if(this.rerollcount > 0 && !this.isPlayerDead() && !game.isEndCondition()) returnvalue = true;
        return returnvalue;
    }
    
    public boolean canHaveAction(BangGame game){
        boolean returnvalue = false;
        if(!this.isPlayerDead() && !game.isEndCondition()){
            returnvalue = true;
        }
        return returnvalue;
    }
    
    public String[] getTargetForDieAtIndex(BangGame game, int index){
        int checkdie = game.getDice().getDieAtIndex(index);
        Player targets[];
        if (checkdie == BULLSEYE1){
            targets = this.getTargetsB1(game);
        }
        else if(checkdie == BULLSEYE2){
            targets = this.getTargetsB2(game);
        }
        else{
            targets = this.getTargetBeer(game);
        }
        
        String[] returnvalue = new String[targets.length];
        for(int i = 0; i < targets.length; i++){
            returnvalue[i] = targets[i].getcharactername();
        }
        return returnvalue;
    }
    
    public String getSelectedTargetForDieAtIndex(BangGame game, int index){
        int checkdie = game.getDice().getDieAtIndex(index);
        Player temp = getSelectedB1(game);
        
        if (checkdie == BULLSEYE1){
            temp = this.getSelectedB1(game);
        }
        else if(checkdie == BULLSEYE2){
            temp = this.getSelectedB2(game);
        }
        else{
            temp = this.getSelectedBeer(game);
        }
        return temp.getcharactername();
    }
    
    public void takeActionOnDieAtIndex(BangGame game, int dieindex, int targetindex){
        int checkdie = game.getDice().getDieAtIndex(dieindex);
        if (checkdie == BULLSEYE1){
            Player b1targets[] = this.getTargetsB1(game);
            this.shootTarget(b1targets[targetindex], game);
        }
        else if(checkdie == BULLSEYE2){
            Player b2targets[] = this.getTargetsB2(game);
            this.shootTarget(b2targets[targetindex], game);
        }
        else{
            Player beertargets[] = this.getTargetBeer(game);
            this.giveBeer(beertargets[targetindex], game);
        }
    }
    
    public void preformGatlingCheckAndAction(BangGame game){
        int gatlingcheck = 0;
        
        for(int i = 0; i < game.getDice().getNumberOfDice(); i++){
            if(game.getDice().getDieAtIndex(i) == GATLING){
                gatlingcheck++;
            }
        }
        if(gatlingcheck >= this.character.getGatlingNeed()){
            game.shootGatlingGun();
        }
    }
    
    public int getTargetValue(Player target){
        int returnvalue = 0;
        if(isTarget(target)){
            for(int i = 0; i <= this.highesttargetindex; i++){
                if(this.pointsystem[i].isTarget(target)){
                    returnvalue = this.pointsystem[i].getTargetPoints();
                    break;
                }
            }
        }
        else{
            this.addTarget(target, true);
            this.pointsystem[this.highesttargetindex].decreaseTargetPoints();
        }
        return returnvalue;
    }
    
    private void addTarget(Player target, boolean increase){
        if(this != target)this.pointsystem[++this.highesttargetindex] = new Target(target, increase);
    }
    
    private boolean isTarget(Player target){
        boolean returnvalue = false;
        if(this.highesttargetindex == -1){returnvalue = false;}
        else{
            for(int i = 0; i <= this.highesttargetindex; i++){
                if(this.pointsystem[i].isTarget(target)){
                    returnvalue = true;
                    break;
                }
            }
        }
        return returnvalue;
    }
    
    protected void increaseTarget(Player target){
        if(isTarget(target)){
            for(int i = 0; i <= this.highesttargetindex; i++){
                if(this.pointsystem[i].isTarget(target)){
                    this.pointsystem[i].increaseTargetPoints();
                    break;
                }
            }
        }
        else{
            this.addTarget(target, true);
        }
    }
    
    protected void decreaseTarget(Player target){
        if(isTarget(target)){
            for(int i = 0; i <= this.highesttargetindex; i++){
                if(this.pointsystem[i].isTarget(target)){
                    this.pointsystem[i].decreaseTargetPoints();
                    break;
                }
            }
        }
        else{
            this.addTarget(target, false);
        }
    }
    
    public void notifySheriff(Player sheriff){
        if (sheriff != this){
            this.getTargetValue(sheriff);
            this.pointsystem[this.highesttargetindex].setMaxValue();
        }
    }
    
    public void notifySheriffShot(Player shooter){
        increaseTarget(shooter);
    }
    
    public void notifySheriffHelped(Player helper){
        decreaseTarget(helper);
    }
}