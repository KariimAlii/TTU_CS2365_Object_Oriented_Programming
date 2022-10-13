/*
 * TITLE: Deck Class for Project 2 UNO Workout
 * AUTHOR: Stephen C. Devaney
 * FOR: CS 2365 Object Oriented Programming Section 001 Spring 2020
 * CLASS DESCRIPTION: class is used to hold an deck of UNO cards for an UNO game
                      or workout.
 * TEST CASES: test cases for this class are held in the workoutclassclient class
 */
package unoworkout;
import java.util.Random;
import static unoworkout.UNOCard.BLUECARD;
import static unoworkout.UNOCard.BLACKCARD;
import static unoworkout.UNOCard.SKIP;
import static unoworkout.UNOCard.WILD;
import static unoworkout.UNOCard.WILDDRAW4;


/** A deck of UNO Cards to be used for a game or a workout
 * @author Stephen C. Devaney
 */
public class UNODeck {
    private UNOCard[] unocarddeck; // deck of Uno cards
    private int currenttopcard; // index of the current top card
    private int currentbottomcard; // index of the current bottom card
    private int numbercardsleftindeck; // total number of cards left in the deck
    private int numberofcardsineachdeck; // number of cards in each deck
    private int numberofdecks; // total number of decks
    
    
    /** UNODeck Base Constructor
     * DESCRIPTION: creates decks base of the number given and weather or not 
     *              actions cards are to be removed
     * test case 1: decks is less than or equal to 0 a group defined null deck 
     *              is created
     * test case 2: number of decks is greater than 0 and removeactioncards 
     *              boolean is true the number of decks is created without 
     *              action cards
     * test case 3: number of decks is greater than 0 and removeactioncards 
     *              boolean is false the number of decks is created with 
     *              action cards
     * @param decks     number of decks to be used
     * @param removeactioncards    true removes action cards false keeps 
     *                             action cards
     */
    public UNODeck(int decks, boolean removeactioncards){
        numberofdecks = decks;
        if (numberofdecks > 0){
            int i = 0;
            int stopcard;
            if (removeactioncards) {
                numberofcardsineachdeck = 76;
                numbercardsleftindeck = numberofcardsineachdeck * numberofdecks;
                stopcard = SKIP;
            }
            else {
                numberofcardsineachdeck = 108;
                numbercardsleftindeck = numberofcardsineachdeck * numberofdecks;
                stopcard = WILD;
            }
            currenttopcard = 0;
            currentbottomcard = numbercardsleftindeck - 1;
            unocarddeck = new UNOCard[numbercardsleftindeck];
            for(int numofdecks = 0; numofdecks < numberofdecks; numofdecks++){
                for(int color = BLUECARD; color < BLACKCARD; color++){
                    unocarddeck[i++] = new UNOCard(color, 0);
                    for(int number = 1; number < stopcard; number++){
                        unocarddeck[i++] = new UNOCard(color, number);
                        unocarddeck[i++] = new UNOCard(color, number);
                    }
                }
                if(!removeactioncards){
                    for(int count = 0; count < 4; count++) {unocarddeck[i++] = new UNOCard(BLACKCARD, WILD);}
                    for(int count = 0; count < 4; count++) {unocarddeck[i++] = new UNOCard(BLACKCARD, WILDDRAW4);}
                }
            }
        }
        else{
            unocarddeck = new UNOCard[1];
            unocarddeck[0] = new UNOCard(-1,-1);
        }
    }
    

