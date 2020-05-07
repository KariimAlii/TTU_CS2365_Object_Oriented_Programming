/*
 * TITLE: Project 3 Bang The Dice Game
 * AUTHOR: Steven Lowry
 * COLLABORATORS: Stephen Devaney
 * FOR: CS 2365 Object Oriented Programming Section 001 Spring 2020
 */
package bang_gui;

import Game.*;
import dice.DieType;
import java.net.URL;
import java.util.ResourceBundle;
import static javafx.collections.FXCollections.observableArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import player.*;


/**
 * FXML Controller class
 *
 * @author Steven
 */
public class GameBoardController implements Initializable {
    private BangGame game;
    private Player curplayer;
    private int playerindexes[];
    private Player b1targets[];
    private Player b2targets[];
    private Player beertargets[];
    
    @FXML private HBox HumanPlayer;
    
    @FXML private HBox ComputerPlayer;
    @FXML private TextArea computeroutput;
    
    @FXML private TitledPane gameover;
    @FXML private Label SandDwincond;
    @FXML private Label Swincond;
    @FXML private Label Owincond;
    @FXML private Label Rwincond;
    
/*
 *  This section of code is for setting up the handles to the central portion of the board.
 */
    @FXML ComboBox Dice1_Hold;
    @FXML ComboBox Dice2_Hold;
    @FXML ComboBox Dice3_Hold;
    @FXML ComboBox Dice4_Hold;
    @FXML ComboBox Dice5_Hold;
/*
 *  Sets ComboBoxes up for the target.  Will have to set set choices based upon player abilities.
 *  Only set up to link these variables to the appropriate boxes.
 */
    @FXML ComboBox Dice1_Target;
    @FXML ComboBox Dice2_Target;
    @FXML ComboBox Dice3_Target;
    @FXML ComboBox Dice4_Target;
    @FXML ComboBox Dice5_Target;

/*
 * dice selection combo boxes
 */
    @FXML ComboBox Dice3_Dice;
    
    
    /*
     *  Array for Dice face images.  Array element zero is just the bang logo, used for initialization.
     */
    String [] diefacefilenames = {"/Images/bang .jpg","/Images/Bang-dice-1.jpg","/Images/Bang-dice-2.jpg","/Images/Bang-dice-3.jpg","/Images/Bang-dice-4.jpg",
                           "/Images/Bang-dice-5.jpg", "Images/Bang-dice-6.jpg"};
    String [] loudmouthfilenames = {"/Images/bang .jpg","/Images/Arrow.jpg", "/Images/Dynamite.jpg","/Images/BullsEyeDouble1.jpg","/Images/BullsEyeDouble2.jpg","/Images/Bullet.jpg", "/Images/Whiskey.jpg" };
    String [] cowardfilenames = {"/Images/bang .jpg","/Images/Arrow.jpg", "/Images/Dynamite.jpg","/Images/Bang-dice-3.jpg","/Images/RetrunArrow.jpg","/Images/Bang-dice-5.jpg", "/Images/DoubleBeer.jpg" };
   String [] dueldiefilenames = {"/Images/bang .jpg","/Images/BlackArrow.jpg", "/Images/BlackDynamite.jpg","/Images/FightADuel.jpg","/Images/FightADuel.jpg","/Images/Whiskey.jpg", "/Images/BlackGatlingGuns.jpg" };
    Image diefaces[];
    Image loudmouth[];
    Image coward[];
    Image dueldie[];
    
    /*
     *  Array for character role images.
     */
    String[] Character_Roles = {"/Images/bang-back-of-card.jpg","/Images/Sheriff1.jpg","/Images/Renegade.jpg","/Images/Outlaw.jpg", "/Images/Deputy.jpg"};
    Image rolecards[];
    
    
    @FXML ImageView Die_1;
    @FXML ImageView Die_2;
    @FXML ImageView Die_3;
    @FXML ImageView Die_4;
    @FXML ImageView Die_5;
    
    @FXML Button Roll;
    @FXML Button ReRoll;
    @FXML Button Action;
    @FXML Button SkipReRoll;
    @FXML Button EndTurn;
    
