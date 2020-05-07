/*
 * TITLE: Testing Client Class for Project 3 Bang The Dice Game
 * AUTHOR: Stephen C. Devaney
 * FOR: CS 2365 Object Oriented Programming Section 001 Spring 2020
 */
package BangClientClass;
import dice.*;
import character.*;
import java.util.Random;
import player.*;
/**
 * Testing class for all individual classes
 * @author Stephen C. Devaney
 */
public class BangTestingClient {

    /**
     * Test all of the individual die classes
     */
    public void bangDieTest(){
        System.out.println("Start of Bang Die Test Cases-------------------------------------------------------------------------------------------------------------------------------------");
        BangDie die1 = new BangDie();
        BangDie die2 = new BangDie();
        System.out.println("Dice in loop (#1 is invalid input):");
        for(int i = 0; i < 7; i++){
            die1.setDie(i);
            System.out.println((i+1) + ".) Die Value: " + die1.getDieSymbol() + " \"" + die1.dieToString() + "\"");
            if(die1.equals(die2)) System.out.println("\t" + die1.dieToString() + " equals " + die2.dieToString());
            else System.out.println("\t" + die1.dieToString() + " does not equal " + die2.dieToString());
        }
        System.out.println("End of loop");
        System.out.println("Die 2 state before processing: " + die2.getDieState());
        die2.processDie();
        System.out.println("Die 2 state after processing: " + die2.getDieState());
        System.out.println("End of Bang Die Test Cases-------------------------------------------------------------------------------------------------------------------------------------");
    }
    
    /**
     * Test the dice class
     */
    public void bangDiceTest(){
        System.out.println("Start of Bang Dice Test Cases-------------------------------------------------------------------------------------------------------------------------------------");
        BangDice dice = new BangDice(true);
        Random rand = new Random(System.currentTimeMillis());
        System.out.println("Dice when starting: " + dice.diceToString());
        dice.rollDice();
        System.out.println("Dice when after rolling all: " + dice.diceToString());
        dice.rollDice();
        System.out.println("Dice when after rolling all: " + dice.diceToString());
        dice.rollDice();
        System.out.println("Dice when after rolling all: " + dice.diceToString());
        dice.rollDice();
        System.out.println("Dice when after rolling all: " + dice.diceToString());
        dice.rollDice();
        System.out.println("Dice when after rolling all: " + dice.diceToString());
        dice.rollDice();
        System.out.println("Dice when after rolling all: " + dice.diceToString());
        dice.rollDieAtIndex(3);
        System.out.println("Dice when after rolling index 3: " + dice.diceToString());
        System.out.print("Testing random number generator: ");
        for(int i = 0; i < 10; i++){
            System.out.print(rand.nextInt(6)+1 + " ");
        }
        System.out.println("");
        System.out.println("End of Bang Dice Test Cases-------------------------------------------------------------------------------------------------------------------------------------");
    }
    
