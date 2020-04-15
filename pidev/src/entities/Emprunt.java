/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;

/**
 *
 * @author Mehdi Tekaya
 */
public class Emprunt {
    
    private int id; 
    private int documentid;
    private int id_user;
    private Date dateemprunt; 
    private Date dateretour;
    private String nomdocument;

    public Emprunt() {
       
    }
 
  

    @Override
    public String toString() {
        return "Emprunt{" + "id=" + id + ", documentid=" + documentid + ", id_user=" + id_user + ", dateemprunt=" + dateemprunt + ", dateretour=" + dateretour + ", nomdocument=" + nomdocument + '}';
    }

    public String getNomdocument() {
        return nomdocument;
    }

    public void setNomdocument(String nomdocument) {
        this.nomdocument = nomdocument;
    }
    
    public Emprunt(int id, int documentid, int id_user, Date dateemprunt, Date dateretour) {
        this.id = id;
        this.documentid = documentid;
        this.id_user = id_user;
        this.dateemprunt = dateemprunt;
        this.dateretour = dateretour;
    }

    public Emprunt(int id, Date dateemprunt, Date dateretour, String nomdocument) {
        this.id = id;
        this.dateemprunt = dateemprunt;
        this.dateretour = dateretour;
        this.nomdocument = nomdocument;
    }
    
    public Emprunt(int id, int documentid,String nomdocument, int id_user, Date dateemprunt, Date dateretour) {
        this.id = id;
        this.documentid = documentid;
        this.nomdocument=nomdocument;
        this.id_user = id_user;
        this.dateemprunt = dateemprunt;
        this.dateretour = dateretour;
    }
    public Emprunt(int documentid, int id_user, Date dateemprunt, Date dateretour) {
        
        this.documentid = documentid;
        this.id_user = id_user;
        this.dateemprunt = dateemprunt;
        this.dateretour = dateretour;
    }   
    public Emprunt(String nomdocument, int id_user, Date dateemprunt, Date dateretour) {
        this.nomdocument = nomdocument;
        this.id_user = id_user;
        this.dateemprunt = dateemprunt;
        this.dateretour = dateretour;
    }
    public Emprunt(String nomdocument, Date dateemprunt, Date dateretour) {
        this.nomdocument = nomdocument;
        this.dateemprunt = dateemprunt;
        this.dateretour = dateretour;
    }    
      public Emprunt(int id,String nomdocument, Date dateemprunt, Date dateretour) {
        this.id=id;
          this.nomdocument = nomdocument;
        this.dateemprunt = dateemprunt;
        this.dateretour = dateretour;
    }   
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDocumentid() {
        return documentid;
    }

    public void setDocumentid(int documentid) {
        this.documentid = documentid;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public Date getDateemprunt() {
        return dateemprunt;
    }

    public void setDateemprunt(Date dateemprunt) {
        this.dateemprunt = dateemprunt;
    }

    public Date getDateretour() {
        return dateretour;
    }

    public void setDateretour(Date dateretour) {
        this.dateretour = dateretour;
    }

}
