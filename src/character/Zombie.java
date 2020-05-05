/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package character;

/**
 *
 * @author raime
 */
public class Zombie extends BangCharacter{
    private static final String NAME = "Zombie";
    private static final String SPECIAL = "No special ability.";
    private static final String IMAGEFILENAME = "";
    
    private Zombie(int numaliveplayers){
        super(numaliveplayers);
    }
    public String getName(){
        return NAME;
    }
    
    public String getSpecial(){
        return SPECIAL;
    }
    
    public String getImageFileName(){
        return IMAGEFILENAME;
    }
    
    public void takeDamage(){
        if(!this.isDead()){
            this.curlifepoints--;
            this.maxlifepoints--;
        }
    }
    
    public int gainLife(){
        int count = 0;
        return count;
    }
}
