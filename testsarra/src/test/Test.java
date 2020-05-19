/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import entities.Personne;
import javafx.stage.StageStyle;
import  services.PersonneService;
import utils.MyConnection;


/**
 *
 * @author wejdene
 */
public class Test extends Application {
    public static Stage stage = null;
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("UI.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
   
        stage.setResizable(false);
        stage.centerOnScreen();
        
       
    
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
     
        launch(args);
    }
    
}
