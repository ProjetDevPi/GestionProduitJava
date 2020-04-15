/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.CategorieProduit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.MyConnection;

/**
 *
 * @author ASUS
 */
public class CategorieProduitService {
    
    
      
     private Connection cnx;
    private Statement st;
    private PreparedStatement pre;
    
    
      public CategorieProduitService() {
        cnx = MyConnection.getInstance().getCnx();
    }
    
    public void ajouterCategorie(CategorieProduit p) {

        try {
            String req = "INSERT INTO categorie1 (nom, description) VALUES "
                    + "('" + p.getNom() + "', '" + p.getDescription() + "')";

            st = cnx.createStatement();

            st.executeUpdate(req);

            System.out.println("Insertion Reussie!");

        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }
    
     public void editcat(CategorieProduit c) throws SQLException {
   
                    pre = cnx.prepareStatement("update Categorie1 set nom=?,description=? where id=?");

                    pre.setString(1, c.getNom());
                    pre.setString(2, c.getDescription());
                   
                    pre.setInt(3, c.getId());
                    pre.executeUpdate();
   
       
        }
     
     
    public void Deletec(int idd) throws SQLException {
   
                    pre = cnx.prepareStatement("DELETE FROM Categorie1 where id=?");

                    pre.setInt(1,idd);
                    pre.executeUpdate();
   
       
        }
        
   
     public List<CategorieProduit> afficherCat() {

        List<CategorieProduit> listC = new ArrayList<>();

        try {

            String req = "SELECT *  FROM categorie1";

            st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);

            while (res.next()) {
                CategorieProduit p = new CategorieProduit();
                p.setId(Integer.parseInt(res.getString("id")));
                p.setNom(res.getString("nom"));
                p.setDescription(res.getString("description"));
                

                listC.add(p);
                System.out.println( listC);
            }
            
            

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return listC;
    }
    
}
