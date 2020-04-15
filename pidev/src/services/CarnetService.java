/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.absence;
import entities.carnet;
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
public class CarnetService {
      private Connection cnx;
    private Statement st;
    private PreparedStatement pre;

    public CarnetService() {
        cnx = MyConnection.getInstance().getCnx();
    }
     public void ajouuterCarnet(carnet c) {

        try {
            String req = "INSERT INTO carnet ( note,appreciation,date,eleve_id,semestre,parent) VALUES "
                    + "('" + c.getNote() + "', '" + c.getAppreciation() + "', '" + c.getDate() + "', '" + c.getEleve_id()+ "', '" + c.getSemestre()+ "', '" + c.getParent()+ "')";

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
      
      public List<eleve> findelevebylogin(int idd) {

        List<eleve> listC = new ArrayList<>();

        try {

            String req = "SELECT *  FROM eleve";

            st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);

            while (res.next()) {
            if (res.getInt("user_id")==idd){
                eleve e = new eleve();
                e.setNbre_absence(res.getInt("nbre_absence"));
               e.setNom(res.getString("nom"));
                e.setId(res.getInt("id"));
                

                listC.add(e);
            }
            
            
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return listC;
    }
      
      public List<absence> findabsencebyeleve(int idd) {

        List<absence> listC = new ArrayList<>();

        try {

            String req = "SELECT *  FROM absence";

            st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);

            while (res.next()) {
            if (res.getInt("eleve_id")==idd)
            {
                absence e = new absence();
                
             e.setEleve_id(res.getInt("eleve_id"));
                e.setId(res.getInt("id"));
                e.setJustification(res.getString("justification"));
                e.setEtat(res.getString("etat"));
                e.setDate(res.getString("date"));
                listC.add(e);
            }
            }
        

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return listC;
    }
      
            public absence findabsence(int user ) throws SQLException{
                  
String req = "SELECT * FROM absence ";
st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);
           
            while (res.next()) {
                  absence e=new absence();
                if (res.getInt("eleve_id")==user)
                {
                   
            

  e.setId(res.getInt("id"));
           return e;
              }
           
             
                      
            }
           
return new absence();
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
      public  ObservableList<carnet> getCarnet() throws SQLException {
     
     
        String req = "select * from carnet";
     st = cnx.createStatement();
            ResultSet result = st.executeQuery(req);
        
        ObservableList<carnet> mealsList = FXCollections.observableArrayList();
        while (result.next()) {
        carnet c =new carnet();

                c.setId(result.getInt("id"));
              
                c.setAppreciation(result.getString("appreciation"));
                      c.setNote(result.getInt("note"));
               
                      
                      
                 
              
                        c.setDate(result.getString("date"));
                        c.setSemestre(result.getString("semestre"));
  c.setYassine(findbynom(result.getInt("eleve_id")));
          
    
            mealsList.add(c);
          
        }
        return mealsList;
    
      }
            public  ObservableList<String> getUser() throws SQLException {
     
     
        String req = "select nom from eleve";
        
     st = cnx.createStatement();
            ResultSet result = st.executeQuery(req);
        
        ObservableList<String> mealsList = FXCollections.observableArrayList();
    
        while (result.next()) {
                
         String    n=  result.getString("nom");
           
   
            mealsList.add(n);
           
        }
        return mealsList;
    
      }
                public  int getEleves(int idp) throws SQLException {
     
     
        String req = "select * from eleve";
        
     st = cnx.createStatement();
            ResultSet result = st.executeQuery(req);
        
  
        while (result.next()) {
            if(result.getInt("id")==idp){
      
                
    int val=  result.getInt("User_id");
       return val;    

            }     
           
        }
        int val=0;
        return val;
    
      }
                 
                   public  ObservableList<carnet> getcarnets(int idE) throws SQLException {
     
     
        String req = "select * from carnet";
        
     st = cnx.createStatement();
            ResultSet result = st.executeQuery(req);
        
        ObservableList<carnet> mealsList = FXCollections.observableArrayList();
    
        while (result.next()) {
            if(result.getInt("parent")==idE){
            carnet e=new carnet();
                
       e.setId(  result.getInt("id"));
       e.setNote(  result.getInt("note"));
       e.setAppreciation(  result.getString("appreciation"));
       e.setDate(result.getString("date"));
            e.setSemestre(result.getString("semestre"));
             e.setEleve_id(result.getInt("eleve_id"));
                  e.setYassine(findbynom(result.getInt("eleve_id")));

   mealsList.add(e);

            }     
           
        }
        return mealsList;
    
      }
                           public carnet Get_carnet_by_Id(int id) throws SQLException {
   
        String resq = "select * from carnet ";
        pre = cnx.prepareStatement(resq);
     ResultSet   rs = pre.executeQuery();
        while (rs.next()) {
            
            if (rs.getInt("id")==id){   
            carnet p= new carnet();
              p.setId( rs.getInt("id"));
              p.setNote( rs.getInt("note"));
              p.setAppreciation(rs.getString("appreciation"));
                 p.setSemestre(rs.getString("semestre"));
                              
                                   p.setDate( rs.getString("date"));
                                                  
              p.setEleve_id( rs.getInt("eleve_id"));
              
                     
      return p;                      


            }
             
        }
                       

  return new carnet();        
}
             public int findbyeleve(String name) throws SQLException
{
    
String req = "SELECT * FROM eleve";
st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);
            
            while (res.next()) {
                if (res.getString("nom").equals(name))
                {
String val=res.getString("id");

int valeur= Integer.parseInt(val);  

return valeur;
                }
}
          
        int valeur=0;
          return valeur;
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
         
 
          public void Update(carnet c) throws SQLException {
   
                    pre = cnx.prepareStatement("update carnet set note=?,appreciation=?,date=? ,semestre=? where id=?");

                    pre.setInt(1, c.getNote());
                    pre.setString(2, c.getAppreciation());
                      pre.setString(3, c.getDate());
                    pre.setString(4, c.getSemestre());
                    pre.setInt(5, c.getId());
                  
                    pre.executeUpdate();
   
       
        }
           public void DeleteProduit(int id) {
        try {
            String sql = "delete from carnet WHERE id = ?";
            PreparedStatement pstmt = cnx.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Delete Absence Done!");
        } catch (SQLException ex) {
            Logger.getLogger(EleveService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
}