    @FXML TitledPane Pos1_ID;
    @FXML TitledPane Pos2_ID;
    @FXML TitledPane Pos3_ID;
    @FXML TitledPane Pos4_ID;
    @FXML TitledPane Pos5_ID;
    @FXML TitledPane Pos6_ID;
    @FXML TitledPane Pos7_ID;
    @FXML TitledPane Pos8_ID;
    @FXML ImageView Pos1_Role;
    @FXML ImageView Pos2_Role;
    @FXML ImageView Pos3_Role;
    @FXML ImageView Pos4_Role;
    @FXML ImageView Pos5_Role;
    @FXML ImageView Pos6_Role;
    @FXML ImageView Pos7_Role;
    @FXML ImageView Pos8_Role;
    @FXML ImageView Pos1_Name;
    @FXML ImageView Pos2_Name;
    @FXML ImageView Pos3_Name;
    @FXML ImageView Pos4_Name;
    @FXML ImageView Pos5_Name;
    @FXML ImageView Pos6_Name;
    @FXML ImageView Pos7_Name;
    @FXML ImageView Pos8_Name;
    @FXML ImageView Pos1_ChiefArrow;
    @FXML ImageView Pos2_ChiefArrow;
    @FXML ImageView Pos3_ChiefArrow;
    @FXML ImageView Pos4_ChiefArrow;
    @FXML ImageView Pos5_ChiefArrow;
    @FXML ImageView Pos6_ChiefArrow;
    @FXML ImageView Pos7_ChiefArrow;
    @FXML ImageView Pos8_ChiefArrow;
    @FXML ImageView Pos9_ChiefArrow;
    @FXML ImageView Pos1_wound_1;
    @FXML ImageView Pos1_wound_2;
    @FXML ImageView Pos1_wound_beer;
    @FXML ImageView Pos1_wound_dynamite;
    @FXML ImageView Pos2_wound_1;
    @FXML ImageView Pos2_wound_2;
    @FXML ImageView Pos2_wound_beer;
    @FXML ImageView Pos2_wound_dynamite;
    @FXML ImageView Pos3_wound_1;
    @FXML ImageView Pos3_wound_2;
    @FXML ImageView Pos3_wound_beer;
    @FXML ImageView Pos3_wound_dynamite;
    @FXML ImageView Pos4_wound_1;
    @FXML ImageView Pos4_wound_2;
    @FXML ImageView Pos4_wound_beer;
    @FXML ImageView Pos4_wound_dynamite;
    @FXML ImageView Pos5_wound_1;
    @FXML ImageView Pos5_wound_2;
    @FXML ImageView Pos5_wound_beer;
    @FXML ImageView Pos5_wound_dynamite;
    @FXML ImageView Pos6_wound_1;
    @FXML ImageView Pos6_wound_2;
    @FXML ImageView Pos6_wound_beer;
    @FXML ImageView Pos6_wound_dynamite;
    @FXML ImageView Pos7_wound_1;
    @FXML ImageView Pos7_wound_2;
    @FXML ImageView Pos7_wound_beer;
    @FXML ImageView Pos7_wound_dynamite;
    @FXML ImageView Pos8_wound_1;
    @FXML ImageView Pos8_wound_2;
    @FXML ImageView Pos8_wound_beer;
    @FXML ImageView Pos8_wound_dynamite;
    @FXML Label arrowpile;
    @FXML Label zombiehand;
    @FXML Label dueltokens;
    @FXML Label Pos1_Cur_Arrow;
    @FXML Label Pos2_Cur_Arrow;
    @FXML Label Pos3_Cur_Arrow;
    @FXML Label Pos4_Cur_Arrow;
    @FXML Label Pos5_Cur_Arrow;
    @FXML Label Pos6_Cur_Arrow;
    @FXML Label Pos7_Cur_Arrow;
    @FXML Label Pos8_Cur_Arrow;
    @FXML Label Pos1_Cur_LP;
    @FXML Label Pos2_Cur_LP;
    @FXML Label Pos3_Cur_LP;
    @FXML Label Pos4_Cur_LP;
    @FXML Label Pos5_Cur_LP;
    @FXML Label Pos6_Cur_LP;
    @FXML Label Pos7_Cur_LP;
    @FXML Label Pos8_Cur_LP;
    @FXML Label Pos1_Max_LP;
    @FXML Label Pos2_Max_LP;
    @FXML Label Pos3_Max_LP;
    @FXML Label Pos4_Max_LP;
    @FXML Label Pos5_Max_LP;
    @FXML Label Pos6_Max_LP;
    @FXML Label Pos7_Max_LP;
    @FXML Label Pos8_Max_LP;
    @FXML ProgressBar Pos1_LP;
    @FXML ProgressBar Pos2_LP;
    @FXML ProgressBar Pos3_LP;
    @FXML ProgressBar Pos4_LP;
    @FXML ProgressBar Pos5_LP;
    @FXML ProgressBar Pos6_LP;
    @FXML ProgressBar Pos7_LP;
    @FXML ProgressBar Pos8_LP;
    
    /**
     * used to pass information from previous scene to this scene
     * @param passedsetup
     * @author Stephen Devaney
     */
    public void controllerSetup(BangSetup passedsetup){
        game = new BangGame(passedsetup);
        Dice1_Hold.getItems().addAll("Hold", "Re-Roll");
        Dice2_Hold.getItems().addAll("Hold", "Re-Roll");
        Dice3_Hold.getItems().addAll("Hold", "Re-Roll");
        Dice4_Hold.getItems().addAll("Hold", "Re-Roll");
        Dice5_Hold.getItems().addAll("Hold", "Re-Roll");
        
        diefaces = new Image[7];
        for(int i = 0; i < diefaces.length; i++){
           diefaces[i] = new Image(diefacefilenames[i]);
        }
        loudmouth = new Image[7];
        for(int i = 0; i < loudmouth.length; i++){
           loudmouth[i] = new Image(loudmouthfilenames[i]);
        }
        coward = new Image[7];
        for(int i = 0; i < coward.length; i++){
           coward[i] = new Image(cowardfilenames[i]);
        }
        dueldie = new Image[7];
        for(int i = 0; i < dueldie.length; i++){
           dueldie[i] = new Image(dueldiefilenames[i]);
        }
       
        rolecards = new Image[5];
        for(int i = 0; i < rolecards.length; i++){
           rolecards[i] = new Image(Character_Roles[i]);
        }
        
        setupPlayers();
       
        updateDice(false);
        
        if(this.curplayer.getPlayerType() == PlayerType.HUMAN){
            this.HumanPlayer.setVisible(true);
            this.ComputerPlayer.setVisible(false);
            if(this.curplayer.canRoll(game)){
                this.Roll.setVisible(true);
                this.ReRoll.setVisible(false);
                this.Action.setVisible(false);
                this.EndTurn.setVisible(false);
                this.SkipReRoll.setVisible(false);
                this.Dice3_Dice.setVisible(true);
            }
            else{
                this.Roll.setVisible(false);
                this.ReRoll.setVisible(false);
                this.Action.setVisible(false);
                this.EndTurn.setVisible(true);
                this.SkipReRoll.setVisible(false);
            }
        }
        else{
            this.HumanPlayer.setVisible(false);
            this.ComputerPlayer.setVisible(true);
            this.Roll.setVisible(false);
            this.ReRoll.setVisible(false);
            this.Action.setVisible(false);
            this.EndTurn.setVisible(true);
            this.SkipReRoll.setVisible(false);
            this.computeroutput.setText(this.curplayer.simulateTurn(game));
            this.updatePlayers();
        }
    }
    
