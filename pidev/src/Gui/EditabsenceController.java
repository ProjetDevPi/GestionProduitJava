/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.sun.javafx.binding.StringFormatter;
import entities.absence;
import entities.eleve;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import services.AbsenceService;

/**
 * FXML Controller class
 *
 * @author wejdene
 */
public class EditabsenceController implements Initializable {
         ObservableList<String> et = FXCollections.observableArrayList("Justifiée","Non justifiée","En attente");
  @FXML
    private JFXDatePicker date;

    @FXML
    private JFXComboBox<String> etat;

    @FXML
    private JFXTextField justification;

    @FXML
    private JFXTextField id;
        AbsenceService as =new AbsenceService();
    @FXML
    void onedit(ActionEvent event) throws SQLException, IOException {
   
        String just= justification.getText();
    LocalDate    d = (date.getValue());

 Date da = Date.valueOf(date.getValue());
    
        int idd = Integer.parseInt(id.getText());
  
         String r = etat.getSelectionModel().getSelectedItem().toString();
    absence a=new absence(); 
   a.setId(idd);
         a.setJustification(just);
        a.setDate(da.toString());
   
          a.setEtat(r);
        

            as.Update(a);
         

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();

        Parent root2 = FXMLLoader.load(getClass().getResource("afficherabsence.fxml"));
        Scene scene2 = new Scene(root2);
        Stage stage2 = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage2.setScene(scene2);
        stage2.centerOnScreen();
        stage2.setTitle("Menu");
        
      stage2.show();
      AfficherabsenceController.close();
       
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         int id1= AfficherabsenceController.E_id_selection;
           id.setText(String.valueOf(id1));
         
        justification.setText(AfficherabsenceController.E_jus_selection);
etat.setValue(AfficherabsenceController.E_etat_selection);
             date.setValue(LOCAL_DATE("2020-02-11"));
          etat.setItems(et);
    }   
    public static final LocalDate LOCAL_DATE (String dateString){
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    LocalDate localDate = LocalDate.parse(dateString, formatter);
    return localDate;
}
    
}
