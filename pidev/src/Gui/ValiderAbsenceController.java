/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import animatefx.animation.FadeIn;
import animatefx.animation.Tada;
import com.jfoenix.controls.JFXButton;
import entities.absence;
import entities.eleve;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import services.AbsenceService;
import services.CarnetService;
import services.EleveService;
import services.UserSevice;

/**
 * FXML Controller class
 *
 * @author wejdene
 */
public class ValiderAbsenceController implements Initializable {
        @FXML
    private Text txt;
    @FXML
    private AnchorPane pane;
      @FXML
    private ImageView img;
        UserSevice ps = new UserSevice();
          EleveService es = new EleveService();
              @FXML
    private Text welcome;

    @FXML
    private JFXButton oui;
       @FXML
    private JFXButton refuser;
        @FXML
    private VBox box;
        AbsenceService ab=new AbsenceService();
  CarnetService ca=new CarnetService();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        String id;
        try {
            id = ps.getlogin();
                int result = Integer.parseInt(id);	
                String nom=es.findbynom(result);
                    welcome.setText("Bonjour Mrs/Mme "+nom);
        } catch (SQLException ex) {
            Logger.getLogger(ValiderAbsenceController.class.getName()).log(Level.SEVERE, null, ex);
        }
           		
	
    
         TranslateTransition tran=new TranslateTransition(Duration.seconds(3),img);
         tran.setToX(600);
         tran.play();
         
      
 
    } 
    
    @FXML
    void tada(MouseEvent event) {
new Tada(refuser).play();
    }

    @FXML
    void tada2(MouseEvent event) {
        new Tada(oui).play();
           new FadeIn(txt).play();

    }
        @FXML
    void confimer(ActionEvent event) throws SQLException {
          String ss = null ;
    try {
       ss= ps.getlogin();
      
    } catch (SQLException ex) {
        Logger.getLogger(CarnetController.class.getName()).log(Level.SEVERE, null, ex);
    }
           int result = Integer.parseInt(ss);
           eleve el= new eleve();
            int    idd=  CarnetController.ide;
              System.out.println(idd);
           el=ca.findnbre_absence(idd);
           System.out.println(el);
      int id= el.getId();

   absence a=new absence();
   a=ca.findabsence(id);
  int idab=a.getId();
  ab.DeleteProduit(idab);
  
int nbr =el.getNbre_absence();
el.setId(id);
 el.setNbre_absence(nbr-1);
 ca.ajouuternbre_absence(el);
  Alert alert = new Alert(Alert.AlertType.CONFIRMATION);


   alert.setHeaderText(null);
         alert.setContentText("cette Absence a été Confirmer par vous \n Elle Sera eliminé de la liste  ");
         alert.showAndWait();
         DialogPane dialogPane = alert.getDialogPane();
               Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
         
    }
    
}
