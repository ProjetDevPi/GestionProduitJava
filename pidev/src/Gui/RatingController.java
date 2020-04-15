/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import animatefx.animation.FadeIn;
import animatefx.animation.Flash;
import animatefx.animation.Tada;
import entities.classe;
import entities.eleve;
import entities.rating;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import org.controlsfx.control.Rating;
import services.ClasseService;
import services.RatingService;
import services.UserSevice;

/**
 * FXML Controller class
 *
 * @author wejdene
 */
public class RatingController implements Initializable {
  @FXML
    private Rating eleveR;

    @FXML
    private Label nom1;

    @FXML
    private Label nom2;

    @FXML
    private Rating scolariteR;

    @FXML
    private Label nom3;

    @FXML
    private Rating activiteR;

    @FXML
    private Label nom4;

    @FXML
    private Rating ensgR;
 @FXML
    private TextField txt1;

    @FXML
    private TextField txt4;

    @FXML
    private TextField txt3;

    @FXML
    private TextField txt2;
  
    RatingService cs =new RatingService();
     @FXML
    private Label nom21;

    @FXML
    private Label nom211;

    @FXML
    private Label nom212;

    @FXML
    private Label nom213;

    @FXML
    private Pane pane;
    @FXML
    void submit(ActionEvent event) throws SQLException {
           UserSevice s1=new UserSevice();
       String ss= s1.getlogin() ;
       int result = Integer.parseInt(ss);
String nomm=nom1.getText();
   Double rat1 =eleveR.getRating(); 
 String cm1=  txt1.getText();
rating r = new rating();
r.setNom(nomm);
r.setRat(rat1);
r.setUser(result);
r.setCommentaire(cm1);
cs.ajouuterRating(r);
/******************************/
String no=nom2.getText();
   Double rat2=scolariteR.getRating();
   String cm2=  txt2.getText();
rating r1 = new rating();
r1.setNom(no);
r1.setRat(rat2);
r1.setUser(result);
r1.setCommentaire(cm2);
cs.ajouuterRating(r1);

/*******************************************/
String n=nom3.getText();
   Double rat3=activiteR.getRating();
     String cm3=  txt3.getText();
rating r2 = new rating();
r2.setNom(n);
r2.setRat(rat3);
r2.setUser(result);
r2.setCommentaire(cm3);
cs.ajouuterRating(r2);
/**************************************/
String non=nom4.getText();
   Double rat4=ensgR.getRating();
     String cm4=  txt4.getText();
   rating r3= new rating();
r3.setNom(non);
r3.setRat(rat4);
r3.setUser(result);
r3.setCommentaire(cm4);
cs.ajouuterRating(r3);


  Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
         alert.setHeaderText(null);
         alert.setContentText("Merci Mrs/Mme Vote Avis a été pris en Consideration ");
         alert.showAndWait();
         DialogPane dialogPane = alert.getDialogPane();
   
   
   
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        new FadeIn(eleveR).play();
        new FadeIn(activiteR).play();
        new FadeIn(scolariteR).play();
        new FadeIn(ensgR).play();
       nom21.setVisible(false);
        nom211.setVisible(false);
         nom212.setVisible(false);
          nom213.setVisible(false);
           txt1.setVisible(false);
            txt2.setVisible(false);
             txt3.setVisible(false);
              txt4.setVisible(false);
              new Flash(pane).play();
 
    } 
    
        @FXML
    void commentaire1(MouseEvent event) {
    nom21.setVisible(true);
      txt1.setVisible(true);
      new Tada(txt1).play();
    }

    @FXML
    void commentaire2(MouseEvent event) {
  nom211.setVisible(true);
   txt2.setVisible(true);
    new Tada(txt2).play();
    }

    @FXML
    void commentaire3(MouseEvent event) {
 nom212.setVisible(true);
   txt3.setVisible(true);
    new Tada(txt3).play();
    }

    @FXML
    void commentaire4(MouseEvent event) {
 nom213.setVisible(true);
 txt4.setVisible(true);
  new Tada(txt4).play();
    }
    
}
