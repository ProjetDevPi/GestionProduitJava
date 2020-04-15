/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import entities.Contrat;
import entities.NotificationEvenement;
import entities.Participant;
import entities.Sponsor;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;
import services.NotificationEvenementService;
import services.ParticipantService;
import services.SponsorService;
import utils.MailSend;
import utils.MyConnection;



/**
 * FXML Controller class
 *
 * @author HP
 */
public class FXMLParticipationbackController implements Initializable {

     @FXML
    private AnchorPane parent;
     @FXML
    private ImageView notif_empty;

    @FXML
    private ImageView notif_1;
      @FXML
    private TableView<Participant> part;

    @FXML
    private TableColumn<?, ?> event;

    @FXML
    private TableColumn<?, ?> participant;

    @FXML
    private TableColumn<Participant,Integer> nbrplace;
      @FXML
    private TableColumn<Participant,Integer> iduser;

    @FXML
    private JFXTextField namedetail;

    @FXML
    private JFXTextField participantdeatil;

    @FXML
    private JFXTextField datedetail;
  @FXML
    private ImageView imageView;
    @FXML
    private JFXTextField nbrpace;
     @FXML
    private JFXButton btninsert1;
         @FXML
    private JFXButton envoyer;
@FXML
    private TableColumn<?,?> imageC;
  private Connection cnx;
    private Statement st;
    private PreparedStatement pre;
    private Image image;
    /////////Men/////////
    @FXML
    private JFXButton btnevent;

    @FXML
    private JFXButton btnsponsor;

    @FXML
    private JFXButton btncontrat;

    @FXML
    private JFXButton btnpart;
    
    
      String path = "C:\\Users\\ASUS\\Desktop\\wejdene\\default-iphone.mp3";
      
      Media media = new Media(new File(path).toURI().toString());
      
      MediaPlayer mediaplayer = new MediaPlayer(media);


 @FXML
    void event(ActionEvent event) throws IOException {
          Parent root = FXMLLoader.load(getClass().getResource("FXMLEvenement.fxml"));//appler la page edit
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
       
         stage.show();
          ((Node) event.getSource()).getScene().getWindow().hide();

    }

    @FXML
    void participant(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("FXMLParticipationback.fxml"));//appler la page edit
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
       
