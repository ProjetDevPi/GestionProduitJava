/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import animatefx.animation.Shake;
import entities.eleve;
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
import services.EleveService;

/**
 * FXML Controller class
 *
 * @author wejdene
 */
public class ShowdetailsEleveController implements Initializable {
EleveService ps=new EleveService();
 @FXML
    private Label id;

    @FXML
    private Label nom;

    @FXML
    private Label prenom;

    @FXML
    private Label niveau;

    @FXML
    private Label age;

    @FXML
    private Label classe;

    @FXML
    private Label parent;

    @FXML
    private Label adresse;

    @FXML
    private Label absence;

    @FXML
    private Label date;

    @FXML
    private ImageView imageC;
     private Image image;

   private void afficher_detail() throws SQLException{
        
        int idd = AfficherEleveController.missionsel.getId();
 
        this.id.setText(String.valueOf(idd));
        eleve personne=new eleve();
        personne=ps.Get_eleve_by_Id(idd);
        System.out.println(personne);
        nom.setText("  "+personne.getNom());
        prenom.setText(" "+personne.getPrenom());
       niveau.setText(" "+personne.getNiveau());
          age.setText(" "+personne.getAge());
             adresse.setText(""+personne.getAdresse());
                absence.setText(" "+personne.getNbre_absence());
                   date.setText(" "+personne.getDate_naissance());
     String yass=  ps.findbynom(personne.getUser_id());
     String nomC=  ps.findbyclassebyid(personne.getClasse_id()); 
 
        parent.setText(" "+yass);
         classe.setText(" "+nomC);
    Image image = new Image("file:"+personne.getNom_image()+"", imageC.getFitWidth(), imageC.getFitHeight(), true, true);
                imageC.setImage(image);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        new Shake(imageC).play();
        try {
            afficher_detail();
            // TODO
        } catch (SQLException ex) {
            Logger.getLogger(ShowdetailsEleveController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}
