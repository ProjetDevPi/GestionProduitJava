/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Contrat;
import entities.Evenement;
import entities.Sponsor;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import utils.MyConnection;

/**
 *
 * @author HP
 */
public class ContratService {
    private Connection cnx;
    private Statement st;
    private PreparedStatement pre;

    public ContratService() {
         cnx = MyConnection.getInstance().getCnx();
    }

    public  ObservableList<String> getSponsor() throws SQLException {
     
     
        String req = "select nomsponsor from sponsor";
        
     st = cnx.createStatement();
            ResultSet result = st.executeQuery(req);
        
        ObservableList<String> List = FXCollections.observableArrayList();
    
        while (result.next()) {
                
         String    n=  result.getString("nomsponsor");
          
   
         List.add(n);
           
        }
        return List;
    
      }
    public int findbynomcategorie(String name) throws SQLException
{
    
String req = "SELECT * FROM sponsor";
st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);
            
            while (res.next()) {
                if (res.getString("nomsponsor").equals(name))
                {
String val=res.getString("id");
System.out.println(val);
int valeur= Integer.parseInt(val);  

return valeur;
                }
}
          
        int valeur=0;
          return valeur;
}
     public void addContrat(Contrat c) {

        try {
            String req = "INSERT INTO contrat (numcontrat, sponsor_id,evenement_id) VALUES "
                    + "('" + c.getNumcontrat() + "', '" + c.getSponsor_id() + "','" + c.getEvenement_id() + "')";

            st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Insertion Reussie!");

        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }
      public ArrayList<Contrat> afficherAll() {

        ArrayList<Contrat> listP = new ArrayList<>();

        try {

            String req = "SELECT * FROM contrat";

            st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);

            while (res.next()) {
               Contrat p = new Contrat();

                p.setId(res.getInt("id"));
                p.setNumcontrat(res.getInt("numcontrat"));
               
                
                //base attention
                
                System.out.println(p.toString());

                listP.add(p);
            }
            
            System.out.println(listP);

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return listP;
    }
      public String findbynom(int user ) throws SQLException{
                  
String req = "SELECT * FROM sponsor ";
st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);
            while (res.next()) {
                if (res.getInt("id")==user)
                {
            
      String  name= res.getString("nomsponsor");
                   
            return name;
              }
            }
            return null;
              }
       public String findbynomevent(int user ) throws SQLException{
                  
String req = "SELECT * FROM evenement ";
st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);
            while (res.next()) {
                if (res.getInt("id")==user)
                {
            
      String  name= res.getString("nom");
                   
            return name;
              }
            }
            return null;
              }
            public String findbyimage(int user ) throws SQLException{
                  
String req = "SELECT * FROM evenement ";
st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);
            while (res.next()) {
                if (res.getInt("id")==user)
                {
            
      String  name= res.getString("nom_image");
                   
            return name;
              }
            }
            return null;
              }
              public Date findbydate(int user ) throws SQLException{
                 Date dateAvant = new Date(0);
String req = "SELECT * FROM evenement ";
st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);
            while (res.next()) {
                if (res.getInt("id")==user)
                {
           
                    Date  name= res.getDate("dateevent");
                   
                     return name;
                 }
            }
            return dateAvant ;
              }
      public  ObservableList<Contrat> getMeals() throws SQLException {
     
     
        String req = "select * from contrat order by numcontrat";
     st = cnx.createStatement();
            ResultSet result = st.executeQuery(req);
        
        ObservableList<Contrat> mealsList = FXCollections.observableArrayList();
        while (result.next()) {
                Contrat p = new Contrat();
             String   a=findbyimage(result.getInt("evenement_id"));
  Image image = new Image("file:"+a+"",70, 70, true, true);

ImageView photo= new ImageView(image);
p.setPhoto(photo);
                p.setId(result.getInt("id"));
                 p.setNumcontrat(result.getInt("numcontrat"));
                
                    p.setYassine(findbynom(result.getInt("sponsor_id")));
                    p.setEvent(findbynomevent(result.getInt("evenement_id")));
                    p.setImage(findbyimage(result.getInt("evenement_id")));
                   p.setDateevent(findbydate(result.getInt("evenement_id")));
System.out.println(p.toString());
          
           
            mealsList.add(p);
          
        }
        return mealsList;
    
      }
       public void DeleteContrat(int id) {
        try {
            String sql = "delete from contrat WHERE id = ?";
            PreparedStatement pstmt = cnx.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Delete contrat Done!");
        } catch (SQLException ex) {
            Logger.getLogger(SponsorService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
       
    }
   
    