    /*
     *  Example of randomizing dice and use of imageview boxes.  Replace code
     *   with appropriate dice class items.  Interacts with Hold/Re-Roll button just as an example.  This is to link the roll dice button with code.
     */
    @FXML
    void Roll_Dice(ActionEvent event) {
        curplayer.rollDice(game);
        Roll.setVisible(false);
        updateDice(false);
        Dice3_Dice.setVisible(false);
        if(this.curplayer.canRoll(game)){
            this.ReRoll.setVisible(true);
            this.SkipReRoll.setVisible(true);
            updatePlayers();
        }
        else{
            this.ReRoll.setVisible(false);
            this.SkipReRoll.setVisible(false);
            Dice1_Hold.setVisible(false);
            Dice2_Hold.setVisible(false);
            Dice3_Hold.setVisible(false);
            Dice4_Hold.setVisible(false);
            Dice5_Hold.setVisible(false);
            
            Dice1_Hold.getSelectionModel().selectFirst();
            Dice2_Hold.getSelectionModel().selectFirst();
            Dice3_Hold.getSelectionModel().selectFirst();
            Dice4_Hold.getSelectionModel().selectFirst();
            Dice5_Hold.getSelectionModel().selectFirst();
            updatePlayers();
            setupAction();
        }
    } 

    @FXML
    void ChangeDie(ActionEvent event){
        String temp = Dice3_Dice.getSelectionModel().getSelectedItem().toString();
        game.getDice().changeDieAtIndex(3,temp);
        
    }
    
    /*
     *  Example of randomizing dice and use of imageview boxes.  Replace code
     *  with appropriate dice class items.  This is to link the Re-roll dice button with code.
     *  Re-roll shows interaction with Hold.  Modify code to work with class abilities and reroll counts, max dynamite, etc.
     */
    @FXML
    void ReRoll_Dice(ActionEvent event) {
        if (Dice1_Hold.getValue()=="Re-Roll"){curplayer.rollDieAtIndex(game,0);}
        if (Dice2_Hold.getValue()=="Re-Roll"){curplayer.rollDieAtIndex(game,1);}
        if (Dice3_Hold.getValue()=="Re-Roll"){curplayer.rollDieAtIndex(game,2);}
        if (Dice4_Hold.getValue()=="Re-Roll"){curplayer.rollDieAtIndex(game,3);}
        if (Dice5_Hold.getValue()=="Re-Roll"){curplayer.rollDieAtIndex(game,4);}
        this.curplayer.decreaseRerollCount();
        updateDice(true);
        if(this.curplayer.canRoll(game)){
            this.ReRoll.setVisible(true);
            this.SkipReRoll.setVisible(true);
            updatePlayers();
        }
        else{
            this.ReRoll.setVisible(false);
            this.SkipReRoll.setVisible(false);
            Dice1_Hold.setVisible(false);
            Dice2_Hold.setVisible(false);
            Dice3_Hold.setVisible(false);
            Dice4_Hold.setVisible(false);
            Dice5_Hold.setVisible(false);
            
            Dice1_Hold.getSelectionModel().selectFirst();
            Dice2_Hold.getSelectionModel().selectFirst();
            Dice3_Hold.getSelectionModel().selectFirst();
            Dice4_Hold.getSelectionModel().selectFirst();
            Dice5_Hold.getSelectionModel().selectFirst();
            updatePlayers();
            setupAction();
        }
    }    

    /**
     * Use to take action after the take action button is clicked
     */
    @FXML
    void TakeAction(ActionEvent event) {
       Dice1_Target.setVisible(false);
       Dice2_Target.setVisible(false);
       Dice3_Target.setVisible(false);
       Dice4_Target.setVisible(false);
       Dice5_Target.setVisible(false);
       Action.setVisible(false);
       EndTurn.setVisible(true);
       Roll.setVisible(false);
       ReRoll.setVisible(false);
       
        if(game.getDice().doesRequireChooseableActionAtIndex(0)){
            this.curplayer.takeActionOnDieAtIndex(game, 0, Dice1_Target.getSelectionModel().getSelectedItem().toString());
        }
        if(game.getDice().doesRequireChooseableActionAtIndex(1)){
            this.curplayer.takeActionOnDieAtIndex(game, 1, Dice2_Target.getSelectionModel().getSelectedItem().toString());
        }
        if(game.getDice().doesRequireChooseableActionAtIndex(2)){
            this.curplayer.takeActionOnDieAtIndex(game, 2, Dice3_Target.getSelectionModel().getSelectedItem().toString());
        }
        if(game.getDice().doesRequireChooseableActionAtIndex(3)){
            this.curplayer.takeActionOnDieAtIndex(game, 3, Dice4_Target.getSelectionModel().getSelectedItem().toString());
        }
        if(game.getDice().doesRequireChooseableActionAtIndex(4)){
            this.curplayer.takeActionOnDieAtIndex(game, 4, Dice5_Target.getSelectionModel().getSelectedItem().toString());
        }
        this.curplayer.preformGatlingCheckAndAction(game);
        updatePlayers();
    }
    
