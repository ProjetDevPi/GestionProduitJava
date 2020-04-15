/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Sponsor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.MyConnection;

/**
 *
 * @author HP
 */
public class SponsorService {
    private Connection cnx;
    private Statement st;
    private PreparedStatement pre;

    public SponsorService() {
        cnx = MyConnection.getInstance().getCnx();
    }
     public void addSponsor(Sponsor s) {

        try {
            String req = "INSERT INTO sponsor (nomsponsor, typeprod) VALUES "
                    + "('" + s.getNomsponsor() + "', '" + s.getTypeprod() + "')";

            st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Insertion Reussie!");

        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }
     public String findbynom(int user ) throws SQLException{
                  
String req = "SELECT * FROM sponsor ";
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
      public ArrayList<Sponsor> afficherAll() {

        ArrayList<Sponsor> listP = new ArrayList<>();

        try {

            String req = "SELECT * FROM sponsor";

            st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);

            while (res.next()) {
                Sponsor S = new Sponsor();

                S.setId(res.getInt("id"));
             
               S.setNomsponsor(res.getString(2));
                S.setTypeprod(res.getString("typeprod"));
                 
                
                
                //base attention
                
                System.out.println(S.toString());

                listP.add(S);
               
            }
            
            System.out.println(listP);

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return listP;
    }
public void Update(Sponsor P) throws SQLException {
   
                    pre = cnx.prepareStatement("update sponsor set nomsponsor=?,typeprod=? where id=?");

                    pre.setString(1, P.getNomsponsor());
                    pre.setString(2, P.getTypeprod());
                    pre.setInt(3, P.getId());
                   
                    pre.executeUpdate();
   
       
        }

    public void DeleteSponsor(int id) {
        try {
            String sql = "delete from sponsor WHERE id = ?";
            PreparedStatement pstmt = cnx.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Delete Sponsor Done!");
        } catch (SQLException ex) {
            Logger.getLogger(SponsorService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ObservableList<String> getMeals() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
            
        
    
    
}
