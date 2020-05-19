/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import entities.Personne;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import services.PersonneService;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class FXMLtestController implements Initializable {
        @FXML
    private TableView<Personne> tableviewP;

    @FXML
    private TableColumn<Personne, String> nom;

    @FXML
    private TableColumn<Personne, String> prenom;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         ObservableList<Personne> data = FXCollections.observableArrayList();
        PersonneService srvRec = new PersonneService();
        data = FXCollections.observableArrayList(srvRec.afficherAll());
     
        nom.setCellValueFactory(new PropertyValueFactory<Personne, String>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<Personne, String>("prenom"));
      
        tableviewP.setItems(data);
    }    
    
}
