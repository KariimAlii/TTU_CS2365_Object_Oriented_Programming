/*
 * TITLE: Card Class for Project 2 UNO Workout
 * AUTHOR: Stephen C. Devaney
 * FOR: CS 2365 Object Oriented Programming Section 001 Spring 2020
 */
package unoworkout;

/**
 *
 * @author Stephen C. Devaney
 */
public class UNOCard {
    public final static int BLUECARD = 0, GREENCARD = 1, REDCARD = 2, YELLOWCARD = 3, BLACKCARD = 4; // constants for the card colors
    public final static int SKIP = 10, DRAW2 = 11, REVERSE = 12, WILD = 13, WILDDRAW4 = 14; // constants for action cards
    private int color; // color of the card
    private int number; // number of the card
    
    
    /** UNOCard Base Constructor
     * @param color     color of the card
     * @param number    number of the card
     */
    public UNOCard(int color, int number){
        if ((0 <= number && number <= REVERSE) && (BLUECARD <= color && color <= YELLOWCARD)) // color cards
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
    
    
    public UNOCard(){
        this(-1,-1);
    }
    
    
    /** isNullCard method
     * @return boolean
     * DESCRIPTION: checks to see if the card is NULL
     */
    public boolean isNullCard(){
        return((this.number == -1 && this.color == -1));
    }
    
    
    /** setCardNull method
     * DESCRIPTION: sets the null card to a what is defined a null card
     */
    public void setCardNull(){
        this.color = -1;
        this.number = -1;
    }
    
    
    /** cardToString method
     * ADDITIONAL PARAMETER: the card needs to be initialize with proper values for UNOCards based upon the constructor
     * @return String
     * DESCRIPTION: the method takes a card and outputs the proper wording for the card in a string
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
     * @param secondcard: UNOCard
     * ADDITIONAL PARAMETER: the card needs to be initialize with proper values for UNOCards based upon the constructor
     * @return int
     * DESCRIPTION: compares this card with the second card 
     *                  if this card goes before the second card -1 is returned
     *                  if this card goes after the second card 1 is returned
     *                  if this card is equal to the second card 0 is returned
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
     * @return int
     * DESCRIPTION: returns the color value of the card
     */
    public int getCardColor(){
        return this.color;
    }
    
    
    /** getCardNumber method
     * ADDITIONAL PARAMETER: the card needs to be initialize with proper values for UNOCards based upon the constructor
     * @return int
     * DESCRIPTION: returns the number value of the card
     */
    public int getCardNumber(){
        return this.number;
    }
    
    public void copyCard(UNOCard card){
        this.color = card.color;
        this.number = card.number;
    }
}