         stage.show();
          ((Node) event.getSource()).getScene().getWindow().hide();

    }

    @FXML
    void sponsor(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("FXMLSponsor.fxml"));//appler la page edit
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
       
         stage.show();
          ((Node) event.getSource()).getScene().getWindow().hide();

    }
       @FXML
    void contrat(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLContrat.fxml"));//appler la page edit
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
       
         stage.show();
          ((Node) event.getSource()).getScene().getWindow().hide();

    }
    ////////////endMenu////
      private void showProductImage(String nomm) throws SQLException{
        cnx = MyConnection.getInstance().getCnx();
        st=cnx.createStatement();
        try {
           String req="Select * from evenement";
            
            
       ResultSet     rs ;
       rs=st.executeQuery(req);
     
    
            while(rs.next()) {
                
                if (rs.getString("nom").equals(nomm))
                {                 
                
                    System.out.println(rs.getString("nom"));
                String a=rs.getString("nom_image");
                
                
                
                image = new Image("file:"+a+"", imageView.getFitWidth(), imageView.getFitHeight(), true, true);
                    System.out.println(image);
                imageView.setImage(image);
                imageView.setPreserveRatio(true);
            
            }
            }
        } catch (SQLException ex) {
            Logger.getLogger(FXMLEvenementController.class.getName()).log(Level.SEVERE, null, ex);
        
        
    }
        }
    @FXML
    void deletes(Event event) {
        if (!part.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Participant ");
            alert.setHeaderText("Are you sure you want to delete this participant"
                    + part.getSelectionModel().getSelectedItem().getId() + "?");
            Optional<ButtonType> result = alert.showAndWait();
        
        
          if (result.get() == ButtonType.OK) {
        
        
             ParticipantService ms = new  ParticipantService();
        ObservableList< Participant> ll, ttmission;
        ttmission = part.getItems();
        // ta3tina les lignes selectionnés 
        ll = part.getSelectionModel().getSelectedItems();

        for ( Participant m : ll) {
            ttmission.remove(m);//refresh tableview
            ms.DeleteParticipant(m.getId());
        }
        JOptionPane.showMessageDialog(null, "supprimer");

    }
          }
          else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Erreur de selection");
            alert.setHeaderText("Vous etes obligé de selectioner un participant  ");

            Optional<ButtonType> result = alert.showAndWait();
        }

    }
    
     ObservableList<Participant> listP = null;
     ParticipantService ps = new ParticipantService();
     ///////////////////////////Detail////////////////////////
     private void detaill(){
        part.setOnMouseClicked((MouseEvent e) -> {
            Participant p = part.getItems().get(part.getSelectionModel().getSelectedIndex());
           namedetail.setText(p.getEvent());
           participantdeatil.setText(p.getParticipant());
           //datedetail.setText(String.valueOf(p.getDateevent()));
           nbrpace.setText(String.valueOf(p.getNbr()));
           /*/ try {
                showProductImage(p.getNom());
            } catch (SQLException ex) {
                Logger.getLogger(FXMLEvenementController.class.getName()).log(Level.SEVERE, null, ex);
            }
               /*/ 
  
          
        });
     }
     /////////////////////////////////////////////////////Graph
     
  
     ///////////////////////////Mail
       @FXML
   private void envoyer(Event event) {
        
       
         envoyer.setOnMouseClicked(e -> {
             Participant p =part.getItems().get(part.getSelectionModel().getSelectedIndex());
             //y récuperi l produit
          int idd=p.getUser_id();
             System.out.println(idd);
         
         ParticipantService as = new ParticipantService();
     
      String mailuser = null; 
      String usernom =null;
     
      try {
          mailuser = as.getemail(idd);
         
        usernom=as.getloginusername();
      MailSend m=new MailSend();
                String subject = "Participation accepted!";
               
               
                String message ="***************Hello"+usernom+"yourparticipation is accepted /n";
                       
               
               
                m.sendMail("mayssa.zaouali@esprit.tn", mailuser, subject, message);
       
               
           } catch (SQLException ex) {
                 Logger.getLogger(FXMLParticipationbackController.class.getName()).log(Level.SEVERE, null, ex);
             }
         });
                 }
  
        
     ////////////////////////////////////////   notifi
     @FXML
    void notification() {
          
    NotificationEvenementService s3=new NotificationEvenementService();
    List<NotificationEvenement> notif = s3.afficherNotif();
    
     for(int i=0;i<notif.size();i++)
         
      {
         Notifications notificationBuilder;
            mediaplayer.play();
    notificationBuilder = Notifications.create()
            .title("Paiement")
            
            .text("Notification " +(i+1)+": New participation from "+notif.get(i).getNom_user()+" in : " +notif.get(i).getNom_event())
           
          
             .hideAfter(Duration.seconds(6))
            .position(Pos.BOTTOM_RIGHT)
           
            ;
            
    notificationBuilder.darkStyle();
        notificationBuilder.show();
      
      
      }
    }
     
 	 private void notif_empty()
    {
        notif_empty.setVisible(true);
        notif_1.setVisible(false);
    }
    
     private void notif_1()
    {
        notif_1.setVisible(true);
        notif_empty.setVisible(false);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        detaill();
        
        nbrplace.setCellValueFactory(new PropertyValueFactory<Participant,Integer>("nbr"));
        participant.setCellValueFactory(new PropertyValueFactory<>("participant"));//participant une variable dans la classe participant
          event.setCellValueFactory(new PropertyValueFactory<>("event"));
          iduser.setCellValueFactory(new PropertyValueFactory<Participant,Integer>("user_id"));
             imageC.setCellValueFactory(new PropertyValueFactory<>("image")); 
        try {
            listP = ps.getMeals();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLParticipantController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        part.setItems(listP);
         ////////notif
         
    NotificationEvenementService s3=new NotificationEvenementService();
    List<NotificationEvenement> notif = s3.afficherNotif();
        if (notif.size() >0)
        {
            notif_1();
        }
        else
        {
            notif_empty();
        }
        notif_1.setOnMouseClicked(e -> {
             
                  notification(); //besh y affichi l notif
                  notif_empty();	//besh y affichi l taswira mtaa ma3adesh aandek notif
                  s3.deletenotif(); //besh yfassakh mel table
                
                
     
     });
         
     notif_empty.setOnMouseClicked(e -> {
             
                 
                Notifications notificationBuilder;
    notificationBuilder = Notifications.create()
            .title("Pas de Notifications")
            
            .text("Vous n'avez pas de notifications pour le moment ! ")
           
          
             .hideAfter(Duration.seconds(6))
            .position(Pos.BOTTOM_RIGHT)
           
            ;
            
    notificationBuilder.darkStyle();
        notificationBuilder.show();
      
                
     
     });
    
       
    }   
   
    
}
