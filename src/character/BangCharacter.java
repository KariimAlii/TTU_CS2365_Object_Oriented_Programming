/*
 * TITLE: Project 3 Bang The Dice Game
 * AUTHOR: Stephen C. Devaney
 * FOR: CS 2365 Object Oriented Programming Section 001 Spring 2020
 */
package character;

import javafx.scene.image.Image;

/**
 * This Class is an abstract class used to implement a character for bang the dice
 * game.
 * @author Stephen C. Devaney
 */
public abstract class BangCharacter {
    protected int curlifepoints;
    protected int maxlifepoints;
    
    /**
     * Constructor for bang a character in bang the dice game life points will be passed
     * from a extended class.
     * @param life: int
     */
    public BangCharacter(int life){
        curlifepoints = maxlifepoints = life;
    }
    
    /**
     * gets image for the characters
     * @return
     */
    public Image getImage(){
        return new Image(getImageFileName());
    }
    
    /**
     * abstract method to get image file of the character, defined in individual character classes
     * @return
     */
    public abstract String getImageFileName();
    
    /**
     * Abstract Method to get the name of the character, defined in individual character classes.
     * @return
     */
    public abstract String getName();
    
    /**
     * Method to get the character's special ability in the form of a string so that
     * it may be displayed. Defined in individual character classes.
     * @return
     */
    public abstract String getSpecial();
    
    /**
     * Method allows for the increase of life and max life due to the characters
     * role being a sheriff
     */
    public void setSheriff(){
        this.maxlifepoints += 2;
        this.curlifepoints +=2;
    }
    
    /**
     * Method gets the characters current life points and returns it to the calling method
     * @return :int
     */
    public int getCurLifePoints(){
        return this.curlifepoints;
    }
    
    /**
     * Method gets the characters Max life points and returns it to the calling method
     * @return 
     * @return :int
     */
    public int getMaxLifePoints(){
        return this.maxlifepoints;
    }
    
    
    /**
     * Method checks to see if the character is dead
     * @return : boolean
     */
    public boolean isDead(){ 
        return (this.curlifepoints <= 0);
    }
    
    
    /**
     *Method to decrease a characters current life points
     */
    public void takeDamage(){
        if(!this.isDead()) this.curlifepoints--;
    }
    
    /**
     * Method to increase a characters current life points this method will not allow current life points to go above the maximum number of life points.
     * returns the value of life that was increased
     * @return count: int
     */
    public int gainLife(){
        int count = 0;
        if((this.curlifepoints < this.maxlifepoints) && !this.isDead()){
            this.curlifepoints++;
            count++;
            if (this.canLowLifeDoubleBeer() && this.curlifepoints <= 4) {
                this.curlifepoints++;
                count++;
            }
        }
        return count;
    }
    
    /**
     * This Method checks to see if the character may re-roll dynamite
     * @return preset to false
     */
    public boolean canRerollDynamite(){ // Black Jack Only
        return false;
    }
    
    /**
     * This Method checks to see if the character gains double health if the character has four or less life points
     * @return preset to false
     */
    public boolean canLowLifeDoubleBeer(){ // Jesse Jones Only
        return false;
    }
    
    /**
     * This method checks to see if the character never lose more than one life point to Indians.
     * @return  preset to false
     */
    public boolean canReducedIndianAttack(){ // Jourdonnais Only
        return false;
    }

    /**
     * This method checks to see if the character may make one extra re-roll
     * @return  preset to false
     */
    public boolean canHaveExtraReroll(){ // Lucky Die Only
        return false;
    }
    
    /**
     * This method checks to see if the character never lose life points to the Gatling Gun.
     * @return  preset to false
     */
    public boolean canHaveGatlingDamageImmunity(){ // Paul Regret Only
        return false;
    }
    
    /**
     * This method checks to see if the character may discard one of their arrows each time they lose a life point
     * @return  preset to false
     */
    public boolean canDiscardArrowIfLoseLife(){ // Pedro Ramirez Only
        return false;
    }
    
    /**
     * This method checks to see if the character can give any player of their choice a life point
     * @return  preset to false
     */
    public boolean canGiveAnyPlayerLife(){ // Sid Ketchum Only
        return false;
    }

    /**
     * This method checks to see if the character only needs to gatling guns to set off the gatling
     * @return  preset to false
     */
    public int getGatlingNeed(){ // Willy The Kid only
        return 3;
    }
    
    /**
     * This method checks to see if the character is able to use whiskey bottles twice for one roll
     * @return
     */
    public boolean canUseWhiskeyTwice(){
        return false;
    }
    
    /**
     * This method checks to see if the character is able to excange one dynamite for a gatling after each roll
     * @return
     */
    public boolean canExchangeOneDynamiteforGatling(){
        return false;
    }
    
    /**
     * This method checks to see if the character has apache kids special ability
     * @return
     */
    public boolean canTakeChiefsArrowFromPlayerWhenRollArrow(){
        return false;
    }
    
    /**
     * This method checks to see if the character bill noface's special ability
     * @return
     */
    public boolean canApplyArrowResultsAfterRerolls(){
        return false;
    }
}