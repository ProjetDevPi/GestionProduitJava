/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import entities.personne;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import  utils.MyConnection;

/**
 *
 * @author fakhreddine
 */
public class persoService {

    private Connection cnx;
    private Statement st;
    private PreparedStatement pre;

    public persoService() {
        cnx = MyConnection.getInstance().getCnx();
    }

    public void ajouuterPersonne(personne p) {

        try {
            String req = "INSERT INTO personne (nom, prenom, id_login,file,user) VALUES "
                    + "('" + p.getNom() + "', '" + p.getPrenom() + "', '" + p.getIdlogin()+ "', '" + p.getFile() + "', '" + p.getUser() + "')";

            st = cnx.createStatement();

            st.executeUpdate(req);

            System.out.println("Insertion Reussie!");

        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }

    public void ajouuterPersonne1(personne p) {

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

    public ArrayList<personne> afficherAll() {

        ArrayList<personne> listP = new ArrayList<>();

        try {

            String req = "SELECT * FROM personne";

            st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);

            while (res.next()) {
                personne p = new personne();
                String a = res.getString("file");
                System.out.println(a);
                
                  Image image = new Image("file:"+a+"",70, 70, true, true);

ImageView photo= new ImageView(image);

 p.setPhoto(photo);


                p.setId(res.getInt("id"));
                p.setNom(res.getString(2));
                p.setPrenom(res.getString("prenom"));
                p.setFile(res.getString("id_login"));
                p.setFile(res.getString("file"));
                p.setYassine(findbynom(res.getInt("user"))); //attention
                //p.setPrenom(""); pkeey pur solde
                
                
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

    /////////////////////affichage du variable du'on a jouté 'cle etrangére)///////// recuperer le nom 
      public  ObservableList<String> getUser() throws SQLException {
     
     
        String req = "select username from fos_user";
        
     st = cnx.createStatement();
            ResultSet result = st.executeQuery(req);
        
        ObservableList<String> mealsList = FXCollections.observableArrayList();
    
        while (result.next()) {
                
         String    n=  result.getString("username");
          
   
            mealsList.add(n);
              System.out.println(mealsList);
           
        }
        return mealsList;
       
    
      }
      ////////////////////////////////////////////////////////////////////pour l id/////////////
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

System.out.println(valeur);
                }
}
          
        int valeur=0;
          return valeur;
}
            
            
 /////////////////////////////nom///////////////////////////
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
            

 
 
  public void Update(personne P) throws SQLException {
   
                    pre = cnx.prepareStatement("update personne set nom=?,prenom=? where id=?");

                    pre.setString(1, P.getNom());
                    pre.setString(2, P.getPrenom());
                    pre.setInt(3, P.getId());
                   
                  
                    pre.executeUpdate();
   
       
        }
        
  
   
   
  public void DeleteMission(int id) {
        try {
            String sql = "delete from personne WHERE id = ?";
            PreparedStatement pstmt = cnx.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Delete Mission Done!");
        } catch (SQLException ex) {
            Logger.getLogger(persoService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


  
  ////////////////////////////delete//////////////////////
  
 /*/
    public void supprimerReclamation(Personne r) {
        PreparedStatement pt;
        try {
            pt = cnx.prepareStatement("delete from personne where id=?");
            pt.setInt(1, r.getId());
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PersonneService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
/*/
    ///////////////////////////////rECHERCHE////////////////////////
    
      public ArrayList<personne> rechercheTacheparsonnom(String nomchercher) throws SQLException {

       ArrayList<personne> retour = new ArrayList<>();
       Statement stm = cnx.createStatement();
         
        String req = " SELECT a.id id , a.nom nom , a.prenom prenom FROM personne as a "
                + "WHERE a.nom='"+nomchercher+"'";
       // PreparedStatement pstm = cnx.prepareStatement(req);
      //  pstm.setString(1, nomchercher);
        ResultSet resultat = stm.executeQuery(req);
        
        while(resultat.next()){
             int id= resultat.getInt(1);
             String nom = resultat.getString(2);
             String prenom= resultat.getString(3);
            
             //int idP = resultat.getInt(7);
              //String nomProjet=resultat.getString(8);
              //String nomFon=resultat.getString(16);
              personne a = new personne(id,nom,prenom);
             
             //a.setNomprojet(nomProjet);
             //a.setNomfon(nomFon);
  
           retour.add(a);
            
        }
            
        return retour;
        }

      
       public personne Get_Mission_by_Id(int id) throws SQLException {
        ResultSet rs = null;
        PreparedStatement pstmt = null;
       
        String resq = "select * from personne";
        pstmt = cnx.prepareStatement(resq);
        rs = pstmt.executeQuery();
        
        while (rs.next()) {
            
            if(rs.getInt("id")==id)
            {
           
             personne P= new personne();
                     
                    P.setId(rs.getInt("id"));
                    P.setNom(rs.getString("nom"));
                    P.setPrenom(rs.getString("prenom"));
                    P.setIdlogin(rs.getInt("id_login"));
                    P.setFile(rs.getString("file"));
                    P.setUser(rs.getInt("user"));
                    
                    System.out.println(P.getUser());
                    
                
                     return P;
        
            
           
            }

        }
       
        return new personne();
    }
    
}
