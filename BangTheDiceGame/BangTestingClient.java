/*
 * TITLE: Testing Client Class for Project 3 Bang The Dice Game
 * AUTHOR: Stephen C. Devaney
 * FOR: CS 2365 Object Oriented Programming Section 001 Spring 2020
 */
package BangClientClass;
import bangthedicegame.*;
import java.util.Random;
/**
 *
 * @author Stephen C. Devaney
 */
public class BangTestingClient {
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
    
    
    public void bangDiceTest(){
        System.out.println("Start of Bang Dice Test Cases-------------------------------------------------------------------------------------------------------------------------------------");
        BangDice dice = new BangDice();
        Random rand = new Random(System.currentTimeMillis());
        System.out.println("Dice when starting: " + dice.diceToString());
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
    
    
        public static void main(String[] args) {
            BangTestingClient test = new BangTestingClient();
            test.bangDieTest();
            test.bangDiceTest();
    }
}
