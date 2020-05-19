/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.NodeOrientation;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

/**
 *
 * @author oXCToo
 */
public class HomeController implements Initializable {
    
    @FXML
    private Label label;
    
      @FXML
    private VBox pnl_scroll;

    
    @FXML
    private void handleButtonAction(MouseEvent event) {        
       refreshNodes();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
  
         refreshNodes();
    }    
    
    private void refreshNodes()
    {
        pnl_scroll.getChildren().clear();
        
        Node [] nodes = new  Node[15];
        
       
            try {
                
                nodes[1] = (Node)FXMLLoader.load(getClass().getResource("Item.fxml"));
               pnl_scroll.getChildren().add(nodes[1]);
               
                 nodes[2] = (Node)FXMLLoader.load(getClass().getResource("info.fxml"));
               pnl_scroll.getChildren().add(nodes[2]);
               
                  nodes[3] = (Node)FXMLLoader.load(getClass().getResource("separation.fxml"));
               pnl_scroll.getChildren().add(nodes[3]);
               
               
               
               nodes[4] = (Node)FXMLLoader.load(getClass().getResource("teachers.fxml"));
               pnl_scroll.getChildren().add(nodes[4]);
               
             
               
                
            } catch (IOException ex) {
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            
           
        }  
    }
    
}
