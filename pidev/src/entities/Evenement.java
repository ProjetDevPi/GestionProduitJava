/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;
import java.util.Objects;

/**
 *
 * @author HP
 */
public class Evenement {

    public static Object getSelectionModel() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    private int id;
    private String nom;
    private String type;
    private Date dateevent;
    private int nbrpart;
    private String nom_image;
   

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Evenement other = (Evenement) obj;
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        return true;
    }
    

    public String getNom_image() {
        return nom_image;
    }

    public void setNom_image(String nom_image) {
        this.nom_image = nom_image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDateevent() {
        return dateevent;
    }

    public void setDateevent(Date dateevent) {
        this.dateevent = dateevent;
    }

    public int getNbrpart() {
        return nbrpart;
    }

    public void setNbrpart(int nbrpart) {
        this.nbrpart = nbrpart;
    }

   

    public Evenement() {
    }

    public Evenement(int id, String nom, String type, Date dateevent, int nbrpart) {
        this.id = id;
        this.nom = nom;
        this.type = type;
        this.dateevent = dateevent;
        this.nbrpart = nbrpart;
       
    }

    
    
    
    
}
