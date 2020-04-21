/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bang_gui;

import Game.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import player.*;


/**
 * FXML Controller class
 *
 * @author Steven
 */
public class GameBoardController implements Initializable {
BangGame game;
int playerindexes[];
    
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
     *  Array for Dice face images.  Array element zero is just the bang logo, used for initialization.
     */
    String [] diefacefilenames = {"/Images/bang .jpg","/Images/Bang-dice-1.jpg","/Images/Bang-dice-2.jpg","/Images/Bang-dice-3.jpg","/Images/Bang-dice-4.jpg",
                           "/Images/Bang-dice-5.jpg", "Images/Bang-dice-6.jpg"};
    Image diefaces[];
    
    /*
     *  Array for character role images.
     */
    String[] Character_Roles = {"/Images/bang-back-of-card.jpg","/Images/Sheriff1.jpg","/Images/Renegade.jpg","/Images/Deputy.jpg","/Images/Outlaw.jpg"};
    Image rolecards[];
    
    
    @FXML ImageView Die_1;
    @FXML ImageView Die_2;
    @FXML ImageView Die_3;
    @FXML ImageView Die_4;
    @FXML ImageView Die_5;
    
    @FXML Button Roll;
    @FXML Button ReRoll;
    @FXML Button Action;
    
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
    @FXML Label Pos1_Cur_Arrow;
    @FXML Label Pos2_Cur_Arrow;
    @FXML Label Pos3_Cur_Arrow;
    @FXML Label Pos4_Cur_Arrow;
    @FXML Label Pos5_Cur_Arrow;
    @FXML Label Pos6_Cur_Arrow;
    @FXML Label Pos7_Cur_Arrow;
    @FXML Label Pos8_Cur_Arrow;
    @FXML ProgressBar Pos1_Arrow;
    @FXML ProgressBar Pos2_Arrow;
    @FXML ProgressBar Pos3_Arrow;
    @FXML ProgressBar Pos4_Arrow;
    @FXML ProgressBar Pos5_Arrow;
    @FXML ProgressBar Pos6_Arrow;
    @FXML ProgressBar Pos7_Arrow;
    @FXML ProgressBar Pos8_Arrow;
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
    /*
     *  Example of randomizing dice and use of imageview boxes.  Replace code
     *   with appropriate dice class items.  Interacts with Hold/Re-Roll button just as an example.  This is to link the roll dice button with code.
     */
    @FXML
    void Roll_Dice(ActionEvent event) {
        game.getDice().rollDice();
        updateDice();
        Roll.setVisible(false);
        ReRoll.setVisible(true);

    } 

    
    /*
     *  Example of randomizing dice and use of imageview boxes.  Replace code
     *  with appropriate dice class items.  This is to link the Re-roll dice button with code.
     *  Re-roll shows interaction with Hold.  Modify code to work with class abilities and reroll counts, max dynamite, etc.
     */
    @FXML
    void ReRoll_Dice(ActionEvent event) {
        if (Dice1_Hold.getValue()=="Re-Roll"){game.getDice().rollDieAtIndex(0);}
        if (Dice2_Hold.getValue()=="Re-Roll"){game.getDice().rollDieAtIndex(1);}
        if (Dice3_Hold.getValue()=="Re-Roll"){game.getDice().rollDieAtIndex(2);}
        if (Dice4_Hold.getValue()=="Re-Roll"){game.getDice().rollDieAtIndex(3);}
        if (Dice5_Hold.getValue()=="Re-Roll"){game.getDice().rollDieAtIndex(4);;}
        updateDice();
    }    

