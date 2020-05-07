/*
 * TITLE: Project 3 Bang The Dice Game
 * AUTHOR: Stephen C Devaney
 * FOR: CS 2365 Object Oriented Programming Section 001 Spring 2020
 */
package bang_gui;

import Game.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ToggleGroup;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;
import javafx.stage.Stage;


/**
 * FXML Controller class
 * @author Stephen C. Devaney
 */
public class BangStartUpController implements Initializable {

    // Old Saloon Modules
    @FXML private CheckBox TheLoudmouthAndTheCoward;
    @FXML private CheckBox TheArrowOfTheIndianChief;
    @FXML private CheckBox OldSaloonCharacters;

    // Undead or Alive Modules
    @FXML private CheckBox LookMeInTheEyes;
    @FXML private CheckBox UndeadOrAliveCharacters;
    @FXML private CheckBox UndeadOrAliveModule;

    // other game setup
    @FXML private RadioButton radiobutton3;
    @FXML private RadioButton radiobutton4;
    @FXML private RadioButton radiobutton5;
    @FXML private RadioButton radiobutton6;
    @FXML private RadioButton radiobutton7;

    // start game
    @FXML private Button StartGame;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        assert UndeadOrAliveModule != null : "fx:id=\"UndeadOrAliveModule\" was not injected: check your FXML file 'BangStartUp.fxml'.";
        assert OldSaloonCharacters != null : "fx:id=\"OldSaloonCharacters\" was not injected: check your FXML file 'BangStartUp.fxml'.";
        assert UndeadOrAliveCharacters != null : "fx:id=\"UndeadOrAliveCharacters\" was not injected: check your FXML file 'BangStartUp.fxml'.";
        assert StartGame != null : "fx:id=\"StartGame\" was not injected: check your FXML file 'BangStartUp.fxml'.";
        assert TheLoudmouthAndTheCoward != null : "fx:id=\"TheLoudmouthAndTheCoward\" was not injected: check your FXML file 'BangStartUp.fxml'.";
        assert TheArrowOfTheIndianChief != null : "fx:id=\"TheArrowOfTheIndianChief\" was not injected: check your FXML file 'BangStartUp.fxml'.";
        assert LookMeInTheEyes != null : "fx:id=\"LookMeInTheEyes\" was not injected: check your FXML file 'BangStartUp.fxml'.";
    }
    
    
    /** 
     * When this method is called, it will change the scene to the gameboard
     * view
     */
    public void startGame(ActionEvent event) throws Exception{
        BangSetup setup = new BangSetup(getNumberOfPlayers());
        if(this.OldSaloonCharacters.isSelected()){setup.addOldSaloonCharacters();}
        if(this.UndeadOrAliveCharacters.isSelected()){setup.addUndeadOrAliveCharacters();}
        if(this.TheArrowOfTheIndianChief.isSelected()){setup.activeIndianChiefArrow();}
        if(this.LookMeInTheEyes.isSelected()){setup.activatedueldice();}
        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("GameBoard.fxml"));
        Parent root = loader.load();
        
        Scene scene = new Scene(root);
        
        GameBoardController controller = loader.getController();
        controller.controllerSetup(setup);
        
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.getIcons().add(new Image("/Images/bang .jpg"));
        stage.show();
    }
    
    
    /** 
     * gets number of players from radio buttons
     */
    private int getNumberOfPlayers(){
        int returnvalue = 4;
        if (radiobutton3.isSelected()) returnvalue = 4;
        else if (radiobutton4.isSelected()) returnvalue = 5;
        else if (radiobutton5.isSelected()) returnvalue = 6;
        else if (radiobutton6.isSelected()) returnvalue = 7;
        else if (radiobutton7.isSelected()) returnvalue = 8;
        return returnvalue;
    }
}
