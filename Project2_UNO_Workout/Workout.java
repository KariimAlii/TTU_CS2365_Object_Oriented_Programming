/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unoworkout;


/**
 *
 * @author raime
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
    private boolean breakstate;
    
    
    public Workout(int decks, boolean removeaction, boolean shufflestate)
    {
        int temp = decks;
        if(temp <= 0) temp = 1;
        else if (temp > 3) temp = 3;
        this.deck = new UNODeck(temp,removeaction);
        this.deck.shuffleDeck(shufflestate);
        this.hand = new UNOHand();
        breakstate = false;
    }
    
    public Workout(int decks)
    {
        this(decks, false, false);
    }
    
    public Workout()
    {
        this(1,false, false);
    }
    
    public void drawHand()
    {
        boolean skip[] = new boolean[4];
        boolean reverse[] = new boolean[4];
        this.hand.discardHand();
        for(int i = 0; i < 4; i++)
        {
            this.skippedcount[i] = 0;
            this.count[i] = 0;
            skip[i] = false;
            reverse[i] = false;
        }
        this.count[4] = 0;
        
        for (int i = 0; i < 7; i++)
        {
            this.hand.insertCard(i, this.deck.dealCard());
        }
        this.hand.sortHand();
        
        for(int i = 0; i < 7; i++)
        {
            if(this.hand.getCardAtIndex(i).getCardNumber() == 0) 
            {
                this.breakstate = true;
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
                this.count[i] = 0;
                this.totalskipped[i] += this.skippedcount[i];
                if (this.skippedcount[i] > this.biggestskipped[i])
                {
                    this.biggestskipped[i] = this.count[i];
                }
            }
        }
        
        for(int i = 0; i < 4; i++){
            if(reverse[i] == true){
                boolean foundfirst = false;
                for(int j = 0; j < 7; j++){
                    if(this.hand.getCardAtIndex(j).getCardColor() == i){
                        if(this.hand.getCardAtIndex(j).getCardNumber() != 12){
                            this.deck.addCardToBottem(this.hand.getCardAtIndex(j));
                        }
                        else{
                            if(foundfirst){
                                this.deck.addCardToBottem(this.hand.getCardAtIndex(j));
                            }
                            else{
                                foundfirst = true;
                            }
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
    
    public String handToString()
    {
        return this.hand.handToString();
    }
    
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
                    output += " Lunges.";
                    break;
            }   
        }
        return output;
    }
    
    public int leftInDeck()
    {
        return this.deck.numbersCardsLeftInDeck();
    }
    
    public boolean getBreakState()
    {
        boolean temp = this.breakstate;
        this.breakstate = false;
        return temp;
    }
    
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
    
    public static void main(String[] args) {
        Workout workout = new Workout();
        workout.drawHand();
        System.out.println(workout.handToString());
        System.out.println(workout.excerciseToString());
        System.out.println(workout.skippedToString());
        System.out.println(workout.leftInDeck());
        System.out.println(workout.getBreakState());
        System.out.println(workout.totalRepsToString());
        System.out.println(workout.totalSkippedToString());
        System.out.println(workout.biggestRepsEachExcercise());
        System.out.println(workout.biggestSkippedEachExcercise());
    }
    
}
