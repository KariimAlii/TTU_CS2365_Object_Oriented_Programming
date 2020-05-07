/*
 * TITLE: Project 3 Bang The Dice Game
 * AUTHOR: Stephen C. Devaney
 * COLLABORATORS: Andrew Sena
 * FOR: CS 2365 Object Oriented Programming Section 001 Spring 2020
 */
package Game;

import character.*;
import java.util.Random;
import player.Role;

/**
 * Provides setup information from a previous scene
 * @author Stephen C. Devaney
 */
public class BangSetup {
    private int numberofplayers;
    private int roleindex = 0;
    private int characterindex = 0;
    private char roles[] = {'S','R','O','O','D','O','D','R'};
    private int characters[] = {0,1,2,3,4,5,6,7};
    private Random rand;
    private boolean undeadoralivemodule;
    private boolean indianchiefarrowmodule;
    private boolean loudmouthandcoward;
    private boolean dueldice;
    
    /**
      constructor for the setup
     */
    public BangSetup(int numberplayers){
        rand = new Random(System.currentTimeMillis());
        this.indianchiefarrowmodule = false;
        this.undeadoralivemodule = false;
        dueldice = false;
        loudmouthandcoward = false;
        numberofplayers = numberplayers;
        shuffleRoles();
        shuffleCharacters();
    }
    
    /**
     * shuffles the roles for the game
     */
    private void shuffleRoles(){
        char temp;
        for(int i = 0; i < numberofplayers; i++){
            int randomnumb = rand.nextInt(numberofplayers);
            temp = roles[i];
            roles[i] = roles[randomnumb];
            roles[randomnumb] = temp;
        }    
    }
    
    
    /**
     * shuffles the characters for the game
     */
    private void shuffleCharacters(){
        int temp;
        for(int i = 0; i < characters.length; i++){
            int randomnumb = rand.nextInt(characters.length);
            temp = characters[i];
            characters[i] = characters[randomnumb];
            characters[randomnumb] = temp;
        }    
    }
    
    /**
     * gets the next available role
     * @return
     */
    public Role getRole(){
        Role role;
        switch(roles[roleindex]){
            case 'S':{
                role = Role.SHERIFF;
                break;
            }
            case 'R':{
                role = Role.RENEGADE;
                break;
            }
            case 'D':{
                role = Role.DEPUTY;
                break;
            }
            default:
                role = Role.OUTLAW;
        }
        roleindex++;
        return role;
    }
    
    /**
     * gets the users chosen number of  players
     * @return
     */
    public int getNumberOfPlayers(){
        return numberofplayers;
    }
    
    /**
     * gets the next available character
     * @return
     */
    public BangCharacter getCharacter(){
        BangCharacter newCharacter;
        switch(characters[characterindex]){
            case 1:{
                newCharacter = new JesseJones();
                break;
            }
            case 2:{
                newCharacter = new Jourdonnais();
                break;
            }
            case 3:{
                newCharacter = new LuckyDuke();
                break;
            }
            case 4:{
                newCharacter = new PaulRegret();
                break;
            }
            case 5:{
                newCharacter = new PedroRamirez();
                break;
            }
            case 6:{
                newCharacter = new SidKetchum();
                break;
            }
            case 7:{
                newCharacter = new WillyTheKid();
                break;
            }
            case 8:{
                newCharacter = new ApacheKid();
                break;
            }
            case 9:{
                newCharacter = new BillNoface();
                break;
            }
            case 10:{
                newCharacter = new BelleStar();
                break;
            }
            case 11:{
                newCharacter = new GregDigger();
                break;
            }
            default:
                newCharacter = new BlackJack();
        }
        characterindex++;
        return newCharacter;
    }
    
    /**
     * adds the old saloon characters to the game
     */
    public void addOldSaloonCharacters(){
        int newarray[] = new int[this.characters.length + 2];
        for(int i = 0; i < this.characters.length; i++){
            newarray[i] = this.characters[i];
        }
        newarray[this.characters.length] = 8;
        newarray[this.characters.length + 1] = 9;
        this.characters = newarray;
        this.shuffleCharacters();
    }
    
    /**
     * adds the undead or alive characters to the game
     */
    public void addUndeadOrAliveCharacters(){
        int newarray[] = new int[this.characters.length + 2];
        for(int i = 0; i < this.characters.length; i++){
            newarray[i] = this.characters[i];
        }
        newarray[this.characters.length] = 10;
        newarray[this.characters.length + 1] = 11;
        this.characters = newarray;
        this.shuffleCharacters();
    }
    
    /**
     * activates the chief arrow for the game
     */
    public void activeIndianChiefArrow(){
        this.indianchiefarrowmodule = true;
    }
    
    /**
     * gets the chief arrow for start up
     * @return
     */
    public boolean getIndianChiefArrow(){
        return this.indianchiefarrowmodule;
    }
    
    /**
     * activates the loudmouth and the coward for the game
     */
    public void activeloudmouthandcoward(){
        this.loudmouthandcoward = true;
    }
    
    /**
     * gets the loudmouth and the coward for start up
     * @return
     */
    public boolean getloudmouthandcoward(){
        return this.loudmouthandcoward;
    }
    
    /**
     * activates the dueldice for the game
     */
    public void activatedueldice(){
        this.dueldice = true;
    }
    
    /**
     * gets the dueldice for start up
     * @return
     */
    public boolean getdueldice(){
        return this.dueldice;
    }
}
