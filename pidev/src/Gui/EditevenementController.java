/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import com.jfoenix.controls.JFXDatePicker;
import entities.Evenement;
import entities.Sponsor;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import services.EvenementService;
import services.SponsorService;

/**
 * FXML Controller class
 *
 * @author wejdene
 */
public class EditevenementController implements Initializable {
    @FXML
    private TextField nom;

    @FXML
    private TextField prenom;
        @FXML
    private TextField id;
 @FXML
    private JFXDatePicker date;

   

    @FXML
    private TextField cap;
    @FXML
    void onedit(Event e) throws SQLException, IOException{
          
         String nomm = nom.getText();
        String prenomm = prenom.getText();
        int idd = Integer.parseInt(id.getText());
         int capacity = Integer.parseInt(cap.getText());
         LocalDate d=(date.getValue());
Date da=Date.valueOf(date.getValue());
        Evenement p =new Evenement();
        p.setId(idd);
        p.setNom(nomm);
        p.setType(prenomm);
        p.setNbrpart(capacity);
        p.setDateevent(da);
       
        
                EvenementService ps = new EvenementService();

            ps.Update(p);
         

        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.close();

        Parent root2 = FXMLLoader.load(getClass().getResource("FXMLEvenement.fxml"));
        Scene scene2 = new Scene(root2);
        Stage stage2 = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage2.setScene(scene2);
        stage2.centerOnScreen();
     stage2.show();
     //FXMLSponsorController.close();
      


    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            int id1= FXMLEvenementController.E_id_selection;
             int capa= FXMLEvenementController.E_cap_selection;

        id.setText(String.valueOf(id1));
        nom.setText(FXMLEvenementController.E_nom_selection);
        prenom.setText(FXMLEvenementController.E_prenom_selection);
        cap.setText(String.valueOf(capa));
        
   
     

    }

    }    
    

