/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import entities.personne;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import services.persoService;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class ShowdetailsPersonneController implements Initializable {
    
       @FXML
    private Label id_personne;

    @FXML
    private Label nom_personne;

    @FXML
    private Label prenom_personne;

    @FXML
    private Label user_personne;

    @FXML
    private Label idlogin_personne;

    @FXML
    private ImageView file_personne;
    
    persoService ps= new persoService();
    personne personne = new personne();
    
    
    private Image image;


     private void afficher_detail() throws SQLException{

        
         
         
         //System.out.println(yass);
       
        int idd = FXMLDocumentController.missionsel.getId();
       
        //String yassine = FXMLDocumentController.missionsel.getYassine();
        
        
        this.id_personne.setText(String.valueOf(idd));
        
        personne =ps.Get_Mission_by_Id(idd);
        id_personne.setText("L'id du personne "+personne.getId());
        nom_personne.setText("Le nom du personne : "+personne.getNom());
        prenom_personne.setText("Le prenom de la Mission : "+personne.getPrenom());
        idlogin_personne.setText("login "+personne.getIdlogin());
        String yass = ps.findbynom(personne.getUser());
        user_personne.setText("nomuser : "+yass);
         image = new Image("file:"+personne.getFile()+"", file_personne.getFitWidth(), file_personne.getFitHeight(), true, true);
                file_personne.setImage(image);
     
      
      
        
        
        // p.setYassine(findbynom(res.getInt("user"))); //attention
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         try {
                afficher_detail();
            } catch (SQLException ex) {
                Logger.getLogger(ShowdetailsPersonneController.class.getName()).log(Level.SEVERE, null, ex);
            }
        // TODO
    }    
    
}
