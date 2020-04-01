/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import entities.personne;
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
import services.persoService;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class FXMLtestController implements Initializable {
        @FXML
    private TableView<personne> tableviewP;

    @FXML
    private TableColumn<personne, String> nom;

    @FXML
    private TableColumn<personne, String> prenom;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         ObservableList<personne> data = FXCollections.observableArrayList();
        persoService srvRec = new persoService();
        data = FXCollections.observableArrayList(srvRec.afficherAll());
     
        nom.setCellValueFactory(new PropertyValueFactory<personne, String>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<personne, String>("prenom"));
      
        tableviewP.setItems(data);
    }    
    
}
