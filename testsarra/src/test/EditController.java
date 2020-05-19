/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import entities.Personne;
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
import services.PersonneService;

/**
 * FXML Controller class
 *
 * @author wejdene
 */
public class EditController implements Initializable {
    @FXML
    private TextField nom;

    @FXML
    private TextField prenom;
        @FXML
    private TextField id;

    @FXML
    void onedit(ActionEvent e) throws SQLException, IOException {
          
        String nomm = nom.getText();
        String prenomm = prenom.getText();
        int idd = Integer.parseInt(id.getText());
        Personne p =new Personne();
        p.setId(idd);
        p.setNom(nomm);
        p.setPrenom(prenomm);
        
                PersonneService ps = new PersonneService();

            ps.Update(p);
         

        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.close();

        Parent root2 = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene scene2 = new Scene(root2);
        Stage stage2 = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage2.setScene(scene2);
        stage2.centerOnScreen();
        stage2.setTitle("Menu");
        
      stage2.show();
        FXMLDocumentController.close();
       
      


    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            int id1= FXMLDocumentController.E_id_selection;

        id.setText(String.valueOf(id1));
        nom.setText(FXMLDocumentController.E_nom_selection);
        prenom.setText(FXMLDocumentController.E_prenom_selection);
        
   
     

    }

    }    
    