    /**
     * used to provide a setup for the action button
     * @author Stephen Devaney
     */
    private void setupAction(){  
        if(this.curplayer.canHaveAction(game)){
            if(game.getDice().doesRequireChooseableActionAtIndex(0)){
                Dice1_Target.setVisible(true);
                Action.setVisible(true);
                this.Dice1_Target.setItems(observableArrayList(this.curplayer.getTargetForDieAtIndex(game, 0)));
                this.Dice1_Target.getSelectionModel().select(this.curplayer.getSelectedTargetForDieAtIndex(game, 0));
            }
            if(game.getDice().doesRequireChooseableActionAtIndex(1)){
                Dice2_Target.setVisible(true);
                Action.setVisible(true);
                this.Dice2_Target.setItems(observableArrayList(this.curplayer.getTargetForDieAtIndex(game, 1)));
                this.Dice2_Target.getSelectionModel().select(this.curplayer.getSelectedTargetForDieAtIndex(game, 1));
            }
            if(game.getDice().doesRequireChooseableActionAtIndex(2)){
                Dice3_Target.setVisible(true);
                Action.setVisible(true);
                this.Dice3_Target.setItems(observableArrayList(this.curplayer.getTargetForDieAtIndex(game, 2)));
                this.Dice3_Target.getSelectionModel().select(this.curplayer.getSelectedTargetForDieAtIndex(game, 2));
            }
            if(game.getDice().doesRequireChooseableActionAtIndex(3)){
                Dice4_Target.setVisible(true);
                Action.setVisible(true);
                this.Dice4_Target.setItems(observableArrayList(this.curplayer.getTargetForDieAtIndex(game, 3)));
                this.Dice4_Target.getSelectionModel().select(this.curplayer.getSelectedTargetForDieAtIndex(game, 3));
            }
            if(game.getDice().doesRequireChooseableActionAtIndex(4)){
                Dice5_Target.setVisible(true);
                Action.setVisible(true);
                this.Dice5_Target.setItems(observableArrayList(this.curplayer.getTargetForDieAtIndex(game, 4)));
                this.Dice5_Target.getSelectionModel().select(this.curplayer.getSelectedTargetForDieAtIndex(game, 4));
            }
            if(!Action.isVisible()){
                this.curplayer.preformGatlingCheckAndAction(game);
                EndTurn.setVisible(true);
            }
        }
        else{
            EndTurn.setVisible(true);
        }
    }
    
    /*
     *  For the actual players...Fields are tagged as Pos1 (for the actual player) and increment clockwise to Pos2 through Pos8.
     *  If fewer than 8 play, say only 4, it is possible to name Pos3 to Player 2, Pos5 to Player 3, and Pos7 to Player 4.  Could make the TitledPanes for the other positions
     *  not visible.
     *
     *  As a side note...the individual @FXML declarations are required to be on individual lines.  The Scene Builder only recognizes the last element on the line
     */
    
    
    /**
     * button to skip the rest of the players rerolls
     */
    @FXML
    void SkipReroll(ActionEvent event){
        this.Roll.setVisible(false);
        this.ReRoll.setVisible(false);
        this.SkipReRoll.setVisible(false);
        Dice1_Hold.setVisible(false);
        Dice2_Hold.setVisible(false);
        Dice3_Hold.setVisible(false);
        Dice4_Hold.setVisible(false);
        Dice5_Hold.setVisible(false);
            
        Dice1_Hold.getSelectionModel().selectFirst();
        Dice2_Hold.getSelectionModel().selectFirst();
        Dice3_Hold.getSelectionModel().selectFirst();
        Dice4_Hold.getSelectionModel().selectFirst();
        Dice5_Hold.getSelectionModel().selectFirst();
        updatePlayers();
        setupAction();
    }
    
    /**
     * button to end the players turn
     * @author Stephen Devaney
     */
    @FXML
    void EndTurn(ActionEvent event){
        updatePlayers();
        this.updateDice(false);
        Dice1_Hold.setVisible(false);
        Dice2_Hold.setVisible(false);
        Dice3_Hold.setVisible(false);
        Dice4_Hold.setVisible(false);
        Dice5_Hold.setVisible(false);
        startNextTurn();
    }
    
    /**
     * help method to display the end condition of the game
     * @author Stephen Devaney
     */
    private void displayEndCondtion(){
        this.HumanPlayer.setVisible(false);
        this.ComputerPlayer.setVisible(false);
        this.gameover.setVisible(true);
        this.gameover.setCollapsible(false);
        this.Roll.setVisible(false);
        this.ReRoll.setVisible(false);
        this.Action.setVisible(false);
        this.EndTurn.setVisible(false);
        this.SkipReRoll.setVisible(false);
        if(game.getEndCondition() == 1){this.SandDwincond.setVisible(true);}
        else if(game.getEndCondition() == 2){this.Swincond.setVisible(true);}
        else if(game.getEndCondition() == 3){this.Rwincond.setVisible(true);}
        else{this.Owincond.setVisible(true);}
    }

