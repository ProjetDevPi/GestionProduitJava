/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import entities.Sponsor;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import services.SponsorService;
import services.UserSevice;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class FXMLSponsorController implements Initializable {

    static void close() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
     ObservableList<String> typeprod = FXCollections.observableArrayList("Délice","Yab","Maestro");
   
    @FXML
    private AnchorPane parent;

    @FXML
    private JFXTextField sponsorname;

    @FXML
    private JFXComboBox type;

    @FXML
    private TableView<Sponsor> tableviewS;

    @FXML
    private TableColumn<Sponsor,String> nom;

    @FXML
    private TableColumn<Sponsor,String> typesp;

    @FXML
    private JFXButton btninsert;
 @FXML
    private JFXButton btndelete;
    @FXML
    private JFXButton btninsert1;
/////////////Menu///////////////////////
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
  ////////////////endMenu////////////
      ObservableList<Sponsor> data = FXCollections.observableArrayList();
       
      
      public static Stage MainStage;
      public static int E_id_selection;
      public static String E_nom_selection;
      public static String E_prenom_selection;
      public static String EditTable = "";
   

    @FXML
    void add(ActionEvent event) {
       
        String nom= sponsorname.getText();//recuperer le nom saisie au niveau du textfield
        String  t= type.getSelectionModel().getSelectedItem().toString();
       
            Sponsor S = new Sponsor();
            S.setNomsponsor(nom);
            S.setTypeprod(t);

            SponsorService sps = new SponsorService();
            sps.addSponsor(S);
            //affichage
        ObservableList<Sponsor> data = FXCollections.observableArrayList();
        SponsorService srvRec = new SponsorService();
        data = FXCollections.observableArrayList(srvRec.afficherAll());
        tableviewS.setItems(data); //pour refresh affichage ou bien la methode refresh mais avec button

    }
    @FXML
    void deletes(Event event) {
        if (!tableviewS.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Sponsor ");
            alert.setHeaderText("Are you sure you want to delete this sponsor"
                    + tableviewS.getSelectionModel().getSelectedItem().getId() + "?");
            Optional<ButtonType> result = alert.showAndWait();
        
        
          if (result.get() == ButtonType.OK) {
        
        
 SponsorService ms = new  SponsorService();
        ObservableList< Sponsor> ll, ttmission;
        ttmission = tableviewS.getItems();
        // ta3tina les lignes selectionnés 
        ll = tableviewS.getSelectionModel().getSelectedItems();

        for ( Sponsor m : ll) {
            ttmission.remove(m);//refresh tableview
            ms.DeleteSponsor(m.getId());
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
    
    @FXML
    void edit(ActionEvent event) throws IOException {
        Stage stage1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
        MainStage = stage1;
        EditTable = ((Button) event.getSource()).getText();//recuperer le nom du bouttom sur laquellle on a appuie
        if (EditTable.equals("Edit")) { //tester sur lenomdu bouttom
            E_id_selection = tableviewS.getSelectionModel().getSelectedItem().getId();//recuperer l'id de la ligne sur la quelle on a appuie
            E_nom_selection = tableviewS.getSelectionModel().getSelectedItem().getNomsponsor();//recuperer le nomde la ligne sur la quelle on a appuie
            E_prenom_selection = tableviewS.getSelectionModel().getSelectedItem().getTypeprod();
         

        } 
        
        Parent root = FXMLLoader.load(getClass().getResource("EditSponsor.fxml"));//appler la page edit
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Edit");
         stage.show();
         ObservableList<Sponsor> data = FXCollections.observableArrayList();
        SponsorService srvRec = new SponsorService();
        data = FXCollections.observableArrayList(srvRec.afficherAll());
     
        tableviewS.setItems(data);
       

    }
    /*/@FXML
    void sponsor(ActionEvent event) {

    }/*/
   
    /**
     * Initializes the controller class.
     * @param url
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          type.setItems(typeprod);//de la liste
           ObservableList<Sponsor> data = FXCollections.observableArrayList();
        SponsorService srvRec = new SponsorService();
        data = FXCollections.observableArrayList(srvRec.afficherAll());
        nom.setCellValueFactory(new PropertyValueFactory<Sponsor, String>("nomsponsor"));//affichage dans le table view
        typesp.setCellValueFactory(new PropertyValueFactory<Sponsor, String>("typeprod"));   
        tableviewS.setItems(data);
    }    

   
}
