/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import static Gui.AfficherEleveController.MainStage;
import animatefx.animation.Pulse;
import animatefx.animation.Shake;
import animatefx.animation.Tada;
import com.jfoenix.controls.JFXButton;
import entities.absence;
import entities.carnet;
import entities.eleve;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import services.AbsenceService;

/**
 * FXML Controller class
 *
 * @author wejdene
 */
public class AfficherabsenceController implements Initializable {
  ObservableList<absence> listA = null;
     @FXML
    private TextField filterfield;
     @FXML
    private AnchorPane rootpane;
         @FXML
    private Pane pane;
    @FXML
    private TableView<absence> tableA;

    @FXML
    private TableColumn<?, ?> idC;

    @FXML
    private TableColumn<?, ?> justificationC;

    @FXML
    private TableColumn<?, ?> etatC;

    @FXML
    private TableColumn<?, ?> dateC;
        @FXML
    private JFXButton detail;
        
    @FXML
    private ImageView alert;
        
    @FXML
    private TableColumn<?, ?> ideleve;
    AbsenceService as =new AbsenceService();
    
    @FXML
    private Text titre;
           @FXML
    private VBox vbox2;
                   @FXML
    private VBox vbox3;
                        @FXML
    private VBox vbox;
       @FXML
    private Text text_eleve;

    @FXML
    private Text text_justification;

    @FXML
    private Text text_etat;

    @FXML
    private Text text_date;
        @FXML
    private JFXButton btnab;
         public static int E_id_selection;
    public static String E_jus_selection;
    public static String E_etat_selection;
        public static String E_date_selection;
              public static Stage MainStage;
public void afficher_detail()
       {
  //ki tenzel aal boutton détail
  detail.setOnMouseClicked(e -> {
             absence p =tableA.getItems().get(tableA.getSelectionModel().getSelectedIndex());
             //y récuperi l produit
          int idd=p.getEleve_id();
      
      String yass = null;
      try {
          yass = as.findbynom(idd);
       
      } catch (SQLException ex) {
          Logger.getLogger(AfficherabsenceController.class.getName()).log(Level.SEVERE, null, ex);
      }
       
  

       text_justification.setText(p.getJustification());
       text_etat.setText(p.getEtat());
       text_date.setText(p.getDate());
          text_eleve.setText(yass);
  
                
                
                   });
     }
      public static void close() {
        MainStage.close();
    }
      
    @FXML
    void pulse(MouseEvent event) {
new Pulse(titre).play();
    }
      @FXML
    void tada(MouseEvent event) {
new Tada(pane).play();

    }
          @FXML
    void bounce2(MouseEvent event) {
new Tada(vbox3).play();
    }
     
           @FXML
    void bounce(MouseEvent event) {
new Tada(vbox).play();
    }
              @FXML
    void alert(MouseEvent event) {
new Shake(alert).play();
    }
    
      @FXML
    void delete(ActionEvent event) throws IOException {
     if (!tableA.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Absence ");
            alert.setHeaderText("Are you sure you want to delete this Absence"
                    + tableA.getSelectionModel().getSelectedItem().getId() + "?");
            Optional<ButtonType> result = alert.showAndWait();
        
        
          if (result.get() == ButtonType.OK) {
        
        

        ObservableList<absence> ll, ttmission;
        ttmission = tableA.getItems();
        // ta3tina les lignes selectionnés 
        ll = tableA.getSelectionModel().getSelectedItems();

        for (absence m : ll) {
           
            as.DeleteProduit(m.getId());
        }
        JOptionPane.showMessageDialog(null, "supprimer");

    }
          }
          else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Error of selection");
            alert.setHeaderText("you need to select an absence  ");

            Optional<ButtonType> result = alert.showAndWait();
        }
           try {
            listA = as.getAbsence();
        } catch (SQLException ex) {
            Logger.getLogger(AfficherEleveController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tableA.setItems(listA); 
    }
@FXML  
    void edit(ActionEvent e) throws IOException {
        
 Stage stage1 = (Stage) ((Node) e.getSource()).getScene().getWindow();
 

        MainStage = stage1;
    

           
            
            E_id_selection = tableA.getSelectionModel().getSelectedItem().getId();
            E_jus_selection = tableA.getSelectionModel().getSelectedItem().getJustification();
            E_etat_selection = tableA.getSelectionModel().getSelectedItem().getEtat();
            E_date_selection = tableA.getSelectionModel().getSelectedItem().getDate();
            
       
            
         

         
        Parent root = FXMLLoader.load(getClass().getResource("editabsence.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Edit");
        stage.show();
        
   
        
 
     
      
    }
    
    @FXML
    void tocarnet(ActionEvent event) throws IOException {
     Parent root = FXMLLoader.load(getClass().getResource("affichercarnet.fxml"));
        Scene scene = btnab.getScene();
        root.translateXProperty().set(scene.getWidth());

    
        rootpane.getChildren().add(root);

        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(root.translateXProperty(), 0, Interpolator.EASE_IN);
        KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
        timeline.getKeyFrames().add(kf);
        timeline.setOnFinished(t -> {
            rootpane.getChildren().remove(rootpane);
        });
        timeline.play();
    }
        
    /*****************************************/
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
       
    
    /******************************************/
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                idC.setCellValueFactory(new PropertyValueFactory<>("id"));
                     ideleve.setCellValueFactory(new PropertyValueFactory<>("eleve_id"));
       justificationC.setCellValueFactory(new PropertyValueFactory<>("justification"));
        etatC.setCellValueFactory(new PropertyValueFactory<>("etat"));
         dateC.setCellValueFactory(new PropertyValueFactory<>("date")); 
         
        
              try {
            listA = as.getAbsence();
        } catch (SQLException ex) {
            Logger.getLogger(AfficherEleveController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tableA.setItems(listA); 
         FilteredList<absence> filteredData = new FilteredList<>(listA, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		filterfield.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(eleve -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (eleve.getDate().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if (eleve.getJustification().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
				
				     else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<absence> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(tableA.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		tableA.setItems(sortedData);
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
        secondView = FXMLLoader.load(getClass().getResource("afficherEleve.fxml"));
        Scene newScene=new Scene(secondView);
        Stage curStage =(Stage) rootpane.getScene().getWindow();
        curStage.setScene(newScene);}
      catch (IOException ex) {
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
}
