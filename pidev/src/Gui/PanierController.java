/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import static Gui.ProduitController.MainStage;
import animatefx.animation.SlideInDown;
import animatefx.animation.SlideInLeft;
import entities.CommandeProduit;
import entities.Produit;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import services.CommandeProduitService;
import services.ProduitService;
import services.UserSevice;


/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class PanierController implements Initializable {

    static void close() {
        MainStage.close();
    }
    
    
    
    @FXML
    private AnchorPane rootpane;

    @FXML
    private ImageView douda;

       @FXML
    private Button payer;

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
    
    private static int prod;


///////////////////////PANE///////////////////

   @FXML
    private Pane panedetail;

    @FXML
    private Pane paneprix;

//////////////////////////////////////////////////////////////////////////
@FXML
    private Text username;

@FXML
    private Text new_quantite;/////TEXT


    @FXML
    private Button confirmer;
    
    @FXML
    private Button edit;
    
    @FXML
    private TextField quantite_new;
    
      @FXML
    private Text idcommande; ///TEXT
      
        @FXML
    private Text idcom; ///TEXT
      public static String etat;
      
      private static double prix;
      
      
      
      @FXML
    private Button delete;
       
    
    CommandeProduitService s3=new CommandeProduitService();
    
    
           
            static public CommandeProduit commande1; // pour selecter
    
    ObservableList<CommandeProduit> data3 = FXCollections.observableArrayList();

ObservableList<CommandeProduit> listC = null;

///////////////////prixtotaldescommandes/////////////////////
    @FXML
    private Text nbrprod;

    @FXML
    private Text totalprod;

//////////////////////details cmd////////////
    
    @FXML
    private Button detailscmd;
public static int idcommande1;

////////////////////////////////////////////////////////
 public void edit()
    {   
       panedetail.setVisible(true);
       paneprix.setVisible(true);
        CommandeProduitService s1 = new CommandeProduitService();
        edit.setOnMouseClicked(a -> {
             CommandeProduit p =liste_commande.getItems().get(liste_commande.getSelectionModel().getSelectedIndex());
             System.out.println(p.getQuantite());
            quantite_new.setText(String.valueOf(p.getQuantite()));
            idcommande.setText(String.valueOf(p.getId()));
            quantite_new.setText(String.valueOf(p.getQuantite()));
            etat=p.getEtat();
            prix=p.getPrixtotal()/p.getQuantite();
            
            
            try {
                nbrprod.setText(String.valueOf(s1.NbrProduit()));
            } catch (SQLException ex) {
                Logger.getLogger(PanierController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try {
                totalprod.setText(String.valueOf(s1.TotalProduit()));
            } catch (SQLException ex) {
                Logger.getLogger(PanierController.class.getName()).log(Level.SEVERE, null, ex);
            }
             
            
        }) ;
    }
 ////////////////////////////////////////CONFIRMER///////////////////////////////////////////
 
 //////////////Modifier quantite
 
  public void confirmer(ActionEvent e) throws SQLException
    {   int idd=Integer.parseInt(idcommande.getText());
        int quantitee= Integer.parseInt(quantite_new.getText()); //modifier 
      
        Double new_prix=quantitee*prix;
        s3.editquantite(idd, quantitee,new_prix);
        if (etat.equals("Validée"))
        {
        JOptionPane.showMessageDialog(null, "Désolé! Votre Commande est déja validée , vous ne pouvez pas changer la quantite");
    }
        else 
        {
            
            JOptionPane.showMessageDialog(null, "Modification avec succés ! En attente de validation ");
            try {
               
               
              edit();
               Parent AnchorPane = FXMLLoader.load(getClass().getResource("Panier.fxml"));
               
               Stage stage = new Stage();
               Scene scene = new Scene(AnchorPane);
               stage.setScene(scene);
               stage.show();
           } catch (IOException ex) {
               Logger.getLogger(FXMLLoginController.class.getName()).log(Level.SEVERE, null, ex);
           }
                   
    
                          ((Node) e.getSource()).getScene().getWindow().hide();
        }
    }
    /////////////////////////////////////////////////////redirection apres delete/////////////
  
   public void AfterSupprimer(ActionEvent e)
     {
         try {
               Parent AnchorPane = FXMLLoader.load(getClass().getResource("Panier.fxml"));
               Stage stage = new Stage();
               Scene scene = new Scene(AnchorPane);
               stage.setScene(scene);
               stage.show();
           } catch (IOException ex) {
               Logger.getLogger(FXMLLoginController.class.getName()).log(Level.SEVERE, null, ex);
           }
                   
    
                          ((Node) e.getSource()).getScene().getWindow().hide();
     }
    
    
   @FXML
    void delete(ActionEvent event) throws SQLException { //commande fl front
      
        CommandeProduitService s3 = new CommandeProduitService();
       delete.setOnMouseClicked(a -> {
             CommandeProduit p =liste_commande.getItems().get(liste_commande.getSelectionModel().getSelectedIndex());
             
             if (p.getEtat().equals("Validée"))
                  {
                      JOptionPane.showMessageDialog(null, "Erreur ! La commande est déja validée");
                  }
             else {
                 s3.DeleteCommande(p.getId());
                 JOptionPane.showMessageDialog(null, "Commande Supprimée");
                 AfterSupprimer(event);
             }
        }) ;
                 
     }


    

    
 
 
 
 
 
 
 
 

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       panedetail.setVisible(false);
        paneprix.setVisible(false);
        
        
         CommandeProduitService cs= new CommandeProduitService();
        
        try {
        // TODO
             ////////////////////////Userrrrrrrrrrrr coooo nameee////////////////////////
        username.setText(cs.getloginusername());
    } catch (SQLException ex) {
        Logger.getLogger(PanierController.class.getName()).log(Level.SEVERE, null, ex);
    }
        
        
        
        
        ///////////////////user co////////////////////
        
        
        UserSevice s1=new UserSevice();
        
         String ss = null ;
    try {
       ss= s1.getlogin();
      
    } catch (SQLException ex) {
        Logger.getLogger(PanierController.class.getName()).log(Level.SEVERE, null, ex);
    }
  
    
           int result = Integer.parseInt(ss);
           
             System.out.println(result);
    try {
        listC=   cs.getcommandes(result);
        System.out.println(listC);
    } catch (SQLException ex) {
        Logger.getLogger(PanierController.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    liste_commande.setItems(listC);
        id_commande.setCellValueFactory(new PropertyValueFactory<>("id"));
        produit_commande.setCellValueFactory(new PropertyValueFactory<>("nom_prod"));
        paye_commande.setCellValueFactory(new PropertyValueFactory<>("pay"));
        prixtotal_commande.setCellValueFactory(new PropertyValueFactory<>("prixtotal"));
        quantite_commande.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        client_commande.setCellValueFactory(new PropertyValueFactory<>("yassine"));
        etat_commande.setCellValueFactory(new PropertyValueFactory<>("etat"));
        imagecommande.setCellValueFactory(new PropertyValueFactory<>("photo2"));

    
       
    
   
    }    
    
    
    ///////////////////////////////details commande a partir de la page panier jusqu'a la page details cmd //////////////
    
    
   @FXML
    void details_commande(ActionEvent event) throws IOException {
          BoxBlur blur = new BoxBlur(3,3,3);
                Stage stage1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
         detailscmd.setOnMouseClicked(a -> {
             CommandeProduit p =liste_commande.getItems().get(liste_commande.getSelectionModel().getSelectedIndex());
             
             if (p.getPay().equals("Payée"))
                  {
                      JOptionPane.showMessageDialog(null, "Erreur ! La commande est déja Payée");
                  }
             
             else {
               idcommande1=p.getId();
               try {
               Parent AnchorPane = FXMLLoader.load(getClass().getResource("DetailsCommandePay.fxml"));
               Stage stage = new Stage();
               Scene scene = new Scene(AnchorPane);
               stage.setScene(scene);
               stage.show();
               rootpane.setEffect(blur);
                stage.setOnHiding( ev -> { rootpane.setEffect(null);} );   
           } catch (IOException ex) {
               Logger.getLogger(FXMLLoginController.class.getName()).log(Level.SEVERE, null, ex);
           }
                   
    
                          //((Node) event.getSource()).getScene().getWindow().hide();
                         
                         


               
             }
        }) ;
      

 

    }  
    
    /////////////////////REDIRECTION pay///////////////////
    
    public void AfterValider(ActionEvent e)
     {
         try {
               Parent AnchorPane = FXMLLoader.load(getClass().getResource("Panier.fxml"));
               Stage stage = new Stage();
               Scene scene = new Scene(AnchorPane);
               stage.setScene(scene);
               stage.show();
           } catch (IOException ex) {
               Logger.getLogger(FXMLLoginController.class.getName()).log(Level.SEVERE, null, ex);
           }
                   
    
                          ((Node) e.getSource()).getScene().getWindow().hide();
     }
     
    
    
 ////////////////////////////REDIRECTION/////////////////////
    
     @FXML
    void products(ActionEvent event) {
        
          try {
               
      
               Parent AnchorPane = FXMLLoader.load(getClass().getResource("ProduitFront.fxml"));
               Stage stage = new Stage();
               Scene scene = new Scene(AnchorPane);
               stage.setScene(scene);
               stage.show();
           } catch (IOException ex) {
              
           }
                   
    
                          ((Node) event.getSource()).getScene().getWindow().hide();
        

    }
    
        @FXML
    void home(ActionEvent event) {
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

    
  @FXML
    void douda(MouseEvent event) {
        new SlideInLeft(douda).play();

    }
  
    
     @FXML
      void chat(MouseEvent event) {
        
try {
               
      
               Parent AnchorPane = FXMLLoader.load(getClass().getResource("FXMLChat.fxml"));
               Stage stage = new Stage();
               Scene scene = new Scene(AnchorPane);
               stage.setScene(scene);
               stage.show();
           } catch (IOException ex) {
              
           }
                   
    
                          ((Node) event.getSource()).getScene().getWindow().hide();
    }

}
