/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import com.jfoenix.controls.JFXButton;
import entities.FosUser;
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
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.UserSevice;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class FXMLyassineController implements Initializable {

       @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private Button btnlogin;
    
      @FXML
    private JFXButton btnregister;
    
    @FXML
    void login(ActionEvent event) throws SQLException, IOException {
        
        
  UserSevice s1=new UserSevice();
  s1.deletelogin();
       FosUser u1=s1.existLogin(username.getText(), password.getText());
       
      
       if (u1 != null)
       {
         s1.ajouterlogin(u1);
       String ss= s1.getlogin() ;
       int result = Integer.parseInt(ss);			
			
                        System.out.println(result);

        
           }      
       else 
       {
           System.out.println("Invalid Cred");
       }
                  
         
               
              
               Parent AnchorPane = FXMLLoader.load(getClass().getResource("Home.fxml"));
               Stage stage = new Stage();
               Scene scene = new Scene(AnchorPane);
               stage.setScene(scene);
               stage.show();
               
    }
    /**
     * Initializes the controller class.
     */
    
     @FXML
    void registerredirection(ActionEvent event) throws IOException {
         
               Parent AnchorPane = FXMLLoader.load(getClass().getResource("FXMLRegisteryassine.fxml"));
               Stage stage = new Stage();
               Scene scene = new Scene(AnchorPane);
               stage.setScene(scene);
               stage.show();
               
        

    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
