/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Objects;
import javafx.scene.image.ImageView;

/**
 *
 * @author ASUS
 */
public class CommandeProduit {
    
    private int id;
    private int produit;
    private int user;
    private int quantite;
    private double prixtotal;
    private String etat;
    private String pay;
    
      private String yassine;
  
    
    private String nom_prod;
    private String nom_client;
    private ImageView photo2;

    public ImageView getPhoto2() {
        return photo2;
    }

    public void setPhoto2(ImageView photo2) {
        this.photo2 = photo2;
    }
    
    

    public String getNom_prod() {
        return nom_prod;
    }

    public void setNom_prod(String nom_prod) {
        this.nom_prod = nom_prod;
    }

    public String getNom_client() {
        return nom_client;
    }

    public void setNom_client(String nom_client) {
        this.nom_client = nom_client;
    }
    
   
    
  

    public String getYassine() {
        return yassine;
    }

    public void setYassine(String yassine) {
        this.yassine = yassine;
    }
    
  

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProduit() {
        return produit;
    }

    public void setProduit(int produit) {
        this.produit = produit;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public double getPrixtotal() {
        return prixtotal;
    }

    public void setPrixtotal(double prixtotal) {
        this.prixtotal = prixtotal;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getPay() {
        return pay;
    }

    public void setPay(String pay) {
        this.pay = pay;
    }

    public CommandeProduit(int id, int produit, int user, int quantite, double prixtotal, String etat, String pay) {
        this.id = id;
        this.produit = produit;
        this.user = user;
        this.quantite = quantite;
        this.prixtotal = prixtotal;
        this.etat = etat;
        this.pay = pay;
    }
    
    public CommandeProduit( int produit, int user, int quantite, double prixtotal, String etat, String pay) {
        
        this.produit = produit;
        this.user = user;
        this.quantite = quantite;
        this.prixtotal = prixtotal;
        this.etat = etat;
        this.pay = pay;
    }

    @Override
    public String toString() {
        return "Commande{" + "id=" + id + ", produit=" + produit + ", user=" + user + ", quantite=" + quantite + ", prixtotal=" + prixtotal + ", etat=" + etat + ", pay=" + pay + ", yassine=" + yassine + '}';
    }

    public CommandeProduit() {
    }
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 73 * hash + this.id;
        hash = 73 * hash + this.produit;
        hash = 73 * hash + this.user;
        hash = 73 * hash + this.quantite;
        hash = 73 * hash + Objects.hashCode(this.prixtotal);
        hash = 73 * hash + Objects.hashCode(this.etat);
        hash = 73 * hash + Objects.hashCode(this.pay);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CommandeProduit other = (CommandeProduit) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.produit != other.produit) {
            return false;
        }
        if (this.user != other.user) {
            return false;
        }
        if (this.quantite != other.quantite) {
            return false;
        }
        if (!Objects.equals(this.prixtotal, other.prixtotal)) {
            return false;
        }
        if (!Objects.equals(this.etat, other.etat)) {
            return false;
        }
        if (!Objects.equals(this.pay, other.pay)) {
            return false;
        }
        return true;
    }
    
    
    
}
