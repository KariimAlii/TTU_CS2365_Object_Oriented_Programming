/*
 * TITLE: 
 * AUTHOR: Stephen C. Devaney
 * FOR: CS 2365 Object Oriented Programming Section 001 Spring 2020
 */
package player;

import Game.BangGame;
import character.*;
import java.util.Random;

/**
 *
 * @author Stephen C. Devaney
 */
public class RandomComputer extends Player {
    private final Random rand;
    
    public RandomComputer(BangCharacter character, Role role, int numberofplayers){
        super(PlayerType.COMPUTER, character, role, numberofplayers); // This is constructor logic can keep this
        rand = new Random(System.currentTimeMillis());
    }
    
    @Override
    public Player getSelectedB1(BangGame game){
        Player curtargets[] = getTargetsB1(game);
        return curtargets[rand.nextInt(curtargets.length)];
    }
    
    @Override
    public Player getSelectedB2(BangGame game){
        Player curtargets[] = getTargetsB2(game);
        return curtargets[rand.nextInt(curtargets.length)];
    }
}
