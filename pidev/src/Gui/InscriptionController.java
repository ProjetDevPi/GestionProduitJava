/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import static Gui.AfficherEleveController.MainStage;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;

import entities.eleve;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import services.EleveService;
import services.UserSevice;
import utils.ControleSaisie;

/**
 * FXML Controller class
 *
 * @author wejdene
 */
public class InscriptionController implements Initializable {
      ObservableList<String> et = FXCollections.observableArrayList("Krech","Garderie","Pretaratoire");
          @FXML
    private JFXComboBox<String> comboG;
    @FXML
    private JFXTextField txtnom;

    @FXML
    private JFXTextField txtprenom;

    

    @FXML
    private JFXTextField txtage;

    @FXML
    private JFXTextField txtadresse;
    @FXML
    private JFXDatePicker txtdate;

    @FXML
    private JFXTextField filechoose;

    @FXML
    private JFXButton chooser;
   FileChooser fc = new FileChooser();
    File selectedFile = new File("");
    
      @FXML
    private ProgressBar prog;

    @FXML
    private ProgressIndicator indic;
    
    @FXML
    private Text txtbar;
    
    @FXML
    private Label erorname;
    @FXML
    private Label erorprenom;
    @FXML
    private Label erorage;
      @FXML
    private Label erordate;
    @FXML
    private Label erorad;
       ControleSaisie controle = new ControleSaisie();
      boolean Nom = true;
    boolean prenom = true;
        boolean prenom1 = true;
            boolean prenom2 = true;
    boolean age = true;
    boolean age1 = true;
    boolean age2 = true;
    boolean adresse = true;
     boolean adresse1 = true;
      boolean adresse2 = true;
  boolean Nom1 = true;
    boolean Nom2 = true;
     boolean date = true;
    
      EleveService ps = new EleveService();
       @FXML
    private AnchorPane rootpane;
          String path = "C:\\Users\\ASUS\\Desktop\\wejdene\\done.wav";
      
      Media media = new Media(new File(path).toURI().toString());
      
      MediaPlayer mediaplayer = new MediaPlayer(media); 
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

   @FXML
    private void verifinom(KeyEvent event) {
          if (!controle.controleTextFieldOnlyLetters(txtnom, "le nom ne peut pas contenir des chiffres", erorname)) {
       controle.controleTextFieldVide(txtnom, "veuillez saisir le nom de l'élève", erorname);
        }
            
    }
      @FXML
    private void verifprenom(KeyEvent event) {
          if (!controle.controleTextFieldOnlyLetters(txtprenom, "le prenom ne peut pas contenir des chiffres", erorprenom)) {
       controle.controleTextFieldVide(txtprenom, "veuillez saisir le prenom de l élève", erorprenom);
        }
          
    }
    
         @FXML
    private void verifadresse(KeyEvent event) {
          if (!controle.controleTextFieldOnlyLetters(txtadresse, "l'adresse ne peut pas contenir des chiffres", erorad)) {
       controle.controleTextFieldVide(txtadresse, "veuillez saisir l'adresse de l'élève", erorad);
        }
            
    }
         @FXML
    private void verifage(KeyEvent event) {
          if (!controle.controleTextFieldVide(txtage, "veuillez saisir l'age", erorage) ){
       controle.controleTextFieldVide(txtage, "veuillez saisir l'age ", erorage);
        }
          
    }
        @FXML
    private void verifdate(KeyEvent event) {
          if (!controle.validateDatePickerexp(txtdate)){
 
        }
    }
    
