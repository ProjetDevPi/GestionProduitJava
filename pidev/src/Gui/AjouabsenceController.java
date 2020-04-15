/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import entities.absence;
import entities.eleve;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import services.AbsenceService;

/**
 * FXML Controller class
 *
 * @author wejdene
 */
public class AjouabsenceController implements Initializable {
      ObservableList<String> et = FXCollections.observableArrayList("Justifié","Non Justifié","En attente");
          @FXML
    private JFXComboBox<String> etat;
 @FXML
    private JFXTextField ideleve;

    @FXML
    private JFXTextField justification;

 

    @FXML
    private JFXDatePicker date;
       @FXML
    private Text alert;
AbsenceService as=new AbsenceService();

   String path = "C:\\Users\\ASUS\\Desktop\\wejdene\\done.wav";
      
      Media media = new Media(new File(path).toURI().toString());
      
      MediaPlayer mediaplayer = new MediaPlayer(media); 
    @FXML
    void addA(ActionEvent event) throws SQLException, IOException {
        LocalDate dateDuJour = LocalDate.now();
LocalDate dk=(date.getValue());
            
String jus= justification.getText();
String et= etat.getSelectionModel().getSelectedItem().toString();

  LocalDate    d = (date.getValue());

 Date da = Date.valueOf(date.getValue());
  if(  dk.isAfter(dateDuJour) ){
      Alert alert = new Alert(Alert.AlertType.ERROR);
         alert.setHeaderText(null);
         alert.setContentText("la date n'est pas valide");
         alert.showAndWait();
         DialogPane dialogPane = alert.getDialogPane();
         
  }
  else{
  
  int idd = Integer.parseInt(ideleve.getText());
  eleve el=new eleve();
   el=    as.findnbre_absence(idd);
 int  nbre=el.getNbre_absence();
 if (el.getNbre_absence()>=5){
alert.setText("This Student Has overtake the 5 absence");
}
int ide =el.getId();
el.setId(ide);
 el.setNbre_absence(nbre+1);
 
as.ajouuternbre_absence(el);
absence a = new absence();
a.setJustification(jus);
a.setEtat(et);
a.setDate(da.toString());
a.setEleve_id(idd);

as.ajouuterAbsence(a);

mediaplayer.play();
  Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
         alert.setHeaderText(null);
         alert.setContentText("Absence ajoutée");
         alert.showAndWait();
         DialogPane dialogPane = alert.getDialogPane();
         
 
             Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();

        Parent root2 = FXMLLoader.load(getClass().getResource("afficherEleve.fxml"));
        Scene scene2 = new Scene(root2);
        Stage stage2 = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage2.setScene(scene2);
        stage2.centerOnScreen();
        stage2.setTitle("Menu");
      
       stage2.show();
       
     AfficherEleveController.close();
    
    }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       etat.setItems(et);   
  int id1= AfficherEleveController.E_id_selection;

        ideleve.setText(String.valueOf(id1));
  eleve el=new eleve();
     try {
         el=    as.findnbre_absence(id1);
     } catch (SQLException ex) {
         Logger.getLogger(AjouabsenceController.class.getName()).log(Level.SEVERE, null, ex);
     }
 int  nbre=el.getNbre_absence();
 if (el.getNbre_absence()>=5){
alert.setText("This Student Has overtake the 5 absence");
}
            
        // TODO
    }    
    
}
