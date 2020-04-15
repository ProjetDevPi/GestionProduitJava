/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import com.jfoenix.controls.JFXButton;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.NodeOrientation;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import services.CommandeProduitService;

/**
 *
 * @author oXCToo
 */
public class HomeController implements Initializable {
      @FXML
    private JFXButton btnab;

    
      @FXML
    private AnchorPane rootpane;
    @FXML
    private JFXButton prod;
    
    @FXML
    private Label label;
    
      @FXML
    private VBox pnl_scroll;
      
      String path = "C:\\Users\\ASUS\\Downloads\\enfants.mp3";
      
      Media media = new Media(new File(path).toURI().toString());
      
      MediaPlayer mediaplayer = new MediaPlayer(media);
        @FXML
    private Text user;
      
      
         @FXML
      void stop(MouseEvent event) {
        
        mediaplayer.stop();
    
    }
      
              
      
      
      
        @FXML
    void play(MouseEvent event) {
        mediaplayer.play();
        

    }


    
    @FXML
    private void handleButtonAction(MouseEvent event) {        
       refreshNodes();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
  CommandeProduitService cs= new CommandeProduitService();
         refreshNodes();
          try {
              user.setText(cs.getloginusername());
          } catch (SQLException ex) {
              Logger.getLogger(ProduitFrontController.class.getName()).log(Level.SEVERE, null, ex);
          }
    }    
    
    private void refreshNodes()
    {
        pnl_scroll.getChildren().clear();
        
        Node [] nodes = new  Node[15];
        
       
            try {
                
                nodes[1] = (Node)FXMLLoader.load(getClass().getResource("Item.fxml"));
               pnl_scroll.getChildren().add(nodes[1]);
               
                 nodes[2] = (Node)FXMLLoader.load(getClass().getResource("info.fxml"));
               pnl_scroll.getChildren().add(nodes[2]);
               
                  nodes[3] = (Node)FXMLLoader.load(getClass().getResource("separation.fxml"));
               pnl_scroll.getChildren().add(nodes[3]);
               
               
               
               nodes[4] = (Node)FXMLLoader.load(getClass().getResource("teachers.fxml"));
               pnl_scroll.getChildren().add(nodes[4]);
               
             
               
                
            } catch (IOException ex) {
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            
           
        }  
    }
    
     @FXML
    void products(ActionEvent event) throws IOException {
         try {
               
      
               Parent AnchorPane = FXMLLoader.load(getClass().getResource("ProduitFront.fxml"));
               Stage stage = new Stage();
               Scene scene = new Scene(AnchorPane);
               stage.setScene(scene);
               stage.show();
                stage.setTitle("Produits");
           } catch (IOException ex) {
              
           }
                   
    
                          ((Node) event.getSource()).getScene().getWindow().hide();
        

    }
       
    
    
      @FXML
    void login(MouseEvent event) {
        
        
         try {
               
      
               Parent AnchorPane = FXMLLoader.load(getClass().getResource("FXMLLogin.fxml"));
               Stage stage = new Stage();
               Scene scene = new Scene(AnchorPane);
               stage.setScene(scene);
               stage.show();
                stage.setTitle("Login");
           } catch (IOException ex) {
              
           }
                   
    
                          ((Node) event.getSource()).getScene().getWindow().hide();
        

    }
    

     @FXML
    void inscrir(ActionEvent event) throws IOException {
makeFateOut();
    }
    
    
    private void makeFateOut(){
        FadeTransition fadeTransition =new FadeTransition();
        fadeTransition.setDuration(Duration.millis(1000));
        fadeTransition.setNode(rootpane);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.setOnFinished(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event ){
                try {
                    loadNextScene();
                } catch (IOException ex) {
                    Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        );
          
      fadeTransition.play();
    }
    private void loadNextScene() throws IOException{
       try { Parent secondView;
        secondView = FXMLLoader.load(getClass().getResource("inscription.fxml"));
        Scene newScene=new Scene(secondView);
        Stage curStage =(Stage) rootpane.getScene().getWindow();
        curStage.setScene(newScene);}
      catch (IOException ex) {
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
       @FXML
    void carnet(ActionEvent event) throws IOException {
   AnchorPane root = FXMLLoader.load(getClass().getResource("carnet.fxml"));
        Scene scene = btnab.getScene();
        root.translateYProperty().set(scene.getHeight());

        rootpane.getChildren().add(root);

        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_IN);
        KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
        timeline.getKeyFrames().add(kf);
        timeline.setOnFinished(t -> {
            rootpane.getChildren().remove(rootpane);
        });
        timeline.play();
    }
    
    
    /////////////////redirection events Mayssa//////////////////
      @FXML
    void events(ActionEvent event) {
try {
               
      
               Parent AnchorPane = FXMLLoader.load(getClass().getResource("FXMLeventfront.fxml"));
               Stage stage = new Stage();
               Scene scene = new Scene(AnchorPane);
               stage.setScene(scene);
               stage.show();
                stage.setTitle("Evenements");
           } catch (IOException ex) {
              
           }
                   
    
                          ((Node) event.getSource()).getScene().getWindow().hide();
    }

   /////////////////////////////redirection Mehdi///////////////////
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
    
    ///////////////////////////////////WEB////////////////////
     @FXML
    void actionPerformed(MouseEvent event) throws URISyntaxException, IOException  {
          Desktop.getDesktop().browse(new URI("http://localhost/ProjetPi/web/app_dev.php/login"));

    }
    }

    

