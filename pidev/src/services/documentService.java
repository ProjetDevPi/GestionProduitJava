/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Documents;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import utils.MyConnection;

/**
 *
 * @author Mehdi Tekaya
 */
public class documentService {
    
     private Connection con;
    private Statement ste;
    private PreparedStatement pst;

    public documentService() {
        con = MyConnection.getInstance().getCnx();

    }
     
    
    public int ajouterdocuemnt(Documents d) throws SQLException {
        int i = 0;
        FileInputStream input = null;
        try {
            PreparedStatement pre = con.prepareStatement("INSERT INTO `pidev`.`document` ( `categorie`,`nomdocument`,`etatdocument` ,`emprunte`, `nom_image` ) VALUES (  ?, ?, ?, ?, ?);");
            
            pre.setInt(1, d.getCategorie());
            pre.setString(2, d.getNomdocument());

            pre.setString(3, d.getEtatdocument());
          
            pre.setInt(4, 0);
            pre.setString(5, d.getNom_image());
            
            System.out.println(pre);
            i = pre.executeUpdate();
            System.out.println("produit ajouté, veuillez consulter votre BD");
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        return i;
    }
    
               public int deletedocument(int id) throws SQLException  {
        int i = 0;
       try {
           ste=con.createStatement();
           String sql="DELETE FROM `pidev`.`document` WHERE id="+id;   
            i=ste.executeUpdate(sql);
       } catch (SQLException ex) {
           Logger.getLogger(categorieService.class.getName()).log(Level.SEVERE, null, ex);
       }
     finally{ste.close();}
      return i;  
    }
               
   public int updatedocument(Documents d) throws SQLException {
        int i = 0;
        FileInputStream input = null;
        try {
            PreparedStatement pre = con.prepareStatement("UPDATE  `pidev`.`document` SET `id`=?,`categorie`=?,`nomdocument`=?,`etatdocument`=?,`nom_image`=?   WHERE `id`='" + d.getId()+ "'");

            pre.setInt(1, d.getId());
            pre.setInt(2, d.getCategorie());
            pre.setString(3, d.getNomdocument());

            pre.setString(4, d.getEtatdocument());
            pre.setString(5, d.getNom_image());
            System.out.println(pre);
            i = pre.executeUpdate();
            System.out.println("le document a été mis à jour, veuillez consulter votre BD");
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        return i;
    }
 public String getNamedocbyId(int id) throws SQLException
    { String name="";
           String query="SELECT nomdocument as nomdocument FROM document WHERE id='"+id+"'";
           ste=con.createStatement();
           
        ResultSet rst = ste.executeQuery(query); 
        while(rst.next())
        {
             name=rst.getString("nomdocument");
            
        }
      
      return(name);  
    }
}
