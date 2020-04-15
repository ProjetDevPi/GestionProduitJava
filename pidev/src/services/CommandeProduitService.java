/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Gui.DetailsCommandePayController;
import entities.CommandeProduit;
import entities.Notif;

import entities.Produit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import utils.MyConnection;

/**
 *
 * @author ASUS
 */
public class CommandeProduitService {
    
    
      private Connection cnx;
    private Statement st;
    private PreparedStatement pre;
    
    
      public CommandeProduitService() {
        cnx = MyConnection.getInstance().getCnx();
    }
      
       public void ajouuterCommande(CommandeProduit p) {
     
        try {
             
           
            
            String req = "INSERT INTO commande1 (produit, user, quantite,prixtotal,etat,pay) VALUES "
                    + "('" + p.getProduit() + "', '" + p.getUser() + "', '" + p.getQuantite()+ "', '" + p.getPrixtotal() + "', '" + p.getEtat() + "', '" + p.getPay()+ "')";

            st = cnx.createStatement();

            st.executeUpdate(req);

            System.out.println("Insertion Reussie!");

        } catch (SQLException ex) {
            System.out.println(ex);
        }
       

    }

       ///////////////////////idloginnnnnnnnnnn////////////////
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
            System.out.println(res.getInt("id"));
      String  name= res.getString("username");
              System.out.println(name);
            return name;
             
              }
            
            }
            return null;
              }
            

 
 ////////////////////////////////////////////////////////
 
  public ArrayList<CommandeProduit> afficherAll() {

        ArrayList<CommandeProduit> listP = new ArrayList<>();

        try {

            String req = "SELECT * FROM commande1";

            st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);

            while (res.next()) {
                CommandeProduit p = new CommandeProduit();
               

                p.setId(res.getInt("id"));
                p.setProduit(res.getInt("produit"));
                
                //p.setCategorie(res.getInt("categorie"));
                p.setYassine(findbynom(res.getInt("user")));
                 p.setPrixtotal(res.getDouble("prixtotal"));
                  p.setQuantite(res.getInt("quantite"));
                 
                 
                //System.out.println(p.getYassine());
                
              System.out.println(findbynom(res.getInt("user")));
                
              
    
                p.setEtat(res.getString("Non validé"));
                p.setPay(res.getString("Non payé"));
                
 
              
 
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

 
  ////////////////////////////////////////
  
  
public List<CommandeProduit> afficherCommande() {

        List<CommandeProduit> listC = new ArrayList<>();

        try {

            String req = "SELECT *  FROM commande1";

            st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);

            while (res.next()) {
                CommandeProduit p = new CommandeProduit();
               ProduitService s1   = new ProduitService();
               
                 ProduitService s2  = new ProduitService();
                     p.setId(Integer.parseInt(res.getString("id")));
                p.setProduit(Integer.parseInt(res.getString("produit")));
                
               p.setUser(Integer.parseInt((res.getString("user"))));
               p.setQuantite(Integer.parseInt(res.getString("quantite")));
               p.setPrixtotal(Double.parseDouble(res.getString("prixtotal")));
               p.setEtat((res.getString("etat")));
               p.setPay((res.getString("pay")));
               p.setNom_client(s1.getusername(p.getUser()));
               p.setNom_prod(s2.getnomprod(p.getProduit()));
               String a=s2.getnomimage(p.getProduit());
               Image image = new Image("file:"+a+"",70, 70, true, true);
                ImageView photo= new ImageView(image);
                p.setPhoto2(photo);
                

                listC.add(p);
            }
            
            

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return listC;
    }

            

   public  ObservableList<CommandeProduit> getcommandes(int idE) throws SQLException {
     
     
         ProduitService s1   = new ProduitService();
               
                 ProduitService s2  = new ProduitService();
        String req = "select * from commande1";
        
     st = cnx.createStatement();
            ResultSet result = st.executeQuery(req);
        
        ObservableList<CommandeProduit> mealsList = FXCollections.observableArrayList();
    
        while (result.next()) {
            if(result.getInt("user")==idE){
            CommandeProduit e=new CommandeProduit();
                
       e.setId(result.getInt("id"));
         e.setProduit(Integer.parseInt(result.getString("produit")));
          e.setPrixtotal(Double.parseDouble(result.getString("prixtotal")));
               e.setEtat((result.getString("etat")));
               e.setPay((result.getString("pay")));
                 e.setQuantite(Integer.parseInt(result.getString("quantite")));
             
      
       
                  e.setYassine(findbynom(result.getInt("user")));
                  
                   e.setNom_prod(s2.getnomprod(e.getProduit()));
               String a=s2.getnomimage(e.getProduit());
               Image image = new Image("file:"+a+"",70, 70, true, true);
                ImageView photo= new ImageView(image);
                e.setPhoto2(photo);
                


   mealsList.add(e);
 
            }     
           
        }
        return mealsList;
  
  
}
   
   
   public void edit(CommandeProduit P) throws SQLException {
   
                    pre = cnx.prepareStatement("update commande1 set etat=? where id=?");

                    pre.setString(1, "Validée");
                   
                    pre.setInt(2, P.getId());
                    pre.executeUpdate();
   
       
        }
   
   
    public void editpaye(int idd) throws SQLException {
   
                    pre = cnx.prepareStatement("update commande1 set pay=? where id=?");

                    pre.setString(1, "Payée");
                    pre.setInt(2, idd);
                    pre.executeUpdate();
   
       
        }
  ///////////////////////////Le nom de l'utilisateurrrrrrrrr Connectéééé (table login)////////////////// 
   
   public String  getloginusername() throws SQLException {
       
           ResultSet rs;
       
        
        st=cnx.createStatement();
        String pseudoL = null;
        int i=0;
        
        String req="SELECT username FROM `login` ";
        rs=st.executeQuery(req);
         while (rs.next()) {
         
         pseudoL= rs.getString("username");
        } 
         return pseudoL;
            }
            
            
     
 
public void editquantite(int idd,int quantitee,Double prix) throws SQLException {
   
                    pre = cnx.prepareStatement("update commande1 set quantite=?,prixtotal=? where id=?");

                    pre.setInt(1, quantitee);
                    pre.setDouble(2, prix);
                    pre.setInt(3, idd);
                    pre.executeUpdate();
   
       
        }




  
  
   public void DeleteCommande(int id) {
        try {
            String sql = "delete from commande1 WHERE id = ?";
            PreparedStatement pstmt = cnx.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Delete Commande Done!");
        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   
  public int NbrProduit() throws SQLException
{
     UserSevice s1 = new UserSevice();
    String req = "SELECT *  FROM commande1 ";

            st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);
            int i=0;
        while (res.next()) {
            //les produits de l'utilisateur connecté
               if(res.getInt(3)==Integer.parseInt(s1.getlogin()))
               {
                   i++;//incrémentation
               }
            }
            
            
            
            return i;
            
}

public Double TotalProduit() throws SQLException
{
    
    UserSevice s1 = new UserSevice();
    String req = "SELECT *  FROM commande1 ";

            st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);
            Double i=0.0;
        while (res.next()) {
               if(res.getInt(3)==Integer.parseInt(s1.getlogin()) && (res.getString("pay").equals("Non Payée")))
               {
                   i= i+res.getDouble("prixtotal");
               }
            }
            
            
            
            return i;
            
} 
  /////////////////////////////mmailuser////////////////////////
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



//////////////////////////////////pour details commande///////////////////////////////////////////////////////

 public CommandeProduit Get_Commande_by_Id(int id) throws SQLException {
        ResultSet result = null;
        PreparedStatement pstmt = null;
        
         ProduitService s1 ,s2  = new ProduitService();
               
                 
       
        String resq = "select * from commande1";
        pstmt = cnx.prepareStatement(resq);
        result = pstmt.executeQuery();
        
        while (result.next()) {
            
            if(result.getInt("id")==id)
            {
           
             CommandeProduit e= new CommandeProduit();
                     
                   e.setId(result.getInt("id"));
         e.setProduit(Integer.parseInt(result.getString("produit")));
          e.setPrixtotal(Double.parseDouble(result.getString("prixtotal")));
               e.setEtat((result.getString("etat")));
               e.setPay((result.getString("pay")));
                 e.setQuantite(Integer.parseInt(result.getString("quantite")));
             
      
       
                  e.setYassine(findbynom(result.getInt("user")));
                  
                   e.setNom_prod(s2.getnomprod(e.getProduit()));
               String a=s2.getnomimage(e.getProduit());
               Image image = new Image("file:"+a+"",70, 70, true, true);
                ImageView photo= new ImageView(image);
                e.setPhoto2(photo);
                
                
                     return e;
        
            
           
            }

        }
       
        return new CommandeProduit();
    }
  
 
 //retourne le prix de la commande telque l id est le meme que l id entrée
 public double findprixbyid(int idd) {

        Double p=0.0;
 
        try {

            String req = "SELECT *  FROM commande1";

            st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);

            while (res.next()) {
                if (Integer.parseInt(res.getString("id"))==idd)
                {
               
                p= Double.parseDouble(res.getString("prixtotal"));
                

                return p;
            }
            }
            
            

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return p;
    }
//////////////////////////////////////////////Notiffffffff/////////////////////////
 public void ajouter_notif() throws SQLException
{    
    double last=DetailsCommandePayController.price;
    CommandeProduitService s1 = new CommandeProduitService();
        String username= s1.getloginusername();
     try {
            
            
            String req3 = "INSERT INTO `notif`(`nom_user`,`prix`) "
                    + "VALUES ('"+username+"','"+last+"') ";
            
         
             st = cnx.createStatement();
             st.executeUpdate(req3);
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
     
 
}

    public void deletenotif() {
        try {
         
 
            
            String req = "DELETE FROM `notif` ";
          st = cnx.createStatement();

            st.executeUpdate(req);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
public List<Notif> afficherNotif() {

        List<Notif> listC = new ArrayList<>();

        try {

            String req = "SELECT *  FROM notif";

            st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);

            while (res.next()) {
                
                {
                    
                Notif p = new Notif();
                p.setId(Integer.parseInt(res.getString("id")));
               p.setNom_user(res.getString("nom_user"));
               p.setPrix(res.getDouble("prix"));
                
             
                listC.add(p);
            }
            }
            

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return listC;
    }
   
}
