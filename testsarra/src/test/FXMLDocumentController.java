/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import entities.FosUser;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import entities.Personne;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import  utils.MyConnection;
import  services.PersonneService;
import services.UserSevice;
import utils.ControleSaisie;

/**
 *
 * @author wejdene
 */
public class FXMLDocumentController implements Initializable {
    
 
      @FXML
    private Label lblnom;

    @FXML
    private TextField txtfield;

    @FXML
    private Label lblpre;
        @FXML
    private TextField txtfield2; //nom

    @FXML
    private Button btnclick;
    
    @FXML
    private Label lab;

         @FXML
    private Button btnexit;
             @FXML
    private Label label2;
            @FXML
    private TableView<Personne> tableviewP;
                  @FXML
    private TableColumn<?, ?> idC;

               @FXML
    private TableColumn<Personne, String> nom;

    @FXML
    private TableColumn<Personne, String> prenom;
    
               @FXML
    private TextField filechoose;
               
                  @FXML
    private Button chooser;
  ///////////////////declaration combo et listN/////////////////                
                     @FXML
    private ComboBox<String> user;
                  
  ObservableList<String> listN = null;
  
      @FXML
    private TableColumn<?, ?> nomU;
                  
    FileChooser fc = new FileChooser();
    File selectedFile = new File("");
        
            @FXML
    private ImageView imageView;
    private Image image;
    
     @FXML
    private TableColumn<Personne, String> imageC;
     
      @FXML
    private JFXButton Refresh;
    
     public static int E_id_selection;
    public static String E_nom_selection;
    public static String E_prenom_selection;
    ////////////////////////////////////Controle////////////////////////////////////////////////////
      boolean Nomok = true;
    boolean Nomok1 = true;
    boolean Nomok2 = true;
    boolean dateexpok1 = true;
    boolean hebergementok = true;
    boolean transportok = true;
    boolean nbrperok = true;
    boolean nbrperok1 = true;
    boolean nbrperok2 = true;
    boolean datebedok = true;
    boolean datefinok = true;
    boolean categorie = true;
    boolean salaireok= true;
    boolean salaireok1 = true;
    boolean salaireok2 = true;
    
       @FXML
    private Label errorcategorie;
    ControleSaisie controle = new ControleSaisie();
    @FXML
      private Label errornbrpersonne;
         @FXML
    private Label errornom;
    
    
  //////////////////////////////////////////////////////////////  
        @FXML
    private JFXTextField recherche;
   
      ObservableList<Personne> data = FXCollections.observableArrayList();
        
      private Connection cnx;
    private Statement st;
    private PreparedStatement pre;
    
     public static String EditTable = "";
     
    private int id;
    
    public static Stage MainStage;

           PersonneService ps = new PersonneService();
           
            public static void close() {
        MainStage.close();
    }
 
      
  @FXML
 void addP(ActionEvent event) throws SQLException {
     
           controle.effacerControleSaisie(errorcategorie);
     
        controle.effacerControleSaisie(errornom);
        controle.effacerControleSaisie(errornbrpersonne);
        
        
      Nomok1 =controle.controleTextFieldOnlyLetters(txtfield2, "le nom ne peut pas contenir des chiffres", errornom);
        nbrperok2 =controle.controleTextFieldVide(txtfield2, "veuillez saisir le nom de la mission", errornom);
        Nomok =Nomok1||Nomok2 ;
        
     
   
String nom= txtfield2.getText();
String prenom= txtfield.getText();
String file=filechoose.getText();
UserSevice s1=new UserSevice();
   
      
    
       String ss= s1.getlogin() ;                 //prendre l id du user connecté
       int result = Integer.parseInt(ss);			
			
                       
        String r = user.getSelectionModel().getSelectedItem().toString();
        
         int idU=ps.findbynomcategorie(r); 
    
          
           

Personne P = new Personne();
P.setNom(nom);
P.setPrenom(prenom);
P.setIdlogin(result);
P.setFile(file);
P.setUser(idU);

 PersonneService ps = new PersonneService();
ps.ajouuterPersonne(P);
lab.setText("hello wej");

 ObservableList<Personne> data = FXCollections.observableArrayList();
        PersonneService srvRec = new PersonneService();
        data = FXCollections.observableArrayList(srvRec.afficherAll());
     
  
        tableviewP.setItems(data); //pour refresh affichage ou bien la methode refresh mais avec button
    
 }
 
 
     @FXML
    void exit(ActionEvent event) {
     Platform.exit();
    }
    
    //browseimage
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
    
   //////////////////////////// 
     private void showProductImage(String nomm) throws SQLException{
        cnx = MyConnection.getInstance().getCnx();
        st=cnx.createStatement();
        try {
           String req="Select * from personne";
            
            
       ResultSet  rs ;
       rs=st.executeQuery(req);
     
    
            while(rs.next()) {
                
                if (rs.getString("nom").equals(nomm))
                {                 
                
                    System.out.println(rs.getString("nom"));
                String a=rs.getString("file");
                
                
                
                image = new Image("file:"+a+"", imageView.getFitWidth(), imageView.getFitHeight(), true, true);
                imageView.setImage(image);
                imageView.setPreserveRatio(true);
            
            }
            }
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        
        
    }
        }
    