    /**
     * updates the dice for the roll dice method
     * @author Stephen Devaney
     */
    private void updateDice(boolean reroll){
        Die_1.setImage(diefaces[game.getDice().getDieAtIndex(2)]);
        Die_2.setImage(diefaces[game.getDice().getDieAtIndex(1)]);
        
        if(game.getDice().getDieTypeAtIndex(2) == DieType.BASIC)Die_3.setImage(diefaces[game.getDice().getDieAtIndex(2)]);
        else if(game.getDice().getDieTypeAtIndex(2) == DieType.LOUDMOUTH)Die_3.setImage(loudmouth[game.getDice().getDieAtIndex(2)]);
        else if(game.getDice().getDieTypeAtIndex(2) == DieType.COWARD)Die_3.setImage(coward[game.getDice().getDieAtIndex(2)]);
        else{Die_3.setImage(dueldie[game.getDice().getDieAtIndex(2)]);}
        
        if(game.getDice().getDieTypeAtIndex(3) == DieType.BASIC)Die_4.setImage(diefaces[game.getDice().getDieAtIndex(3)]);
        else if(game.getDice().getDieTypeAtIndex(3) == DieType.LOUDMOUTH)Die_4.setImage(loudmouth[game.getDice().getDieAtIndex(3)]);
        else if(game.getDice().getDieTypeAtIndex(3) == DieType.COWARD)Die_4.setImage(coward[game.getDice().getDieAtIndex(3)]);
        else{Die_4.setImage(dueldie[game.getDice().getDieAtIndex(3)]);}
        
        if(game.getDice().getDieTypeAtIndex(4) == DieType.BASIC)Die_5.setImage(diefaces[game.getDice().getDieAtIndex(4)]);
        else if(game.getDice().getDieTypeAtIndex(4) == DieType.LOUDMOUTH)Die_5.setImage(loudmouth[game.getDice().getDieAtIndex(4)]);
        else if(game.getDice().getDieTypeAtIndex(4) == DieType.COWARD)Die_5.setImage(coward[game.getDice().getDieAtIndex(4)]);
        else{Die_5.setImage(dueldie[game.getDice().getDieAtIndex(4)]);}
        
        Dice1_Hold.setVisible(game.getDice().getRerollableAtIndex(0));
        Dice2_Hold.setVisible(game.getDice().getRerollableAtIndex(1));
        Dice3_Hold.setVisible(game.getDice().getRerollableAtIndex(2));
        Dice4_Hold.setVisible(game.getDice().getRerollableAtIndex(3));
        Dice5_Hold.setVisible(game.getDice().getRerollableAtIndex(4));
        
        if(!Dice1_Hold.isVisible()){Dice1_Hold.getSelectionModel().selectFirst();}
        else if (!reroll) {Dice1_Hold.getSelectionModel().select("Re-Roll");}
        
        if(!Dice2_Hold.isVisible()){Dice2_Hold.getSelectionModel().selectFirst();}
        else if (!reroll) {Dice2_Hold.getSelectionModel().select("Re-Roll");}
        
        if(!Dice3_Hold.isVisible()){Dice3_Hold.getSelectionModel().selectFirst();}
        else if (!reroll) {Dice3_Hold.getSelectionModel().select("Re-Roll");}
        
        if(!Dice4_Hold.isVisible()){Dice4_Hold.getSelectionModel().selectFirst();}
        else if (!reroll) {Dice4_Hold.getSelectionModel().select("Re-Roll");}
        
        if(!Dice5_Hold.isVisible()){Dice5_Hold.getSelectionModel().selectFirst();}
        else if (!reroll) {Dice5_Hold.getSelectionModel().select("Re-Roll");}
    }
    
    /**
     *  method to start a players next turn
     * @author Stephen Devaney
     */
    private void startNextTurn(){
        this.curplayer.endTurn(game);
        this.curplayer = game.getCurPlayer();
        updatePlayers();
        if(this.curplayer.getPlayerType() == PlayerType.HUMAN){
            this.HumanPlayer.setVisible(true);
            this.ComputerPlayer.setVisible(false);
            if(this.curplayer.canRoll(game)){
                this.Roll.setVisible(true);
                this.ReRoll.setVisible(false);
                this.Action.setVisible(false);
                this.EndTurn.setVisible(false);
                this.SkipReRoll.setVisible(false);
                this.Dice3_Dice.setVisible(true);
            }
            else{
                this.Roll.setVisible(false);
                this.ReRoll.setVisible(false);
                this.Action.setVisible(false);
                this.EndTurn.setVisible(true);
                this.SkipReRoll.setVisible(false);
            }
        }
        else{
                this.HumanPlayer.setVisible(false);
                this.ComputerPlayer.setVisible(true);
                this.Roll.setVisible(false);
                this.ReRoll.setVisible(false);
                this.Action.setVisible(false);
                this.EndTurn.setVisible(true);
                this.SkipReRoll.setVisible(false);
                this.computeroutput.setText(this.curplayer.simulateTurn(game));
                this.updatePlayers();
        }
    }
    
    /**
     * method to change the highlighted box to the current active player
     * @author Stephen Devaney
     */
    private void updateActivePlayer(){
        // Indicates inactive player
        Pos1_ID.setStyle("-fx-border-color: transparent");
        Pos2_ID.setStyle("-fx-border-color: transparent");
        Pos3_ID.setStyle("-fx-border-color: transparent");
        Pos4_ID.setStyle("-fx-border-color: transparent");
        Pos5_ID.setStyle("-fx-border-color: transparent");
        Pos6_ID.setStyle("-fx-border-color: transparent");
        Pos7_ID.setStyle("-fx-border-color: transparent");
        Pos8_ID.setStyle("-fx-border-color: transparent");
        int activeplayerindex = 0;
        for(int i = 0; i < playerindexes.length; i++){
            if(game.getCurPlayerIndex() == playerindexes[i]){
                activeplayerindex = i;
                break;
            }
        }
        
        // Indicates active player
        switch(activeplayerindex){
            case 1:{
                Pos2_ID.setStyle("-fx-border-color: red"); 
                break;
            }
            case 2:{
                Pos3_ID.setStyle("-fx-border-color: red"); 
                break;
            }
            case 3:{
                Pos4_ID.setStyle("-fx-border-color: red"); 
                break;
            }
            case 4:{
                Pos5_ID.setStyle("-fx-border-color: red"); 
                break;
            }
            case 5:{
                Pos6_ID.setStyle("-fx-border-color: red"); 
                break;
            }
            case 6:{
                Pos7_ID.setStyle("-fx-border-color: red"); 
                break;
            }
            case 7:{
                Pos8_ID.setStyle("-fx-border-color: red"); 
                break;
            }
            default :{
                Pos1_ID.setStyle("-fx-border-color: red"); 
                break;
            }
        }
    }
    
