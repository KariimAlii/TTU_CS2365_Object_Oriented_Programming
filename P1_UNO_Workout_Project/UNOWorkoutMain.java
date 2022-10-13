/*
 * TITLE: Main Class for Project 2 UNO Workout
 * AUTHOR: Steven Lowry
 * FOR: CS 2365 Object Oriented Programming Section 001 Spring 2020
 * CLASS DESCRIPTION: class is used as and interface between the user and a 
                      workout using an deck or UNO cards
 * TEST CASES: test cases for this class are in the main function and are based 
               off of user input and can be inputted on the console for this 
               class.
 */
package UNOWorkoutMain;
import java.io.FileNotFoundException;
import unoworkout.Workout;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import unoworkout.Html;


/** Creates the interface between the user and the workout
 * @author Steven Lowry
 */
public class UNOWorkoutMain {
    
    
    /** getnumberofdecks Method
     * DESCRIPTION: used to select the number of decks to be used
     *  test case 1: 1, 2, or 3 decks returns value to calling function
     *  test case 2: anything else re asks the question
     * @param htmlfile
     * @return an integer value of the choice of the number of decks
     */
    public int getnumberofdecks(Html htmlfile){
        int choice;
        do{
            Scanner scan = new Scanner( System.in );
            System.out.println("Choose number of decks (1, 2, and 3)");
            htmlfile.addLine("Choose number of decks (1, 2, and 3)");
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
            htmlfile.addLine("Choice: " + choice);
            if (choice == 0) {htmlfile.addLine("Invaild choice please try again!");}
        }while(choice == 0);
        return choice;
    }
    
    
    /** getactioncardschoice Method
     * DESCRIPTION: decides if you want to keep in the action cards
     *  test case 1: y or Y true returns value to calling function
     *  test case 2: n or N false returns value to calling function
     *  test case 3: anything else re asks the question
     * @param htmlfile
     * @return boolean of the choice of action cards
     */
    public boolean getactioncardschoice(Html htmlfile){
        char choice;
        boolean returnvalue = false;
        do{
            Scanner scan = new Scanner( System.in );
            System.out.println("Would you like to remove actions cards. (Y or N)");
            htmlfile.addLine("Would you like to remove actions cards. (Y or N)");
            System.out.print("Choice: ");
            choice = scan.next().charAt(0);
            htmlfile.addLine("Choice: " + choice);
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
                    htmlfile.addLine("Invaild choice please try again!");
                    choice = 'a';
                    break;
                }
            }
        }while(choice == 'a');
        return returnvalue;
    }
    
    
    /** getshuffledeckschoice Method
     * DESCRIPTION: asks to shuffle the decks together if more than one deck is used
     *  test case 1: y or Y true returns value to calling function
     *  test case 2: n or N false returns value to calling function
     *  test case 3: anything else re asks the question
     * @param htmlfile
     * @return boolean for weather or not the decks are shuffled individually
     */
    public boolean getshuffledeckschoice(Html htmlfile){
        char choice;
        boolean returnvalue = false;
        do{
            Scanner scan = new Scanner( System.in );
            System.out.println("Would you like to shuffle the decks individiually instead of together. (Y or N)");
            htmlfile.addLine("Would you like to shuffle the decks individiually instead of together. (Y or N)");
            System.out.print("Choice: ");
            choice = scan.next().charAt(0);
            htmlfile.addLine("Choice: " + choice);
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
                    htmlfile.addLine("Invaild choice please try again!");
                    choice = 'a';
                    break;
                }
            }
        }while(choice == 'a');
        return returnvalue;
    }
    
    
    /** main Method
     * DESCRIPTION: is the interface between the user and the workout the main
     *              method also takes the output between the user and the console
     *              and makes a copy of it in a HTML file
     * @param args
     */
    public static void main(String[] args) {
        Html htmlfile = new Html();
        try {
            htmlfile.WriteFileOpener("UNO_Workout.html");
            htmlfile.printHeader();
            htmlfile.addOpeningParagraphTag();
            UNOWorkoutMain obj = new UNOWorkoutMain();
            int numberofdecks;
            boolean removeactioncards;
            boolean suffledeckstogether = false;
            numberofdecks = obj.getnumberofdecks(htmlfile);
            removeactioncards = obj.getactioncardschoice(htmlfile);
            if(numberofdecks > 1) {suffledeckstogether = obj.getshuffledeckschoice(htmlfile);}
            Workout workout = new Workout(numberofdecks, removeactioncards, suffledeckstogether);
            System.out.println("Starting number of cards in deck: " +  workout.leftInDeck() + "\n");
            htmlfile.addLine("Starting number of cards in deck: " +  workout.leftInDeck() + "\n");
            htmlfile.addClosingParagraphTag();
            while(workout.leftInDeck() > 0){
                htmlfile.addOpeningParagraphTag();
                workout.drawHand();
                System.out.println("Cards in hand: " + workout.handToString());
                htmlfile.addLine("Cards in hand: " + workout.handToString());
                System.out.println(workout.excerciseToString());
                htmlfile.addLine(workout.excerciseToString());
                System.out.println(workout.skippedToString());
                htmlfile.addLine(workout.skippedToString());
                System.out.println("Number of cards left in deck: " +  workout.leftInDeck());
                htmlfile.addLine("Number of cards left in deck: " +  workout.leftInDeck());
                if(workout.getBreakState() > 0) {
                    System.out.println(workout.getBreakState() + "-Minute break awarded. ENJOY!!!");
                    htmlfile.addLine(workout.getBreakState() + "-Minute break awarded. ENJOY!!!");
                    System.out.println("When finished with exercises and break. Press enter to continue...");
                }
                else {System.out.println("When finished with exercises. Press enter to continue...");}
                Scanner scan = new Scanner( System.in );
                scan.nextLine();
                htmlfile.addClosingParagraphTag();
            }
            htmlfile.addOpeningParagraphTag();
            System.out.println("Workout is over!");
            htmlfile.addLine("Workout is ended!");
            System.out.println("Stats from workout:");
            htmlfile.addLine("Stats from workout:");
            System.out.println("\t" + workout.totalRepsToString());
            htmlfile.addLine("&emsp;" + workout.totalRepsToString());
            if(!removeactioncards) {
                System.out.println("\t" + workout.totalSkippedToString());
                htmlfile.addLine("&emsp;" + workout.totalSkippedToString());
            }
            System.out.println("\t" + workout.biggestRepsEachExcercise());
            htmlfile.addLine("&emsp;" + workout.biggestRepsEachExcercise());
            if(!removeactioncards) {
                System.out.println("\t" + workout.biggestSkippedEachExcercise());
                htmlfile.addLine("&emsp;" + workout.biggestSkippedEachExcercise());
            }
            htmlfile.addClosingParagraphTag();
            htmlfile.closeFile();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(UNOWorkoutMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
