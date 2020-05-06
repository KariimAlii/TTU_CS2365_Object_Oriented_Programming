/*
 * TITLE: Project 3 Bang The Dice Game
 * AUTHOR: Steven Lowry
 * FOR: CS 2365 Object Oriented Programming Section 001 Spring 2020
 */
package bang_gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Application class for Bang GUI
 * @author Steven
 */
public class Bang_GUI extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("BangStartUp.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("BANG - The Dice Game");
        stage.show();
    }

    /**
     * main method
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
