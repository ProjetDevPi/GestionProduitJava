/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Evenement;
import entities.Sponsor;
import java.sql.Connection;
import java.sql.Date;
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
public class EvenementService {
   private Connection cnx;
    private Statement st;
    private PreparedStatement pre;

    public EvenementService() {
        cnx = MyConnection.getInstance().getCnx();
    }
     public void addEvenement(Evenement e) {

        try {
            String req = "INSERT INTO evenement (nom, type,dateevent,nbrpart,nom_image) VALUES "
                    + "('" + e.getNom() + "', '" + e.getType() + "', '" +e.getDateevent()+ "', '" + e.getNbrpart()+ "', '" + e.getNom_image()+ "') ";

            st = cnx.createStatement();
            st.executeUpdate(req);
            
            System.out.println("Insertion Reussie!");

        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }
     public ArrayList<Evenement> afficherAll() {

        ArrayList<Evenement> listP = new ArrayList<>();

        try {

            String req = "SELECT * FROM evenement where dateevent>sysdate()";

            st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);

            while (res.next()) {
               Evenement p = new Evenement();

                p.setId(res.getInt("id"));
                p.setNom(res.getString(2));
                p.setType(res.getString("type"));
                p.setDateevent(res.getDate("dateevent"));                          
                p.setNom_image(res.getString("nom_image"));
                  p.setNbrpart(res.getInt("nbrpart"));
                
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
     public void Update(Evenement P) throws SQLException {
   
                    pre = cnx.prepareStatement("update evenement set nom=?,type=? ,dateevent=?,nbrpart=? where id=?");

                    pre.setString(1, P.getNom());
                    pre.setString(2, P.getType());
                     pre.setDate(3, P.getDateevent());
                     pre.setInt(4,P.getNbrpart());
                    pre.setInt(5, P.getId());
                    
                   
                    pre.executeUpdate();
   
       
        }
      public void DeleteSponsor(int id) {
        try {
            String sql = "delete from evenement WHERE id = ?";
            PreparedStatement pstmt = cnx.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Delete Sponsor Done!");
        } catch (SQLException ex) {
            Logger.getLogger(SponsorService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      //nbrparticipant qui va decrementer
      
      
public Evenement Eventfindbyid(int idd)
{
    Evenement p= new Evenement();
    try {

            String req = "SELECT * FROM evenement";

            st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);

            while (res.next()) {
                if( res.getInt(1)== idd )
                        {
              p.setId(idd)  ;          
                
               p.setNom(res.getString(2));
                p.setType(res.getString("type"));
                p.setDateevent(res.getDate("dateevent"));                          
                p.setNom_image(res.getString("nom_image"));
                  p.setNbrpart(res.getInt("nbrpart"));
                p.setId(Integer.parseInt(res.getString("id")));

               
                
            }
            }
            
            

        } catch (SQLException ex) {
            System.out.println(ex);
        }


    return p;
}

  public Date finddate() throws SQLException{
                  
String req = "SELECT * FROM evenement ";
st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);
            while (res.next()) {
               
            
      Date  date= res.getDate("dateevent");
            
            }
            return null;
              }
  ///////////////
   public Date findbynom(Date date ) throws SQLException{
                  
String req = "SELECT * FROM evenement ";
st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);
            while (res.next()) {
                Date r=res.getDate("dateevent");
                System.out.println(r);
                
                System.out.println("mesmouusa katous");
                if (res.getDate("dateevent").compareTo(date)==0)
                {
            
                    Date  d= res.getDate("dateevent");
                    System.out.println(d);
                  return d;
                  
              }
            }
            return null;
              }
////////////////////////serviceproduit/////////
public void edit(int quantitee,int idd) throws SQLException {
   
                    pre = cnx.prepareStatement("update evenement set nbrpart=? where id=?");

                    pre.setInt(1, quantitee);
                    pre.setInt(2, idd);
                    
                    pre.executeUpdate();
   
       
        }
     

        
}


    