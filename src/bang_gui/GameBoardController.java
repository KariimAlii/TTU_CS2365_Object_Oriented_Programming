/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bang_gui;

import Game.BangGame;
import Game.BangSetup;
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


/**
 * FXML Controller class
 *
 * @author Steven
 */
public class GameBoardController implements Initializable {
BangGame game;
    
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
     *  Array for character role images.
     */
    String[] Character_Roles = {"/Images/Sheriff1.jpg","/Images/Outlaw.jpg","/Images/Deputy.jpg","/Images/Renegade.jpg","/Images/bang-back-of-card.jpg"};
    /*
     *  Array for character names.  Could add "/Path/character.jpg" to the character class.  In the initialization, the following line:
     *   Image char_name = new Image(Character_Names[0]); would change to:
     *   Image char_name = new Image(BartCassidy.character_pic);    
     */
    String [] Character_Names = {"/Images/Bart Cassidy.jpg", "/Images/Black Jack.jpg", "/Images/Calamity Janet.jpg","/Images/El Gringo.jpg",
        "/Images/Jesse Jones.jpg","/Images/Jourdonnais.jpg","Images/Kit Carlson.jpg","Images/Lucky Duke.jpg","/Images/Paul Regret.jpg","/Images/Pedro Ramirez.jpg",
        "/Images/Rose Doolan.jpg","/Images/Sid Ketchum.jpg","/Images/Slab the Killer.jpg","/Images/Suzy Lafayette.jpg","/Images/Vulture Sam.jpg","/Images/Willy the Kid.jpg"};
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
       
       Image role = new Image(Character_Roles[1]);
       Pos1_Role.setImage(role);
       Image char_name = new Image(Character_Names[0]);
       Pos1_Name.setImage(char_name);
       Pos1_ID.setText("Player 1");
       //  Indicates active player 
       Pos1_ID.setStyle("-fx-border-color: red");
       Pos1_Cur_Arrow.setText("1");
       //  If current and max arrows (max arrows = 9) are written to labels, the following would calculate the progress bar
       double A_Value = Double.parseDouble(Pos1_Cur_Arrow.getText())/9.0;
       Pos1_Arrow.setProgress(A_Value);
       Pos1_Max_LP.setText("8");
       Pos1_Cur_LP.setText("7");
       //  If current and max life points are written to labels, the following would calculate the progress bar.
       double LP_Value = Double.parseDouble(Pos1_Cur_LP.getText())/Double.parseDouble(Pos1_Max_LP.getText());
       Pos1_LP.setProgress(LP_Value);
       Pos1_ID.setCollapsible(false);
       
       role = new Image(Character_Roles[3]);
       Pos2_Role.setImage(role);
       char_name = new Image(Character_Names[1]);
       Pos2_Name.setImage(char_name);
       Pos2_ID.setText("Player 2");
       //  Indicates active player 
       Pos2_ID.setStyle("-fx-border-color: transparent");
       Pos2_Cur_Arrow.setText("0");
       Pos2_Arrow.setProgress(0);
       Pos2_Max_LP.setText("8");
       Pos2_Cur_LP.setText("8");
       Pos2_LP.setProgress(1);
       Pos2_ID.setCollapsible(false);

       role = new Image(Character_Roles[0]);
       Pos3_Role.setImage(role);
       char_name = new Image(Character_Names[2]);
       Pos3_Name.setImage(char_name);
       Pos3_ID.setText("Player 3");
       //  Indicates active player 
       Pos3_ID.setStyle("-fx-border-color: transparent");
       Pos3_Cur_Arrow.setText("0");
       Pos3_Arrow.setProgress(0);
       Pos3_Max_LP.setText("10");
       Pos3_Cur_LP.setText("10");
       Pos3_LP.setProgress(1);
       Pos3_ID.setCollapsible(false);

       role = new Image(Character_Roles[1]);
       Pos4_Role.setImage(role);
       char_name = new Image(Character_Names[3]);
       Pos4_Name.setImage(char_name);
       Pos4_ID.setText("Player 4");
       //  Indicates active player 
       Pos4_ID.setStyle("-fx-border-color: transparent");
       Pos4_Cur_Arrow.setText("0");
       Pos4_Arrow.setProgress(0);
       Pos4_Max_LP.setText("7");
       Pos4_Cur_LP.setText("7");
       Pos4_LP.setProgress(1);
       Pos4_ID.setCollapsible(false);

       role = new Image(Character_Roles[1]);
       Pos5_Role.setImage(role);
       char_name = new Image(Character_Names[4]);
       Pos5_Name.setImage(char_name);
       Pos5_ID.setText("Player 5");
       //  Indicates active player 
       Pos5_ID.setStyle("-fx-border-color: transparent");
       Pos5_Cur_Arrow.setText("0");
       Pos5_Arrow.setProgress(0);
       Pos5_Max_LP.setText("9");
       Pos5_Cur_LP.setText("9");
       Pos5_LP.setProgress(1);
       Pos5_ID.setCollapsible(false);

       role = new Image(Character_Roles[3]);
       Pos6_Role.setImage(role);
       char_name = new Image(Character_Names[5]);
       Pos6_Name.setImage(char_name);
       Pos6_ID.setText("Player 6");
       //  Indicates active player 
       Pos6_ID.setStyle("-fx-border-color: transparent");
       Pos6_Cur_Arrow.setText("0");
       Pos6_Arrow.setProgress(0);
       Pos6_Max_LP.setText("7");
       Pos6_Cur_LP.setText("7");
       Pos6_LP.setProgress(1);
       Pos6_ID.setCollapsible(false);

       role = new Image(Character_Roles[2]);
       Pos7_Role.setImage(role);
       char_name = new Image(Character_Names[6]);
       Pos7_Name.setImage(char_name);
       Pos7_ID.setText("Player 7");
       //  Indicates active player 
       Pos7_ID.setStyle("-fx-border-color: transparent");
       Pos7_Cur_Arrow.setText("0");
       Pos7_Arrow.setProgress(0);
       Pos7_Max_LP.setText("7");
       Pos7_Cur_LP.setText("7");
       Pos7_LP.setProgress(1);
       Pos7_ID.setCollapsible(false);

       role = new Image(Character_Roles[2]);
       Pos8_Role.setImage(role);
       char_name = new Image(Character_Names[7]);
       Pos8_Name.setImage(char_name);
       Pos8_ID.setText("Player 8");
       //  Indicates active player 
       Pos8_ID.setStyle("-fx-border-color: transparent");
       Pos8_Cur_Arrow.setText("0");
       Pos8_Arrow.setProgress(0);
       Pos8_Max_LP.setText("8");
       Pos8_Cur_LP.setText("8");
       
       // progress bar info need from players
       int curlife = 7;
       int maxlife = 8;
       double curlifedouble = curlife;
       double maxlifedouble = maxlife;
       Pos8_Max_LP.setText(Integer.toString(maxlife));
       Pos8_Cur_LP.setText(Integer.toString(curlife));
       Pos8_LP.setProgress(curlifedouble/maxlifedouble);
       Pos8_ID.setCollapsible(false);
       


//  If a position is not to be filled, set Pos8_ID.setVisible to false       
//       Pos8_ID.setVisible(false);
       diefaces = new Image[7];
       for(int i = 0; i < diefaces.length; i++){
           diefaces[i] = new Image(diefacefilenames[i]);
       }
       updateDice();
    }  
}