    /**
     * method to setup the players
     * @author Steven Lowry
     * @author Stephen Devaney updated after connection game to business logic
     */
    private void setupPlayers(){
        playerindexes = new int[8];
        if(game.getStartingNumPlayers() == 4){
            Pos2_ID.setVisible(false);
            Pos4_ID.setVisible(false);
            Pos6_ID.setVisible(false);
            Pos8_ID.setVisible(false);
            playerindexes[0] = 0;
            playerindexes[1] = 0;
            playerindexes[2] = 1;
            playerindexes[3] = 0;
            playerindexes[4] = 2;
            playerindexes[5] = 0;
            playerindexes[6] = 3;
            playerindexes[7] = 0;
        }
        else if(game.getStartingNumPlayers() == 5){
            Pos4_ID.setVisible(false);
            Pos6_ID.setVisible(false);
            Pos8_ID.setVisible(false);
            playerindexes[0] = 0;
            playerindexes[1] = 1;
            playerindexes[2] = 2;
            playerindexes[3] = 0;
            playerindexes[4] = 3;
            playerindexes[5] = 0;
            playerindexes[6] = 4;
            playerindexes[7] = 0;
        }
        else if(game.getStartingNumPlayers() == 6){
            Pos6_ID.setVisible(false);
            Pos8_ID.setVisible(false);
            playerindexes[0] = 0;
            playerindexes[1] = 1;
            playerindexes[2] = 2;
            playerindexes[3] = 3;
            playerindexes[4] = 4;
            playerindexes[5] = 0;
            playerindexes[6] = 5;
            playerindexes[7] = 0;
        }
        else if(game.getStartingNumPlayers() == 7){
            Pos8_ID.setVisible(false);
            playerindexes[0] = 0;
            playerindexes[1] = 1;
            playerindexes[2] = 2;
            playerindexes[3] = 3;
            playerindexes[4] = 4;
            playerindexes[5] = 5;
            playerindexes[6] = 6;
            playerindexes[7] = 0;
        }
        else{
            playerindexes[0] = 0;
            playerindexes[1] = 1;
            playerindexes[2] = 2;
            playerindexes[3] = 3;
            playerindexes[4] = 4;
            playerindexes[5] = 5;
            playerindexes[6] = 6;
            playerindexes[7] = 7;
        }
        Pos1_Role.setImage(rolecards[game.getPlayerAtIndex(playerindexes[0]).getRoleindex(game)]);
        Pos1_Name.setImage(game.getPlayerAtIndex(playerindexes[0]).getCharacterImage());
        Pos1_ID.setText("You: " + game.getPlayerAtIndex(playerindexes[0]).getcharactername());
        Pos1_Cur_Arrow.setText(Integer.toString(game.getPlayerAtIndex(playerindexes[0]).getArrows()));
        Pos1_Cur_LP.setText(Integer.toString(game.getPlayerAtIndex(playerindexes[0]).getCurLife()));
        Pos1_Max_LP.setText(Integer.toString(game.getPlayerAtIndex(playerindexes[0]).getMaxLife()));
        Pos1_LP.setProgress(game.getPlayerAtIndex(playerindexes[0]).getLifeProgress());
        Pos1_ID.setCollapsible(false);
        
        Pos2_Role.setImage(rolecards[game.getPlayerAtIndex(playerindexes[1]).getRoleindex(game)]);
        Pos2_Name.setImage(game.getPlayerAtIndex(playerindexes[1]).getCharacterImage());
        Pos2_ID.setText("Computer Player " + Integer.toString(playerindexes[1]) + ": " + game.getPlayerAtIndex(playerindexes[1]).getcharactername());
        Pos2_Cur_Arrow.setText(Integer.toString(game.getPlayerAtIndex(playerindexes[1]).getArrows()));
        Pos2_Cur_LP.setText(Integer.toString(game.getPlayerAtIndex(playerindexes[1]).getCurLife()));
        Pos2_Max_LP.setText(Integer.toString(game.getPlayerAtIndex(playerindexes[1]).getMaxLife()));
        Pos2_LP.setProgress(game.getPlayerAtIndex(playerindexes[1]).getLifeProgress());
        Pos2_ID.setCollapsible(false);
        
        Pos3_Role.setImage(rolecards[game.getPlayerAtIndex(playerindexes[2]).getRoleindex(game)]);
        Pos3_Name.setImage(game.getPlayerAtIndex(playerindexes[2]).getCharacterImage());
        Pos3_ID.setText("Computer Player " + Integer.toString(playerindexes[2]) + ": " + game.getPlayerAtIndex(playerindexes[2]).getcharactername());
        Pos3_Cur_Arrow.setText(Integer.toString(game.getPlayerAtIndex(playerindexes[2]).getArrows()));
        Pos3_Cur_LP.setText(Integer.toString(game.getPlayerAtIndex(playerindexes[2]).getCurLife()));
        Pos3_Max_LP.setText(Integer.toString(game.getPlayerAtIndex(playerindexes[2]).getMaxLife()));
        Pos3_LP.setProgress(game.getPlayerAtIndex(playerindexes[2]).getLifeProgress());
        Pos3_ID.setCollapsible(false);
        
        Pos4_Role.setImage(rolecards[game.getPlayerAtIndex(playerindexes[3]).getRoleindex(game)]);
        Pos4_Name.setImage(game.getPlayerAtIndex(playerindexes[3]).getCharacterImage());
        Pos4_ID.setText("Computer Player " + Integer.toString(playerindexes[3]) + ": " + game.getPlayerAtIndex(playerindexes[3]).getcharactername());
        Pos4_Cur_Arrow.setText(Integer.toString(game.getPlayerAtIndex(playerindexes[3]).getArrows()));
        Pos4_Cur_LP.setText(Integer.toString(game.getPlayerAtIndex(playerindexes[3]).getCurLife()));
        Pos4_Max_LP.setText(Integer.toString(game.getPlayerAtIndex(playerindexes[3]).getMaxLife()));
        Pos4_LP.setProgress(game.getPlayerAtIndex(playerindexes[3]).getLifeProgress());
        Pos4_ID.setCollapsible(false);
        
        Pos5_Role.setImage(rolecards[game.getPlayerAtIndex(playerindexes[4]).getRoleindex(game)]);
        Pos5_Name.setImage(game.getPlayerAtIndex(playerindexes[4]).getCharacterImage());
        Pos5_ID.setText("Computer Player " + Integer.toString(playerindexes[4]) + ": " + game.getPlayerAtIndex(playerindexes[4]).getcharactername());
        Pos5_Cur_Arrow.setText(Integer.toString(game.getPlayerAtIndex(playerindexes[4]).getArrows()));
        Pos5_Cur_LP.setText(Integer.toString(game.getPlayerAtIndex(playerindexes[4]).getCurLife()));
        Pos5_Max_LP.setText(Integer.toString(game.getPlayerAtIndex(playerindexes[4]).getMaxLife()));
        Pos5_LP.setProgress(game.getPlayerAtIndex(playerindexes[4]).getLifeProgress());
        Pos5_ID.setCollapsible(false);
        
        Pos6_Role.setImage(rolecards[game.getPlayerAtIndex(playerindexes[5]).getRoleindex(game)]);
        Pos6_Name.setImage(game.getPlayerAtIndex(playerindexes[5]).getCharacterImage());
        Pos6_ID.setText("Computer Player " + Integer.toString(playerindexes[5]) + ": " + game.getPlayerAtIndex(playerindexes[5]).getcharactername());
        Pos6_Cur_Arrow.setText(Integer.toString(game.getPlayerAtIndex(playerindexes[5]).getArrows()));
        Pos6_Cur_LP.setText(Integer.toString(game.getPlayerAtIndex(playerindexes[5]).getCurLife()));
        Pos6_Max_LP.setText(Integer.toString(game.getPlayerAtIndex(playerindexes[5]).getMaxLife()));
        Pos6_LP.setProgress(game.getPlayerAtIndex(playerindexes[5]).getLifeProgress());
        Pos6_ID.setCollapsible(false);
        
        Pos7_Role.setImage(rolecards[game.getPlayerAtIndex(playerindexes[6]).getRoleindex(game)]);
        Pos7_Name.setImage(game.getPlayerAtIndex(playerindexes[6]).getCharacterImage());
        Pos7_ID.setText("Computer Player " + Integer.toString(playerindexes[6]) + ": " + game.getPlayerAtIndex(playerindexes[6]).getcharactername());
        Pos7_Cur_Arrow.setText(Integer.toString(game.getPlayerAtIndex(playerindexes[6]).getArrows()));
        Pos7_Cur_LP.setText(Integer.toString(game.getPlayerAtIndex(playerindexes[6]).getCurLife()));
        Pos7_Max_LP.setText(Integer.toString(game.getPlayerAtIndex(playerindexes[6]).getMaxLife()));
        Pos7_LP.setProgress(game.getPlayerAtIndex(playerindexes[6]).getLifeProgress());
        Pos7_ID.setCollapsible(false);
        
        Pos8_Role.setImage(rolecards[game.getPlayerAtIndex(playerindexes[7]).getRoleindex(game)]);
        Pos8_Name.setImage(game.getPlayerAtIndex(playerindexes[7]).getCharacterImage());
        Pos8_ID.setText("Computer Player " + Integer.toString(playerindexes[7]) + ": " + game.getPlayerAtIndex(playerindexes[7]).getcharactername());
        Pos8_Cur_Arrow.setText(Integer.toString(game.getPlayerAtIndex(playerindexes[7]).getArrows()));
        Pos8_Cur_LP.setText(Integer.toString(game.getPlayerAtIndex(playerindexes[7]).getCurLife()));
        Pos8_Max_LP.setText(Integer.toString(game.getPlayerAtIndex(playerindexes[7]).getMaxLife()));
        Pos8_LP.setProgress(game.getPlayerAtIndex(playerindexes[7]).getLifeProgress());
        Pos8_ID.setCollapsible(false);
        this.curplayer = game.getCurPlayer();
        updateActivePlayer();
    }
    
