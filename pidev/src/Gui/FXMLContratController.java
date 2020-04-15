/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import static Gui.FXMLSponsorController.E_id_selection;
import static Gui.FXMLSponsorController.E_nom_selection;
import static Gui.FXMLSponsorController.E_prenom_selection;
import static Gui.FXMLSponsorController.EditTable;
import static Gui.FXMLSponsorController.MainStage;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import entities.Contrat;
import entities.Evenement;
import entities.Sponsor;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import services.ContratService;
import services.EvenementService;
import services.SponsorService;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class FXMLContratController implements Initializable {
 @FXML
    private AnchorPane parent;
 
  
    @FXML
    private TextField eventid;

    @FXML
    private JFXTextField eventnam;

    @FXML
    private JFXTextField num;

    @FXML
    private JFXComboBox<String> sponsor;

   
     @FXML
    private JFXButton btninsert;

    @FXML
    private JFXButton btninsert1;

    @FXML
    private JFXButton btninsert11;
   
    @FXML
    private TableView<Contrat> tableviewC;
     @FXML
    private TableColumn<?, ?> student;
  @FXML
    private TableColumn<?, ?> event;
  @FXML
    private TableColumn<?, ?> photo;
   @FXML
    private TableColumn<?, ?> dateevent;
    @FXML
    private TableColumn<Contrat, Integer> affichagenumcontrat;
     @FXML
    private TableColumn<Contrat,String> affichageevent;
   
      ObservableList<String> listS = null;
        ContratService ps = new ContratService();
          ObservableList<Contrat> listP = null;
          ObservableList<Contrat> data = FXCollections.observableArrayList();
@FXML
    private TextField id;
//////////////////////Menu////////////////////////
   @FXML
    private JFXButton btnevent;

    @FXML
    private JFXButton btnsponsor;

    @FXML
    private JFXButton btncontrat;

    @FXML
    private JFXButton btnpart;

 @FXML
    void event(ActionEvent event) throws IOException {
          Parent root = FXMLLoader.load(getClass().getResource("FXMLEvenement.fxml"));//appler la page edit
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
       
         stage.show();
          ((Node) event.getSource()).getScene().getWindow().hide();

    }

    @FXML
    void participant(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("FXMLParticipationback.fxml"));//appler la page edit
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
       
         stage.show();
          ((Node) event.getSource()).getScene().getWindow().hide();

    }

    @FXML
    void sponsor(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("FXMLSponsor.fxml"));//appler la page edit
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
       
         stage.show();
          ((Node) event.getSource()).getScene().getWindow().hide();

    }
       @FXML
    void contrat(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLContrat.fxml"));//appler la page edit
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
       
         stage.show();
          ((Node) event.getSource()).getScene().getWindow().hide();

    }

   

    @FXML
    void participation(ActionEvent event) throws IOException {
        
        
        Parent root = FXMLLoader.load(getClass().getResource("FXMLParticipationtback.fxml"));//appler la page edit
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
         ((Node) event.getSource()).getScene().getWindow().hide();

    }

   
///////////////////////////////////////////////Ajouter contrat//////////////////////////////////////////

    @FXML
    void add(Event event) throws SQLException 
    {
  
         String nomevent= eventnam.getText();
         System.out.println(num.getText());
         int nb=Integer.valueOf(num.getText());
         int id=Integer.valueOf(eventid.getText());
         String r = sponsor.getSelectionModel().getSelectedItem().toString();
         int idU=ps.findbynomcategorie(r); 


                Contrat P = new Contrat();

                P.setNumcontrat(nb);
                P.setSponsor_id(idU);
                P.setEvenement_id(id);



        ContratService ps = new ContratService();
        ps.addContrat(P);
        ObservableList<Contrat> data = FXCollections.observableArrayList();
        ContratService srvRec = new ContratService();
        data = FXCollections.observableArrayList(srvRec.getMeals());
     
        tableviewC.setItems(data);



    }
 //////////////////
    @FXML
    void edit(Event event) { }
    ///////////////////////////////Delete contrat//////////////////////////////////////////
     @FXML
    void deletes(Event event) 
    {
        if (!tableviewC.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Contract ");
            alert.setHeaderText("Are you sure you want to delete this contract."
                    + tableviewC.getSelectionModel().getSelectedItem().getId() + "?");
            Optional<ButtonType> result = alert.showAndWait();
        
        
          if (result.get() == ButtonType.OK) {
        
        
                ContratService ms = new  ContratService();
        ObservableList< Contrat> ll, ttmission;
        ttmission = tableviewC.getItems();
        // ta3tina les lignes selectionnés 
        ll = tableviewC.getSelectionModel().getSelectedItems();

        for ( Contrat m : ll) {
            ttmission.remove(m);//refresh tableview
            ms.DeleteContrat(m.getId());
        }
        JOptionPane.showMessageDialog(null, "supprimer");

         }
          }
          else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Erreur de selection");
            alert.setHeaderText("Vous etes obligé de selectioner un produit  ");

            Optional<ButtonType> result = alert.showAndWait();
        }

    }


    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         int id1= FXMLEvenementController.E_id_selection;

         
        try {
            listS = ps.getSponsor();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLContratController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
            
        sponsor.setItems(listS);      
        eventid.setText(String.valueOf(id1));
        eventnam.setText(FXMLEvenementController.E_nom_selection);//recupererle nom de levenementg du table view qui existe dans 
       
         ObservableList<Contrat> data = FXCollections.observableArrayList();
        ContratService srvRec = new ContratService();
        data = FXCollections.observableArrayList(srvRec.afficherAll());
       //afichage dans le tab
        affichagenumcontrat.setCellValueFactory(new PropertyValueFactory<Contrat,Integer>("numcontrat"));
        student.setCellValueFactory(new PropertyValueFactory<>("yassine"));
        event.setCellValueFactory(new PropertyValueFactory<>("event"));
        photo.setCellValueFactory(new PropertyValueFactory<>("photo"));
        dateevent.setCellValueFactory(new PropertyValueFactory<>("dateevent"));
            
        try {
            listP = ps.getMeals();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLContratController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        tableviewC.setItems(listP);
                
    }
    
}
