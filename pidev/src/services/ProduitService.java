/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;


import Gui.DetailsCommandePayController;
import entities.CommandeProduit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import entities.Produit;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.TimeZone;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import  utils.MyConnection;

/**
 *
 * @author ASUS
 */
public class ProduitService {
    
     private Connection cnx;
    private Statement st;
    private PreparedStatement pre;
    
    
      public ProduitService() {
        cnx = MyConnection.getInstance().getCnx();
    }

    
      public void ajouuterProduit(Produit p) {
     
        try {
            
           

            String req =  "INSERT INTO produit (categorie, nom, prix,description,nom_image,quantite) VALUES "
                    + "('" + p.getCategorie() + "', '" + p.getNom() + "', '" + p.getPrix()+ "', '" + p.getDescription() + "', '" + p.getNom_image() + "' , '" + p.getQuantite() + "')";

            st = cnx.createStatement();

            st.executeUpdate(req);

            System.out.println("Insertion Reussie!");

        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }
      
       public ArrayList<Produit> afficherAll() {

        ArrayList<Produit> listP = new ArrayList<>();

        try {

            String req = "SELECT * FROM produit";

            st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);

            while (res.next()) {
                Produit p = new Produit();
                String a = res.getString("nom_image");
                //System.out.println(a);
                
                 Image image = new Image("file:"+a+"",70, 70, true, true);

ImageView photo= new ImageView(image);
System.out.println(res.getString("nom_image"));

 p.setPhoto(photo);

                p.setId(res.getInt("id"));
                //p.setCategorie(res.getInt("categorie"));
                p.setYassine(findbynom(res.getInt("categorie")));
                 
                //System.out.println(p.getYassine());
                
              System.out.println(findbynom(res.getInt("categorie")));
                
               
                
                p.setNom(res.getString("nom"));
                p.setPrix(res.getDouble("prix"));
                
                p.setDescription(res.getString("description"));
                p.setNom_image(res.getString("nom_image"));
                p.setQuantite(res.getInt("quantite"));
                
                
                
                
                
                //base attention
                
                //System.out.println(p.toString());

                listP.add(p);
                              System.out.println(listP);

                
            }
            
            System.out.println(listP);

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return listP;
    }

         
    
     public void Update(Produit P) throws SQLException {
   
                    pre = cnx.prepareStatement("update produit set categorie=?,nom=? ,prix=? ,description=? ,nom_image=? , quantite=?   where id=?");

                    pre.setInt(1, P.getCategorie());
                    pre.setString(2, P.getNom());
                    pre.setDouble(3, P.getPrix());
                    pre.setString(4, P.getDescription());
                    pre.setString(5, P.getNom_image());
                    pre.setInt(6, P.getQuantite());
                    pre.setInt(7, P.getId());
                    
                  
                    pre.executeUpdate();
                    
                    
                    
 
        }
     
      public void DeleteProduit(int id) {
        try {
            String sql = "delete from produit WHERE id = ?";
            PreparedStatement pstmt = cnx.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Delete Produit Done!");
        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
  //////////////////////////////////////////////////////////////////////////////
   public String findbynom(int categorie ) throws SQLException{
                  
String req = "SELECT * FROM categorie1 ";
st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);
            while (res.next()) {
                if (res.getInt("id")==categorie)
                {
            
      String  name= res.getString("nom");
           return name;
           //System.out.println(name);
            
              }
                
            }
            return null;
              }
   
   
     public  ObservableList<String> getCategorie() throws SQLException {
     
     
        String req = "select nom from categorie1";
        
     st = cnx.createStatement();
            ResultSet result = st.executeQuery(req);
        
        ObservableList<String> mealsList = FXCollections.observableArrayList();
    
        while (result.next()) {
                
         String    n=  result.getString("nom");
          
   
            mealsList.add(n);
           
        }
        return mealsList;
    
      }
     
     public int findbynomcategorie(String name) throws SQLException
{
    
String req = "SELECT * FROM categorie1";
st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);
            
            while (res.next()) {
                //System.out.println(res);
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
 ///////////////////////////////////////////////////////////////////////////////////////////////////////////// 
     
     
      public ArrayList<Produit> Get_Produit_by_Categorie(String nomCategorie) throws SQLException {
          
        ArrayList<Produit> listP = new ArrayList<>();
          try {
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        ProduitService cs = new ProduitService();
        int idcategorie = cs.findbynomcategorie(nomCategorie);
        String resq = "select * from produit where categorie ='" + idcategorie + "'";
        pstmt = cnx.prepareStatement(resq);
        rs = pstmt.executeQuery();
        while (rs.next()) {
                Produit p = new Produit();
                String a = rs.getString("nom_image");
                //System.out.println(a);
                
                 Image image = new Image("file:"+a+"",100, 100, true, true);

ImageView photo= new ImageView(image);
System.out.println(rs.getString("nom_image"));

 p.setPhoto(photo);

                p.setId(rs.getInt("id"));
                //p.setCategorie(res.getInt("categorie"));
                p.setYassine(findbynom(rs.getInt("categorie")));
                 
                //System.out.println(p.getYassine());
                
              System.out.println(findbynom(rs.getInt("categorie")));
                
               
                
                p.setNom(rs.getString("nom"));
                p.setPrix(rs.getDouble("prix"));
                
                p.setDescription(rs.getString("description"));
                p.setNom_image(rs.getString("nom_image"));
                p.setQuantite(rs.getInt("quantite"));
                
                
                
                
                
                //base attention
                
                //System.out.println(p.toString());

                listP.add(p);
                              System.out.println(listP);

                
            }
            
            System.out.println(listP);

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return listP;
}
      
      
       public Produit Get_Mission_by_Id(int id) throws SQLException {
        ResultSet rs = null;
        PreparedStatement pstmt = null;
       
        String resq = "select * from produit";
        pstmt = cnx.prepareStatement(resq);
        rs = pstmt.executeQuery();
        
        while (rs.next()) {
            
            if(rs.getInt("id")==id)
            {
           
             Produit P= new Produit();
                     
                   Produit p = new Produit();
                String a = rs.getString("nom_image");
                //System.out.println(a);
                
                 Image image = new Image("file:"+a+"",100, 100, true, true);

ImageView photo= new ImageView(image);
System.out.println(rs.getString("nom_image"));

 p.setPhoto(photo);

                p.setId(rs.getInt("id"));
                //p.setCategorie(res.getInt("categorie"));
                p.setYassine(findbynom(rs.getInt("categorie")));
                 
                //System.out.println(p.getYassine());
                
              System.out.println(findbynom(rs.getInt("categorie")));
                
               
                
                p.setNom(rs.getString("nom"));
                p.setPrix(rs.getDouble("prix"));
                
                p.setDescription(rs.getString("description"));
                p.setNom_image(rs.getString("nom_image"));
                p.setQuantite(rs.getInt("quantite"));
                
                    
                    
                    
                
                     return P;
        
            
           
            }

        }
       
        return new Produit();
    }  
       
       
       
       
       
       
     
public String getnomprod(int idd) throws SQLException {
           ResultSet rs;
       
        
        st=cnx.createStatement();
        String pseudoL = null;
        
        
        String req="SELECT * FROM `produit` ";
        rs=st.executeQuery(req);
         while (rs.next()) {
         if( rs.getInt("id") ==idd)
             
         pseudoL= rs.getString("nom");
        } 
         return pseudoL;
            }

////////////////////////////////nom userr qui a passée la commande ou on va envoyé un mail///////////////////////

public String getusername(int idd) throws SQLException {
           ResultSet rs;
       
        
        st=cnx.createStatement();
        String pseudoL = null;
        
        
        String req="SELECT * FROM fos_user ";
        rs=st.executeQuery(req);
         while (rs.next()) {
         if( rs.getInt("id") ==idd)
             
         pseudoL= rs.getString("username");
        } 
         System.out.println(pseudoL);
         return pseudoL;
         
            }





public String getnomimage(int idd) throws SQLException {
           ResultSet rs;
       
        
        st=cnx.createStatement();
        String pseudoL = null;
        
        
        String req="SELECT * FROM `produit` ";
        rs=st.executeQuery(req);
         while (rs.next()) {
         if( rs.getInt("id") ==idd)
             
         pseudoL= rs.getString("nom_image");
        } 
         return pseudoL;
            }
    
/////////////////////update quantite prod apres validation commande/////////////////////////////////
public void editproduit(int quantitee,int idd) throws SQLException {
   
                    pre = cnx.prepareStatement("update Produit set quantite=? where id=?");

                    pre.setInt(1, quantitee);
                    pre.setInt(2, idd);
                    
                    pre.executeUpdate();
   
       
        }


////////////////////////////////retourner produit selon id passé en param//////////////////////////////////////////////

public Produit Produitfindbyid(int idd)
{
    Produit p= new Produit();
    
    
    try {

            String req = "SELECT * FROM produit";

            st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);

            while (res.next()) {
                if( res.getInt(1)== idd )
                        {
                p.setId(idd)  ;          
                String a = res.getString("nom_image");
                System.out.println(a);
                Image image = new Image("file:"+a+"",70, 70, true, true);
                ImageView photo= new ImageView(image);
                p.setPhoto(photo);
                p.setNom(res.getString("nom"));
                p.setCategorie(res.getInt(2));
                p.setPrix(res.getDouble(4));
                p.setQuantite(res.getInt(7));
                p.setDescription(res.getString("description"));
                p.setNom_image(res.getString("nom_image"));
                p.setId(Integer.parseInt(res.getString("id")));
                
                
                

               
                
            }
            }
            
            

        } catch (SQLException ex) {
            System.out.println(ex);
        }


    return p;
}


/////////////////////////Revenue///////////////////////////////


public void ajouter_revenu() throws SQLException
{    
    double last=DetailsCommandePayController.price; //variable static
        LocalDateTime last2 = LocalDateTime.now();
     try {
            
            String req = "SELECT * FROM `revenu` ";
            
             st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);
Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Paris"));

            while (res.next()) {
                cal.setTime(res.getDate("date"));
                System.out.println(cal.get(Calendar.DAY_OF_MONTH));
                if (cal.get(Calendar.DAY_OF_MONTH) - last2.getDayOfMonth() !=0 )
                {
                    
                    last=last;
                }
                else 
                {   
                    
                    last +=res.getInt("revenu");
                }
                
            }
            
             String req2 = "delete FROM `revenu` ";
             
             
             st = cnx.createStatement();

            
            st.executeUpdate(req2);
             
            String req3 = "INSERT INTO `revenu`(`revenu`,`date`) "
                    + "VALUES ('"+last+"','"+last2+"') ";
            
         
             st = cnx.createStatement();
             st.executeUpdate(req3);
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
     
 
}
public double revenu() throws SQLException
{
    
            String req = "SELECT * FROM `revenu` ";
            
             st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);
             while (res.next()) {
                 return res.getDouble("revenu");
             }
            return 0.0;
            
}

public int jour() throws SQLException
{
    
            String req = "SELECT * FROM `revenu` ";
            
             st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);
            Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Paris"));
             while (res.next()) {
                  cal.setTime(res.getDate("date"));
                  return cal.get(Calendar.DAY_OF_MONTH);
                  
             }
            return 0;
            
}

public void editrevenu() throws SQLException {
   
                    pre = cnx.prepareStatement("update revenu set revenu=?");

                    pre.setInt(1, 0);
                    
                    pre.executeUpdate();
   
       
        }






}
