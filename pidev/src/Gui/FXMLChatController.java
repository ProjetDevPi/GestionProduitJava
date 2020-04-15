/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import entities.Chat;
import java.io.IOException;
import services.ChatService;
import services.UserSevice;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import services.CommandeProduitService;


/**
 * FXML Controller class
 *
 * @author Yassiine
 */
public class FXMLChatController implements Initializable {
   @FXML
    private TextField input;

    @FXML
    private TextArea messages;

   
    UserSevice s1= new UserSevice();
    ChatService s2= new ChatService();
    CommandeProduitService s3 = new CommandeProduitService();
    
    String username;
    
        @FXML
    private ImageView send;

   
    public FXMLChatController() throws SQLException {
        this.username = s3.getloginusername();
        
    }
    public void send() throws SQLException
{
    
     LocalDateTime now = LocalDateTime.now();
    System.out.println(now);
    { 
        if(input.getText() != null)
        {
            
        Chat p = new Chat(username,"admin",input.getText(),now);
            s2.ajouterChat(p);
        }
     //messages.appendText("Message de " +username+ " : " +input.getText()+"\r\n");

              
     }
}
    
     public void afficher()
     {
         List<Chat> listC=s2.afficherChat(username,"admin");
         
         for (int i=0;i<listC.size();i++)
         {
             messages.appendText("Message de " +listC.get(i).getSender()+ " Envoyé le " +listC.get(i).getD()+ " : " +listC.get(i).getMessage()+"\r\n");
         }
     }
     
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        messages.setEditable(false);
        
        
        messages.setOnMouseMoved(e -> {
        messages.clear();       
        afficher();
 }) ;
        send.setOnMouseClicked(e -> {
             messages.clear();    
            try {
                send();
            } catch (SQLException ex) {
                Logger.getLogger(FXMLChatController.class.getName()).log(Level.SEVERE, null, ex);
            }
            afficher();
 }) ;
        // TODO
    }  
    
       @FXML
    void retour(MouseEvent event) {
          try {
               
      
               Parent AnchorPane = FXMLLoader.load(getClass().getResource("Home.fxml"));
               Stage stage = new Stage();
               Scene scene = new Scene(AnchorPane);
               stage.setScene(scene);
               stage.show();
           } catch (IOException ex) {
              
           }
                   
    
                          ((Node) event.getSource()).getScene().getWindow().hide();
        

    }

}
    

/*/
 
    

  
  

}
/*/