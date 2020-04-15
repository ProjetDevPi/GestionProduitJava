/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import entities.Produit;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import services.ProduitService;

/**
 * FXML Controller class
 *
 * @author wejdene
 */
public class EditController implements Initializable {
    @FXML
    private TextField nomm;

    @FXML
    private TextField prixx;

    @FXML
    private TextField descriptionn;
    
            @FXML
    private TextField id;

    @FXML
    private ComboBox<String> categorie;

    @FXML
    private TextField filechoose;

    @FXML
    private TextField quantitee;

    @FXML
    private Button chooser;
    
      ObservableList<String> listN = null; //listN
                    ProduitService ps = new ProduitService();
                    
                    
                    
    //////////////////////////IMAGE/////////////////
        private Image image;
        
          FileChooser fc = new FileChooser();
    File selectedFile = new File("");
    


    @FXML
    void onedit(ActionEvent e) throws SQLException, IOException {
          
        String r = categorie.getSelectionModel().getSelectedItem().toString();
        int idU=ps.findbynomcategorie(r); 
        String nom = nomm.getText();
        String description = descriptionn.getText();
        double prix = Double.valueOf(prixx.getText());
        int idd = Integer.parseInt(id.getText());
        int quantite = Integer.parseInt(quantitee.getText());
        String file = filechoose.getText();
        Produit p =new Produit();
        p.setId(idd);
        p.setCategorie(idU);
        p.setNom_image(file);
        p.setNom(nom);
        p.setQuantite(quantite);
        p.setPrix(prix);
        p.setDescription(description);
        

            ps.Update(p);
         
               
         

        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.close();

        Parent root2 = FXMLLoader.load(getClass().getResource("Produit.fxml"));
        Scene scene2 = new Scene(root2);
        Stage stage2 = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage2.setScene(scene2);
        stage2.centerOnScreen();
        stage2.setTitle("Menu");
        
      stage2.show();
      ProduitController.close();
       
      


    }
    
    //////////////////////image///////////////////////////
    @FXML
    void image(ActionEvent event) {
      fc.setTitle("Open Resource File");
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text files","*.pdf", "*.tkt", "*.docx","*.png","*.jpg"));
        fc.setInitialDirectory(new File("C:"));
        selectedFile = fc.showOpenDialog(null);

        //UploadFile.upload(selectedFile,"", "");
        File file = new File("" + selectedFile.getName());
        filechoose.setText(selectedFile.getName());

        //images.setImage(imagee);   
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        int id1= ProduitController.E_id_selection;
        int q1= ProduitController.E_quantite_selection;
        Double p1 =ProduitController.E_prix_selection;
        id.setText(String.valueOf(id1));
        nomm.setText(ProduitController.E_nom_selection);
        filechoose.setText(ProduitController.E_image_selection);
        prixx.setText(String.valueOf(p1));
        quantitee.setText(String.valueOf(q1));
        descriptionn.setText(ProduitController.E_description_selection);
        //String r = categorie.getSelectionModel().getSelectedItem().toString();
        
          try {
            listN = ps.getCategorie();
        } catch (SQLException ex) {
            Logger.getLogger(ProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
        categorie.setItems(listN);
       
        
        
        
        
        
     
        
        
        
        
        
   
     

    }

    }    
    

