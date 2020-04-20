/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package player;

import Game.BangGame;
import character.*;

/**
 *
 * @author anamo
 */
public class HumanPlayer extends Player {
    
    public HumanPlayer(BangCharacter character, char role){
        super(PlayerType.Human, character, role); // This is constructor logic can keep this
    }
    //TO DO Whole Class
    
    public Player getSelectedB1(BangGame game){
        BangCharacter temp2 = new BlackJack(); // Just here to get rid or temp errors this is not logic
        Player temp = new HumanPlayer(temp2,'s'); // Just here to get rid or temp errors this is not logic
        return temp; // Just here to get rid or temp errors this is not logic
        // TO DO Whole Method
    }
    
    public Player getSelectedB2(BangGame game){
        BangCharacter temp2 = new BlackJack(); // Just here to get rid or temp errors this is not logic
        Player temp = new HumanPlayer(temp2,'s'); // Just here to get rid or temp errors this is not logic
        return temp;
        // TO DO Whole Method
    }
}
