/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;


import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import entities.CommandeProduit;
import entities.Produit;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import services.CommandeProduitService;
import services.ProduitService;
import services.UserSevice;
import tray.animations.AnimationType;
import tray.notification.TrayNotification;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;



import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import utils.MyConnection;



/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class ProduitFrontController implements Initializable {
      @FXML
    private AnchorPane rootpane;
     
      @FXML
    private TableView<Produit> tableP;

    @FXML
    private TableColumn<Produit, Integer> idC;

    @FXML
    private TableColumn<Produit, String> categorieC;

    @FXML
    private TableColumn<Produit, String> nomproduitC;

    @FXML
    private TableColumn<Produit, Double> prixC;

    @FXML
    private TableColumn<Produit, String> imageC;

    @FXML
    private TableColumn<Produit, String> descriptionC;

    @FXML
    private TableColumn<Produit, Integer> quantiteC;

    @FXML
    private TableColumn<Produit, String> image2;

  
    @FXML
    private JFXComboBox<String> Trie_par;
    
      @FXML
    private Text user;
    
  @FXML
    private ImageView detail;
    
    static public Produit prod; //personne
    
     public static String EditTable = "";
     
    private int id;
    
    public static Stage MainStage;
    
          @FXML
    private JFXTextField filterfield;
   
    /////////////////////////////TEXT pour details///////////////////////////
    
     @FXML
    private Pane panecommande;
     
     
      @FXML
    private Pane  yassine;
      
         @FXML
    private Pane  sarra;

    @FXML
    private Text nomprod;

    @FXML
    private Text prixprod;
    
 

    @FXML
    private Text quantiteprod;

    @FXML
    private Text descriptionprod;

    @FXML
    private ImageView imageprod;

    @FXML
    private TextField quantitecmd;

  
      @FXML
    private Text idprod;
    
   
      Produit p = new Produit();
      ProduitService ps = new ProduitService();
      
        private Image image;
        
   
    ////////////////////////////////////////////////Error Controle de saisie////////////////////////////////////
                @FXML
    private Label errornom;

    @FXML
    private Label errorprix;

    @FXML
    private Label errordescription;

    @FXML
    private Label errorquantite;

    @FXML
    private Label errorcategorie;

    
    ///////////////////////////////////////////CONTROLE de saisie///////////////////////////////////////////////////////////////
    
    boolean Nomok = true;
    boolean Nomok1 = true;
    boolean Nomok2 = true;
    
    boolean quantiteok = true;
    boolean quantiteok1 = true;
    boolean quantiteok2 = true;

    boolean categorie = true;
    boolean prixok= true;
    boolean prixok1 = true;
    boolean prixok2 = true;
    @FXML
    void fourniturescolaire(ActionEvent e) throws SQLException {
        
           ProduitService ms = new ProduitService();
       Stage stage1 = (Stage) ((Node) e.getSource()).getScene().getWindow();
 

             //int idU=ps.findbynomcategorie(r); 
        MainStage = stage1;
        EditTable = ((Button) e.getSource()).getText();
        if (EditTable.equals("Fourniture Scolaire"))
        {

            ArrayList<Produit> arrayList = (ArrayList<Produit>) ms.Get_Produit_by_Categorie("Fourniture Scolaire");
            ObservableList obs = FXCollections.observableArrayList(arrayList);
            tableP.setItems(obs);
        
        } else if (Trie_par.getValue() == "Sac a Dos") {
            ArrayList<Produit> arrayList = (ArrayList<Produit>) ms.Get_Produit_by_Categorie("Sac a Dos");
            ObservableList obs = FXCollections.observableArrayList(arrayList);
            tableP.setItems(obs);
        } else
            
        {
                 ArrayList<Produit> arrayList = (ArrayList<Produit>) ms.Get_Produit_by_Categorie("Tablier Scolaire");
            ObservableList obs = FXCollections.observableArrayList(arrayList);
            tableP.setItems(obs);
                
           }
        idC.setCellValueFactory(new PropertyValueFactory<>("id"));
        categorieC.setCellValueFactory(new PropertyValueFactory<>("yassine"));
        nomproduitC.setCellValueFactory(new PropertyValueFactory<Produit, String>("nom"));
        prixC.setCellValueFactory(new PropertyValueFactory<Produit, Double>("prix"));
        descriptionC.setCellValueFactory(new PropertyValueFactory<Produit, String>("description"));
        imageC.setCellValueFactory(new PropertyValueFactory<>("nom_image")); //file dans l'entite
        quantiteC.setCellValueFactory(new PropertyValueFactory<Produit, Integer>("quantite"));
        image2.setCellValueFactory(new PropertyValueFactory<>("photo"));

    }

    @FXML
    void sacados(ActionEvent e) throws SQLException {
        
          ProduitService ms = new ProduitService();
       Stage stage1 = (Stage) ((Node) e.getSource()).getScene().getWindow();
 

             //int idU=ps.findbynomcategorie(r); 
        MainStage = stage1;
        EditTable = ((Button) e.getSource()).getText();
        if (EditTable.equals("Fourniture Scolaire"))
        {

            ArrayList<Produit> arrayList = (ArrayList<Produit>) ms.Get_Produit_by_Categorie("Fourniture Scolaire");
            ObservableList obs = FXCollections.observableArrayList(arrayList);
            tableP.setItems(obs);
        
        } else if (Trie_par.getValue() == "Tablier Scolaire") {
            ArrayList<Produit> arrayList = (ArrayList<Produit>) ms.Get_Produit_by_Categorie("Tablier Scolaire");
            ObservableList obs = FXCollections.observableArrayList(arrayList);
            tableP.setItems(obs);
        } else
            
        {
                 ArrayList<Produit> arrayList = (ArrayList<Produit>) ms.Get_Produit_by_Categorie("Sac a Dos");
            ObservableList obs = FXCollections.observableArrayList(arrayList);
            tableP.setItems(obs);
                
           
        idC.setCellValueFactory(new PropertyValueFactory<>("id"));
        //categorieC.setCellValueFactory(new PropertyValueFactory<>("yassine"));
        nomproduitC.setCellValueFactory(new PropertyValueFactory<Produit, String>("nom"));
        prixC.setCellValueFactory(new PropertyValueFactory<Produit, Double>("prix"));
        descriptionC.setCellValueFactory(new PropertyValueFactory<Produit, String>("description"));
        imageC.setCellValueFactory(new PropertyValueFactory<>("nom_image")); //file dans l'entite
        quantiteC.setCellValueFactory(new PropertyValueFactory<Produit, Integer>("quantite"));
        image2.setCellValueFactory(new PropertyValueFactory<>("photo"));
        }
        

    }

    @FXML
    void tablierscolaire(ActionEvent e) throws SQLException {
        
          ProduitService ms = new ProduitService();
       Stage stage1 = (Stage) ((Node) e.getSource()).getScene().getWindow();
 

             //int idU=ps.findbynomcategorie(r); 
        MainStage = stage1;
        EditTable = ((Button) e.getSource()).getText();
        if (EditTable.equals("Tablier Scolaire"))
        {

            ArrayList<Produit> arrayList = (ArrayList<Produit>) ms.Get_Produit_by_Categorie("Tablier Scolaire");
            ObservableList obs = FXCollections.observableArrayList(arrayList);
            tableP.setItems(obs);
        
        
        } else if (Trie_par.getValue() == "Fourniture Scolaire") {
            ArrayList<Produit> arrayList = (ArrayList<Produit>) ms.Get_Produit_by_Categorie("Fourniture Scolaire");
            ObservableList obs = FXCollections.observableArrayList(arrayList);
            tableP.setItems(obs);
        } else
            
        {
                 ArrayList<Produit> arrayList = (ArrayList<Produit>) ms.Get_Produit_by_Categorie("Sac a Dos");
            ObservableList obs = FXCollections.observableArrayList(arrayList);
            tableP.setItems(obs);
                
           
        idC.setCellValueFactory(new PropertyValueFactory<>("id"));
        //categorieC.setCellValueFactory(new PropertyValueFactory<>("yassine"));
        nomproduitC.setCellValueFactory(new PropertyValueFactory<Produit, String>("nom"));
        prixC.setCellValueFactory(new PropertyValueFactory<Produit, Double>("prix"));
        descriptionC.setCellValueFactory(new PropertyValueFactory<Produit, String>("description"));
        imageC.setCellValueFactory(new PropertyValueFactory<>("nom_image")); //file dans l'entite
        quantiteC.setCellValueFactory(new PropertyValueFactory<Produit, Integer>("quantite"));
        image2.setCellValueFactory(new PropertyValueFactory<>("photo"));
        }
    }

    @FXML
    void trier_categorie(ActionEvent event) throws SQLException {
        
        
        
          
          ProduitService ms = new ProduitService();
        if (Trie_par.getValue() == "Tablier Scolaire") {

            ArrayList<Produit> arrayList = (ArrayList<Produit>) ms.Get_Produit_by_Categorie("Tablier Scolaire");
            ObservableList obs = FXCollections.observableArrayList(arrayList);
            tableP.setItems(obs);
        } else if (Trie_par.getValue() == "Fourniture Scolaire") {
            ArrayList<Produit> arrayList = (ArrayList<Produit>) ms.Get_Produit_by_Categorie("Fourniture Scolaire");
            ObservableList obs = FXCollections.observableArrayList(arrayList);
            tableP.setItems(obs);
        } else
            
        {
                 ArrayList<Produit> arrayList = (ArrayList<Produit>) ms.Get_Produit_by_Categorie("Sac a Dos");
            ObservableList obs = FXCollections.observableArrayList(arrayList);
            tableP.setItems(obs);
                
           
        idC.setCellValueFactory(new PropertyValueFactory<>("id"));
        //categorieC.setCellValueFactory(new PropertyValueFactory<>("yassine"));
        nomproduitC.setCellValueFactory(new PropertyValueFactory<Produit, String>("nom"));
        prixC.setCellValueFactory(new PropertyValueFactory<Produit, Double>("prix"));
        descriptionC.setCellValueFactory(new PropertyValueFactory<Produit, String>("description"));
        imageC.setCellValueFactory(new PropertyValueFactory<>("nom_image")); //file dans l'entite
        quantiteC.setCellValueFactory(new PropertyValueFactory<Produit, Integer>("quantite"));
        image2.setCellValueFactory(new PropertyValueFactory<>("photo"));
        }

    }
    
    /////////////////////////////Affichage All produit////////////////
    
     @FXML
    void all(ActionEvent event) {
         ObservableList<Produit> data = FXCollections.observableArrayList();
           
        ProduitService srvRec = new ProduitService();
        data = FXCollections.observableArrayList(srvRec.afficherAll());
        idC.setCellValueFactory(new PropertyValueFactory<>("id"));
        //categorieC.setCellValueFactory(new PropertyValueFactory<>("yassine"));
        nomproduitC.setCellValueFactory(new PropertyValueFactory<Produit, String>("nom"));
        prixC.setCellValueFactory(new PropertyValueFactory<Produit, Double>("prix"));
        descriptionC.setCellValueFactory(new PropertyValueFactory<Produit, String>("description"));
        imageC.setCellValueFactory(new PropertyValueFactory<>("nom_image")); //file dans l'entite
        quantiteC.setCellValueFactory(new PropertyValueFactory<Produit, Integer>("quantite"));
        image2.setCellValueFactory(new PropertyValueFactory<>("photo"));

        

        tableP.setItems(data);
        

    }

    
    private void remplirComboBox() {
        String list[] = {"Tablier Scolaire", "Fourniture Scolaire" , "Sac a Dos"};

        for (int i = 0; i < list.length; i++) {
            Trie_par.getItems().add(list[i]);
        }
    }
    

    
    public void afficher_champs(Produit p)
     {
         
          CommandeProduitService cs = new CommandeProduitService();
          try {
              user.setText(cs.getloginusername());
          } catch (SQLException ex) {
              Logger.getLogger(ProduitFrontController.class.getName()).log(Level.SEVERE, null, ex);
          }
       nomprod.setText(p.getNom());
       quantiteprod.setText(String.valueOf(p.getQuantite()));
       prixprod.setText(String.valueOf(p.getPrix()));
       descriptionprod.setText(p.getDescription());
       image = new Image("file:"+p.getNom_image()+"", imageprod.getFitWidth(), imageprod.getFitHeight(), true, true);
                imageprod.setImage(image);
                imageprod.setPreserveRatio(true);
                
       
       
     }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
        
        CommandeProduit c = new CommandeProduit();
        ProduitService P = new ProduitService();
        
       
        
         
         
       
        
         remplirComboBox();
        // TODO
          ObservableList<Produit> data = FXCollections.observableArrayList();
           
        ProduitService srvRec = new ProduitService();
        data = FXCollections.observableArrayList(srvRec.afficherAll());
        idC.setCellValueFactory(new PropertyValueFactory<>("id"));
        //categorieC.setCellValueFactory(new PropertyValueFactory<>("yassine"));
        nomproduitC.setCellValueFactory(new PropertyValueFactory<Produit, String>("nom"));
        prixC.setCellValueFactory(new PropertyValueFactory<Produit, Double>("prix"));
        descriptionC.setCellValueFactory(new PropertyValueFactory<Produit, String>("description"));
        imageC.setCellValueFactory(new PropertyValueFactory<>("nom_image")); //file dans l'entite
        quantiteC.setCellValueFactory(new PropertyValueFactory<Produit, Integer>("quantite"));
        image2.setCellValueFactory(new PropertyValueFactory<>("photo"));

        

        tableP.setItems(data);
        
      
       
        afficher_champs(data.get(0));
        
        
          try {
              detail();
          } catch (SQLException ex) {
              Logger.getLogger(ProduitFrontController.class.getName()).log(Level.SEVERE, null, ex);
          }
        
         
         
              FilteredList<Produit> filteredData = new FilteredList<>(data, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		filterfield.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(Produit -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
                                
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (Produit.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if (Produit.getDescription().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
				
				     else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Produit> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(tableP.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		tableP.setItems(sortedData);
   
            
         
        
        
      
    }  
    
    
    ////////////////////////////commande details////////////////////////////
    
      @FXML
    void detail() throws SQLException {
        
        CommandeProduit c = new CommandeProduit();
        
        
       
        
         //yassine.setVisible(true);
        
         prod = tableP.getSelectionModel().getSelectedItem();
         
          detail.setOnMouseClicked(e -> {
             Produit p =tableP.getItems().get(tableP.getSelectionModel().getSelectedIndex());
             
            
             
             idprod.setText(String.valueOf(p.getId()));
               nomprod.setText(p.getNom());
           descriptionprod.setText(p.getDescription());
           quantiteprod.setText(String.valueOf(p.getQuantite()));
          
      
       prixprod.setText(String.valueOf(p.getPrix()));
       descriptionprod.setText(p.getDescription());
        image = new Image("file:"+p.getNom_image()+"", imageprod.getFitWidth(), imageprod.getFitHeight(), true, true);
                imageprod.setImage(image);
         
           
                
                  
                
                
                   });
     }
         
    
      @FXML
    void ajouter_commande(ActionEvent event) throws SQLException {
        
        
        UserSevice s1=new UserSevice();
        CommandeProduit s = new CommandeProduit();
        
        Produit p = new Produit();
   
      CommandeProduitService s2 = new CommandeProduitService();
    
       String ss= s1.getlogin() ;                 //prendre l id du user connecté
       int result = Integer.parseInt(ss);			
			
                       
        String r = user.getText();
        
        
         int idU=ps.findbynomcategorie(r); 
       
         
        //Integer quantitecmdd= Integer.parseInt(quantitecmd.getText());
         
         Double prixtotal= Integer.parseInt(quantitecmd.getText())*Double.parseDouble(prixprod.getText());
             
         
         CommandeProduit c = 
   new CommandeProduit(Integer.parseInt(idprod.getText()),Integer.parseInt(s1.getlogin()),Integer.parseInt(quantitecmd.getText()),prixtotal,"Non Validée","Non Payée");
         
          if (Integer.parseInt(quantiteprod.getText())==0)
          {
              
         Alert alert = new Alert(Alert.AlertType.WARNING);
         alert.setHeaderText(null);
         alert.setContentText("Erreur! Le stock est épuisé !");
         alert.showAndWait();
         DialogPane dialogPane = alert.getDialogPane();
           
          }
         
          else if(Integer.parseInt(quantiteprod.getText())-Integer.parseInt(quantitecmd.getText())<=0)
          {
              
               Alert alert = new Alert(Alert.AlertType.WARNING);
         alert.setHeaderText(null);
         alert.setContentText("Erreur! La quantité de votre commande dépasse le stock !");
         alert.showAndWait();
         DialogPane dialogPane = alert.getDialogPane();
             
          }
          else
          {
         s2.ajouuterCommande(c);
             
               TrayNotification tray = new TrayNotification();
                Image whatsAppImg = new Image("/images/source1.gif");
                String text = "Commande ajoutée avec succés ";
                  tray.setTray("welcome", text + " ", whatsAppImg, Paint.valueOf("#66ff33"), AnimationType.SLIDE);

            tray.showAndDismiss(Duration.seconds(7));
             }
        
         
         
    }
    
    
/////////////////////////////////////REDIRECTION AU PANIER///////////////////////////
      @FXML
    void panier(MouseEvent event) {

    

    
 
          try {
               
      
               Parent AnchorPane = FXMLLoader.load(getClass().getResource("Panier.fxml"));
               Stage stage = new Stage();
               Scene scene = new Scene(AnchorPane);
               stage.setScene(scene);
               stage.show();
           } catch (IOException ex) {
              
           }
                   
    
                          ((Node) event.getSource()).getScene().getWindow().hide();
        

    }
    
    @FXML
    void login(MouseEvent event) {
         try {
               
      
               Parent AnchorPane = FXMLLoader.load(getClass().getResource("FXMLLogin.fxml"));
               Stage stage = new Stage();
               Scene scene = new Scene(AnchorPane);
               stage.setScene(scene);
               stage.show();
           } catch (IOException ex) {
              
           }
                   
    
                          ((Node) event.getSource()).getScene().getWindow().hide();
        

    }
 //////////////////////Homeretour///////////////
    
        @FXML
    void homeretour(ActionEvent event) {
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
    
    
  
    
     ////////////redirectionnnnnnnn///////////////
      @FXML
    void carnet(ActionEvent event) {
        
          try {
               
      
               Parent AnchorPane = FXMLLoader.load(getClass().getResource("carnet.fxml"));
               Stage stage = new Stage();
               Scene scene = new Scene(AnchorPane);
               stage.setScene(scene);
               stage.show();
           } catch (IOException ex) {
              
           }
                   
    
                          ((Node) event.getSource()).getScene().getWindow().hide();
        

    

    }

    @FXML
    void events(ActionEvent event) {
        
          try {
               
      
               Parent AnchorPane = FXMLLoader.load(getClass().getResource("FXMLeventfront.fxml"));
               Stage stage = new Stage();
               Scene scene = new Scene(AnchorPane);
               stage.setScene(scene);
               stage.show();
           } catch (IOException ex) {
              
           }
                   
    
                          ((Node) event.getSource()).getScene().getWindow().hide();
        

    

    }

    @FXML
    void inscription(ActionEvent event) {
        

    
          try {
               
      
               Parent AnchorPane = FXMLLoader.load(getClass().getResource("inscription.fxml"));
               Stage stage = new Stage();
               Scene scene = new Scene(AnchorPane);
               stage.setScene(scene);
               stage.show();
           } catch (IOException ex) {
              
           }
                   
    
                          ((Node) event.getSource()).getScene().getWindow().hide();
        

    }
    
      @FXML
    void library(ActionEvent event) {
        
          try {
               
      
               Parent AnchorPane = FXMLLoader.load(getClass().getResource("Emprunt.fxml"));
               Stage stage = new Stage();
               Scene scene = new Scene(AnchorPane);
               stage.setScene(scene);
               stage.show();
           } catch (IOException ex) {
              
           }
                   
    
                          ((Node) event.getSource()).getScene().getWindow().hide();

    }

   
}
   


    

        
   
         
         
         
         
         
         
         



     
    