          private void setCellValueFromTableToTextField(){
        tableviewP.setOnMouseClicked(e -> {
            Personne pl = tableviewP.getItems().get(tableviewP.getSelectionModel().getSelectedIndex());
      
           txtfield2.setText(pl.getNom());
   
      
            try {
                
                showProductImage(pl.getNom());
            } catch (SQLException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
          
        });
        
        
    } 
          /////////////////////refresh data//////////////////////
      /*/
          @FXML
    void Refresh(MouseEvent event) {
          ObservableList<Personne> data = FXCollections.observableArrayList();
        PersonneService srvRec = new PersonneService();
        data = FXCollections.observableArrayList(srvRec.afficherAll());
     
        nom.setCellValueFactory(new PropertyValueFactory<Personne, String>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<Personne, String>("prenom"));
        imageC.setCellValueFactory(new PropertyValueFactory<>("file")); //file dans l'entite

      
        tableviewP.setItems(data);

    }
/*/
          
          
            @FXML  
    void edit(ActionEvent e) throws IOException {
        
 Stage stage1 = (Stage) ((Node) e.getSource()).getScene().getWindow();
        MainStage = stage1;
        EditTable = ((Button) e.getSource()).getText();
        if (EditTable.equals("_edit personne")) {
            E_id_selection = tableviewP.getSelectionModel().getSelectedItem().getId();
            E_nom_selection = tableviewP.getSelectionModel().getSelectedItem().getNom();
            E_prenom_selection = tableviewP.getSelectionModel().getSelectedItem().getPrenom();
         

        } 
        Parent root = FXMLLoader.load(getClass().getResource("Edit.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Edit");
        stage.show();
        
   
        
         ObservableList<Personne> data = FXCollections.observableArrayList();
        PersonneService srvRec = new PersonneService();
        data = FXCollections.observableArrayList(srvRec.afficherAll());
         
        tableviewP.setItems(data); 
     
      
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        // TODO
         setCellValueFromTableToTextField();
         
          try {
            listN = ps.getUser();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        user.setItems(listN);
            
         /*/
        UserSevice s1=new UserSevice();
           String ss = null ;
          try {
              ss = s1.getlogin();
             	
          } catch (SQLException ex) {
              Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
          }
       		
			 int result = Integer.parseInt(ss);
                         
           label2.setText(ss)    ;    

/*/
           
            ObservableList<Personne> data = FXCollections.observableArrayList();
        PersonneService srvRec = new PersonneService();
        data = FXCollections.observableArrayList(srvRec.afficherAll());
        idC.setCellValueFactory(new PropertyValueFactory<>("id"));
        nom.setCellValueFactory(new PropertyValueFactory<Personne, String>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<Personne, String>("prenom"));
        imageC.setCellValueFactory(new PropertyValueFactory<>("file")); //file dans l'entite
        
         nomU.setCellValueFactory(new PropertyValueFactory<>("yassine")); 

      
        tableviewP.setItems(data);
       // Refresh.setVisible(true);
        
        
        
    }    
    
    /*/
     @FXML
    void delete(ActionEvent event) {
 PersonneService ms = new PersonneService();
        ObservableList<Personne> ll, ttmission;
        ttmission = tableviewP.getItems();
        // ta3tina les lignes selectionnés 
        ll = tableviewP.getSelectionModel().getSelectedItems();

        for (Personne m : ll) {
            ttmission.remove(m);
            ms.DeleteMission(m.getId());
        }
        JOptionPane.showMessageDialog(null, "supprimer");

    }

    
/*/
    ////////////////////////////delete2//////////////////////
    @FXML
     void delete(ActionEvent event) {
     if (!tableviewP.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Cart ");
            alert.setHeaderText("Are you sure you want to delete this cart"
                    + tableviewP.getSelectionModel().getSelectedItem().getId() + "?");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
                PersonneService crd = new PersonneService();
                Personne s1 = tableviewP.getSelectionModel().getSelectedItem();
                ArrayList<Personne> data1 = crd.afficherAll();

                for (Personne recl : data1) {
                    if (s1.equals(recl)) {
                        crd.supprimerReclamation(s1);
                    }

                }

                tableviewP.getItems().removeAll(s1);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Erreur de selection");
            alert.setHeaderText("Vous etes obligé de selectioner une reclamation  ");

            Optional<ButtonType> result = alert.showAndWait();
        }
    }
   
    /////////////////////////////RECHERCHEEEEEEE///////////////////////////////
    
     @FXML
    private void rechercheAction(ActionEvent event) throws SQLException {
        
        String nom= recherche.getText();
        PersonneService ts = new PersonneService();

        ArrayList<Personne> listTaches = ts.rechercheTacheparsonnom(nom);

        for (Personne tv : listTaches) {
             
            data.clear();
            data.add(tv);
             tableviewP.setItems(data);
        }
        
    }

        
 }    
    