    @FXML
    void TakeAction(ActionEvent event) {
       Dice1_Hold.setDisable(false);
       Dice1_Hold.getSelectionModel().select("Select");
       Dice2_Hold.setDisable(false);
       Dice2_Hold.getSelectionModel().select("Select");
       Dice3_Hold.setDisable(false);
       Dice3_Hold.getSelectionModel().select("Select");
       Dice4_Hold.setDisable(false);
       Dice4_Hold.getSelectionModel().select("Select");
       Dice5_Hold.setDisable(false);
       Dice5_Hold.getSelectionModel().select("Select");
       Roll.setVisible(true);
       ReRoll.setVisible(false);
    }
    /*
     *  For the actual players...Fields are tagged as Pos1 (for the actual player) and increment clockwise to Pos2 through Pos8.
     *  If fewer than 8 play, say only 4, it is possible to name Pos3 to Player 2, Pos5 to Player 3, and Pos7 to Player 4.  Could make the TitledPanes for the other positions
     *  not visible.
     *
     *  As a side note...the individual @FXML declarations are required to be on individual lines.  The Scene Builder only recognizes the last element on the line
     */

    /**
     * updates the dice for the roll dice method
     * @author Stephen Devaney
     */
    private void updateDice(){
        Die_1.setImage(diefaces[game.getDice().getDieAtIndex(0)]);
        Die_2.setImage(diefaces[game.getDice().getDieAtIndex(1)]);
        Die_3.setImage(diefaces[game.getDice().getDieAtIndex(2)]);
        Die_4.setImage(diefaces[game.getDice().getDieAtIndex(3)]);
        Die_5.setImage(diefaces[game.getDice().getDieAtIndex(4)]);
        
        Dice1_Hold.setVisible(game.getDice().getRerollableAtIndex(0));
        Dice2_Hold.setVisible(game.getDice().getRerollableAtIndex(1));
        Dice3_Hold.setVisible(game.getDice().getRerollableAtIndex(2));
        Dice4_Hold.setVisible(game.getDice().getRerollableAtIndex(3));
        Dice5_Hold.setVisible(game.getDice().getRerollableAtIndex(4));
        
        if(!Dice1_Hold.isVisible()){Dice1_Hold.getSelectionModel().selectFirst();}
        else {Dice1_Hold.getSelectionModel().select("Re-Roll");}
        
        if(!Dice2_Hold.isVisible()){Dice2_Hold.getSelectionModel().selectFirst();}
        else {Dice2_Hold.getSelectionModel().select("Re-Roll");}
        
        if(!Dice3_Hold.isVisible()){Dice3_Hold.getSelectionModel().selectFirst();}
        else {Dice3_Hold.getSelectionModel().select("Re-Roll");}
        
        if(!Dice4_Hold.isVisible()){Dice4_Hold.getSelectionModel().selectFirst();}
        else {Dice4_Hold.getSelectionModel().select("Re-Roll");}
        
        if(!Dice5_Hold.isVisible()){Dice5_Hold.getSelectionModel().selectFirst();}
        else {Dice5_Hold.getSelectionModel().select("Re-Roll");}
    }
    
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
            playerindexes[7] = 8;
        }
        Pos1_Role.setImage(rolecards[game.getPlayerAtIndex(playerindexes[0]).getRoleindex()]);
        Pos1_Name.setImage(game.getPlayerAtIndex(playerindexes[0]).getCharacterImage());
        Pos1_ID.setText("You: " + game.getPlayerAtIndex(playerindexes[0]).getcharactername());
        Pos1_Cur_Arrow.setText(Integer.toString(game.getPlayerAtIndex(playerindexes[0]).getArrows()));
        Pos1_Max_LP.setText(Integer.toString(game.getPlayerAtIndex(playerindexes[0]).getCurLife()));
        Pos1_Cur_LP.setText(Integer.toString(game.getPlayerAtIndex(playerindexes[0]).getMaxLife()));
        Pos1_LP.setProgress(game.getPlayerAtIndex(playerindexes[0]).getLifeProgress());
        Pos1_ID.setCollapsible(false);
        //  Indicates active player 
        Pos1_ID.setStyle("-fx-border-color: red");
        
        Pos2_Role.setImage(rolecards[game.getPlayerAtIndex(playerindexes[1]).getRoleindex()]);
        Pos2_Name.setImage(game.getPlayerAtIndex(playerindexes[1]).getCharacterImage());
        Pos2_ID.setText("Computer Player " + Integer.toString(playerindexes[1]) + game.getPlayerAtIndex(playerindexes[1]).getcharactername());
        Pos2_Cur_Arrow.setText(Integer.toString(game.getPlayerAtIndex(playerindexes[1]).getArrows()));
        Pos2_Max_LP.setText(Integer.toString(game.getPlayerAtIndex(playerindexes[1]).getCurLife()));
        Pos2_Cur_LP.setText(Integer.toString(game.getPlayerAtIndex(playerindexes[1]).getMaxLife()));
        Pos2_LP.setProgress(game.getPlayerAtIndex(playerindexes[1]).getLifeProgress());
        Pos2_ID.setCollapsible(false);
        //  Indicates active player 
        Pos2_ID.setStyle("-fx-border-color: transparent");
        
        Pos3_Role.setImage(rolecards[game.getPlayerAtIndex(playerindexes[2]).getRoleindex()]);
        Pos3_Name.setImage(game.getPlayerAtIndex(playerindexes[2]).getCharacterImage());
        Pos3_ID.setText("Computer Player " + Integer.toString(playerindexes[2]) + game.getPlayerAtIndex(playerindexes[2]).getcharactername());
        Pos3_Cur_Arrow.setText(Integer.toString(game.getPlayerAtIndex(playerindexes[2]).getArrows()));
        Pos3_Max_LP.setText(Integer.toString(game.getPlayerAtIndex(playerindexes[2]).getCurLife()));
        Pos3_Cur_LP.setText(Integer.toString(game.getPlayerAtIndex(playerindexes[2]).getMaxLife()));
        Pos3_LP.setProgress(game.getPlayerAtIndex(playerindexes[2]).getLifeProgress());
        Pos3_ID.setCollapsible(false);
        //  Indicates active player 
        Pos3_ID.setStyle("-fx-border-color: transparent");
        
        Pos4_Role.setImage(rolecards[game.getPlayerAtIndex(playerindexes[3]).getRoleindex()]);
        Pos4_Name.setImage(game.getPlayerAtIndex(playerindexes[3]).getCharacterImage());
        Pos4_ID.setText("Computer Player " + Integer.toString(playerindexes[3]) + game.getPlayerAtIndex(playerindexes[3]).getcharactername());
        Pos4_Cur_Arrow.setText(Integer.toString(game.getPlayerAtIndex(playerindexes[3]).getArrows()));
        Pos4_Max_LP.setText(Integer.toString(game.getPlayerAtIndex(playerindexes[3]).getCurLife()));
        Pos4_Cur_LP.setText(Integer.toString(game.getPlayerAtIndex(playerindexes[3]).getMaxLife()));
        Pos4_LP.setProgress(game.getPlayerAtIndex(playerindexes[3]).getLifeProgress());
        Pos4_ID.setCollapsible(false);
        //  Indicates active player 
        Pos4_ID.setStyle("-fx-border-color: transparent");
        
        Pos5_Role.setImage(rolecards[game.getPlayerAtIndex(playerindexes[4]).getRoleindex()]);
        Pos5_Name.setImage(game.getPlayerAtIndex(playerindexes[4]).getCharacterImage());
        Pos5_ID.setText("Computer Player " + Integer.toString(playerindexes[4]) + game.getPlayerAtIndex(playerindexes[4]).getcharactername());
        Pos5_Cur_Arrow.setText(Integer.toString(game.getPlayerAtIndex(playerindexes[4]).getArrows()));
        Pos5_Max_LP.setText(Integer.toString(game.getPlayerAtIndex(playerindexes[4]).getCurLife()));
        Pos5_Cur_LP.setText(Integer.toString(game.getPlayerAtIndex(playerindexes[4]).getMaxLife()));
        Pos5_LP.setProgress(game.getPlayerAtIndex(playerindexes[4]).getLifeProgress());
        Pos5_ID.setCollapsible(false);
        //  Indicates active player 
        Pos5_ID.setStyle("-fx-border-color: transparent");
        
        Pos6_Role.setImage(rolecards[game.getPlayerAtIndex(playerindexes[5]).getRoleindex()]);
        Pos6_Name.setImage(game.getPlayerAtIndex(playerindexes[5]).getCharacterImage());
        Pos6_ID.setText("Computer Player " + Integer.toString(playerindexes[5]) + game.getPlayerAtIndex(playerindexes[5]).getcharactername());
        Pos6_Cur_Arrow.setText(Integer.toString(game.getPlayerAtIndex(playerindexes[5]).getArrows()));
        Pos6_Max_LP.setText(Integer.toString(game.getPlayerAtIndex(playerindexes[5]).getCurLife()));
        Pos6_Cur_LP.setText(Integer.toString(game.getPlayerAtIndex(playerindexes[5]).getMaxLife()));
        Pos6_LP.setProgress(game.getPlayerAtIndex(playerindexes[5]).getLifeProgress());
        Pos6_ID.setCollapsible(false);
        //  Indicates active player 
        Pos6_ID.setStyle("-fx-border-color: transparent");
        
        Pos7_Role.setImage(rolecards[game.getPlayerAtIndex(playerindexes[6]).getRoleindex()]);
        Pos7_Name.setImage(game.getPlayerAtIndex(playerindexes[6]).getCharacterImage());
        Pos7_ID.setText("Computer Player " + Integer.toString(playerindexes[6]) + game.getPlayerAtIndex(playerindexes[6]).getcharactername());
        Pos7_Cur_Arrow.setText(Integer.toString(game.getPlayerAtIndex(playerindexes[6]).getArrows()));
        Pos7_Max_LP.setText(Integer.toString(game.getPlayerAtIndex(playerindexes[6]).getCurLife()));
        Pos7_Cur_LP.setText(Integer.toString(game.getPlayerAtIndex(playerindexes[6]).getMaxLife()));
        Pos7_LP.setProgress(game.getPlayerAtIndex(playerindexes[6]).getLifeProgress());
        Pos7_ID.setCollapsible(false);
        //  Indicates active player 
        Pos7_ID.setStyle("-fx-border-color: transparent");
        
        Pos8_Role.setImage(rolecards[game.getPlayerAtIndex(playerindexes[7]).getRoleindex()]);
        Pos8_Name.setImage(game.getPlayerAtIndex(playerindexes[7]).getCharacterImage());
        Pos8_ID.setText("Computer Player " + Integer.toString(playerindexes[7]) + game.getPlayerAtIndex(playerindexes[7]).getcharactername());
        Pos8_Cur_Arrow.setText(Integer.toString(game.getPlayerAtIndex(playerindexes[7]).getArrows()));
        Pos8_Max_LP.setText(Integer.toString(game.getPlayerAtIndex(playerindexes[7]).getCurLife()));
        Pos8_Cur_LP.setText(Integer.toString(game.getPlayerAtIndex(playerindexes[7]).getMaxLife()));
        Pos8_LP.setProgress(game.getPlayerAtIndex(playerindexes[7]).getLifeProgress());
        Pos8_ID.setCollapsible(false);
        //  Indicates active player 
        Pos8_ID.setStyle("-fx-border-color: transparent");
    }
    
    public void updatePlayers(){
        if(game.getStartingNumPlayers() == 4){;}
        else if(game.getStartingNumPlayers() == 5){;}
        else if(game.getStartingNumPlayers() == 6){;}
        else if(game.getStartingNumPlayers() == 7){;}
        else if(game.getStartingNumPlayers() == 8){;}
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       BangSetup setup = new BangSetup(8);
       game = new BangGame(setup);
       Dice1_Hold.getItems().addAll("Hold", "Re-Roll");
       Dice2_Hold.getItems().addAll("Hold", "Re-Roll");
       Dice3_Hold.getItems().addAll("Hold", "Re-Roll");
       Dice4_Hold.getItems().addAll("Hold", "Re-Roll");
       Dice5_Hold.getItems().addAll("Hold", "Re-Roll");
       
       diefaces = new Image[7];
       for(int i = 0; i < diefaces.length; i++){
           diefaces[i] = new Image(diefacefilenames[i]);
       }
       
       rolecards = new Image[5];
       for(int i = 0; i < rolecards.length; i++){
           rolecards[i] = new Image(Character_Roles[i]);
       }
       
       setupPlayers();
       
       updateDice();
    }  
}

