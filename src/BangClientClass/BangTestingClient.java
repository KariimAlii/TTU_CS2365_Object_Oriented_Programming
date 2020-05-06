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
     * main method when testing will take place
     * @param args
     */
    public static void main(String[] args) {
        BangTestingClient test = new BangTestingClient();
        test.bangDiceTest();
    }
}
