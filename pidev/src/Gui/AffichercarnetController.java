/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import static Gui.EditabsenceController.LOCAL_DATE;
import animatefx.animation.FadeIn;
import animatefx.animation.Tada;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import entities.absence;
import entities.carnet;
import entities.eleve;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
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
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import services.CarnetService;

/**
 * FXML Controller class
 *
 * @author wejdene
 */
public class AffichercarnetController implements Initializable {
         ObservableList<String> et = FXCollections.observableArrayList("Semestre1","Semestre2");
      ObservableList<carnet> listC = null;
  @FXML
    private TextField filterfield;
   String path = "C:\\Users\\ASUS\\Desktop\\wejdene\\done.wav";
      
      Media media = new Media(new File(path).toURI().toString());
      
      MediaPlayer mediaplayer = new MediaPlayer(media); 
    @FXML
    private TableView<carnet> tableC;
     @FXML
    private AnchorPane rootpane;
    @FXML
    private TableColumn<?, ?> idC;

    @FXML
    private TableColumn<?, ?> noteC;

    @FXML
    private TableColumn<?, ?> appreciationC;

    @FXML
    private TableColumn<?, ?> dateC;

    @FXML
    private TableColumn<?, ?> semestreC;
        @FXML
    private TableColumn<?, ?> eleveC;
    CarnetService cs= new CarnetService();
    
        @FXML
    private JFXComboBox<String> eleve;
         ObservableList<String> listE= null;

    @FXML
    private JFXTextField note;

    @FXML
    private JFXTextArea appreciation;

    @FXML
    private JFXDatePicker date;

    @FXML
    private JFXComboBox<String> semestre;
        @FXML
    private Label lab;

    @FXML
    private JFXTextField idd;
        @FXML
    private Label el;
        
    @FXML
    private JFXButton addbut;
    private Object refs;
    
    @FXML
    private Text add;
       @FXML
    private VBox vbox2;
                   @FXML
    private VBox vbox3;
    @FXML
    private Text edit;
        @FXML
    private VBox vbox;
              
    @FXML
    private Text titre;
        @FXML
    private JFXButton btnab;
    @FXML
 void addC(ActionEvent event) throws SQLException {
     
String n= note.getText();
  int result = Integer.parseInt(n);	
String app= appreciation.getText();

String sem= semestre.getSelectionModel().getSelectedItem().toString();
  
         String r = eleve.getSelectionModel().getSelectedItem().toString();
         
         int idU=cs.findbyeleve(r);
            LocalDate    datee = (date.getValue());

 int idp=cs.getEleves(idU);
     System.out.println(idp);
         
     Date d = Date.valueOf(date.getValue());
carnet P = new carnet();
P.setNote(result);
P.setAppreciation(app);
P.setSemestre(sem);
P.setDate(d.toString());
P.setEleve_id(idU);
P.setParent(idp);


cs.ajouuterCarnet(P);
mediaplayer.play();
  Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
         alert.setHeaderText(null);
         alert.setContentText("carnet ajoutée a"+r);
         alert.showAndWait();
         DialogPane dialogPane = alert.getDialogPane();

        try {
            listC = cs.getCarnet();
        } catch (SQLException ex) {
            Logger.getLogger(AffichercarnetController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tableC.setItems(listC);

    }
 
 
      private void setCellValueFromTableToTextField(){
        tableC.setOnMouseClicked(e -> {
            carnet pl = tableC.getItems().get(tableC.getSelectionModel().getSelectedIndex());
      int n=pl.getNote();
              note.setText(String.valueOf(n));
               appreciation.setText(pl.getAppreciation());
           semestre.setItems(et);
           eleve.setVisible(false);
           el.setVisible(false);
           lab.setVisible(true);
           idd.setVisible(true);
           idd.setText(String.valueOf(pl.getId()));
           date.setValue(LOCAL_DATE("2020-02-11"));
           edit.setVisible(true);
           add.setVisible(false);
      
           
          
        });
      }
       public static final LocalDate LOCAL_DATE (String dateString){
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    LocalDate localDate = LocalDate.parse(dateString, formatter);
    return localDate;
       }
      private void setCellValueFromTableToTextField1(){
        addbut.setOnMouseClicked(e -> {
           
           eleve.setVisible(true);
           el.setVisible(true);
           lab.setVisible(false);
           idd.setVisible(false);
        appreciation.clear();
        note.clear();
        semestre.getSelectionModel().clearSelection();
        
         date.getEditor().clear();
  
               edit.setVisible(false);
           add.setVisible(true);
      
           
       });
      }
         @FXML
    void bounce(MouseEvent event) {
new Tada(vbox).play();
    }
      @FXML
    void fade(MouseEvent event) {
new FadeIn(titre).play();
    }
      @FXML
    void bounce2(MouseEvent event) {
new Tada(vbox2).play();
    }
      @FXML
    void bounce3(MouseEvent event) {
new Tada(vbox3).play();
    }
         @FXML
    void toeleve(ActionEvent event) throws IOException {
   AnchorPane root = FXMLLoader.load(getClass().getResource("afficherEleve.fxml"));
        Scene scene = btnab.getScene();
        root.translateYProperty().set(scene.getHeight());

        rootpane.getChildren().add(root);

        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_IN);
        KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
        timeline.getKeyFrames().add(kf);
        timeline.setOnFinished(t -> {
            rootpane.getChildren().remove(rootpane);
        });
        timeline.play();
    }
     
         @FXML
    void toclasse(ActionEvent event) throws IOException {
  AnchorPane root = FXMLLoader.load(getClass().getResource("afficherClasse.fxml"));
        Scene scene = btnab.getScene();
        root.translateYProperty().set(scene.getHeight());

        rootpane.getChildren().add(root);

        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_IN);
        KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
        timeline.getKeyFrames().add(kf);
        timeline.setOnFinished(t -> {
            rootpane.getChildren().remove(rootpane);
        });
        timeline.play();
    }
            
         
    
