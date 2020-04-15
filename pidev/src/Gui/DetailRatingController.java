/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import services.EleveService;

/**
 * FXML Controller class
 *
 * @author wejdene
 */
public class DetailRatingController implements Initializable {
EleveService ps=new EleveService();
   
     @FXML
    private Text utilisateur;

    @FXML
    private Text com;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    try {
        afficher_detail();
    } catch (SQLException ex) {
        Logger.getLogger(DetailRatingController.class.getName()).log(Level.SEVERE, null, ex);
    }
    }    
     private void afficher_detail() throws SQLException{
        
        int idd = UIController.missionsel.getUser();
          String yass=  ps.findbynom(idd);
          System.out.println(yass);
          String c=UIController.missionsel.getCommentaire();
 utilisateur.setText(yass);
 com.setText(c);
        
    }
}
