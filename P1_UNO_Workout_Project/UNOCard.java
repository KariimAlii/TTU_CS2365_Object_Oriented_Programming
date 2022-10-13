/*
 * TITLE: Card Class for Project 2 UNO Workout
 * AUTHOR: Stephen C. Devaney
 * FOR: CS 2365 Object Oriented Programming Section 001 Spring 2020
 * CLASS DESCRIPTION: class is used to create a card to be added to a deck or a 
                      hand for an UNO game or workout.
 * TEST CASES: test cases for this class are held in the workoutclassclient class
 */
package unoworkout;

/** A card for an game or workout of UNO
 * @author Stephen C. Devaney
 */
public class UNOCard {
    public final static int BLUECARD = 0, YELLOWCARD = 1, REDCARD = 2, GREENCARD = 3, BLACKCARD = 4; // constants for the card colors
    public final static int SKIP = 10, DRAW2 = 11, REVERSE = 12, WILD = 13, WILDDRAW4 = 14; // constants for action cards
    private int color; // color of the card
    private int number; // number of the card
    
    
    /** UNOCard Base Constructor
     * test case 1: proper color and number returns proper card
     * test case 2: improper color and proper number returns group designated null card
     * test case 3: proper color and improper number returns group designated null card
     * test case 4: improper color and improper number returns group designated null card
     * @param color     color of the card
     * @param number    number of the card
     */
    public UNOCard(int color, int number){
        if ((0 <= number && number <= REVERSE) && (BLUECARD <= color && color <= GREENCARD)) // color cards
        {
            this.color = color;
            this.number = number;
        }
        else if ((WILD <= number && number <= WILDDRAW4) && color == BLACKCARD) // wild cards
        {
            this.color = color;
            this.number = number;
        }
        else{
            this.color = -1;
            this.number = -1;
        }
    }
    
    /** UNOCard overloaded Constructor
     * color is preset to -1 which is going to be used for null
     * number is preset to -1 which is going to be used for null
     * test case 1: always returns -1, -1 when called
     */
    public UNOCard(){
        this(-1,-1);
    }
    
    
    /** isNullCard method
     * DESCRIPTION: checks to see if the card is NULL
     * test case 1: card is group designated null returns true
     * test case 2: card is not group designated null returns false
     * @return boolean
     */
    public boolean isNullCard(){
        return((this.number == -1 && this.color == -1));
    }
    
    
    /** setCardNull method
     * DESCRIPTION: sets the null card to a what is defined a null card
     * test case 1: always sets card to -1,-1 when called
     */
    public void setCardNull(){
        this.color = -1;
        this.number = -1;
    }
    
    
    /** cardToString method
     * ADDITIONAL PARAMETER: the card needs to be initialize with proper values for UNOCards based upon the constructor
     * DESCRIPTION: the method takes a card and outputs the proper wording for the card in a string
     * test case 1: non null card returns the proper string
     * test case 2: null card returns a null string
     * @return String
     */
    public String cardToString(){
        String returnString = "";
        switch (this.color) {
            case BLUECARD:
                returnString += "Blue ";
                break;
            case GREENCARD:
                returnString += "Green ";
                break;
            case REDCARD:
                returnString += "Red ";
                break;
            case YELLOWCARD:
                returnString += "Yellow ";
                break;
            default:
                break;
        }
        if (this.color != BLACKCARD){
            if (0 <= this.number && this.number <= 9) {returnString += this.number;}
            else if (this.number == SKIP) {returnString += "Skip";}
            else if (this.number == DRAW2) {returnString += "Draw Two";}
            else if (this.number == REVERSE) {returnString += "Reverse";}
        }
        else
        {
            returnString += "Wild";
            if (this.number == 14) {returnString += " Draw 4";}
        }
        return returnString;
    }
    
    
    /** compareCard method

     * ADDITIONAL PARAMETER: the card needs to be initialize with proper values for UNOCards based upon the constructor
     * DESCRIPTION: compares this card with the second card 
     *                  if this card goes before the second card -1 is returned
     *                  if this card goes after the second card 1 is returned
     *                  if this card is equal to the second card 0 is returned
     * test case 1: first card goes before second card returns -1
     * test case 2: first card equals second card returns 0
     * test case 3: first card goes after second card returns 1
     * @param secondcard: UNOCard
     * @return int
     */
    public int compareCard(UNOCard secondcard){
        int result;
        if(!this.isNullCard()){
            if (this.color < secondcard.color) {result = -1;}
            else if (this.color > secondcard.color) {result = 1;}
            else{
                if (this.number < secondcard.number) {result = -1;}
                else if (this.number > secondcard.number) {result = 1;}
                else {result = 0;}
            }
        }
        else return 1;
        return result;
    }
    
    
    /** getCardColor method
     * ADDITIONAL PARAMETER: the card needs to be initialize with proper values for UNOCards based upon the constructor
     * DESCRIPTION: returns the color value of the card
     * test case 1: non null card returns the value of the color
     * test case 2: null card returns -1
     * @return int
     */
    public int getCardColor(){
        return this.color;
    }
    
    
    /** getCardNumber method
     * ADDITIONAL PARAMETER: the card needs to be initialize with proper values for UNOCards based upon the constructor
     * DESCRIPTION: returns the number value of the card
     * test case 1: non null card returns the value of the number
     * test case 2: null card returns -1
     * @return int
     */
    public int getCardNumber(){
        return this.number;
    }
    
    /** copyCard method
     * ADDITIONAL PARAMETER: both cards needs to be initialize with proper values for UNOCards based upon the constructor
     * DESCRIPTION: copies a cards value onto another card best use in the hand.
     * test case 1: non null card is copied exactly
     * test case 2: null card is copied exactly
     * @param card is the card to be copied from
     */
    public void copyCard(UNOCard card){
        this.color = card.color;
        this.number = card.number;
    }
}