    /** UNODeck Overloaded Constructor
     * removeactioncards parameter is preset to false
     * test cases are seen in base constructor since this is constructor overloading
     * @param numberofdecks     number of decks to be used
     */
    public UNODeck(int numberofdecks){
        this(numberofdecks, false);
    }
    
    
    /** UNODeck Overloaded Constructor
     * numberofdecks to be used is preset to 1
     * test cases are seen in base constructor since this is constructor overloading
     * @param removeactioncards    true removes action cards false keeps action cards
     */
    public UNODeck(boolean removeactioncards){
        this(1, removeactioncards);
    }
    
    
    /** UNODeck Overloaded Constructor
     * numberofdecks to be used is preset to 1
     * removeactioncards parameter is preset to false
     * test cases are seen in base constructor since this is constructor overloading
     */
    public UNODeck(){
        this(1, false);
    }
    
    
    /** isNullDeck method
     * DESCRIPTION: checks if deck is initialized
     * test case 1: if deck is designer defined null return true
     * test case 2: if deck is not designer defined null return false
     * @return boolean true if the deck is null false otherwise
     */
    public boolean isNullDeck(){
        return ((this.unocarddeck.length == 1 && this.unocarddeck[0].isNullCard()));
    }
    
    
    /** isEmptyDeck method
     * ADDITIONAL PARAMETER: the deck needs to be initialized
     * DESCRIPTION: checks if deck is empty
     * test case 1: if deck is empty return true
     * test case 2: if deck is not empty return false
     * @return boolean true if the deck is empty false otherwise
     */
    public boolean isEmptyDeck(){
        return (this.numbercardsleftindeck <= 0);
    }
    
    
    /** isFullDeck method
     * ADDITIONAL PARAMETER: the deck needs to be initialized
     * DESCRIPTION: checks if deck is full
     * test case 1: if deck is full return true
     * test case 2: if deck is not full return false
     * @return boolean true if the deck is full false otherwise
     */
    public boolean isFullDeck(){
        return (this.numbercardsleftindeck == this.numberofcardsineachdeck * this.numberofdecks);
    }
    
    
    /** shuffleDeck method
     * ADDITIONAL PARAMETER: the deck needs to be initialized
     * DESCRIPTION: shuffles decks
     * test case 1: if state is true shuffle decks individually
     * test case 2: if state is false shuffle decks together
     * @param state // true if shuffling deck individually false if shuffling together
     */
    public void shuffleDeck(boolean state){
        if(!this.isNullDeck()){
            if(!this.isEmptyDeck()){
                Random rand = new Random(System.currentTimeMillis());
                if(!state){ // shuffle whole deck together
                    for(int i = this.currenttopcard; i < (this.unocarddeck.length); i++){
                        int random1 = rand.nextInt(this.unocarddeck.length);
                        while (random1 == i || this.unocarddeck[random1].compareCard(this.unocarddeck[i]) == 0) {
                            random1 = rand.nextInt(this.unocarddeck.length);
                        }
                        UNOCard temp = new UNOCard();
                        temp.copyCard(this.unocarddeck[random1]);
                        this.unocarddeck[random1].copyCard(this.unocarddeck[i]);
                        this.unocarddeck[i].copyCard(temp);
                    }
                }
                else // shuffle decks individually
                {
                    for(int count = 0; count < this.numberofdecks; count++){
                        int modifier = count * this.numberofcardsineachdeck;
                        int i = this.currenttopcard;
                        if (i > this.numberofcardsineachdeck - modifier){i = 0;}
                        for(; i < this.numberofcardsineachdeck + modifier; i++){
                            int random1 = rand.nextInt(this.numberofcardsineachdeck) + modifier;
                            while (random1 == i || this.unocarddeck[random1].compareCard(this.unocarddeck[i]) == 0 || random1 < this.currenttopcard) {
                                random1 = rand.nextInt(this.numberofcardsineachdeck) + modifier;
                            }
                            UNOCard temp = new UNOCard();
                            temp.copyCard(this.unocarddeck[random1]);
                            this.unocarddeck[random1].copyCard(this.unocarddeck[i]);
                            this.unocarddeck[i].copyCard(temp);
                        }
                    }
                }
            }
            else {System.out.println("Deck is empty!");} 
        }
        else {System.out.println("No deck need to intialize deck first!");}
    }
    
    
    /** shuffleDeck overloaded method
     * state is preset to false so decks will be shuffled together
     * ADDITIONAL PARAMETER: the deck needs to be initialized
     * DESCRIPTION: shuffles decks
     * test cases are the same as other suffle deck as this is an over loaded method
     */
    public void shuffleDeck(){
        this.shuffleDeck(false);
    }
    
    
    /** displayDeck method
     * ADDITIONAL PARAMETER: the deck needs to be initialized
     * DESCRIPTION: displays the deck
     * test case 1: Deck is empty display "Deck is empty!"
     * test case 2: Deck is not empty display the whole deck
     */
    public void displayDeck(){
        if(!this.isNullDeck()){
            if(!this.isEmptyDeck()){
                int i = this.currenttopcard;
                int count = 0;
                if(i <= this.currentbottomcard) {for(; i <= this.currentbottomcard; i++){
                    System.out.println(++count + ": " + this.unocarddeck[i].cardToString());}
                }
                else{
                    for(; i < this.unocarddeck.length; i++){System.out.println(++count + ": " + this.unocarddeck[i].cardToString());}
                    for(i = 0; i <= this.currentbottomcard; i++){System.out.println(++count + ": " + this.unocarddeck[i].cardToString());}
                }
            }
            else {System.out.println("Deck is empty!");} 
        }
        else {System.out.println("No deck. Need to intialize deck first!");}
    }
    
    
    /** dealCard method
     * ADDITIONAL PARAMETER: the deck needs to be initialized
     * DESCRIPTION: removes and returns the top card of the deck
     * test case 1: if deck is empty a designer defined null card is returned
     * test case 2: if the deck is not empty the top card is returned, the index
     *              is incremented, and the number left in the deck is decremented
     * @return UNOCard
     */
    public UNOCard dealCard(){
        UNOCard topcard = new UNOCard();
        if(!this.isNullDeck()){
            if (!this.isEmptyDeck()) {
                topcard.copyCard(this.unocarddeck[this.currenttopcard]);
                this.currenttopcard = (this.currenttopcard + 1) % this.unocarddeck.length;
                this.numbercardsleftindeck--;
            }
        }
        else {System.out.println("No deck. Need to intialize deck first!");}
        return topcard;
    }
    
    
    /** addCardToBottom method
     * ADDITIONAL PARAMETER: the deck needs to be initialized
     * DESCRIPTION: removes and returns the top card of the deck
     * test case 1: card is added to bottom
     * @param addCardtobottom card to be added to the bottom of the deck
     */
    public void addCardToBottom(UNOCard addCardtobottom){
        if(!this.isNullDeck()){
            if(!this.isFullDeck()){
                if(!addCardtobottom.isNullCard())
                {
                    this.currentbottomcard = (this.currentbottomcard + 1) % this.unocarddeck.length;
                    this.unocarddeck[(this.currentbottomcard)].copyCard(addCardtobottom);
                    this.numbercardsleftindeck++;
                }
            }
        }
        else {System.out.println("No deck. Need to intialize deck first!");}
    }
    
    
    /** numbersCardsLeftInDeck method
     * ADDITIONAL PARAMETER: the deck needs to be initialized
     * DESCRIPTION: gives the number of cards left in the deck
     * test case 1: returns the number of cards in the deck
     * @return int
     */
    public int numbersCardsLeftInDeck(){
        if(this.isNullDeck()){System.out.println("No deck. Need to intialize deck first!");}
        return this.numbercardsleftindeck;
    }
}