    /**
     *Test the character class(abstract) with the ApacheKid class(extends character)
     */
    public void bangCharacterTest(){
        System.out.println("Start of Bang Character Test Cases------------------------------------------------------------------------------------------------------------------------------");
        ApacheKid apachekid = new ApacheKid();
        System.out.println("Apache Kid take damage");
        apachekid.takeDamage();
        System.out.println("Apache Kid's life points: " + apachekid.getCurLifePoints());
        System.out.println("Apache Kid's max life points: " + apachekid.getMaxLifePoints());
        apachekid.setSheriff();
        System.out.println("Apache Kid as sheriff life points: " + apachekid.getCurLifePoints());
        System.out.println("Apache Kid as sheriff max life points: " + apachekid.getMaxLifePoints());
        System.out.println("Is Apache Kid dead: "+ apachekid.isDead());
        apachekid.gainLife();
        System.out.println("Apache Kid's life after healing: " + apachekid.getCurLifePoints());
        System.out.println("Can Apache Kid re roll dynamite: "+ apachekid.canRerollDynamite());
        System.out.println("Can Apache Kid gain two life from beer: "+apachekid.canLowLifeDoubleBeer());
        System.out.println("Can Apache Kid reduce and Indian attack damage: "+apachekid.canReducedIndianAttack());
        System.out.println("Can Apache Kid have an extra re roll: "+apachekid.canHaveExtraReroll());
        System.out.println("Can Apache Kid negate gatling damage: "+apachekid.canHaveGatlingDamageImmunity());
        System.out.println("Can Apache Kid discard an arrow if life is lost: "+apachekid.canDiscardArrowIfLoseLife());
        System.out.println("Can Apache Kid give life to any player: "+apachekid.canGiveAnyPlayerLife());
        System.out.println("Can Apache Kid activate Gatling with only 2 symbols: "+apachekid.getGatlingNeed());
        System.out.println("Can Apache Kid use whiskey twice: "+apachekid.canUseWhiskeyTwice());
        System.out.println("Can Apache Kid exchange one dynamite for gatling: "+apachekid.canExchangeOneDynamiteforGatling());
        System.out.println("Can Apache Kid take Chief's arrow when arrow is rolled: "+apachekid.canTakeChiefsArrowFromPlayerWhenRollArrow());
        System.out.println("Can Apache Kid apply arrow results after re rolls: "+apachekid.canApplyArrowResultsAfterRerolls());
        
        BlackJack blackjack = new BlackJack();
        System.out.println("Black Jack take damage");
        blackjack.takeDamage();
        System.out.println("Black Jack life points: " + blackjack.getCurLifePoints());
        System.out.println("Black Jack max life points: " + blackjack.getMaxLifePoints());
        blackjack.setSheriff();
        System.out.println("Black Jack as sheriff life points: " + blackjack.getCurLifePoints());
        System.out.println("Black Jack as sheriff max life points: " + blackjack.getMaxLifePoints());
        System.out.println("Is Black Jack dead: "+ blackjack.isDead());
        blackjack.gainLife();
        System.out.println("Black Jack life after healing: " + blackjack.getCurLifePoints());
        System.out.println("Can Black Jack re roll dynamite: "+ blackjack.canRerollDynamite());
        System.out.println("Can Black Jack gain two life from beer: "+blackjack.canLowLifeDoubleBeer());
        System.out.println("Can Black Jack reduce and Indian attack damage: "+blackjack.canReducedIndianAttack());
        System.out.println("Can Black Jack have an extra re roll: "+blackjack.canHaveExtraReroll());
        System.out.println("Can Black Jack negate gatling damage: "+blackjack.canHaveGatlingDamageImmunity());
        System.out.println("Can Black Jack discard an arrow if life is lost: "+blackjack.canDiscardArrowIfLoseLife());
        System.out.println("Can Black Jack give life to any player: "+blackjack.canGiveAnyPlayerLife());
        System.out.println("Can Black Jack activate Gatling with only 2 symbols: "+blackjack.getGatlingNeed());
        System.out.println("Can Black Jack use whiskey twice: "+blackjack.canUseWhiskeyTwice());
        System.out.println("Can Black Jack exchange one dynamite for gatling: "+blackjack.canExchangeOneDynamiteforGatling());
        System.out.println("Can Black Jack take Chief's arrow when arrow is rolled: "+blackjack.canTakeChiefsArrowFromPlayerWhenRollArrow());
        System.out.println("Can Black Jack apply arrow results after re rolls: "+blackjack.canApplyArrowResultsAfterRerolls());
        
        GregDigger gregdigger = new GregDigger();
        System.out.println("Greg Digger take damage");
        gregdigger.takeDamage();
        System.out.println("Greg Digger life points: " + gregdigger.getCurLifePoints());
        System.out.println("Greg Digger max life points: " + gregdigger.getMaxLifePoints());
        gregdigger.setSheriff();
        System.out.println("Greg Digger as sheriff life points: " + gregdigger.getCurLifePoints());
        System.out.println("Greg Digger as sheriff max life points: " + gregdigger.getMaxLifePoints());
        System.out.println("Is Greg Digger dead: "+ gregdigger.isDead());
        gregdigger.gainLife();
        System.out.println("Greg Digger life after healing: " + gregdigger.getCurLifePoints());
        System.out.println("Can Greg Digger re roll dynamite: "+ gregdigger.canRerollDynamite());
        System.out.println("Can Greg Digger gain two life from beer: "+gregdigger.canLowLifeDoubleBeer());
        System.out.println("Can Greg Digger reduce and Indian attack damage: "+gregdigger.canReducedIndianAttack());
        System.out.println("Can Greg Digger have an extra re roll: "+gregdigger.canHaveExtraReroll());
        System.out.println("Can Greg Digger negate gatling damage: "+gregdigger.canHaveGatlingDamageImmunity());
        System.out.println("Can Greg Digger discard an arrow if life is lost: "+gregdigger.canDiscardArrowIfLoseLife());
        System.out.println("Can Greg Digger give life to any player: "+gregdigger.canGiveAnyPlayerLife());
        System.out.println("Can Greg Digger activate Gatling with only 2 symbols: "+gregdigger.getGatlingNeed());
        System.out.println("Can Greg Digger use whiskey twice: "+gregdigger.canUseWhiskeyTwice());
        System.out.println("Can Greg Digger exchange one dynamite for gatling: "+gregdigger.canExchangeOneDynamiteforGatling());
        System.out.println("Can Greg Digger take Chief's arrow when arrow is rolled: "+gregdigger.canTakeChiefsArrowFromPlayerWhenRollArrow());
        System.out.println("Can Greg Digger apply arrow results after re rolls: "+gregdigger.canApplyArrowResultsAfterRerolls());
        System.out.println("End of Bang Character Test Cases--------------------------------------------------------------------------------------------------------------------------------");
    }
    
