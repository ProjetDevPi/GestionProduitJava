/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;


import entities.Chat;
import services.ChatService;
import services.UserSevice;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import services.CommandeProduitService;
/**
 * FXML Controller class
 *
 * @author Yassiine
 */
public class FXMLChatAdminController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
   
    @FXML
    private ChoiceBox<String> clients;
      
    @FXML
    private TextField input;

    @FXML
    private TextArea messages;

    @FXML
    private Button send;
   
     @FXML
    private Pane pane;
    
    UserSevice s1= new UserSevice();
    ChatService s2= new ChatService();
    CommandeProduitService s3 = new CommandeProduitService();
    
    String username;

    public FXMLChatAdminController() throws SQLException {
        this.username = s3.getloginusername();
    }
    public void send(String user) throws SQLException
{
    
     LocalDateTime now = LocalDateTime.now();
    System.out.println(now);
    { 
        if(input.getText() != null)
        {
            
        Chat p = new Chat(username,user,input.getText(),now);
            s2.ajouterChat(p);
        }
  
              
     }
}
    
     public void afficher(String user)
     {
         List<Chat> listC=s2.afficherChat(user,user);
         
         for (int i=0;i<listC.size();i++)
         {
             messages.appendText("Message de " +listC.get(i).getSender()+ " Envoyé le " +listC.get(i).getD()+ " : " +listC.get(i).getMessage()+"\r\n");
         }
     }
    
     
     @Override
    public void initialize(URL url, ResourceBundle rb) {
    
        
        ObservableList<String> client_name = FXCollections.observableArrayList();
        
   List<String> d3 = s1.listeuser();
   
   
   
   for(int i=0;i<d3.size();i++)
   
   {
       String val=(d3.get(i));
       
       client_name.add(val); //je met le contenu dans observable list
       
   }
    clients.setItems(client_name);//remplir combo
    
    
    
   
    messages.setOnMouseMoved(e -> {
        messages.clear();
         String user= clients.getSelectionModel().getSelectedItem(); //renvoyer un objet de type ComboBoxChoice
       
        afficher(user);

               }) ;
    
     send.setOnMouseClicked(e -> {
            try {
                String user= clients.getSelectionModel().getSelectedItem();
                send(user);
                messages.clear();
                afficher(user);
            } catch (SQLException ex) {
                Logger.getLogger(FXMLChatAdminController.class.getName()).log(Level.SEVERE, null, ex);
            }

               }) ;
    
    
}
@FXML
    void retour(MouseEvent event) {
 try {
               
      
               Parent AnchorPane = FXMLLoader.load(getClass().getResource("UI.fxml"));
               Stage stage = new Stage();
               Scene scene = new Scene(AnchorPane);
               stage.setScene(scene);
               stage.show();
           } catch (IOException ex) {
              
           }
                   
    
                          ((Node) event.getSource()).getScene().getWindow().hide();
        
    }

}
