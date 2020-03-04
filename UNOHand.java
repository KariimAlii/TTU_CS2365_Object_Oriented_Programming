/*
 * TITLE: Deck Class for Project 2 UNO Workout
 * AUTHOR: Anamol Acharya
 * FOR: CS 2365 Object Oriented Programming Section 001 Spring 2020
 */
package unoworkout;
import java.util.ArrayList;

/**
 *  Hand of UNOCards, held by a particular player. A Hand object is
 * responsible for playing a Card-choosing a card to play-
 * when the player's turn comes up.
 * To do this, it implements the
 * strategy pattern by which this choice can be delegated to an arbitrary
 * implementer of the UnoPlayer class.
 */
public class UNOHand {

    private final ArrayList<UNOCard> cards;
    private UNOCard player;
    private final String playerName;
   // private lastcardindex 

    /**
     * Declare a UNOHand object to be played by the UnoPlayer class, and
     * the player name, passed as arguments. This implements a strategy
     * pattern whereby the constructor accepts various strategies that
     * implement the UnoPlayer interface.
     * @param unoPlayerClassName
     * @param playerName
     */
    public UNOHand(String unoPlayerClassName, String playerName) {
        try {
            player = (UNOCard)
                Class.forName(unoPlayerClassName).newInstance();
        }
        catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            System.out.println("Problem with " + unoPlayerClassName + ".");
            System.exit(1);
        }
        this.playerName = playerName;
        cards = new ArrayList<>();
    }

    /**
     * Add a card to the hand.
     */
    void insertCard(UNOCard c) {
        cards.add(c);
    }

     /**
     * Remove a card to the hand.
     */
    void removeCard(UNOCard c){
        cards.remove(c);
        
    }
   
    /**
     * Return true only if this Hand has no cards, which should trigger a
     * winning condition.
     * @return 
     */
    public boolean isHandEmpty() {
        return cards.isEmpty();
    }

    /*
    Boolean to check if hand if full or not
    */
    public boolean isHandFull(){
       return cards.size()==1;
    }
    
     /**
     * Return the number of cards in the hand.
     * @return 
     */
    public int size() {
        return cards.size();
    }

    
    /**
   To sort the hand calling from the UNOCard class
     */
    void sortHand() {
        
    }
    /**
     * Return a string rendering of this Hand. See Card::toString() for
     * notes about how individual cards are rendered.
     * @return 
     */
    public String handToString() {
        String returnString = "";
        for (int i=0; i<cards.size(); i++) {
            returnString += cards.get(i);
            if (i<cards.size()-1) {
                returnString += ",";
            }
        }
        return returnString;
    }

    
    

    
}