    /**
     *Test the player classes 
     */
    public void bangPlayerTest(){
        System.out.println("Start of Bang Player Test Cases---------------------------------------------------------------------------------------------------------------------------------");
        Player player = new HumanPlayer(new WillyTheKid(), Role.RENEGADE,4);
        System.out.println("Willy the Kid character created.");
        System.out.println("Willy the Kid role: "+ player.getRole());
        System.out.println("Willy the Kid player type: "+player.getPlayerType());
        System.out.println("Willy the Kid reroll count: "+player.getRerollCount());
        System.out.println("Willy the Kid character name: "+player.getcharactername());
        System.out.println("Willy the Kid current life: "+player.getCurLife());
        System.out.println("Willy the Kid max life: "+player.getMaxLife());
        System.out.println("Willy the Kid life progress: "+player.getLifeProgress());
        System.out.println("Willy the Kid previous player: "+player.getPreviousPlayer());
        System.out.println("Willy the Kid next player: "+player.getNextPlayer());
        System.out.println("Willy the Kid number of arrows: "+player.getArrows());
        System.out.println("Willy the Kid is dead? "+player.isPlayerDead());
        
        System.out.println("Willy the Kid Deputy Computer Player");
        player = new DeputyComputer(new BlackJack(),4);
        System.out.println(player.getcharactername() + " character created.");
        System.out.println(player.getcharactername() + " role: "+ player.getRole());
        System.out.println(player.getcharactername() + " player type: "+player.getPlayerType());
        System.out.println(player.getcharactername() + " reroll count: "+player.getRerollCount());
        System.out.println(player.getcharactername() + " character name: "+player.getcharactername());
        System.out.println(player.getcharactername() + " current life: "+player.getCurLife());
        System.out.println(player.getcharactername() + " max life: "+player.getMaxLife());
        System.out.println(player.getcharactername() + " life progress: "+player.getLifeProgress());
        System.out.println(player.getcharactername() + " previous player: "+player.getPreviousPlayer());
        System.out.println(player.getcharactername() + " next player: "+player.getNextPlayer());
        System.out.println(player.getcharactername() + " number of arrows: "+player.getArrows());
        System.out.println(player.getcharactername() + " is dead? "+player.isPlayerDead());
        System.out.println("End of Bang Player Test Cases-----------------------------------------------------------------------------------------------------------------------------------");
    }
    
    /**
     * main method when testing will take place
     * @param args
     */
    public static void main(String[] args) {
        BangTestingClient test = new BangTestingClient();
        test.bangDiceTest();
        test.bangCharacterTest();
        test.bangPlayerTest();
    }
}
