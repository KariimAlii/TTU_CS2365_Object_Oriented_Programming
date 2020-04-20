/*
 * TITLE: Main Class for Project 2 UNO Workout 
 * AUTHOR: Stephen C. Devaney
 * FOR: CS 2365 Object Oriented Programming Section 001 Spring 2020
 */
package workoutclassclient;

import unoworkout.UNOCard;
import static unoworkout.UNOCard.BLACKCARD;
import static unoworkout.UNOCard.BLUECARD;
import static unoworkout.UNOCard.WILDDRAW4;
import unoworkout.UNODeck;
import unoworkout.UNOHand;


/** Code of test cases for the UNOCard, UNODeck, and UNOHand classes
 * @author Stephen C. Devaney
 */
public class workoutClassClient {
    private boolean unoCardDebug;
    private boolean unoDeckDebug;
    private boolean unoHandDebug;
    
    
    /** workoutClassClient
     * Description: presets all debugging to false
     */
    workoutClassClient(){
        unoCardDebug = unoDeckDebug = unoHandDebug = false;
    }
    
    
    /** setUnoCardDebug
     * Allows for changing of the debugging state
     * @param state
     */
    public void setUnoCardDebug(boolean state){
        this.unoCardDebug = state;
    }
    
    
    /** setUnoDeckDebug
     * Allows for changing of the debugging state
     * @param state
     */
    public void setUnoDeckDebug(boolean state){
        this.unoDeckDebug = state;
    }
    
    
    /** setUnoHandDebug
     * Allows for changing of the debugging state
     * @param state
     */
    public void setUnoHandDebug(boolean state){
        this.unoHandDebug = state;
    }
    
    
    /** testUnoCardClass
     * Description: provides test cases for the card class
     */
    public void testUnoCardClass(){
        if(this.unoCardDebug){
            System.out.println("Start of UNO Card Test Cases-------------------------------------------------------------------------------------------------------------------------------------");
            UNOCard card1 = new UNOCard(BLACKCARD,WILDDRAW4);
            UNOCard card2 = new UNOCard(BLUECARD,1);
            UNOCard card3 = new UNOCard();
            if(!card1.isNullCard()) {System.out.println("Card 1: " + card1.cardToString() + " :is not a null card!");}
            if(card3.isNullCard()) {System.out.println("Card 3: " + card3.cardToString() + " :is a null card!");}
            card3.copyCard(card1);
            card1.setCardNull();
            if(card1.isNullCard()){System.out.println("Card 1: " + card1.cardToString() + " :is now a null card!");}
            if(!card3.isNullCard()) {System.out.println("Card 3: " + card3.cardToString() + " :is no longer a null card!");}
            if(card2.compareCard(card3) == -1) {System.out.println("Card 2 goes before card 3");}
            if(card3.compareCard(card2) == 1) {System.out.println("Card 3 goes after card 2");}
            if(card3.compareCard(card3) == 0) {System.out.println("Card 3 goes equals card 3");}
            {System.out.println("The color of the card is black which is 4: " + card3.getCardColor());}
            {System.out.println("The color of the card is null which is -1: " + card1.getCardColor());}
            {System.out.println("The number of the card is WildDraw4 which is 14: " + card3.getCardNumber());}
            {System.out.println("The number of the card is null which is -1: " + card1.getCardNumber());}
            card3.copyCard(card1);
            if(card3.isNullCard()) {System.out.println("Card 1: " + card1.cardToString() + " :is now a null card!");}
            System.out.println("End of UNO Card Test Cases---------------------------------------------------------------------------------------------------------------------------------------");
        }
    }
    
    
    /** testUnoDeckClass
     * Description: provides test cases for the deck class
     */
    public void testUnoDeckClass(){
        if(this.unoDeckDebug){
            System.out.println("Start of UNO Deck Test Cases-------------------------------------------------------------------------------------------------------------------------------------");
            UNODeck nulldeck = new UNODeck(0,false);
            UNODeck Deck1 = new UNODeck(3);
            UNODeck Deck2 = new UNODeck(3);
            if(nulldeck.isNullDeck()) {System.out.println("nulldeck is null");}
            if(!Deck1.isNullDeck()) {System.out.println("Deck 1 is not null");}
            if(Deck1.isFullDeck()) {System.out.println("Deck 1 is a full deck;");}
            Deck1.shuffleDeck(true);
            Deck2.shuffleDeck();
            System.out.println("---------Start of Deck 1----------");
            Deck1.displayDeck();
            System.out.println("---------End of Deck 1----------");
            System.out.println("---------Start of Deck 2----------");
            Deck2.displayDeck();
            System.out.println("---------End of Deck 2----------");
            System.out.println("Dealt top card of deck 2: " + Deck2.dealCard().cardToString());
            while(!Deck2.isEmptyDeck()) {Deck2.dealCard();}
            if(Deck2.isEmptyDeck()){System.out.println("Deck 2 is now empty");}
            Deck2.addCardToBottom(Deck1.dealCard());
            Deck2.addCardToBottom(Deck1.dealCard());
            System.out.println("After adding 2 cards from the top of deck 1 to the bottem of deck 2");
            System.out.println("---------Start of Deck 2----------");
            Deck2.displayDeck();
            System.out.println("---------End of Deck 2----------");
            System.out.println("Current number of cards in deck 1: " + Deck1.numbersCardsLeftInDeck());
            System.out.println("Current number of cards in deck 2: " + Deck2.numbersCardsLeftInDeck());
            System.out.println("End of UNO Deck Test Cases---------------------------------------------------------------------------------------------------------------------------------------");
        }
    }
    
    
    /** testUnoHandClass
     * Description: provides test cases for the hand class
     */
    public void testUnoHandClass(){
        if(this.unoHandDebug){
            System.out.println("Start of UNO Hand Test Cases-------------------------------------------------------------------------------------------------------------------------------------");
            UNODeck deck = new UNODeck();
            UNOHand hand = new UNOHand();
            deck.shuffleDeck();
            if(hand.isHandEmpty()){System.out.println("Empty hand shows: " + hand.handToString());}
            for(int i = 0; i <= 7; i++){
                hand.insertCard(i, deck.dealCard());
            }
            if(hand.isHandFull()){System.out.println("Full hand unsorted shows: " + hand.handToString());}
            hand.sortHand();
            if(hand.isHandFull()){System.out.println("Full hand sorted shows: " + hand.handToString());}
            hand.removeCard(3);
            if(!hand.isHandEmpty() && !hand.isHandFull()){System.out.println("Hand after removing index 3: " + hand.handToString());}
            hand.discardHand();
            if(hand.isHandEmpty()){System.out.println("Hand after discard: " + hand.handToString());}
            for(int i = 0; i <= 7; i++){
                hand.insertCard(i, deck.dealCard());
            }
            if(hand.isHandFull()){System.out.println("New Hand: " + hand.handToString());}
            hand.removeCard(3);
            if(!hand.isHandEmpty() && !hand.isHandFull()){System.out.println("Hand after removing index 3 again: " + hand.handToString());}
            hand.sortHand();
            if(!hand.isHandEmpty() && !hand.isHandFull()){System.out.println("Hand sorted after index 3 was removed: " + hand.handToString());}
            System.out.println("Card at last index is a null card: " + hand.getCardAtIndex(6).cardToString());
            System.out.println("Card at index 5: " + hand.getCardAtIndex(5).cardToString());
            System.out.println("End of UNO Hand Test Cases---------------------------------------------------------------------------------------------------------------------------------------");
        }
    }

    
    /** Main Method runs the test cases for each class
     * @param args
     */
    public static void main(String[] args) {
        workoutClassClient test = new workoutClassClient(); 
        test.setUnoCardDebug(true);
        test.setUnoDeckDebug(true);
        test.setUnoHandDebug(true);
        test.testUnoCardClass();
        test.testUnoDeckClass();
        test.testUnoHandClass();
    }
}