    /**
     * method to update the player status in the game
     * @author Stephend Devaney
     */
    public void updatePlayers(){
        updateActivePlayer();
        Pos1_Role.setImage(rolecards[game.getPlayerAtIndex(playerindexes[0]).getRoleindex(game)]);
        Pos1_Cur_Arrow.setText(Integer.toString(game.getPlayerAtIndex(playerindexes[0]).getArrows()));
        Pos1_Cur_LP.setText(Integer.toString(game.getPlayerAtIndex(playerindexes[0]).getCurLife()));
        Pos1_LP.setProgress(game.getPlayerAtIndex(playerindexes[0]).getLifeProgress());
        Pos1_ChiefArrow.setVisible(game.getPlayerAtIndex(playerindexes[0]).hasIndianChiefArrow());
        if(game.getPlayerAtIndex(playerindexes[0]).isPlayerDead()) {Pos1_ID.setText("You Are Dead");}
        
        Pos2_Role.setImage(rolecards[game.getPlayerAtIndex(playerindexes[1]).getRoleindex(game)]);
        Pos2_Cur_Arrow.setText(Integer.toString(game.getPlayerAtIndex(playerindexes[1]).getArrows()));
        Pos2_Cur_LP.setText(Integer.toString(game.getPlayerAtIndex(playerindexes[1]).getCurLife()));
        Pos2_LP.setProgress(game.getPlayerAtIndex(playerindexes[1]).getLifeProgress());
        Pos2_ChiefArrow.setVisible(game.getPlayerAtIndex(playerindexes[1]).hasIndianChiefArrow());
        if(game.getPlayerAtIndex(playerindexes[1]).isPlayerDead()) {Pos2_ID.setText("Computer Player " + Integer.toString(playerindexes[1]) + " Is Dead");}
        
        Pos3_Role.setImage(rolecards[game.getPlayerAtIndex(playerindexes[2]).getRoleindex(game)]);
        Pos3_Cur_Arrow.setText(Integer.toString(game.getPlayerAtIndex(playerindexes[2]).getArrows()));
        Pos3_Cur_LP.setText(Integer.toString(game.getPlayerAtIndex(playerindexes[2]).getCurLife()));
        Pos3_LP.setProgress(game.getPlayerAtIndex(playerindexes[2]).getLifeProgress());
        Pos3_ChiefArrow.setVisible(game.getPlayerAtIndex(playerindexes[2]).hasIndianChiefArrow());
        if(game.getPlayerAtIndex(playerindexes[2]).isPlayerDead()) {Pos3_ID.setText("Computer Player " + Integer.toString(playerindexes[2]) + " Is Dead");}
        
        Pos4_Role.setImage(rolecards[game.getPlayerAtIndex(playerindexes[3]).getRoleindex(game)]);
        Pos4_Cur_Arrow.setText(Integer.toString(game.getPlayerAtIndex(playerindexes[3]).getArrows()));
        Pos4_Cur_LP.setText(Integer.toString(game.getPlayerAtIndex(playerindexes[3]).getCurLife()));
        Pos4_LP.setProgress(game.getPlayerAtIndex(playerindexes[3]).getLifeProgress());
        Pos4_ChiefArrow.setVisible(game.getPlayerAtIndex(playerindexes[3]).hasIndianChiefArrow());
        if(game.getPlayerAtIndex(playerindexes[3]).isPlayerDead()) {Pos4_ID.setText("Computer Player " + Integer.toString(playerindexes[3]) + " Is Dead");}
        
        Pos5_Role.setImage(rolecards[game.getPlayerAtIndex(playerindexes[4]).getRoleindex(game)]);
        Pos5_Cur_Arrow.setText(Integer.toString(game.getPlayerAtIndex(playerindexes[4]).getArrows()));
        Pos5_Cur_LP.setText(Integer.toString(game.getPlayerAtIndex(playerindexes[4]).getCurLife()));
        Pos5_LP.setProgress(game.getPlayerAtIndex(playerindexes[4]).getLifeProgress());
        Pos5_ChiefArrow.setVisible(game.getPlayerAtIndex(playerindexes[4]).hasIndianChiefArrow());
        if(game.getPlayerAtIndex(playerindexes[4]).isPlayerDead()) {Pos5_ID.setText("Computer Player " + Integer.toString(playerindexes[4]) + " Is Dead");}
        
        Pos6_Role.setImage(rolecards[game.getPlayerAtIndex(playerindexes[5]).getRoleindex(game)]);
        Pos6_Cur_Arrow.setText(Integer.toString(game.getPlayerAtIndex(playerindexes[5]).getArrows()));
        Pos6_Cur_LP.setText(Integer.toString(game.getPlayerAtIndex(playerindexes[5]).getCurLife()));
        Pos6_LP.setProgress(game.getPlayerAtIndex(playerindexes[5]).getLifeProgress());
        Pos6_ChiefArrow.setVisible(game.getPlayerAtIndex(playerindexes[5]).hasIndianChiefArrow());
        if(game.getPlayerAtIndex(playerindexes[5]).isPlayerDead()) {Pos6_ID.setText("Computer Player " + Integer.toString(playerindexes[5]) + " Is Dead");}
        
        Pos7_Role.setImage(rolecards[game.getPlayerAtIndex(playerindexes[6]).getRoleindex(game)]);
        Pos7_Cur_Arrow.setText(Integer.toString(game.getPlayerAtIndex(playerindexes[6]).getArrows()));
        Pos7_Cur_LP.setText(Integer.toString(game.getPlayerAtIndex(playerindexes[6]).getCurLife()));
        Pos7_LP.setProgress(game.getPlayerAtIndex(playerindexes[6]).getLifeProgress());
        Pos7_ChiefArrow.setVisible(game.getPlayerAtIndex(playerindexes[6]).hasIndianChiefArrow());
        if(game.getPlayerAtIndex(playerindexes[6]).isPlayerDead()) {Pos7_ID.setText("Computer Player " + Integer.toString(playerindexes[6]) + " Is Dead");}
        
        Pos8_Role.setImage(rolecards[game.getPlayerAtIndex(playerindexes[7]).getRoleindex(game)]);
        Pos8_Cur_Arrow.setText(Integer.toString(game.getPlayerAtIndex(playerindexes[7]).getArrows()));
        Pos8_Cur_LP.setText(Integer.toString(game.getPlayerAtIndex(playerindexes[7]).getCurLife()));
        Pos8_LP.setProgress(game.getPlayerAtIndex(playerindexes[7]).getLifeProgress());
        Pos8_ChiefArrow.setVisible(game.getPlayerAtIndex(playerindexes[7]).hasIndianChiefArrow());
        if(game.getPlayerAtIndex(playerindexes[7]).isPlayerDead()) {Pos8_ID.setText("Computer Player " + Integer.toString(playerindexes[7]) + " Is Dead");}
        
        Pos9_ChiefArrow.setVisible(game.hasindianChiefArrow);
        arrowpile.setText(Integer.toString(game.getArrowPile()));
        if(this.game.isEndCondition()){displayEndCondtion();}
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }  
}
