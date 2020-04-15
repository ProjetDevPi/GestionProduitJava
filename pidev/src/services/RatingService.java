/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.classe;
import entities.rating;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.MyConnection;

/**
 *
 * @author wejdene
 */
public class RatingService {
      private Connection cnx;
    private Statement st;
    private PreparedStatement pre;
    
    
      public RatingService() {
        cnx = MyConnection.getInstance().getCnx();
    }

    public void ajouuterRating(rating r) {

        try {
            String req = "INSERT INTO rating ( nom,rat,user,commentaire) VALUES "
                    + "('" + r.getNom() + "', '" + r.getRat() + "', '" + r.getUser() + "', '" + r.getCommentaire() + "')";

            st = cnx.createStatement();

            st.executeUpdate(req);

            System.out.println("Insertion Reussie!");

        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }
     public  ObservableList<rating> getrating() throws SQLException {
     
     
        String req = "select * from rating";
     st = cnx.createStatement();
            ResultSet result = st.executeQuery(req);
        
        ObservableList<rating> mealsList = FXCollections.observableArrayList();
        while (result.next()) {
                rating c = new rating();

               
                c.setNom(result.getString("nom"));
                c.setRat(result.getDouble("rat"));
                   c.setUser(result.getInt("user"));
                     c.setCommentaire(result.getString("commentaire"));

          
    
            mealsList.add(c);
          
        }
        return mealsList;
    
      }
}
