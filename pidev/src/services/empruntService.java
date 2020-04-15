/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import entities.Documents;
import entities.Emprunt;
import entities.FosUser;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.MyConnection;
import services.UserSevice;

/**
 *
 * @author Mehdi Tekaya
 */
public class empruntService {
           private ResultSet rs=null;

    private Connection con;
    private Statement ste;
    private PreparedStatement pst;
    
     public empruntService() {
        con = MyConnection.getInstance().getCnx();

    }
    
    public int deleteemprunt(int id) throws SQLException  {
    
        int i=0;
       try {
           ste=con.createStatement();
           String sql1="select documentid from EMPRUNT where id="+id;
           String sql="DELETE FROM `pidev`.`emprunt` WHERE id="+id;   
           
         
        pst=con.prepareStatement(sql1);
        rs=pst.executeQuery();
           System.out.println(id);
           i=ste.executeUpdate(sql);
           while(rs.next())
           {
         PreparedStatement pkk =con.prepareStatement( "UPDATE `pidev`.`document` SET `emprunte` = '0' WHERE `id`= '" + rs.getInt("documentid")+ "'");  
          pkk.executeUpdate(); 
         System.out.println(pkk);
           }
       } catch (SQLException ex) {
           Logger.getLogger(categorieService.class.getName()).log(Level.SEVERE, null, ex);
       }
     finally{ste.close();}
      return i;  
    }
    
public int emprunter(Emprunt e ) throws SQLException {
    
               int i = 0;
UserSevice u= new UserSevice();
int user=Integer.parseInt(u.getlogin());
               try {
            PreparedStatement pre = con.prepareStatement("INSERT INTO `pidev`.`emprunt` ( `documentid` ,`id_user` ,`dateemprunt`, `dateretour` ) VALUES (  ?, ?, ?, ? );");
            PreparedStatement pkk =con.prepareStatement( "UPDATE `pidev`.`document` SET `emprunte` = '1' WHERE `id`= '" + e.getDocumentid()+ "'");

           pre.setInt(1,e.getDocumentid());
            pre.setInt(2,user);

            pre.setDate(3, e.getDateemprunt());
            pre.setDate(4, e.getDateretour());
                        
            pkk.executeUpdate();
            System.out.println(pre);
            System.out.println(pkk);
            i = pre.executeUpdate();
            System.out.println("emprunt ajouté, veuillez consulter votre BD");
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        return i;
}
public int verifemp(Emprunt e) throws SQLException
{
int i=0;
       try {
           ste=con.createStatement();
           String sql="select emprunte from document where id ="+e.getDocumentid();   
           
           //PreparedStatement pkk =con.prepareStatement( "UPDATE `pidev`.`document` SET `emprunte` = '1' WHERE `id`= '" + e.getDocumentid()+ "'");
 
           i=ste.executeUpdate(sql);
           /*pkk.executeUpdate(); 
           System.out.println(pkk);*/
           
       } catch (SQLException ex) {
           Logger.getLogger(categorieService.class.getName()).log(Level.SEVERE, null, ex);
       }
     finally{ste.close();}
      return i;  
}
public int extemp(int id, Date newdate ) throws SQLException {
        int i = 0;
        FileInputStream input = null;
        try {
            PreparedStatement pre = con.prepareStatement("UPDATE  `pidev`.`emprunt` SET `dateretour`=? WHERE `id`='" + id+ "'");

            pre.setDate(1, newdate);
            System.out.println(pre);
            i = pre.executeUpdate();
            System.out.println(" veuillez consulter votre BD");
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        return i;
    }

    public String getmail() throws SQLException {
           ResultSet rs;
       
        
        ste=con.createStatement();
        String mailL = null;
        int i=0;
        
        String req="SELECT mail FROM `login` ";
        rs=ste.executeQuery(req);
         while (rs.next()) {
         
         mailL= rs.getString("mail");
        } 
         return mailL;
            }
}
