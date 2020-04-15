/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import entities.Contrat;
import entities.Evenement;
import entities.Participant;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.management.Notification;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;
import services.ContratService;
import services.EvenementService;
import services.NotificationEvenementService;
import services.ParticipantService;
import services.UserSevice;
import tray.animations.AnimationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class FXMLParticipantController implements Initializable {

     @FXML
    private AnchorPane parent;


    @FXML
    private TextField id;

    @FXML
    private ImageView imageView;

    @FXML
    private JFXTextField eventnam;

    @FXML
    private JFXTextField eventid;

    @FXML
    private JFXTextField eventname;

    @FXML
    private JFXButton btnparticiper;

  public static String nomevent="";
    @FXML
    void participer(Event event) throws SQLException {
       
        int nb=Integer.valueOf(id.getText());//le nbr de place reserver par le user  
        int ide=Integer.valueOf(eventid.getText());//recuperer leevent
        //Recuperation du user connecte
       UserSevice s1=new UserSevice();
       String ss= s1.getlogin() ;                 //prendre l id du user connecté
       int result = Integer.parseInt(ss);			
       System.out.println(ss);
       String notif=s1.getnotification();
        Participant P = new Participant();
       
           EvenementService es=new EvenementService();   
           Evenement e=es.Eventfindbyid(ide);//retourne levent qui a lid passer en parametre
           
           System.out.println("mesmes");
           System.out.println(ide);
           if(e.getNbrpart()<nb){
             //JOptionPane.showMessageDialog(null, "Erreur! Quantite de la commande dépasse le stock");
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Pas de place pour cet evenement ");
            alert.setHeaderText("Il reste que"
                    + e.getNbrpart() +     "Places");
               Optional<ButtonType> resul = alert.showAndWait();
           }
           else{
        int neweventcapacity= e.getNbrpart()-nb;
        System.out.println(e.getNbrpart());
   
         P.setEvenement_id(ide);
         P.setUser_id(result);
          P.setNbr(nb);
          //System.out.println(nb);
          es.edit(neweventcapacity,e.getId());
          //System.out.println(neweventcapacity);

 ParticipantService ps = new ParticipantService();
ps.addParticipant(P);
TrayNotification tray = new TrayNotification();
                Image whatsAppImg = new Image("/images/mother.png");
                String text = "Vous avez Participer ";
                  tray.setTray("welcome", text + " ", whatsAppImg, Paint.valueOf("#66ff33"), AnimationType.SLIDE);

            tray.showAndDismiss(Duration.seconds(7));
           
              
          NotificationEvenementService s3=new NotificationEvenementService();
           
 	s3.ajouter_notif();
        
           
                 
           
           
           
           }

 

    }
    
    ////////////////////////////redirection de mayssaa////////////////
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
    void homeretour(ActionEvent event) {
        
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
    void inscriptionretour(ActionEvent event) {
        

    
          try {
               
      
               Parent AnchorPane = FXMLLoader.load(getClass().getResource("inscription.fxml"));
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

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         int id1= FXMLEvenementController.E_id_selection;
 eventid.setText(String.valueOf(id1));
          eventnam.setText(FXMLEvenementController.E_nom_selection);
    }    
    
}
