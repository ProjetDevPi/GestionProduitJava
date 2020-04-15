/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import entities.eleve;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
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
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import services.EleveService;

/**
 * FXML Controller class
 *
 * @author wejdene
 */
public class EditeleveController implements Initializable {

    @FXML
    private JFXTextField id;

    @FXML
    private JFXTextField prenom;

    @FXML
    private JFXTextField age;

    @FXML
    private JFXTextField adresse;

    @FXML
    private JFXTextField niveau;

    @FXML
    private JFXTextField filechoose;

    @FXML
    private JFXTextField nom;
     String path = "C:\\Users\\ASUS\\Desktop\\wejdene\\done.wav";
      
      Media media = new Media(new File(path).toURI().toString());
      
      MediaPlayer mediaplayer = new MediaPlayer(media);  
    @FXML
    private JFXButton chooser;
      FileChooser fc = new FileChooser();
    File selectedFile = new File("");
    EleveService ps = new EleveService();
    @FXML
    void onedit(ActionEvent event) throws SQLException, IOException {
   
        String nomm = nom.getText();
        String pre = prenom.getText();
       int agee = Integer.parseInt(age.getText());
        int idd = Integer.parseInt(id.getText());
       String adressee = adresse.getText();
        String niveauu = niveau.getText();
        String file = filechoose.getText();
     eleve e=new eleve();
        e.setId(idd);
      
        e.setNom_image(file);
        e.setNom(nomm);
         e.setPrenom(pre);
        e.setAge(agee);
        e.setNiveau(niveauu);
          e.setAdresse(adressee);
        

            ps.Update(e);
             mediaplayer.play();
     Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
         alert.setHeaderText(null);
         alert.setContentText("Eleve editée");
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
      @FXML
    void image(ActionEvent event) {
      fc.setTitle("Open Resource File");
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text files","*.pdf", "*.tkt", "*.docx","*.png","*.jpg"));
        fc.setInitialDirectory(new File("C:"));
        selectedFile = fc.showOpenDialog(null);

        //UploadFile.upload(selectedFile,"", "");
        File file = new File("" + selectedFile.getName());
        filechoose.setText(selectedFile.getName());

        //images.setImage(imagee);   
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         int id1= AfficherEleveController.E_id_selection;
   int agee= AfficherEleveController.E_age_selection;
        id.setText(String.valueOf(id1));
            age.setText(String.valueOf(agee));
        nom.setText(AfficherEleveController.E_nom_selection);
         prenom.setText(AfficherEleveController.E_prenom_selection);
             niveau.setText(AfficherEleveController.E_niveau_selection);
        filechoose.setText(AfficherEleveController.E_image_selection);
   
        adresse.setText(AfficherEleveController.E_adresse_selection);
      
    }    
    
}