    /****ajouter elever****/
     @FXML
 void addE(ActionEvent event) throws SQLException, IOException {
    LocalDate dateDuJour = LocalDate.now();
LocalDate da=(txtdate.getValue());
       controle.effacerControleSaisie(erorad);
            controle.effacerControleSaisie(erorad);
            controle.effacerControleSaisie(erorage);
        controle.effacerControleSaisie(erorname);
         controle.effacerControleSaisie(erorprenom);

  if((txtnom.getText().isEmpty()||txtprenom.getText().isEmpty()||txtage.getText().isEmpty()||txtadresse.getText().isEmpty()||txtdate.getValue()==null)||comboG.getSelectionModel().getSelectedItem().toString().isEmpty())      {
        Alert alert = new Alert(Alert.AlertType.ERROR);
         alert.setHeaderText(null);
         alert.setContentText("Veuillez remplir tous les Champs");
         alert.showAndWait();
         DialogPane dialogPane = alert.getDialogPane();
         
  
  }
  else if(  da.isAfter(dateDuJour) ){
      Alert alert = new Alert(Alert.AlertType.ERROR);
         alert.setHeaderText(null);
         alert.setContentText("la date n'est pas valide");
         alert.showAndWait();
         DialogPane dialogPane = alert.getDialogPane();
         
  } 
  else{
           
         UserSevice s1=new UserSevice();
       String ss= s1.getlogin() ;
       int result = Integer.parseInt(ss);			
			
String nom= txtnom.getText();
String prenom= txtprenom.getText();
String niveau=comboG.getSelectionModel().getSelectedItem().toString();
String age= txtage.getText();
int valeur= Integer.parseInt(age);  
String adresse= txtadresse.getText();

    
   LocalDate    date = (txtdate.getValue());
String file=filechoose.getText();
  
        
         
     Date d = Date.valueOf(txtdate.getValue());
         
eleve e = new eleve();
e.setNom(nom);
e.setPrenom(prenom);
e.setNiveau(niveau);
e.setAge(valeur);
e.setAdresse(adresse);
e.setUser_id(result);
e.setDate_naissance(d.toString());



e.setNom_image(file);


ps.ajouuterEleve(e);
mediaplayer.play();
  Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
         alert.setHeaderText(null);
         alert.setContentText("Vote enfant a ete bien incrit");
         alert.showAndWait();
         DialogPane dialogPane = alert.getDialogPane();
    
         Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();

        Parent root2 = FXMLLoader.load(getClass().getResource("Video.fxml"));
        Scene scene2 = new Scene(root2);
        Stage stage2 = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage2.setScene(scene2);
        stage2.centerOnScreen();
        stage2.setTitle("Publicité");
      
       stage2.show();
       
//     InscriptionController.close();
  
  }
 }  
     
 
 
               public static void close() {
        MainStage.close();
    }
   @Override
    public void initialize(URL url, ResourceBundle rb) {
        comboG.setItems(et);
            prog.indeterminateProperty().addListener((ObservableValue<? extends Boolean> ov, Boolean t, Boolean t1) -> {
          if(t1){
              txtbar.setText("Calculating Time");
          }
          else{
              txtbar.setText("In Progress");
              
          }
            });
        prog.progressProperty().addListener(new ChangeListener<Number>() {

         
            @Override
            public void changed(ObservableValue<? extends Number> ov, Number t, Number t1) {
                
                if(t1.doubleValue()==1){
                    txtbar.setText("Work Done");
                    
                }
            }

        }); 
         indic.indeterminateProperty().addListener(new ChangeListener<Boolean>() {

           @Override
            public void changed(ObservableValue<? extends Boolean> ov, Boolean t, Boolean t1) {
                    if(t1){
                    txtbar.setText("Calculating Time");

                }
                else{
                    txtbar.setText("In Progress");
                            
                }
            }
        });
        // TODO
    }

   @FXML
    void textchanger(MouseEvent event) {
      
               
             
          
                    //Create new Task and Thread -  Bind Progress Property to Task Progress
                    Task task = taskCreator();
                    prog.progressProperty().unbind();
                    prog.progressProperty().bind(task.progressProperty());
                    indic.progressProperty().unbind();
                    indic.progressProperty().bind(task.progressProperty());
                    new Thread(task).start();
                    

                
            }
                          private Task taskCreator() {
                     return new Task() {
               
int seconds=30;
                
@Override
                   protected Object call() throws Exception {
                       for(int i=0; i<seconds;i++){
                        Thread.sleep(1000);
                        updateProgress(i+1, seconds);
                       
                       }
                       return true;
                   }
               };
                }    
    //////////////////////redirection///////////////
                          
                          
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

