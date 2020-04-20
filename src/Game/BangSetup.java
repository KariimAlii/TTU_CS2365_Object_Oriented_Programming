/*
 * TITLE: 
 * AUTHOR: Stephen C. Devaney
 * FOR: CS 2365 Object Oriented Programming Section 001 Spring 2020
 */
package Game;

import character.*;
import java.util.Random;

/**
 *
 * @author Stephen C. Devaney
 */
public class BangSetup {
    private int numberofplayers;
    private int roleindex = 0;
    private int characterindex = 0;
    private char roles[] = {'S','R','O','O','D','O','D','R'};
    private int characters[] = {0,1,2,3,4,5,6,7};
    private Random rand;
    
    public BangSetup(int numberplayers){
        numberofplayers = numberplayers;
        shuffleRoles();
        shuffleCharacters();
    }
    
    private void shuffleRoles(){
        char temp;
        for(int i = 0; i < numberofplayers; i++){
            int randomnumb = rand.nextInt(numberofplayers);
            temp = roles[i];
            roles[i] = roles[randomnumb];
            roles[randomnumb] = temp;
        }    
    }
    
    private void shuffleCharacters(){
        int temp;
        for(int i = 0; i < characters.length; i++){
            int randomnumb = rand.nextInt(characters.length);
            temp = characters[i];
            characters[i] = characters[randomnumb];
            characters[randomnumb] = temp;
        }    
    }
    
    public char getRole(){
        return roles[++roleindex];
    }
    
    public int getNumberOfPlayers(){
        return numberofplayers;
    }
    
    public BangCharacter getCharacter(){
        BangCharacter newCharacter;
        switch(characters[characterindex]){
            case 1:{
                newCharacter = new BlackJack();
                break;
            }
            case 2:{
                newCharacter = new BlackJack();
                break;
            }
            case 3:{
                newCharacter = new BlackJack();
                break;
            }
            case 4:{
                newCharacter = new BlackJack();
                break;
            }
            case 5:{
                newCharacter = new BlackJack();
                break;
            }
            case 6:{
                newCharacter = new BlackJack();
                break;
            }
            case 7:{
                newCharacter = new BlackJack();
                break;
            }
            default:
                newCharacter = new BlackJack();
        }
        return newCharacter;
    }
}
