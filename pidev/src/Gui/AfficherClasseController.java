/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import animatefx.animation.FadeIn;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import entities.absence;
import entities.classe;
import entities.eleve;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import services.ClasseService;

/**
 * FXML Controller class
 *
 * @author wejdene
 */
public class AfficherClasseController implements Initializable {
    
  ObservableList<classe> listC = null;
      @FXML
    private TextField filterfield;

    @FXML
    private JFXComboBox<String> salle;

    @FXML
    private JFXTextField nbre;

    @FXML
    private Label lab;
   @FXML
    private AnchorPane rootpane;
    @FXML
    private JFXTextField nom;

    @FXML
    private JFXTextField cap;

    @FXML
    private Text add;

    @FXML
    private TableView<classe> tableC;

    @FXML
    private TableColumn<?, ?> nomC;

    @FXML
    private TableColumn<?, ?> nbreC;
     @FXML
    private Label nbe;

    
    ClasseService cs =new ClasseService();
    
    @FXML
    private Text titre;

    @FXML
    private ImageView img;
        @FXML
    private Pane pane2;

    @FXML
    private Pane pane;
        @FXML
    private Pane pane1;

    @FXML
    private JFXButton btnab;
    @FXML
    private TableColumn<?, ?> capC;

    @FXML
    private TableColumn<?, ?> salleC;
       ObservableList<String> et = FXCollections.observableArrayList("2B","3A","5N","7H","6D");
       
        String path = "C:\\Users\\ASUS\\Desktop\\wejdene\\done.wav";
      
      Media media = new Media(new File(path).toURI().toString());
      
      MediaPlayer mediaplayer = new MediaPlayer(media);  
    @FXML
    void addC(ActionEvent event) throws SQLException, IOException {
     
            
String nomm= nom.getText();


 int nb = Integer.parseInt(nbre.getText());
 int capp= Integer.parseInt(cap.getText());
  
   String r = salle.getSelectionModel().getSelectedItem().toString();

  




classe a = new classe();
a.setNom_classe(nomm);
a.setNbre_eleve(nb);
a.setCapacite(capp);
a.setSalle(r);

cs.ajouuterClasse(a);
mediaplayer.play();
  Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
         alert.setHeaderText(null);
         alert.setContentText("Classe" +nomm+ " ajoutée ");
         alert.showAndWait();
         DialogPane dialogPane = alert.getDialogPane();

        try {
            listC = cs.getClasse();
        } catch (SQLException ex) {
            Logger.getLogger(AfficherClasseController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tableC.setItems(listC);
        
      
        

    }
      @FXML
    void box(MouseEvent event) {
new FadeIn(pane).play();
    }

 

    @FXML
    void fade1(MouseEvent event) {
new FadeIn(img).play();
    }

    @FXML
    void fade2(MouseEvent event) {
new FadeIn(titre).play();
    }

    @FXML
    void manager(MouseEvent event) {
new FadeIn(pane2).play();
    }

    @FXML
    void panetot(MouseEvent event) {
        new FadeIn(pane1).play();

    }
       @FXML
    void stat(ActionEvent event) throws IOException {
 BoxBlur blur = new BoxBlur(3,3,3);
        Parent root = FXMLLoader.load(getClass().getResource("stat.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("classe");
       stage.show();
           rootpane.setEffect(blur);
      stage.setOnHiding( ev -> { rootpane.setEffect(null);} ); 
    }
    
     @FXML
    void toAbsence(ActionEvent event) throws IOException {
  Parent root = FXMLLoader.load(getClass().getResource("afficherabsence.fxml"));
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
           
    
       @FXML
    void toEleve(ActionEvent event) throws IOException {
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
    void toCarnet(ActionEvent event) throws IOException {
   Parent root = FXMLLoader.load(getClass().getResource("affichercarnet.fxml"));
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
    void delete(ActionEvent event) throws IOException, SQLException {
     if (!tableC.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Classe ");
            alert.setHeaderText("Are you sure you want to delete this Classe"
                    + tableC.getSelectionModel().getSelectedItem().getId() + "?");
            Optional<ButtonType> result = alert.showAndWait();
        
        
          if (result.get() == ButtonType.OK) {
        
   
    
        ObservableList<classe> ll, ttmission;
        ttmission = tableC.getItems();
        // ta3tina les lignes selectionnés 
        ll = tableC.getSelectionModel().getSelectedItems();

        for (classe m : ll) {
           
            cs.DeleteProduit(m.getId());
        }
        JOptionPane.showMessageDialog(null, "supprimer");

    }
          }
          else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Error of selection");
            alert.setHeaderText("you need to select a Classe  ");

            Optional<ButtonType> result = alert.showAndWait();
        }
             try {
            listC = cs.getClasse();
        } catch (SQLException ex) {
            Logger.getLogger(AfficherEleveController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tableC.setItems(listC);

    }
    
    
       @FXML
    void retour(MouseEvent event) throws IOException {
  Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();

        Parent root2 = FXMLLoader.load(getClass().getResource("affichercarnet.fxml"));
        Scene scene2 = new Scene(root2);
        Stage stage2 = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage2.setScene(scene2);
        stage2.centerOnScreen();
        stage2.setTitle("Menu");
        
      stage2.show();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          int nbee;
      try {
          nbee = cs.TotalEleve();
             nbe.setText(String.valueOf(nbee));
      } catch (SQLException ex) {
          Logger.getLogger(AfficherClasseController.class.getName()).log(Level.SEVERE, null, ex);
      }
     
        
                 nomC.setCellValueFactory(new PropertyValueFactory<>("nom_classe"));
                     capC.setCellValueFactory(new PropertyValueFactory<>("capacite"));
       nbreC.setCellValueFactory(new PropertyValueFactory<>("nbre_eleve"));
        salleC.setCellValueFactory(new PropertyValueFactory<>("salle"));
          
              try {
            listC = cs.getClasse();
        } catch (SQLException ex) {
            Logger.getLogger(AfficherEleveController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tableC.setItems(listC); 
        salle.setItems(et);
    }    
    
}
