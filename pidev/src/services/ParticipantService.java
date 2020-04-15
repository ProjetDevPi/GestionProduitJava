/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Contrat;
import entities.Participant;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.MyConnection;

/**
 *
 * @author HP
 */
public class ParticipantService {
     private Connection cnx;
    private Statement st;
    private PreparedStatement pre;

    public ParticipantService() {
         cnx = MyConnection.getInstance().getCnx();
    }

   
     public void addParticipant(Participant p) {

        try {
            String req = "INSERT INTO participant (evenement_id, user_id,nbr) VALUES "
                    + "('" + p.getEvenement_id() + "', '" + p.getUser_id() + "','" + p.getNbr() + "')";

            st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Insertion Reussie!");

        } catch (SQLException ex) {
            System.out.println(ex);
        }

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
      public String findbynom(int user ) throws SQLException{
                  
String req = "SELECT * FROM fos_user ";
st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);
            while (res.next()) {
                if (res.getInt("id")==user)
                {
            
      String  name= res.getString("username");
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
public void DeleteParticipant(int id) {
        try {
            String sql = "delete from participant WHERE id = ?";
            PreparedStatement pstmt = cnx.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Delete Participant Done!");
        } catch (SQLException ex) {
            Logger.getLogger(SponsorService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      public  ObservableList<Participant> getMeals() throws SQLException {
     
     
        String req = "select * from participant";
     st = cnx.createStatement();
            ResultSet result = st.executeQuery(req);
        
        ObservableList<Participant> mealsList = FXCollections.observableArrayList();
        while (result.next()) {
                Participant p = new Participant();

                p.setId(result.getInt("id"));
                 p.setNbr(result.getInt("nbr"));
                 p.setUser_id(result.getInt("user_id"));
                
                  p.setParticipant(findbynom(result.getInt("user_id")));
                    p.setEvent(findbynomevent(result.getInt("evenement_id")));
                     p.setImage(findbyimage(result.getInt("evenement_id")));
System.out.println(p.toString());
          
           
            mealsList.add(p);
          
        }
        return mealsList;
    
      }
      public String getemail(int idd) throws SQLException {
           ResultSet rs;
       
       
        st=cnx.createStatement();
        String pseudoL = null;
       
       
        String req="SELECT * FROM fos_user ";
        rs=st.executeQuery(req);
         while (rs.next()) {
         if( rs.getInt("id") ==idd)
             
         pseudoL= rs.getString("email");
        }
         System.out.println(pseudoL);
         return pseudoL;
         
            }
       public String getloginusername() throws SQLException {
           ResultSet rs;
       
       
        st=cnx.createStatement();
        String pseudoL = null;
       int i=0;
       
        String req="SELECT username FROM login ";
        rs=st.executeQuery(req);
         while (rs.next()) {
         
         pseudoL= rs.getString("username");
        }
         System.out.println(pseudoL);
         return pseudoL;
         
       }
        
   


                public int findbynomcategorie(String name) throws SQLException
{
    
String req = "SELECT * FROM fos_user";
st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);
            
            while (res.next()) {
                if (res.getString("username").equals(name))
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

  

    
}
