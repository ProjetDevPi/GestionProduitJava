/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.classe;
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
public class EleveService {
    
    
    private Connection cnx;
    private Statement st;
    private PreparedStatement pre;

    public EleveService() {
        cnx = MyConnection.getInstance().getCnx();
    }

    public void ajouuterEleve(eleve e) {

        try {
            String req = "INSERT INTO eleve (nom, prenom,niveau,age,adresse ,User_id,date_naissance,nom_image) VALUES "
                    + "('" + e.getNom() + "', '" + e.getPrenom() + "', '" + e.getNiveau() + "', '" + e.getAge() + "', '" + e.getAdresse() + "', '" + e.getUser_id() + "', '" + e.getDate_naissance() + "', '" + e.getNom_image() + "')";

            st = cnx.createStatement();

            st.executeUpdate(req);

            System.out.println("Insertion Reussie!");

        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }
        public eleve Get_eleve_by_Id(int id) throws SQLException {
   
        String resq = "select * from eleve ";
        pre = cnx.prepareStatement(resq);
     ResultSet   rs = pre.executeQuery();
        while (rs.next()) {
            
            if (rs.getInt("id")==id){   
            eleve p= new eleve();
              p.setId( rs.getInt("id"));
              p.setNom( rs.getString("nom"));
              p.setPrenom(rs.getString("prenom"));
                 p.setNiveau(rs.getString("niveau"));
                                  p.setAdresse(rs.getString("adresse"));
                                   p.setDate_naissance( rs.getString("date_naissance"));
                                                  
              p.setUser_id( rs.getInt("user_id"));
                 p.setAge( rs.getInt("age"));
                  p.setNbre_absence( rs.getInt("nbre_absence"));
                   p.setClasse_id( rs.getInt("classe_id"));
              p.setNom_image(rs.getString("nom_image"));
                     
      return p;                      


            }
             
        }
                       

  return new eleve();        
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
      public  ObservableList<eleve> getEleve() throws SQLException {
     
     
        String req = "select * from eleve";
     st = cnx.createStatement();
            ResultSet result = st.executeQuery(req);
        
        ObservableList<eleve> mealsList = FXCollections.observableArrayList();
        while (result.next()) {
                eleve e = new eleve();

                e.setId(result.getInt("id"));
                e.setNom(result.getString("nom"));
                e.setPrenom(result.getString("prenom"));
                      e.setNiveau(result.getString("Niveau"));
                 e.setAdresse(result.getString("adresse"));
                   e.setNom_image(result.getString("nom_image"));
                      e.setNbre_absence(result.getInt("nbre_absence"));
                        e.setAge(result.getInt("age"));
                 
              
                        e.setDate_naissance(result.getString("date_naissance"));

          
    
            mealsList.add(e);
          
        }
        return mealsList;
    
      }
      public  ObservableList<eleve> getEleve_niveau(String niv) throws SQLException {
     
     
        String req = "select * from eleve";
     st = cnx.createStatement();
            ResultSet result = st.executeQuery(req);
        
        ObservableList<eleve> mealsList = FXCollections.observableArrayList();
        while (result.next()) {
            if (result.getString("Niveau").equals(niv)){
                eleve e = new eleve();

                e.setId(result.getInt("id"));
                e.setNom(result.getString("nom"));
                e.setPrenom(result.getString("prenom"));
                      e.setNiveau(result.getString("Niveau"));
                 e.setAdresse(result.getString("adresse"));
                   e.setNom_image(result.getString("nom_image"));
                      e.setNbre_absence(result.getInt("nbre_absence"));
                        e.setAge(result.getInt("age"));
                 
              
                        e.setDate_naissance(result.getString("date_naissance"));

          
    
           mealsList.add(e);
         
            }  
             
        }
        return mealsList;
    
      }
      public void DeleteProduit(int id) {
        try {
            String sql = "delete from eleve WHERE id = ?";
            PreparedStatement pstmt = cnx.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Delete student Done!");
        } catch (SQLException ex) {
            Logger.getLogger(EleveService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
            public  ObservableList<String> getClasse() throws SQLException {
     
     
        String req = "select nom_classe from classe";
        
     st = cnx.createStatement();
            ResultSet result = st.executeQuery(req);
        
        ObservableList<String> mealsList = FXCollections.observableArrayList();
    
        while (result.next()) {
                
         String    n=  result.getString("nom_classe");
          
   
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
                if (res.getString("nom_classe").equals(name))
                {
String val=res.getString("id");

int valeur= Integer.parseInt(val);  

return valeur;
                }
}
          
        int valeur=0;
          return valeur;
}
               public String findbyclassebyid(int id) throws SQLException
{
    
String req = "SELECT * FROM classe";
st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);
            
            while (res.next()) {
                if (res.getInt("id")==id)
                {
String val=res.getString("nom_classe");



return val;
                }
}
          
      String val="";
          return val;
}
                public classe findnbreE(int id) throws SQLException
{
    
String req = "SELECT * FROM classe";
st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);
            
            while (res.next()) {
                if (res.getInt("id")==id)
                {
                    classe c =new classe();
c.setNbre_eleve(res.getInt("nbre_eleve"));

c.setId(res.getInt("id"));


return c;
                }
}
     return new classe();
}
     public int findidclasse(int id) throws SQLException
{
    
String req = "SELECT * FROM eleve";
st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);
            
            while (res.next()) {
                if (res.getInt("id")==id)
                {


int val = res.getInt("classe_id");


return val;
                }
}
          
   int val =0;
   return val;
}        
            
    public void ajouuterclasse(eleve e) {

        try {
     pre = cnx.prepareStatement("update eleve set classe_id=? where id=?");

                    pre.setInt(1, e.getClasse_id());
               
                    pre.setInt(2, e.getId());
                   
                  
                    pre.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }
    
               
    public void updateC(classe e) {

        try {
     pre = cnx.prepareStatement("update classe set nbre_eleve=? where id=?");

                    pre.setInt(1, e.getNbre_eleve());
               
                    pre.setInt(2, e.getId());
                   
                  
                    pre.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex);
        }

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
         
 
          public void Update(eleve e) throws SQLException {
   
                    pre = cnx.prepareStatement("update eleve set nom=?,prenom=?,niveau=?,age=?,adresse=? ,nom_image=? where id=?");

                    pre.setString(1, e.getNom());
                    pre.setString(2, e.getPrenom());
                       pre.setString(3, e.getNiveau());
                          pre.setInt(4, e.getAge());
                       pre.setString(5, e.getAdresse());
                           pre.setString(6, e.getNom_image());
                    pre.setInt(7, e.getId());
                   
                  
                    pre.executeUpdate();
   
       
        }
          
                public eleve findeleve(int user ) throws SQLException{
                  
String req = "SELECT * FROM eleve ";
st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);
           
            while (res.next()) {
                  eleve e=new eleve();
                if (res.getInt("id")==user)
                {
                   
            
 e.setNbre_absence(res.getInt("classe_id"));
   e.setNom(res.getString("nom"));
      e.setPrenom(res.getString("prenom"));
      e.setUser_id(res.getInt("user_id"));
  e.setId(res.getInt("id"));
           return e;
              }
           
             
                      
            }
           
return new eleve();
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
                          
                public classe findeclasse(int user ) throws SQLException{
                  
String req = "SELECT * FROM classe";
st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);
           
            while (res.next()) {
                  classe c=new classe();
                if (res.getInt("id")==user)
                {
                   
            
 c.setNbre_eleve(res.getInt("nbre_eleve"));
  c.setCapacite(res.getInt("capacite"));
  c.setId(res.getInt("id"));
   return c;
              }
           
             
                      
            }
           
return new classe();
              }
                  
    public void ajouutercap(classe c) {

        try {
     pre = cnx.prepareStatement("update classe set nbre_eleve=? where id=?");

                    pre.setInt(1, c.getNbre_eleve());
               
                    pre.setInt(2, c.getId());
                   
                  
                    pre.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }
          
}
