/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.absence;
import entities.eleve;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.MyConnection;

/**
 *
 * @author wejdene
 */
public class AbsenceService {
    
      private Connection cnx;
    private Statement st;
    private PreparedStatement pre;

    public AbsenceService() {
        cnx = MyConnection.getInstance().getCnx();
    }

    public void ajouuterAbsence(absence a) {

        try {
            String req = "INSERT INTO absence ( eleve_id,justification,etat,date) VALUES "
                    + "('" + a.getEleve_id() + "', '" + a.getJustification() + "', '" + a.getEtat() + "', '" + a.getDate()+ "')";

            st = cnx.createStatement();

            st.executeUpdate(req);

            System.out.println("Insertion Reussie!");

        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }
     public void ajouuternbre_absence(eleve e) {

        try {
     pre = cnx.prepareStatement("update eleve set nbre_absence=? where id=?");

                    pre.setInt(1, e.getNbre_absence());
               
                    pre.setInt(2, e.getId());
                   
                  
                    pre.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }

    public void ajouuterPersonne1(eleve p) {

        try {
            String req = "INSERT INTO personne (nom, prenom) VALUES (?, ?)";

            pre = cnx.prepareStatement(req);

            pre.setString(1, p.getNom());
            pre.setString(2, p.getPrenom());

            pre.executeUpdate();

            System.out.println("Insertion 2 Reussie!");

        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }

    public List<eleve> afficherAll() {

        List<eleve> listP = new ArrayList<>();

        try {

            String req = "SELECT * FROM eleve";

            st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);

            while (res.next()) {
                eleve p = new eleve();

                p.setId(res.getInt("id"));
                p.setNom(res.getString(2));
                p.setPrenom(res.getString("prenom"));
              
                listP.add(p);
            }
            
         

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return listP;
    }
      public  ObservableList<absence> getAbsence() throws SQLException {
     
     
        String req = "select * from absence";
     st = cnx.createStatement();
            ResultSet result = st.executeQuery(req);
        
        ObservableList<absence> mealsList = FXCollections.observableArrayList();
        while (result.next()) {
                absence a = new absence();

                a.setId(result.getInt("id"));
                a.setEleve_id(result.getInt("eleve_id"));
                a.setJustification(result.getString("justification"));
                      a.setEtat(result.getString("etat"));
               
                      
                      
                 
              
                        a.setDate(result.getString("date"));

          
    
            mealsList.add(a);
          
        }
        return mealsList;
    
      }
            public  ObservableList<String> getUser() throws SQLException {
     
     
        String req = "select Dname from Drinks";
        
     st = cnx.createStatement();
            ResultSet result = st.executeQuery(req);
        
        ObservableList<String> mealsList = FXCollections.observableArrayList();
    
        while (result.next()) {
                
         String    n=  result.getString("Dname");
          
   
            mealsList.add(n);
           
        }
        return mealsList;
    
      }
            public int findbyclasse(String name) throws SQLException
{
    
String req = "SELECT * FROM classe";
st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);
            
            while (res.next()) {
                if (res.getString("nom").equals(name))
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
             public eleve findnbre_absence(int user ) throws SQLException{
                  
String req = "SELECT * FROM eleve ";
st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);
           
            while (res.next()) {
                  eleve e=new eleve();
                if (res.getInt("id")==user)
                {
                   
            
 e.setNbre_absence(res.getInt("nbre_absence"));
   e.setNom(res.getString("nom"));
  e.setId(res.getInt("id"));
           return e;
              }
           
             
                      
            }
           
return new eleve();
              }
            
            
 /*** ll affichage*/
             
 public String findbynom(int user ) throws SQLException{
                  
String req = "SELECT * FROM eleve ";
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
         
 
          public void Update(absence a) throws SQLException {
   
                    pre = cnx.prepareStatement("update absence set justification=?,etat=?,date=?  where id=?");

                    pre.setString(1, a.getJustification());
                    pre.setString(2, a.getEtat());
                      pre.setString(3, a.getDate());
                    pre.setInt(4, a.getId());
                   
                  
                    pre.executeUpdate();
   
       
        }
           public void DeleteProduit(int id) {
        try {
            String sql = "delete from absence WHERE id = ?";
            PreparedStatement pstmt = cnx.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Delete Absence Done!");
        } catch (SQLException ex) {
            Logger.getLogger(EleveService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
          
}
