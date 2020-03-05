/*
 * TITLE: Deck Class for Project 2 UNO Workout
 * AUTHOR: Stephen C. Devaney
 * FOR: CS 2365 Object Oriented Programming Section 001 Spring 2020
 */
package unoworkout;
import java.util.Random;
import static unoworkout.UNOCard.BLUECARD;
import static unoworkout.UNOCard.BLACKCARD;
import static unoworkout.UNOCard.SKIP;
import static unoworkout.UNOCard.WILD;
import static unoworkout.UNOCard.WILDDRAW4;


/**
 *
 * @author Stephen C. Devaney
 */
public class UNODeck {
    private UNOCard[] unocarddeck; // deck of Uno cards
    private int currenttopcard; // index of the current top card
    private int currentbottemcard; // index of the current bottem card
    private int numbercardsleftindeck; // number of cards left in the deck
    
    
    /** UNODeck Base Constructor
     * @param numberofdecks     number of decks to be used
     * @param removeactioncards    true removes action cards false keeps action cards
     */
    public UNODeck(int numberofdecks, boolean removeactioncards){
        if (numberofdecks > 0){
            int i = 0;
            int stopcard;
            if (removeactioncards) {
                numbercardsleftindeck = 76 * numberofdecks;
                stopcard = SKIP;
            }
            else {
                numbercardsleftindeck = 108 * numberofdecks;
                stopcard = WILD;
            }
            currenttopcard = 0;
            currentbottemcard = numbercardsleftindeck - 1;
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
     * @param numberofdecks     number of decks to be used
     * removeactioncards parameter is preset to false
     */
    public UNODeck(int numberofdecks){
        this(numberofdecks, false);
    }
    
    
    /** UNODeck Overloaded Constructor
     * numberofdecks to be used is preset to 1
     * @param removeactioncards    true removes action cards false keeps action cards
     */
    public UNODeck(boolean removeactioncards){
        this(1, removeactioncards);
    }
    
    
    /** UNODeck Overloaded Constructor
     * numberofdecks to be used is preset to 1
     * removeactioncards parameter is preset to false
     */
    public UNODeck(){
        this(1, false);
    }
    
    
    /** isNullDeck method
     * @return boolean true if the deck is null false otherwise
     * DESCRIPTION: checks if deck is initialized
     */
    public boolean isNullDeck(){
        return ((this.unocarddeck.length == 1 && this.unocarddeck[0].isNullCard()));
    }
    
    
    /** isEmptyDeck method
     * ADDITIONAL PARAMETER: the deck needs to be initialized
     * @return boolean true if the deck is empty false otherwise
     * DESCRIPTION: checks if deck is empty
     */
    public boolean isEmptyDeck(){
        return (this.currenttopcard >= this.unocarddeck.length);
    }
    
    
    /** isFullDeck method
     * ADDITIONAL PARAMETER: the deck needs to be initialized
     * @return boolean true if the deck is full false otherwise
     * DESCRIPTION: checks if deck is full
     */
    public boolean isFullDeck(){
        return ((this.currenttopcard == 0 && this.currentbottemcard == this.unocarddeck.length-1) || (this.currentbottemcard == this.currenttopcard-1));
    }
    
    
    /** shuffleDeck method
     * @param state // true if shuffling deck individually false if shuffling together
     * ADDITIONAL PARAMETER: the deck needs to be initialized
     * DESCRIPTION: shuffles decks
     */
    public void shuffleDeck(boolean state){
        if(!this.isNullDeck()){
            if(!this.isEmptyDeck()){
                Random rand = new Random(System.currentTimeMillis());
                if(!state){ // shuffle whole deck together
                    for(int i = 0; i < (this.unocarddeck.length); i++){
                        int random1 = rand.nextInt(this.unocarddeck.length);
                        while (random1 == i || this.unocarddeck[random1].compareCard(this.unocarddeck[i]) == 0) {
                            random1 = rand.nextInt(this.unocarddeck.length);
                        }
                        UNOCard temp = this.unocarddeck[random1];
                        this.unocarddeck[random1] = this.unocarddeck[i];
                        this.unocarddeck[i] = temp;
                    }
                }
                else // shuffle decks individually
                {
                    int numberofdecks = this.unocarddeck.length / this.numbercardsleftindeck;
                    for(int count = 0; count < numberofdecks; count++){
                        int modifier = count * this.numbercardsleftindeck;
                        for(int i = 0 + modifier; i < this.numbercardsleftindeck + modifier; i++){
                            int random1 = rand.nextInt(this.numbercardsleftindeck) + modifier;
                            while (random1 == i || this.unocarddeck[random1].compareCard(this.unocarddeck[i]) == 0) {
                                random1 = rand.nextInt(this.numbercardsleftindeck) + modifier;
                            }
                            UNOCard temp = this.unocarddeck[random1];
                            this.unocarddeck[random1] = this.unocarddeck[i];
                            this.unocarddeck[i] = temp;
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
     */
    public void shuffleDeck(){
        this.shuffleDeck(false);
    }
    
    
    /** displayDeck method
     * ADDITIONAL PARAMETER: the deck needs to be initialized
     * DESCRIPTION: displays the deck
     */
    public void displayDeck(){
        if(!this.isNullDeck()){
            if(!this.isEmptyDeck()){
                int i = this.currenttopcard;
                int count = 0;
                if(i <= this.currentbottemcard) {for(; i <= this.currentbottemcard; i++){
                    System.out.println(++count + ": " + this.unocarddeck[i].cardToString());}
                }
                else{
                    for(; i < this.unocarddeck.length; i++){System.out.println(++count + ": " + this.unocarddeck[i].cardToString());}
                    for(i = 0; i <= this.currentbottemcard; i++){System.out.println(++count + ": " + this.unocarddeck[i].cardToString());}
                }
            }
            else {System.out.println("Deck is empty!");} 
        }
        else {System.out.println("No deck. Need to intialize deck first!");}
    }
    
    
    /** dealCard method
     * ADDITIONAL PARAMETER: the deck needs to be initialized
     * @return UNOCard
     * DESCRIPTION: removes and returns the top card of the deck
     */
    public UNOCard dealCard(){
        UNOCard topcard = new UNOCard();
        if(!this.isNullDeck()){
            if (!this.isEmptyDeck()) {
                topcard.copyCard(this.unocarddeck[this.currenttopcard++]);
                this.numbercardsleftindeck--;
            }
        }
        else {System.out.println("No deck. Need to intialize deck first!");}
        return topcard;
    }
    
    
    /** addCardToBottem method
     * ADDITIONAL PARAMETER: the deck needs to be initialized
     * @param addCardtobottem card to be added to the bottem of the deck
     * DESCRIPTION: removes and returns the top card of the deck
     */
    public void addCardToBottem(UNOCard addCardtobottem){
        if(!this.isNullDeck()){
            if(!this.isFullDeck()){
                this.currentbottemcard = (this.currentbottemcard + 1) % this.unocarddeck.length;
                this.unocarddeck[(this.currentbottemcard)] = addCardtobottem;
                this.numbercardsleftindeck++;
            }
        }
        else {System.out.println("No deck. Need to intialize deck first!");}
    }
    
    
    /** numbersCardsLeftInDeck method
     * ADDITIONAL PARAMETER: the deck needs to be initialized
     * @return int
     * DESCRIPTION: gives the number of cards left in the deck
     */
    public int numbersCardsLeftInDeck(){
        if(this.isNullDeck()){System.out.println("No deck. Need to intialize deck first!");}
        return this.numbercardsleftindeck;
    }
}
