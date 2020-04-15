/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import entities.Sponsor;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.SponsorService;

/**
 * FXML Controller class
 *
 * @author wejdene
 */
public class EditSponsorController implements Initializable {
    @FXML
    private TextField nom;

    @FXML
    private TextField type;
        @FXML
    private TextField id;

    @FXML
    void onedit(ActionEvent e) throws SQLException, IOException{
          
     String nomm = nom.getText();
        String prenomm = type.getText();
        int idd = Integer.parseInt(id.getText());
        Sponsor p =new Sponsor();
        p.setId(idd);
      p.setNomsponsor(nomm);
      p.setTypeprod(prenomm);
       
        
                SponsorService ps = new SponsorService();

            ps.Update(p);
         

        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.close();

        Parent root2 = FXMLLoader.load(getClass().getResource("FXMLSponsor.fxml"));
        Scene scene2 = new Scene(root2);
        Stage stage2 = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage2.setScene(scene2);
        stage2.centerOnScreen();
     stage2.show();
     FXMLSponsorController.close();
      


    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            int id1= FXMLSponsorController.E_id_selection;

        id.setText(String.valueOf(id1));
        nom.setText(FXMLSponsorController.E_nom_selection);
        type.setText(FXMLSponsorController.E_prenom_selection);
        
   
     

    }

    }    
    

