/*
 * TITLE: Project 3 Bang The Dice Game
 * AUTHOR: Stephen Devaney
 * COLLABORATOR: Anamol Acharya, Andrew Sena, Steven Lowry, Shree Shrestha
 * FOR: CS 2365 Object Oriented Programming Section 001 Spring 2020
 */
package player;

import Game.BangGame;
import bang_gui.GameBoardController;
import character.BangCharacter;
import dice.*;
import static dice.BangDie.*;
import static dice.CowardDie.*;
import static dice.DuelDie.*;
import static dice.LoudmouthDie.BULLET;
import javafx.scene.image.Image;

/**
 * DESCRIPTION: Abstract class to represent a player
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
    private int indianattackhappened;
    protected Target pointsystem[];
    protected int highesttargetindex;
    private Player nextPlayer;
    private Player previousPlayer;
    private boolean setdead;
    protected String turnoutput;
    private boolean gatlinggunwentoff;
    private boolean hasIndianChiefArrow;

    
    /**
     * DESCRIPTION: Constructor for player
     * @param playertype
     * @param character
     * @param setuprole
     * @param startingnumberofplayers
     */
    public Player(PlayerType playertype, BangCharacter character, Role setuprole, int startingnumberofplayers){
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
        indianattackhappened = 0;
        gatlinggunwentoff = false;
    }

    /**
     * DESCRIPTION: gets the role for the player
     * @return
     */
    public Role getRole(){
        return this.role;
    }
    
    /**
     * DESCRIPTION: gets player type for the player
     * @return
     */
    public PlayerType getPlayerType(){
        return this.playertype;
    }

    /**
     * DESCRIPTION: indexes the roles to determine if the player is a human and what role they have
     * @param game
     * @return
     */
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
    
    /**
     * DESCRIPTION: determines if the character given by the game functionality can have a certain number of rerolls
     * @return
     */
    public int getRerollCount(){
        if(this.character.canHaveExtraReroll()){
            return 3;
        }
        else {return 2;}
    }

    /**
     * DESCRIPTION: finds the characters name given by the game functionality
     * @return
     */
    public String getcharactername(){
        return character.getName();
    }

    /**
     * DESCRIPTION: gets the image of the character
     * @return
     */
    public Image getCharacterImage(){
        return character.getImage();
    }

    /**
     * DESCRIPTION: gets the current life points of the character
     * @return
     */
    public int getCurLife(){
        return this.character.getCurLifePoints();
    }

    /**
     * DESCRIPTION: gets the max life points of the character
     * @return
     */
    public int getMaxLife(){
        return this.character.getMaxLifePoints();
    }

    /**
     * DESCRIPTION: gets the current progress of the characters health pool out of the characters max life pool
     * @return
     */
    public double getLifeProgress(){
        double curlife = this.character.getCurLifePoints();
        double maxlife = this.character.getMaxLifePoints();
        return curlife / maxlife;
    }
    
    /**
     * DESCRIPTION: gets the player before the current player
     * @return
     */
    public Player getPreviousPlayer(){
        return this.previousPlayer;
    }

    /**
     * DESCRIPTION: gets the player after the current player
     * @return
     */
    public Player getNextPlayer(){
        return this.nextPlayer;
    }
    
    /**
     * DESCRIPTION: gets the arrows
     * @return
     */
    public int getArrows(){
        return arrows;
    }
    
    /**
     * DESCRIPTION: selects target for bullseye 1 and gets targets within range of the player
     * @param game
     * @return
     */
    public Player[] getTargetsB1(BangGame game){
        Player curtargets[] = {this.getPreviousPlayer(), this.getNextPlayer()};
        return curtargets;
    }

    /**
     * DESCRIPTION: gets the selected target for bullseye 1
     * @param game
     * @return
     */
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

    /**
     * DESCRIPTION: selects target for bullseye 2 and gets targets within range of the play
     * @param game
     * @return
     */
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

    /**
     * DESCRIPTION: gets selected target for bullseye 2
     * @param game
     * @return
     */
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
    
    /**
     * DESCRIPTION: gets target for beer
     * @param game
     * @return
     */
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

    /**
     * DESCRIPTION: gets target for beer
     * @param game
     * @return
     */
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
    
    /**
     * DESCRIPTION: gets the targets for the player based on dice rolled
     * @param game
     * @param index
     * @return
     */
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

    /**
     * DESCRIPTION: gets targets for dice rolled
     * @param game
     * @param index
     * @return
     */
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
    
    /**
     * DESCRIPTION: gets the point values for the target player
     * @param target
     * @return
     */
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

    /**
     * DESCRIPTION: sets the next player (who is next after current player)
     * @param player
     */
    public void setNextPlayer(Player player){
        this.nextPlayer = player;
    }
    
    /**
     * DESCRIPTION: sets the previous player (who was before the current player)
     * @param player
     */
    public void setPreviousPlayer(Player player){
        this.previousPlayer = player;
    }
    
    /**
     * DESCRIPTION: sets player to dead if life total is zero reveals roles
     */
    private void setDead(BangGame game){
        this.setdead = true;
        this.getPreviousPlayer().setNextPlayer(this.nextPlayer);
        this.getNextPlayer().setPreviousPlayer(this.previousPlayer);
        game.returnArrows(this.arrows);
        this.arrows = 0;
        game.reduceCurrentNumberOfPlayers();
        game.hasindianCheifArrow = true;
        this.hasIndianChiefArrow = false;
        if(this.role == Role.OUTLAW || this.role == Role.RENEGADE){game.reduceNumberOfBadGuys();}
    }
    
    /**
     * DESCRIPTION: checks on if the player is dead
     * @return
     */
    public boolean isPlayerDead(){
        return character.isDead();
    }

    /**
     * DESCRIPTION: selected target point systems
     */
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
    
    /**
     * DESCRIPTION: checks to see if the player can reroll
     * @param game
     * @return
     */
    public boolean canRoll(BangGame game){
        boolean returnvalue = false;
        if(this.rerollcount > 0 && !this.isPlayerDead() && !game.isEndCondition()) returnvalue = true;
        return returnvalue;
    }

    /**
     * DESCRIPTION:checks if the player can take an action
     * @param game
     * @return
     */
    public boolean canHaveAction(BangGame game){
        boolean returnvalue = false;
        if(!this.isPlayerDead() && !game.isEndCondition()){
            returnvalue = true;
        }
        return returnvalue;
    }

    /**
     * DESCRIPTION: sets up the turn for the current player, allows current player to take their turn
     * @param game
     */
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
        indianattackhappened = 0;
        dynamiteexploded = false;
        gatlinggunwentoff = false;
        indianattackhappened = 0;
        turnoutput = "";
    }

    /**
     * DESCRIPTION: ends the turn of the current player
     * @param game
     */
    public void endTurn(BangGame game){
        game.endTurn();
    }
    
    /**
     * DESCRIPTION: decrements the amount of rerolls a player can make
     */
    public void decreaseRerollCount(){
        this.rerollcount--;
    }
    
    /**
     * DESCRIPTION: rolls the dice and if a character can reroll dynamite allow them to reroll them
     * @param game
     */
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

    /**
     * DESCRIPTION: rolls dice and checks if rolled dice have dynamite
     * @param game
     * @param index
     */
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

    /**
     * DESCRIPTION: target character takes damage if selected for attack, checks if character is dead
     * @param game
     */
    protected void takeDamage(BangGame game){
        this.character.takeDamage();
        if(this.character.isDead() && !this.setdead){
            setDead(game);
        }
    }

    /**
     * DESCRIPTION: character gains health if targeted to gain health
     */
    protected void gainHealth(){
        this.character.gainLife();
    }
    
    /**
     * DESCRIPTION: character gains an arrow if dice rolled have an arrow
     */
    private void takeArrow(BangGame game){
            if(!this.character.isDead()){
            this.arrows++;
            if(game.takeArrow()){
                this.indianattackhappened++;
            }
        }
    }
    
    
    /**
     * DESCRIPTION: takes an indian chief arrow for the game and give it to the player
     * @param game
     * @author Shree Shrestha
     */
    public void takeIndianChiefArrow(BangGame game){
        game.hasindianCheifArrow = false;
        this.hasIndianChiefArrow = true;
    }

   /**
     * this methods is to resolve the individual Indian attack 
     * where the damage are taken on needed conditions
     * @param game
     * @return returnvalue
     * @author Stephen C. Devaney
     * @author Shree Shrestha added indian chief code to this method
     */
    public int individualIndianAttack(BangGame game){
        int returnvalue = this.arrows;
        if(this.hasIndianChiefArrow && checkMostArrows(game)){
        }
        else{
        for (; this.arrows > 0; this.arrows--){
            if(this.character.canReducedIndianAttack()){
                this.arrows = 1;
            }
            this.character.takeDamage();
        }
        if(this.hasIndianChiefArrow){
            this.character.takeDamage();
            this.character.takeDamage();            
            }
        }
        if(this.hasIndianChiefArrow){
            this.hasIndianChiefArrow = false;
            game.hasindianCheifArrow = true;
        }
        return returnvalue;
    }
  
  
    /**
     * This methods check if any players has the most arrows
     * @param game
     * @return return value
     * @author Shree Shrestha
     */
    private boolean checkMostArrows(BangGame game){
        boolean returnvalue = true;
        for(int i = 0; i < game.getStartingNumPlayers(); i++){
            if(game.getPlayerAtIndex(i).arrows > this.arrows){
                returnvalue = false;
                break;
            }
        }
        return returnvalue;
    }

    /**
     * DESCRIPTION: processes arrows and dynamite rolled from dice
     */
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

    /**
     * DESCRIPTION: checks if dynamite has exploded
     */
    private void checkDynamiteExplodes(BangGame game){
        if(this.dynamitecount >= 3 && !dynamiteexploded){
            this.rerollcount = 0;
            this.takeDamage(game);
            dynamiteexploded = true;
        }
    }

    /**
     * DESCRIPTION: select target to shoot
     * @param target
     * @param game
     */
    public void shootTarget(Player target, BangGame game){
        target.takeShot(this, game);
    }

    /**
     * DESCRIPTION: shoots the selected target
     * @param shooter
     * @param game
     */
    protected void takeShot(Player shooter, BangGame game){
        this.takeDamage(game);
        this.increaseTarget(shooter);
    }

    /**
     * DESCRIPTION: select target to give beer
     */
    private void giveBeer(Player target, BangGame game){
        target.takeBeer(this, game);
    }

    /**
     * DESCRIPTION: gain health if targeted by beer
     * @param giver
     * @param game
     */
    protected void takeBeer(Player giver, BangGame game){
        this.gainHealth();
        if(giver != this) decreaseTarget(giver);
    }

    /**
     * DESCRIPTION: checks if character can take damage from gatling
     */
    public void individualGatlingGunShot(){
        if(!this.character.canHaveGatlingDamageImmunity()){
            this.character.takeDamage();
        }
    }

    /**
     * DESCRIPTION: shoots gatling
     * @return
     */
    public int individualGatlingGunShoot(){
        int returnvalue = this.arrows;
        this.arrows = 0;
        return returnvalue;
    }

    /**
     * DESCRIPTION: actions preformed by player based on targets and dice rolled
     * @param game
     * @param dieindex
     * @param targetindex
     */
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

    /**
     * DESCRIPTION: checks for gatling and shoots gatling if requirements are met
     * @param game
     */
    public void preformGatlingCheckAndAction(BangGame game){
        int gatlingcheck = 0;
        
        for(int i = 0; i < game.getDice().getNumberOfDice(); i++){
            if(game.getDice().getDieAtIndex(i) == GATLING){
                gatlingcheck++;
            }
        }
        if(gatlingcheck >= this.character.getGatlingNeed()){
            game.shootGatlingGun();
            this.gatlinggunwentoff = true;
        }
    }

    /**
     * DESCRIPTION: sets target for player to shoot
     */
    private void addTarget(Player target, boolean increase){
        if(this != target)this.pointsystem[++this.highesttargetindex] = new Target(target, increase);
    }

    /**
     * DESCRIPTION: adds points to threat level
     * @param target
     */
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

    /**
     * DESCRIPTION: reduces points to threat level
     * @param target
     */
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

    /**
     * DESCRIPTION: notifies sheriff of player threat level
     * @param sheriff
     */
    public void notifySheriff(Player sheriff){
        if (sheriff != this){
            this.getTargetValue(sheriff);
            this.pointsystem[this.highesttargetindex].setMaxValue();
        }
    }

    /**
     * DESCRIPTION: if player shoots sheriff notify sheriff and raise shooters threat level
     * @param shooter
     */
    public void notifySheriffShot(Player shooter){
        increaseTarget(shooter);
    }

    /**
     * DESCRIPTION: if player helps sheriff notify sheriff and reduce helpers threat level
     * @param helper
     */
    public void notifySheriffHelped(Player helper){
        decreaseTarget(helper);
    }
    
    public void simulateReroll(BangGame game){
        int individualrerollcount = 0;
        int beercount = 0;
        int gatcount = 0;
        for(int i = 0; i < game.getDice().getNumberOfDice(); i++){
            if(game.getDice().getDieTypeAtIndex(i) == DieType.BASIC){
                if(game.getDice().getDieAtIndex(i) == BEER) {beercount++;}
                else if(game.getDice().getDieAtIndex(i) == GATLING) {gatcount++;}
            }
            if(this.rerollStrategy(game, i, beercount, gatcount)){
                if(game.getDice().getDieTypeAtIndex(i) == DieType.BASIC){
                    if(game.getDice().getDieAtIndex(i) == BEER) {beercount--;}
                    else if(game.getDice().getDieAtIndex(i) == GATLING) {gatcount--;}
                }
                if (individualrerollcount != 0){this.turnoutput += ", ";}
                this.turnoutput += game.getDice().getDieStringAtIndex(i);
                game.getDice().rollDieAtIndex(i);
                individualrerollcount++;
            }
        }
        if(individualrerollcount == 0){
            this.turnoutput += "None";
            this.rerollcount = 0;
        }
        this.turnoutput += "\n";
    }
    
    protected boolean rerollStrategy(BangGame game,int index, int beercount, int gatcount){
        boolean returnvalue = false;
        int diesymbol = game.getDice().getDieAtIndex(index);
        DieType dietype = game.getDice().getDieTypeAtIndex(index);
        
        if(dietype == DieType.LOUDMOUTH && diesymbol == BULLET){returnvalue = true;}
        
        else if (dietype == DieType.COWARD && (diesymbol == BROKENARROW || diesymbol == DOUBLEBEER)){
            if(diesymbol == BROKENARROW) {returnvalue = true;}
            else{
                if(game.getCurPlayer().getCurLife() + beercount >= game.getCurPlayer().getMaxLife()){returnvalue = true;}
            }
        }
        
        else if(dietype == DieType.DUEL && (diesymbol == WHISKEYBOTTLE || diesymbol == FIGHTADUEL)){
            if(diesymbol == WHISKEYBOTTLE){
                if(game.getCurPlayer().getCurLife() + beercount >= game.getCurPlayer().getMaxLife()){returnvalue = true;}
                else{
                    if(game.getCurNumPlayers() < 4 && this.getCurLife() < this.getMaxLife()/3 ) {returnvalue = true;}
                    else if(game.getCurNumPlayers() >= 4 && this.getCurLife() < this.getMaxLife()/2 ) {returnvalue = true;}
                }
            }
        }
        
        else{
            if(diesymbol == ARROW || (diesymbol == DYNAMITE && this.character.canRerollDynamite())) {returnvalue = true;}
            else if (diesymbol == BULLSEYE1 || diesymbol == BULLSEYE2){
                if(game.getCurNumPlayers() <= 4 && this.getCurLife() < this.getMaxLife()/3 ) {returnvalue = true;}
                else if(game.getCurNumPlayers() > 4 && this.getCurLife() < this.getMaxLife()/2 ) {returnvalue = true;}
            }
            else if (diesymbol == BEER){
                if(game.getCurPlayer().getCurLife() + beercount >= game.getCurPlayer().getMaxLife()){returnvalue = true;}
            }
            else{
                if(game.getCurNumPlayers() > 3 ){returnvalue = true;}
                else if(game.getCurNumPlayers() <= 3 && game.getCurPlayer().getArrows()  <= 3){returnvalue = true;}
            }
        }
        return returnvalue;
    }

    /**
     * DESCRIPTION: simulates turn of the player
     * @param game
     * @return
     */
    public String simulateTurn(BangGame game){
        this.turnoutput = "It was " + this.getcharactername() + "'s turn!\n";
        if(this.canRoll(game)){
            this.rollDice(game);
            this.turnoutput += this.getcharactername() + "'s initial roll was: " + game.getDice().diceToString() + "\n";
            int temprerollcount = 0;
            while(this.canRoll(game)){
                this.turnoutput += "On reroll " + ++temprerollcount + ", " + this.getcharactername() + " chose to reroll: ";
                simulateReroll(game);
                this.turnoutput += "\nOn reroll " + temprerollcount + ", " + this.getcharactername() + " rolled: " + game.getDice().diceToString() + "\n";
                this.processArrowsOrDynamite(game);
                if(this.dynamiteexploded){
                    this.turnoutput += "Dynamite Exploded, " + this.getcharactername() + " lost the rest of their rerolls!\n";
                }
                else{
                   this.decreaseRerollCount(); 
                }
            }
            if(this.canHaveAction(game)){
                //TODO Get aciton and process action
                this.turnoutput += this.getcharactername() + " chose to shoot: ";
                this.turnoutput += "\n";
                this.turnoutput += this.getcharactername() + " chose to give beer to: ";
                this.turnoutput += "\n";
                this.preformGatlingCheckAndAction(game);
                if(this.gatlinggunwentoff){
                    this.turnoutput += this.getcharactername() + " shot the gatling gun! Their Arrows were returned to the arrow pile and everyone else loses one health!\n";
                }
            }
        }
        else{
            this.turnoutput += "Error this player is not allowed a turn!\n";
        }
        
        if(this.indianattackhappened > 0){
            if(this.indianattackhappened > 1){
                this.turnoutput += this.indianattackhappened + " indian attacks happened during " + this.getcharactername() + "'s turn!";
            }
            else{
                this.turnoutput += "An indian attack happened during " + this.getcharactername() + "'s turn!";
            }
        }
        
        this.turnoutput += "\nAfter reading this player's turn output please click End Turn!\n";
        return this.turnoutput;
    }
}
