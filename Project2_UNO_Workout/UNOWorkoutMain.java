/*
 * TITLE: Main Class for Project 2 UNO Workout
 * AUTHOR: Steven Lowry
 * FOR: CS 2365 Object Oriented Programming Section 001 Spring 2020
 */
package UNOWorkoutMain;
import unoworkout.Workout;
import java.util.Scanner;
/**
 *
 * @author Steven Lowry
 */

public class UNOWorkoutMain {
    
    public int getnumberofdecks(){
        int choice;
        do{
            Scanner scan = new Scanner( System.in );
            System.out.println("Choose number of decks (1, 2, and 3)");
            System.out.print("Choice: ");
            if(scan.hasNextInt()) {choice = scan.nextInt();}
            else {choice = 0;}
            switch (choice){
                case 1: {break;}
                case 2: {break;}
                case 3: {break;}
                default: {
                    System.out.println("Invaild choice please try again!");
                    choice = 0;
                    break;
                }
            }
        }while(choice == 0);
        return choice;
    }
    
    
    public boolean getactioncardschoice(){
        char choice;
        boolean returnvalue = false;
        do{
            Scanner scan = new Scanner( System.in );
            System.out.println("Would you like to remove actions cards. (Y or N)");
            System.out.print("Choice: ");
            choice = scan.next().charAt(0);
            switch (choice){
                case 'y':
                case 'Y': {
                    returnvalue = true;
                    break;
                }
                case 'n':
                case 'N': {break;}
                default: {
                    System.out.println("Invaild choice please try again!");
                    choice = 'a';
                    break;
                }
            }
        }while(choice == 'a');
        return returnvalue;
    }
        
      
    public boolean getshuffledeckschoice(){
        char choice;
        boolean returnvalue = false;
        do{
            Scanner scan = new Scanner( System.in );
            System.out.println("Would you like to shuffle the decks together instead of individiually. (Y or N)");
            System.out.print("Choice: ");
            choice = scan.next().charAt(0);
            switch (choice){
                case 'y':
                case 'Y': {
                    returnvalue = true;
                    break;
                }
                case 'n':
                case 'N': {break;}
                default: {
                    System.out.println("Invaild choice please try again!");
                    choice = 'a';
                    break;
                }
            }
        }while(choice == 'a');
        return returnvalue;
    }
    
    public static void main(String[] args) {
        UNOWorkoutMain obj = new UNOWorkoutMain();
        int numberofdecks = 0;
        boolean removeactioncards = false;
        boolean suffledeckstogether = false;
        numberofdecks = obj.getnumberofdecks();
        removeactioncards = obj.getactioncardschoice();
        if(numberofdecks > 1) {suffledeckstogether = obj.getshuffledeckschoice();}
        Workout workout = new Workout(numberofdecks, removeactioncards, suffledeckstogether);
        while(workout.leftInDeck() > 0){
            workout.drawHand();
            System.out.println("Cards in hand: " + workout.handToString());
            System.out.println(workout.excerciseToString());
            System.out.println(workout.skippedToString());
            System.out.println("Number of cards left in deck: " +  workout.leftInDeck());
            if(workout.getBreakState()) {
                System.out.println("One Minute break awarded. ENJOY!!!");
                System.out.println("When finished with exercises and break. Press enter to continue...");
            }
            else {System.out.println("When finished with exercises. Press enter to continue...");}
            try{System.in.read();}
            catch(Exception e)
                {e.printStackTrace();}
        }
        System.out.println("Workout is over!");
        System.out.println("Stats from workout:");
        System.out.println("\t" + workout.totalRepsToString());
        if(!removeactioncards) {System.out.println("\t" + workout.totalSkippedToString());}
        System.out.println("\t" + workout.biggestRepsEachExcercise());
        if(!removeactioncards) {System.out.println("\t" + workout.biggestSkippedEachExcercise());}
    }
}
