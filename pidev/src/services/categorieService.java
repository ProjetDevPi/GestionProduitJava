/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Categorie;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.MyConnection;

/**
 *
 * @author Mehdi Tekaya
 */
public class categorieService {
    private Connection con;
    private Statement ste;
    private PreparedStatement pst;

    public categorieService() {
        con = MyConnection.getInstance().getCnx();

    }
   
    public int ajoutercategory(Categorie t) throws SQLException  {

        String requeteInsert = "INSERT INTO `pidev`.`categorie` (`id`, `nomcategorie`) VALUES (null, '" + t.getNomcategorie() + "');";
        int i = 0;   
       try {
           ste = con.createStatement();
           i = ste.executeUpdate(requeteInsert);
           
       } catch (SQLException ex) {
           Logger.getLogger(categorieService.class.getName()).log(Level.SEVERE, null, ex);
       }
       finally{ste.close();}
       
       return i;         
    }

   
    public int deletecategory(int id) throws SQLException  {
        int i = 0;
       try {
           ste=con.createStatement();
           String sql="DELETE FROM `pidev`.`categorie` WHERE id="+id;   
            i=ste.executeUpdate(sql);
       } catch (SQLException ex) {
           Logger.getLogger(categorieService.class.getName()).log(Level.SEVERE, null, ex);
       }
     finally{ste.close();}
      return i;  
    }


    public int updatecategory(Categorie cat) throws SQLException {
         //ste = con.createStatement();
           String requestUpdate="UPDATE `pidev`.`categorie` SET `nomcategorie`=?   WHERE categorie.id=?";       
        PreparedStatement statement =con.prepareStatement(requestUpdate);
        statement.setString(1, cat.getNomcategorie());
        statement.setInt(2,cat.getId());
         /*int i = 0;
       try {
           
           i = ste.executeUpdate(requestUpdate);
       } catch (SQLException ex) {
           Logger.getLogger(Servicecategory.class.getName()).log(Level.SEVERE, null, ex);
       }
//       
         finally{ste.close();}*
                 return i;*/
         return statement.executeUpdate();
    }
  public int getIdatbyName(String name) throws SQLException
    {
        String i="";
        int id=0;
           String query="SELECT id as Id FROM categorie WHERE nomcategorie LIKE '%"+name+"%'";
           ste=con.createStatement();
        ResultSet rst = ste.executeQuery(query); 
         while(rst.next())
        {
             i=rst.getString("Id");
             id=Integer.valueOf(i);
        }
      
        
        return id;
    }
     public String getNamecatbyId(int id) throws SQLException
    { String name="";
           String query="SELECT nomcategorie as nomcategorie FROM categorie WHERE id='"+id+"'";
           ste=con.createStatement();
           
        ResultSet rst = ste.executeQuery(query); 
        while(rst.next())
        {
             name=rst.getString("nomcategorie");
            
        }
      
      return(name);  
    }
 

 
}
