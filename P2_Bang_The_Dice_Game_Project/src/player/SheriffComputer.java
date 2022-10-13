/*
 * TITLE: Project 3 Bang The Dice Game
 * AUTHOR: Andrew Sena
 * FOR: CS 2365 Object Oriented Programming Section 001 Spring 2020
 */
package player;
import Game.BangGame;
import character.*;

/**
 * DESCRIPTION: A class for a computer player with the role of a sheriff
 * @author raime
 */
public class SheriffComputer extends Player {
    
    /**
     * constructor for SheriffComputer
     * @param character character selected for the game
     * @param startingnumberofplayers number of players selected for the game
     */
    public SheriffComputer(BangCharacter character, int startingnumberofplayers){
        super(PlayerType.COMPUTER,character,Role.SHERIFF,startingnumberofplayers);
    }
    
    /**
     * DESCRIPTION: override method for the computer sheriff getting shot 
     * @param shooter the player shooting the sheriff
     * @param game overall game functionality
     */
    @Override
    protected void takeShot(Player shooter, BangGame game){
        this.takeDamage(game);
        this.increaseTarget(shooter);
        game.sheriffShot();
    }
    
    /**
     * DESCRIPTION: override method for the computer sheriff getting helped
     * @param giver the player giving help to the sheriff
     * @param game overall game functionality
     */
    @Override
    protected void takeBeer(Player giver, BangGame game){
        this.gainHealth();
        if(giver != this) {
            decreaseTarget(giver);
            game.sheriffHelped();
        }
    }
}
