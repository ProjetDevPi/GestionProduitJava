/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import static Gui.FXMLEvenementController.E_id_selection;
import static Gui.FXMLEvenementController.E_nom_selection;
import static Gui.FXMLEvenementController.E_prenom_selection;
import static Gui.FXMLEvenementController.EditTable;
import static Gui.FXMLEvenementController.MainStage;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import entities.Evenement;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import services.EvenementService;
import utils.MyConnection;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class FXMLeventfrontController implements Initializable {

    
   
    @FXML
    private TableView<Evenement> tableviewE;

    @FXML
    private TableColumn<Evenement, String> nom;

    @FXML
    private TableColumn<Evenement, String> afftype;

    @FXML
    private TableColumn<Evenement, Date> affdate;

    @FXML
    private TableColumn<Evenement, String> imageC;

   @FXML
    private AnchorPane btnpartriciper;

      @FXML
    private ImageView imageView;

    @FXML
    private JFXTextField namedetail;

    @FXML
    private JFXTextField typedetail;

    @FXML
    private JFXTextField datedetail;

    @FXML
    private JFXTextField capacitydetail;

    @FXML
    private JFXButton btnaffecter1;
   @FXML
    private TableColumn<Evenement,Integer> capacity; 

    @FXML
    void participer(Event event) throws IOException {
        
        System.out.println("vghdv");
        Stage stage1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
        MainStage = stage1;
        EditTable = ((Button) event.getSource()).getText();
        if (EditTable.equals("Participer")) {
            E_id_selection = tableviewE.getSelectionModel().getSelectedItem().getId();
            E_nom_selection = tableviewE.getSelectionModel().getSelectedItem().getNom();
            E_prenom_selection = tableviewE.getSelectionModel().getSelectedItem().getType();
         

        } 
        
        Parent root = FXMLLoader.load(getClass().getResource("FXMLParticipant.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Participation Evenement");
        
        
        stage.show();
           ((Node) event.getSource()).getScene().getWindow().hide();
    }
              
           
       

    
 private Connection cnx;
    private Statement st;
    private PreparedStatement pre;
    private Image image;
      private void showProductImage(String nomm) throws SQLException{
        cnx = MyConnection.getInstance().getCnx();
        st=cnx.createStatement();
        try {
           String req="Select * from evenement";
            
            
       ResultSet     rs ;
       rs=st.executeQuery(req);
     
    
            while(rs.next()) {
                
                if (rs.getString("nom").equals(nomm))
                {                 
                
                    System.out.println(rs.getString("nom"));
                String a=rs.getString("nom_image");
                
                
                
                image = new Image("file:"+a+"", imageView.getFitWidth(), imageView.getFitHeight(), true, true);
                    System.out.println(image);
                imageView.setImage(image);
                imageView.setPreserveRatio(true);
            
            }
            }
        } catch (SQLException ex) {
            Logger.getLogger(FXMLEvenementController.class.getName()).log(Level.SEVERE, null, ex);
        
        
    }
        }
   private void detaill(){
        tableviewE.setOnMouseClicked((MouseEvent e) -> {
            Evenement p = tableviewE.getItems().get(tableviewE.getSelectionModel().getSelectedIndex());
           namedetail.setText(p.getNom());
           typedetail.setText(p.getType());
           datedetail.setText(String.valueOf(p.getDateevent()));
           capacitydetail.setText(String.valueOf(p.getNbrpart()));
            try {
                showProductImage(p.getNom());
            } catch (SQLException ex) {
                Logger.getLogger(FXMLEvenementController.class.getName()).log(Level.SEVERE, null, ex);
            }
               
  
          
        });
   }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        detaill();
          ObservableList<Evenement> data = FXCollections.observableArrayList();
        EvenementService srvRec = new EvenementService();
        data = FXCollections.observableArrayList(srvRec.afficherAll());
     
        nom.setCellValueFactory(new PropertyValueFactory<Evenement, String>("nom"));
        afftype.setCellValueFactory(new PropertyValueFactory<Evenement, String>("type"));
        affdate.setCellValueFactory(new PropertyValueFactory<Evenement, Date>("dateevent"));
          capacity.setCellValueFactory(new PropertyValueFactory<Evenement, Integer>("nbrpart"));   
        imageC.setCellValueFactory(new PropertyValueFactory<Evenement,String>("nom_image")); //file dans l'entite
        tableviewE.setItems(data);
    }    
    
    
    //////////////////Redirection avec Home//////////////
    
       @FXML
    void carnetretour(ActionEvent event) {
          try {
               
      
               Parent AnchorPane = FXMLLoader.load(getClass().getResource("carnet.fxml"));
               Stage stage = new Stage();
               Scene scene = new Scene(AnchorPane);
               stage.setScene(scene);
               stage.show();
                stage.setTitle("Carnet");
           } catch (IOException ex) {
              
           }
                   
    
                          ((Node) event.getSource()).getScene().getWindow().hide();
        

    }

    @FXML
    void homeretour(ActionEvent event) {
          try {
               
      
               Parent AnchorPane = FXMLLoader.load(getClass().getResource("Home.fxml"));
               Stage stage = new Stage();
               Scene scene = new Scene(AnchorPane);
               stage.setScene(scene);
               stage.show();
                stage.setTitle("Home");
           } catch (IOException ex) {
              
           }
                   
    
                          ((Node) event.getSource()).getScene().getWindow().hide();
        

    }

    @FXML
    void inscriptionretour(ActionEvent event) {
          try {
               
      
               Parent AnchorPane = FXMLLoader.load(getClass().getResource("inscription.fxml"));
               Stage stage = new Stage();
               Scene scene = new Scene(AnchorPane);
               stage.setScene(scene);
               stage.show();
                stage.setTitle("Inscription");
           } catch (IOException ex) {
              
           }
                   
    
                          ((Node) event.getSource()).getScene().getWindow().hide();
        

    }

   

    @FXML
    void products(ActionEvent event) {
          try {
               
      
               Parent AnchorPane = FXMLLoader.load(getClass().getResource("ProduitFront.fxml"));
               Stage stage = new Stage();
               Scene scene = new Scene(AnchorPane);
               stage.setScene(scene);
               stage.show();
                stage.setTitle("Produit");
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
