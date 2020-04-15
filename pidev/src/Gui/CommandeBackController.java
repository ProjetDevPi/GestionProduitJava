/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import static Gui.ProduitController.E_categorie_selection;
import static Gui.ProduitController.E_id_selection;
import animatefx.animation.SlideInDown;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import entities.CommandeProduit;
import entities.Produit;
import static java.awt.SystemColor.text;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import services.CommandeProduitService;
import services.ProduitService;
import utils.MailSend;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class CommandeBackController implements Initializable {
    
      @FXML
    private Pane panemanager;

    @FXML
    private AnchorPane rootpane;
  @FXML
    private TableView<CommandeProduit> liste_commande;
  @FXML
    private TableColumn<?, ?> produit_commande;
  @FXML
    private TableColumn<?, ?> paye_commande;

    @FXML
    private TableColumn<?, ?> prixtotal_commande;
     @FXML
    private TableColumn<?, ?> quantite_commande;
      @FXML
    private TableColumn<?, ?> client_commande;
        @FXML
    private Pane Commande;
        @FXML
    private TableColumn<?, ?> etat_commande;
@FXML
    private TableColumn<?, ?> imagecommande;

 @FXML
    private TableColumn<?, ?> id_commande;

  private Connection cnx;
    private Statement st;
    private PreparedStatement pre;
    
     @FXML
    private Button envoyer;

    
       @FXML
    private JFXTextField filterfield;

      
      
        @FXML
    private JFXButton Valider;


///////////////////////////EDIT VALIDER COMMANDE MEME INTERFACE////////////////////


 
        
     public void AfterValider(ActionEvent e)
     {
         try {
               Parent AnchorPane = FXMLLoader.load(getClass().getResource("CommandeBack.fxml"));
               Stage stage = new Stage();
               Scene scene = new Scene(AnchorPane);
               stage.setScene(scene);
               stage.show();
           } catch (IOException ex) {
               Logger.getLogger(FXMLLoginController.class.getName()).log(Level.SEVERE, null, ex);
           }
                   
    
                          ((Node) e.getSource()).getScene().getWindow().hide();
     }
     
     
     
      public void Valider(ActionEvent e)
     {
         
         CommandeProduitService s3 = new CommandeProduitService();
         ProduitService s1 = new ProduitService();
         
      
          Valider.setOnMouseClicked(a -> {
             CommandeProduit p =liste_commande.getItems().get(liste_commande.getSelectionModel().getSelectedIndex());
             Produit p2 =s1.Produitfindbyid(p.getProduit());
             int new_quantite= p2.getQuantite()-p.getQuantite();
             
            
             
             if (p.getEtat().equals("Validée"))
            {
                JOptionPane.showMessageDialog(null, "Commande déja Validée");
            }
             else if (new_quantite<0) 
             {
                 JOptionPane.showMessageDialog(null, "Erreur! Quantite de la commande dépasse le stock");
             }
            else 
            {
                 try {
                     s3.edit(p);
                     s1.editproduit(new_quantite,p2.getId());
                   
                     AfterValider(e);
                 } catch (SQLException ex) {
                     Logger.getLogger(CommandeBackController.class.getName()).log(Level.SEVERE, null, ex);
                 }
                  int idd=p.getUser();
         
          CommandeProduitService as = new CommandeProduitService();
          
          ProduitService ps= new ProduitService();
     
      String mailuser = null;
     
      String usernom =null;
     
      try {
        
          
      
          mailuser = as.getemail(idd);
          
      
       usernom= ps.getusername(idd); // retourne le nom de l'utilsateur qui a passée la commande
      MailSend m = new MailSend();
                String subject = "Commande validée";
               
               
                String message ="Bonjour Mr/Mme " +usernom+ " Votre commande de " +p.getQuantite()+ " de " +p.getNom_prod()+ " est validée! "
                        + " Merci pour votre confiance";
                       
               System.out.println(message);
               
                m.sendMail("sarra.ferchichi@esprit.tn", mailuser, subject, message);
       
               
           } catch (SQLException ex) {
                 Logger.getLogger(CommandeBackController.class.getName()).log(Level.SEVERE, null, ex);
             }
            }
        }) ;
         
          
         
          
         
     }
      
   
      
     /////////////////////////Delete Commande/////////////////////////////////
      
      
       @FXML
    void delete(ActionEvent event) throws SQLException {
          if (!liste_commande.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Product ");
            alert.setHeaderText("Are you sure you want to delete this order"
                    + liste_commande.getSelectionModel().getSelectedItem().getId() + "?");
            Optional<ButtonType> result = alert.showAndWait();
        
        
          if (result.get() == ButtonType.OK) {
        
        
 CommandeProduitService ms = new CommandeProduitService();
        ObservableList<CommandeProduit> ll, ttmission=FXCollections.observableArrayList();;
        ttmission = liste_commande.getItems();
        // ta3tina les lignes selectionnés 
        ll = liste_commande.getSelectionModel().getSelectedItems();

        for (CommandeProduit m : ll) {
            //ttmission.remove(m);//refresh tableview
            ms.DeleteCommande((m.getId()));
            
                 
            
           
        }
        
        JOptionPane.showMessageDialog(null, "delete");
        ObservableList<CommandeProduit> data = FXCollections.observableArrayList();
        CommandeProduitService srvRec = new CommandeProduitService();
        data = FXCollections.observableArrayList(srvRec.afficherCommande());
     
  
        liste_commande.setItems(data);

    }
          }
          else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Erreur de selection");
            alert.setHeaderText("You are obliged to select a product ");

            Optional<ButtonType> result = alert.showAndWait();
        }
    }

    
      

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         ObservableList<CommandeProduit> data3 = FXCollections.observableArrayList();
        CommandeProduitService srv = new CommandeProduitService();
        data3 = FXCollections.observableArrayList(srv.afficherCommande());
       liste_commande.setItems(data3);
       
          id_commande.setCellValueFactory(new PropertyValueFactory<>("id"));
        produit_commande.setCellValueFactory(new PropertyValueFactory<>("nom_prod"));
        paye_commande.setCellValueFactory(new PropertyValueFactory<>("pay"));
        prixtotal_commande.setCellValueFactory(new PropertyValueFactory<>("prixtotal"));
        quantite_commande.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        client_commande.setCellValueFactory(new PropertyValueFactory<>("nom_client"));
        etat_commande.setCellValueFactory(new PropertyValueFactory<>("etat"));
        imagecommande.setCellValueFactory(new PropertyValueFactory<>("photo2"));

        

       liste_commande.setItems(data3);
    
       //////////////////////RECHERCHE//////////////////////////////
      
       
		

		
   
    }    
    
    /////////////////////////////////////////REDIRECTION/////////////////////////////////////////////////
  
    
    @FXML
    void dashbord(MouseEvent event) {
        
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
     
       
     
               
               

// Traditional way to get the response value.
             
                   
     //doo stuff
              

            

        

    

    
    

