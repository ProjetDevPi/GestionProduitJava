/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import entities.FosUser;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;


import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.Cryptage;
import services.UserSevice;







/**
 * FXML Controller class
 *
 * @author wejdene
 */
public class FXMLRegisterController implements Initializable {
     ObservableList<String> role = FXCollections.observableArrayList("parent","enseignant","admin");
   @FXML
    private TextField username;
    

   @FXML
    private PasswordField password;

    @FXML
    private TextField email;


    @FXML
    private ChoiceBox roles;
    

    @FXML
    private Button btninsert;
      
    @FXML
    void register(ActionEvent event) throws Exception {
        
           
        try {
                
                            
                javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.CONFIRMATION);
                alert.setHeaderText("Votre Compte a étè crée avec succés");
                Optional<ButtonType> result = alert.showAndWait();
                                UserSevice s1 = new UserSevice();
                                Cryptage Cryptage = new Cryptage("lv39eptlvuhaqqsr");
                                String encdata = Cryptage.encrypt(password.getText());
                                        String r = roles.getSelectionModel().getSelectedItem().toString();
                            
                                
                               
                                
                                FosUser u = new FosUser(username.getText(),email.getText(),encdata,r);
                               
                                s1.ajouterUser(u);
                                   
                                Stage stage = new Stage();

                                ((Node) event.getSource()).getScene().getWindow().hide();
                                if(r.equals("parent")){
                               
                                
                                Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
                                Scene scene = new Scene(root);
                                stage.setScene(scene);
                                stage.show();
                                 }
                                else
                                {
                                     Parent root = FXMLLoader.load(getClass().getResource("UI.fxml"));
                                Scene scene = new Scene(root);
                                stage.setScene(scene);
                                stage.show();
                                }
                            } catch (IOException ex) {
                                Logger.getLogger(FXMLRegisterController.class.getName()).log(Level.SEVERE, null, ex);
                            }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
         roles.setItems(role);
         
   
         
        // TODO
    }    
    
}