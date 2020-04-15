/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import entities.classe;
import entities.eleve;
import entities.rating;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.BoxBlur;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import services.RatingService;

/**
 * FXML Controller class
 *
 * @author wejdene
 */
public class AfficherRController implements Initializable {
  ObservableList<rating> listR = null;
       @FXML
    private TableView<rating> tabR;
    @FXML
    private BarChart<String,Double> barchart;
    @FXML
    private TableColumn<?, ?> nomC;

    @FXML
    private TableColumn<?, ?> ratC;
        @FXML
    private TableColumn<?, ?> userC;

    @FXML
    private TableColumn<?, ?> comC;
     @FXML
    private AnchorPane rootpane;
            static public rating missionsel;
     @FXML
    private TextField filterfield;
      RatingService cs =new RatingService();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nomC.setCellValueFactory(new PropertyValueFactory<>("nom"));
       ratC.setCellValueFactory(new PropertyValueFactory<>("rat"));
              userC.setCellValueFactory(new PropertyValueFactory<>("user"));
                     comC.setCellValueFactory(new PropertyValueFactory<>("commentaire"));
     
          
              try {
            listR = cs.getrating();
        } catch (SQLException ex) {
            Logger.getLogger(AfficherEleveController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tabR.setItems(listR); 
        
          XYChart.Series<String, Double> series11 = new XYChart.Series<>();
         
        try {
            for (rating t1 : cs.getrating()) {
                
                XYChart.Data<String, Double> data11;
                
                
                
               
                  
                    data11 = new XYChart.Data<String, Double>(t1.getNom(), t1.getRat());
                    
                    series11.getData().add(data11);
                 
                
                
                
                
                
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(AfficherRController.class.getName()).log(Level.SEVERE, null, ex);
        }
          barchart.getData().addAll(series11);
                XYChart.Series<String, Double> series = new XYChart.Series<>();
                
                
                 FilteredList<rating> filteredData = new FilteredList<>(listR, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		filterfield.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(rating -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (rating.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} 
				
				     else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<rating> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(tabR.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		tabR.setItems(sortedData);
        
        setCellValueFromTableToTextField();
    }  
     private void setCellValueFromTableToTextField() {
        tabR.setOnMouseClicked(e -> {
     
            try {
                missionsel = tabR.getSelectionModel().getSelectedItem();
                
                BoxBlur blur = new BoxBlur(3,3,3);
                Stage stage1 = (Stage) ((Node) e.getSource()).getScene().getWindow();
                
                
                
                
                
                Parent root;
                
                root = FXMLLoader.load(getClass().getResource("detailRating.fxml"));
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("Rating");
                stage.show();
                
                
                
                
                rootpane.setEffect(blur);
                stage.setOnHiding( ev -> { rootpane.setEffect(null);} );   
            } catch (IOException ex) {
                Logger.getLogger(AfficherRController.class.getName()).log(Level.SEVERE, null, ex);
            }
         
          
        });
        
        
    }
    
}
