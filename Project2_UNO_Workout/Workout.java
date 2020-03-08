/*
 * TITLE: Workout Class for Project 2 UNO Workout
 * AUTHOR: Andrew Sena
 * FOR: CS 2365 Object Oriented Programming Section 002 Spring 2020
 * CLASS DESCRIPTION: class is used to run a workout using an deck or UNO cards
 * TEST CASES: test cases for this class are held in the UNOWorkoutMain class
 */
package unoworkout;


/** A workout routine base off of an UNO Game
 * @author Andrew Sena
 */
public class Workout {
    private UNODeck deck;
    private UNOHand hand;
    private int count[] = new int[5];
    private int totalcount[] = new int[5];
    private int biggestexercise[] = new int[5];
    private int biggestskipped[] = new int[4];
    private int skippedcount[] = new int[4];
    private int totalskipped[] = new int[4];
    private int breakstate;
    
    
    /** Workout base constructor
     * @param decks
     * @param removeaction
     * @param shufflestate
     */
    public Workout(int decks, boolean removeaction, boolean shufflestate)
    {
        int temp = decks;
        if(temp <= 0) temp = 1;
        else if (temp > 3) temp = 3;
        this.deck = new UNODeck(temp,removeaction);
        this.deck.shuffleDeck(shufflestate);
        this.hand = new UNOHand();
        breakstate = 0;
    }
    
    
        
    /** Workout overloaded constructor
     * removeaction is preset to false
     * shufflestate is prest to false
     * @param decks
     */
    public Workout(int decks)
    {
        this(decks, false, false);
    }
    
    
    /** Workout overloaded constructor
     * decks is preset to 1
     * removeaction is preset to false
     * shufflestate is prest to false
     */
    public Workout()
    {
        this(1,false, false);
    }
    
    /** drawHand method
     *  DESCRIPTION: used to draw a whole hand
     *  test case 1: empties hand and draws a new one
     */
    public void drawHand()
    {
        boolean skip[] = new boolean[4];
        boolean reverse[] = new boolean[4];
        this.hand.discardHand();
        this.breakstate = 0;
        for(int i = 0; i < 4; i++)
        {
            this.skippedcount[i] = 0;
            this.count[i] = 0;
            skip[i] = false;
            reverse[i] = false;
        }
        this.count[4] = 0;
        
        for (int i = 0; i < 7; i++){
            this.hand.insertCard(i, this.deck.dealCard());
        }
        this.hand.sortHand();
        
        for(int i = 0; i < 7; i++)
        {
            if(this.hand.getCardAtIndex(i).getCardNumber() == 0) 
            {
                this.breakstate++;
            }
            else if (1 <= this.hand.getCardAtIndex(i).getCardNumber() && this.hand.getCardAtIndex(i).getCardNumber() <= 9)
            {
                this.count[this.hand.getCardAtIndex(i).getCardColor()] += this.hand.getCardAtIndex(i).getCardNumber();
            }
            else if (this.hand.getCardAtIndex(i).getCardNumber() == 10){
                skip[this.hand.getCardAtIndex(i).getCardColor()] = true;
            }
            else if (this.hand.getCardAtIndex(i).getCardNumber() == 11){
                this.count[this.hand.getCardAtIndex(i).getCardColor()] *= 2;
            }
            else if (this.hand.getCardAtIndex(i).getCardNumber() == 12){
                reverse[this.hand.getCardAtIndex(i).getCardColor()] = true;
            }
            else if (this.hand.getCardAtIndex(i).getCardNumber() > 12){
                this.count[this.hand.getCardAtIndex(i).getCardColor()] += 10;
                if (this.hand.getCardAtIndex(i).getCardNumber() == 14){
                    for(int j = 0; j < 4; j++){
                        this.count[j] *= 4;
                    }
                }
            }
        }
        
        for(int i = 0; i < 4; i++){
            if(skip[i] == true){
                this.skippedcount[i] = this.count[i];
                if (this.count[i] > this.biggestskipped[i]){
                    this.biggestskipped[i] = this.count[i];
                }
                this.count[i] = 0;
                this.totalskipped[i] += this.skippedcount[i];
            }
        }
        
        for(int i = 0; i < 4; i++){
            if(reverse[i] == true){
                for(int j = 0; j < 7; j++){
                    if(this.hand.getCardAtIndex(j).getCardColor() == i){
                        if(this.hand.getCardAtIndex(j).getCardNumber() != 12 && this.hand.getCardAtIndex(j).getCardNumber() != 0){
                            this.deck.addCardToBottom(this.hand.getCardAtIndex(j));
                        }
                    }
                }
            }
        }
        
        for(int i = 0; i < 5; i++){
            this.totalcount[i] += this.count[i];
            if (this.count[i] > this.biggestexercise[i]){
                this.biggestexercise[i] = this.count[i];
            }
        }
    }
    
    
    /** handToString method
     * DESCRIPTION: returns the whole hand to a string so it may be outputted
     * test case 1: returns the hand
     * @return returns the hand to a string
     */
    public String handToString()
    {
        return this.hand.handToString();
    }
    
    
    /** excerciseToString method
     * DESCRIPTION: returns the number of exercises to be preformed to a string so it may be outputted
     * test case 1: returns the exercise as a string
     * @return returns the exercise of the hand to a string
     */
    public String excerciseToString()
    {
        String output = "Perform ";
        for(int i = 0; i <= 4; i++)
        {
            output += this.count[i];
            switch(i){
                case 0:
                    output += " Push Ups, ";
                    break;
                case 1:
                    output += " Squats, ";
                    break;
                case 2:
                    output += " Sit Ups, ";
                    break;
                case 3:
                    output += " Lunges, ";
                    break;
                case 4:
                    output += " Burpees.";
                    break;
            }
                
        }
        return output;
    }
    
    
    /** skippedToString method
     * DESCRIPTION: returns the number of exercises to be skipped to a string so it may be outputted
     * test case 1: returns the exercise as a string
     * @return returns the skipped exercise of the hand to a string
     */
    public String skippedToString()
    {
        String output = "You skipped ";
        for(int i = 0; i <= 3; i++)
        {
            output += this.skippedcount[i];
            switch(i){
                case 0:
                    output += " Push Ups, ";
                    break;
                case 1:
                    output += " Squats, ";
                    break;
                case 2:
                    output += " Sit Ups, ";
                    break;
                case 3:
                    output += " Lunges ";
                    break;
            }   
        }
        output += "this round.";
        return output;
    }
    
