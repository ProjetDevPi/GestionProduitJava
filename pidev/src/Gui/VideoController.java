/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import static javafx.scene.media.MediaPlayer.Status.PLAYING;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class VideoController implements Initializable {
    
      MediaPlayer mediaplayer;
    
    @FXML
    private MediaView mv;

        @FXML
    private Button btn_play;

    @FXML
    private Button btn_stop;

    
   

    @FXML
    void stopvideo(MouseEvent event) {
        
  mediaplayer.stop();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
       String VUrl="file:/C:/Users/ASUS/Desktop/kids.mp4";

       Media media = new Media(VUrl);

        mediaplayer = new MediaPlayer(media);
       mv.setFitHeight(700);
       mv.setFitWidth(700);
       mv.setMediaPlayer(mediaplayer);
        mediaplayer.play();
       
    }    
   ///////////////////////////////////redirectionnnn//////////////////
    
    @FXML
    void carnet(ActionEvent event) {
         try {
               
      
               Parent AnchorPane = FXMLLoader.load(getClass().getResource("carnet.fxml"));
               Stage stage = new Stage();
               Scene scene = new Scene(AnchorPane);
               stage.setScene(scene);
               stage.show();
               
           } catch (IOException ex) {
              
           }
                   
    
                          ((Node) event.getSource()).getScene().getWindow().hide();

    }

    @FXML
    void events(ActionEvent event) {
         try {
               
      
               Parent AnchorPane = FXMLLoader.load(getClass().getResource("FXMLEvenement.fxml"));
               Stage stage = new Stage();
               Scene scene = new Scene(AnchorPane);
               stage.setScene(scene);
               stage.show();
               
           } catch (IOException ex) {
              
           }
                   
    
                          ((Node) event.getSource()).getScene().getWindow().hide();

    }

    @FXML
    void home(ActionEvent event) {
         try {
               
      
               Parent AnchorPane = FXMLLoader.load(getClass().getResource("Home.fxml"));
               Stage stage = new Stage();
               Scene scene = new Scene(AnchorPane);
               stage.setScene(scene);
               stage.show();
               
           } catch (IOException ex) {
              
           }
                   
    
                          ((Node) event.getSource()).getScene().getWindow().hide();

    }

    @FXML
    void library(ActionEvent event) {
         try {
               
      
               Parent AnchorPane = FXMLLoader.load(getClass().getResource("Emprunt.fxml"));
               Stage stage = new Stage();
               Scene scene = new Scene(AnchorPane);
               stage.setScene(scene);
               stage.show();
               
           } catch (IOException ex) {
              
           }
                   
    
                          ((Node) event.getSource()).getScene().getWindow().hide();

    }

    @FXML
    void products(ActionEvent event) {
         try {
               
      
               Parent AnchorPane = FXMLLoader.load(getClass().getResource("ProduitFront.fxml"));
               Stage stage = new Stage();
               Scene scene = new Scene(AnchorPane);
               stage.setScene(scene);
               stage.show();
               
           } catch (IOException ex) {
              
           }
                   
    
                          ((Node) event.getSource()).getScene().getWindow().hide();

    }
}