       public void clear() {
        note.clear();
        appreciation.clear();
        eleve.getSelectionModel().clearSelection();
        semestre.getSelectionModel().clearSelection();
        date.getEditor().clear();
        idd.clear();
    }
       @FXML
    void retour(MouseEvent event) throws IOException {
makeFateOutR();
    }
              private void makeFateOutR(){
        FadeTransition fadeTransition =new FadeTransition();
        fadeTransition.setDuration(Duration.millis(800));
        fadeTransition.setNode(rootpane);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.setOnFinished((ActionEvent event )->{
            try {
                loadNextSceneR();
            } catch (IOException ex) {
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
           
            }
        
        );
          
      fadeTransition.play();
    }
    private void loadNextSceneR() throws IOException{
       try { Parent secondView;
        secondView = FXMLLoader.load(getClass().getResource("afficherabsence.fxml"));
        Scene newScene=new Scene(secondView);
        Stage curStage =(Stage) rootpane.getScene().getWindow();
        curStage.setScene(newScene);}
      catch (IOException ex) {
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
      
        @FXML
    void delete(ActionEvent event) throws IOException {
     if (!tableC.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Notebook");
            alert.setHeaderText("Are you sure you want to delete this notebook"
                    + tableC.getSelectionModel().getSelectedItem().getId() + "?");
            Optional<ButtonType> result = alert.showAndWait();
        
        
          if (result.get() == ButtonType.OK) {
        
        

        ObservableList<carnet> ll, ttmission;
        ttmission = tableC.getItems();
        // ta3tina les lignes selectionnés 
        ll = tableC.getSelectionModel().getSelectedItems();

        for (carnet m : ll) {
           
            cs.DeleteProduit(m.getId());
        }
        mediaplayer.play();
        JOptionPane.showMessageDialog(null, "supprimer");

    }
          }
          else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Error of selection");
            alert.setHeaderText("you need to select a NoteBook  ");

            Optional<ButtonType> result = alert.showAndWait();
        }
          try {
            listC = cs.getCarnet();
        } catch (SQLException ex) {
            Logger.getLogger(AffichercarnetController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tableC.setItems(listC);


    }
       @FXML
    void onedit(ActionEvent event) throws SQLException, IOException {
   
        String app= appreciation.getText();
             String sem= semestre.getSelectionModel().getSelectedItem().toString();;
String n= note.getText();
  int result = Integer.parseInt(n);
    LocalDate    d = (date.getValue());

 Date da = Date.valueOf(date.getValue());
    
        int id = Integer.parseInt(idd.getText());
  
  
  carnet c =new carnet();
   c.setId(id);
         c.setAppreciation(app);
        c.setDate(da.toString());
   c.setNote(result);
          c.setSemestre(sem);
        

            cs.Update(c);
     mediaplayer.play();

      Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
         alert.setHeaderText(null);
         alert.setContentText("carnet Modifiée");
         alert.showAndWait();
         DialogPane dialogPane = alert.getDialogPane();

        try {
            listC = cs.getCarnet();
        } catch (SQLException ex) {
            Logger.getLogger(AffichercarnetController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tableC.setItems(listC);
       
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
             edit.setVisible(false);
        lab.setVisible(false);
        idd.setVisible(false);
        setCellValueFromTableToTextField();
          setCellValueFromTableToTextField1();
                    try {
            listE = cs.getUser();
        } catch (SQLException ex) {
            Logger.getLogger(AffichercarnetController.class.getName()).log(Level.SEVERE, null, ex);
        }
        eleve.setItems(listE);
        
           semestre.setItems(et);
         idC.setCellValueFactory(new PropertyValueFactory<>("id"));
       noteC.setCellValueFactory(new PropertyValueFactory<>("note"));
        appreciationC.setCellValueFactory(new PropertyValueFactory<>("appreciation"));
         dateC.setCellValueFactory(new PropertyValueFactory<>("date")); 
            semestreC.setCellValueFactory(new PropertyValueFactory<>("semestre"));
               eleveC.setCellValueFactory(new PropertyValueFactory<>("yassine")); 
             try {
            listC = cs.getCarnet();
        } catch (SQLException ex) {
            Logger.getLogger(AffichercarnetController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tableC.setItems(listC);
        
      FilteredList<carnet> filteredData = new FilteredList<>(listC, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		filterfield.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(eleve -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (eleve.getYassine().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if (eleve.getSemestre().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
				
				     else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<carnet> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(tableC.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		tableC.setItems(sortedData);
    }    
    
}
