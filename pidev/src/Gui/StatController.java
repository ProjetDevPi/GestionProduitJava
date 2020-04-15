/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import animatefx.animation.Bounce;
import animatefx.animation.FadeIn;
import animatefx.animation.FadeOut;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.BorderPane;
import utils.MyConnection;

/**
 * FXML Controller class
 *
 * @author wejdene
 */
public class StatController implements Initializable {

    private final ObservableList<PieChart.Data> details= FXCollections.observableArrayList();
private PieChart pieChart;
BorderPane root;
   
    private ResultSet rs=null,rs1=null;
    private PreparedStatement pst,pst1;
    @FXML
    PieChart piechart1;
    ObservableList<PieChart.Data> piechartdata;
ArrayList<Integer> np=new ArrayList<Integer>();
ArrayList<String> cat=new ArrayList<String>();

     private Connection cnx;
  
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        new Bounce(piechart1).play();
      cnx = MyConnection.getInstance().getCnx();
        Piechart();
        piechart1.setData(piechartdata);
    }    
    private void Piechart(){
        piechartdata=FXCollections.observableArrayList();
    try {
        
        pst=cnx.prepareStatement("select * from classe");
           
      
        rs=pst.executeQuery();
       
        while(rs.next() )
        {
              pst1=cnx.prepareStatement("SELECT COUNT(*) as countuser FROM eleve WHERE classe_id='"+rs.getString("id")+"'");
        rs1=pst1.executeQuery();
           
        while(rs1.next())
        {
            int i=Integer.valueOf(rs1.getString("countuser"));
            piechartdata.add(new PieChart.Data(rs.getString("nom_classe"),i));
            np.add(i);
            cat.add(rs.getString("nom_classe"));
        }
        }
    } catch (SQLException ex) {
        Logger.getLogger(StatController.class.getName()).log(Level.SEVERE, null, ex);
    }
   
        // TODO
    }    
    
}
