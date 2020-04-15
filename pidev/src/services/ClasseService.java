/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.absence;
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
public class ClasseService {
     private Connection cnx;
    private Statement st;
    private PreparedStatement pre;
    
    
      public ClasseService() {
        cnx = MyConnection.getInstance().getCnx();
    }

    public void ajouuterClasse(classe c) {

        try {
            String req = "INSERT INTO classe ( nom_classe,nbre_eleve,capacite,salle) VALUES "
                    + "('" + c.getNom_classe() + "', '" + c.getNbre_eleve() + "', '" + c.getCapacite() + "', '" + c.getSalle()+ "')";

            st = cnx.createStatement();

            st.executeUpdate(req);

            System.out.println("Insertion Reussie!");

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
      public  ObservableList<classe> getClasse() throws SQLException {
     
     
        String req = "select * from classe";
     st = cnx.createStatement();
            ResultSet result = st.executeQuery(req);
        
        ObservableList<classe> mealsList = FXCollections.observableArrayList();
        while (result.next()) {
                classe c = new classe();

                c.setId(result.getInt("id"));
                c.setNom_classe(result.getString("nom_classe"));
                c.setNbre_eleve(result.getInt("nbre_eleve"));
                      c.setCapacite(result.getInt("capacite"));
               
                      
          
                        c.setSalle(result.getString("salle"));

          
    
            mealsList.add(c);
          
        }
        return mealsList;
    
      }
      
public int TotalEleve() throws SQLException
{
    
 
    String req = "SELECT *  FROM classe ";

            st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);
            int i=0;
        while (res.next()) {
             
                   i= i+res.getInt("nbre_eleve");
               
            }
            
            
            
            return i;
            
}
          
          
           
 /*** ll affichage*/
             
 
         
 
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
            String sql = "delete from classe WHERE id = ?";
            PreparedStatement pstmt = cnx.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Delete classe Done!");
        } catch (SQLException ex) {
            Logger.getLogger(EleveService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
          
    
}
