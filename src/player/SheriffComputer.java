/*
 * TITLE: Project 3 Bang The Dice Game
 * AUTHOR: Andrew Sena
 * FOR: CS 2365 Object Oriented Programming Section 001 Spring 2020
 */
package player;
import Game.BangGame;
import character.*;

/**
 *
 * @author raime
 */
public class SheriffComputer extends Player {
    
    public SheriffComputer(BangCharacter character, int startingnumberofplayers){
        super(PlayerType.COMPUTER,character,Role.SHERIFF,startingnumberofplayers);
    }
    
    @Override
    protected void takeShot(Player shooter, BangGame game){
        this.takeDamage(game);
        this.increaseTarget(shooter);
        game.sheriffShot();
    }
    
    @Override
    protected void takeBeer(Player giver, BangGame game){
        this.gainHealth();
        if(giver != this) {
            decreaseTarget(giver);
            game.sheriffHelped();
        }
    }
}
