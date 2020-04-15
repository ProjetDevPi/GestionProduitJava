/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import com.jfoenix.controls.JFXComboBox;
import entities.classe;
import entities.eleve;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.stage.Stage;
import services.EleveService;

/**
 * FXML Controller class
 *
 * @author wejdene
 */
public class AffecterclasseController implements Initializable {
  ObservableList<String> listN = null;
    @FXML
    private JFXComboBox<String> classe;
   EleveService ps = new EleveService();
      String path = "C:\\Users\\ASUS\\Desktop\\wejdene\\done.wav";
      
      Media media = new Media(new File(path).toURI().toString());
      
      MediaPlayer mediaplayer = new MediaPlayer(media);  
    @FXML
                    
   void affecter(ActionEvent event) throws SQLException, IOException {
          int id1= AfficherEleveController.E_id_selection;
   String r = classe.getSelectionModel().getSelectedItem().toString();
         
         int idU=ps.findbyclasse(r);
         classe ca=new classe();
         ca=ps.findeclasse(idU);
  int nb  = ca.getNbre_eleve();
          eleve el=new eleve();
          
   el=    ps.findeleve(id1);

int ide =el.getId();
el.setId(ide);
 el.setClasse_id(idU);
 if(ca.getCapacite()==ca.getNbre_eleve()){
     
     Alert alert = new Alert(Alert.AlertType.WARNING);
         alert.setHeaderText(null);
         alert.setContentText("Cette classe est saturee");
         alert.showAndWait();
         DialogPane dialogPane = alert.getDialogPane();
 }
 else
 { ca.setNbre_eleve(nb+1);
         ps.ajouutercap(ca);
ps.ajouuterclasse(el);
mediaplayer.play();
Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
         alert.setHeaderText(null);
         alert.setContentText("classe ajoutée");
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
                  try {
            listN = ps.getClasse();
        } catch (SQLException ex) {
            Logger.getLogger(AffecterclasseController.class.getName()).log(Level.SEVERE, null, ex);
        }
        classe.setItems(listN);
    }    
    
}
