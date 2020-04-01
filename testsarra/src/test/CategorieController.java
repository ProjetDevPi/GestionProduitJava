/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import entities.CategorieProduit;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import services.CategorieService;
import services.ProduitService;
import services.UserSevice;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class CategorieController implements Initializable {
    
   


    @FXML
    private TableView<CategorieProduit> liste_categories;

    @FXML
    private TableColumn<CategorieProduit, ?> idC;

    @FXML
    private TableColumn<CategorieProduit, String> nom_categorie1=new TableColumn<>("Nom");; 

    @FXML
    private TableColumn<CategorieProduit, String> description_categorie1 =new TableColumn<>("Description");;
    
    ObservableList<CategorieProduit> data2 = FXCollections.observableArrayList();
    
        @FXML
    private JFXButton btnclick;

    @FXML
    private TextField nom_categorie;

    @FXML
    private TextField description_categorie;
    
     public static String E_id_selection;
    public static String E_nom_selection;
    public static String E_description_selection;

    
      @FXML
    private JFXTextField filterfield;
    
    
    
    
    
    public void addCategorie(ActionEvent event) {
       
       
        CategorieProduit P = new CategorieProduit();
        String nom= nom_categorie.getText();

        String description= description_categorie.getText();



      
       



P.setNom(nom);
P.setDescription(description);






 CategorieService ps = new CategorieService();
ps.ajouterCategorie(P);


 ObservableList<CategorieProduit> data2 = FXCollections.observableArrayList();
        CategorieService srvRec = new CategorieService();
        data2 = FXCollections.observableArrayList(srvRec.afficherCat());
     
  
        liste_categories.setItems(data2); //pour refresh affichage ou bien la methode refresh mais avec button
     
 }
 
        
        @FXML
    void delete(ActionEvent event) throws SQLException {
          if (!liste_categories.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Product ");
            alert.setHeaderText("Are you sure you want to delete this category"
                    + liste_categories.getSelectionModel().getSelectedItem().getId() + "?");
            Optional<ButtonType> result = alert.showAndWait();
        
        
          if (result.get() == ButtonType.OK) {
        
        
 CategorieService ms = new CategorieService();
        ObservableList<CategorieProduit> ll, ttmission;
        ttmission = liste_categories.getItems();
        // ta3tina les lignes selectionnés 
        ll = liste_categories.getSelectionModel().getSelectedItems();

        for (CategorieProduit m : ll) {
            //ttmission.remove(m);//refresh tableview
            ms.Deletec(m.getId());
            ObservableList<CategorieProduit> data2 = FXCollections.observableArrayList();
        CategorieService srvRec = new CategorieService();
        data2 = FXCollections.observableArrayList(srvRec.afficherCat());
        
        liste_categories.setItems(data2);
            
           
        }
         
       

    }
          }
          else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Erreur de selection");
            alert.setHeaderText("You are obliged to select a category ");

            Optional<ButtonType> result = alert.showAndWait();
        }
    }

    
    public void Edit_cat(ActionEvent e)
         {      
             
             E_id_selection=String.valueOf(liste_categories.getItems().get(liste_categories.getSelectionModel().getSelectedIndex()).getId());
             E_nom_selection=liste_categories.getItems().get(liste_categories.getSelectionModel().getSelectedIndex()).getNom();
             E_description_selection=liste_categories.getItems().get(liste_categories.getSelectionModel().getSelectedIndex()).getDescription();
             System.out.println(E_id_selection);
             
             
             
             try {
               Parent AnchorPane = FXMLLoader.load(getClass().getResource("FXMLEditC.fxml"));
               Stage stage = new Stage();
               Scene scene = new Scene(AnchorPane);
               stage.setScene(scene);
               stage.show();
           } catch (IOException ex) {
              // Logger.getLogger(FXMLLoginController.class.getName()).log(Level.SEVERE, null, ex);
           }
                   
    
                          ((Node) e.getSource()).getScene().getWindow().hide();
         }

         
       
     
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         ObservableList<CategorieProduit> data2 = FXCollections.observableArrayList();
     CategorieService srvRec = new CategorieService();
        data2 = FXCollections.observableArrayList(srvRec.afficherCat());
         idC.setCellValueFactory(new PropertyValueFactory<>("id"));
         nom_categorie1.setCellValueFactory(new PropertyValueFactory<>("nom")); //entite
         description_categorie1.setCellValueFactory(new PropertyValueFactory<>("description"));
       
         
        liste_categories.setItems(data2);
        
        
         FilteredList<CategorieProduit> filteredData = new FilteredList<>(data2, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		filterfield.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(Categorie -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (Categorie.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if (Categorie.getDescription().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
				
				     else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<CategorieProduit> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(liste_categories.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		liste_categories.setItems(sortedData);
   
        
        // TODO
    }    
    
    
    //////////////////REDIRECTION TO PRODUITS/////////////////
    
    @FXML
    void products(MouseEvent event) {
        
        
           try {
               
      
               Parent AnchorPane = FXMLLoader.load(getClass().getResource("Produit.fxml"));
               Stage stage = new Stage();
               Scene scene = new Scene(AnchorPane);
               stage.setScene(scene);
               stage.show();
           } catch (IOException ex) {
              
           }
                   
    
                          ((Node) event.getSource()).getScene().getWindow().hide();
        
        

    }

    
}