    /** leftInDeck Method
     * DESCRIPTION: to get the number of cards currently in the deck
     * test case 1: returns a value greater than or equal to 0
     * @return an integer which is the number of cards in the deck
     */
    public int leftInDeck()
    {
        return this.deck.numbersCardsLeftInDeck();
    }
    
    
    /** getBreakState Method
     * DESCRIPTION: to get the how long the user will get for a break
     * test case 1: returns a value greater than or equal to 0
     * @return an integer which is how long the user gets for a break
     */
    public int getBreakState()
    {
        return this.breakstate;
    }
    
    
    /** totalRepsToString Method
     * DESCRIPTION: is used for end of workout stats
     * test case 1: outputs value equal to or greater than 0 for each exercise
     * @return string the total number of each exercises that were preformed
     */
    public String totalRepsToString()
    {
        String output = "Your total reps of each exercise are ";
        for(int i = 0; i <= 4; i++)
        {
            output += this.totalcount[i];
            switch(i){
                case 0:
                    output += " Push Ups, ";
                    break;
                case 1:
                    output += " Squats, ";
                    break;
                case 2:
                    output += " Sit Ups, ";
                    break;
                case 3:
                    output += " Lunges, ";
                    break;
                case 4:
                    output += " Burpees.";
            }   
        }
        return output;
    }
    
    /** totalSkippedToString Method
     * DESCRIPTION: is used for end of workout stats
     * test case 1: outputs value equal to or greater than 0 for each exercise
     * @return string the total number of each exercises that were skipped
     */
    public String totalSkippedToString()
    {
        String output = "Your total skipped reps are ";
        for(int i = 0; i <= 3; i++)
        {
            output += this.totalskipped[i];
            switch(i){
                case 0:
                    output += " Push Ups, ";
                    break;
                case 1:
                    output += " Squats, ";
                    break;
                case 2:
                    output += " Sit Ups, ";
                    break;
                case 3:
                    output += " Lunges.";
                    break;
            }   
        }
        return output;
    }
    
    
    /** biggestRepsEachExcercise Method
     * DESCRIPTION: is used for end of workout stats
     * test case 1: outputs value equal to or greater than 0 for each exercise
     * @return string the biggest number of each exercises that were preformed
     */
    public String biggestRepsEachExcercise()
    {
        String output = "Your largest number of reps in a single hand for every exercise is ";
        for(int i = 0; i <= 4; i++)
        {
            output += this.biggestexercise[i];
            switch(i){
                case 0:
                    output += " Push Ups, ";
                    break;
                case 1:
                    output += " Squats, ";
                    break;
                case 2:
                    output += " Sit Ups, ";
                    break;
                case 3:
                    output += " Lunges, ";
                    break;
                case 4:
                    output += " Burpees.";
            }   
        }
        return output;
    }
    
    
    /** biggestSkippedEachExcercise Method
     * DESCRIPTION: is used for end of workout stats
     * test case 1: outputs value equal to or greater than 0 for each exercise
     * @return string the biggest number of each exercises that were skipped
     */
    public String biggestSkippedEachExcercise()
    {
        String output = "Your largest number of reps skipped in a single hand for every exercise is ";
        for(int i = 0; i < 4; i++)
        {
            output += this.biggestskipped[i];
            switch(i){
                case 0:
                    output += " Push Ups, ";
                    break;
                case 1:
                    output += " Squats, ";
                    break;
                case 2:
                    output += " Sit Ups, ";
                    break;
                case 3:
                    output += " Lunges. ";
                    break;
            }   
        }
        return output;
    }    
}
