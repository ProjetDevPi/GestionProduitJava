/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.logging.Logger;
import javafx.scene.image.ImageView;

/**
 *
 * @author ASUS
 */
public class Produit {
    
    private int id;
    private int categorie;
    private String nom;
    private String prenom;
    private double prix;
    private String description;
    private String nom_image;
    private int quantite;
    private ImageView photo;
    
     private String yassine;

    public String getYassine() {
        return yassine;
    }

    public void setYassine(String yassine) {
        this.yassine = yassine;
    }
    
   

    public Produit(int id, int categorie, String nom, String prenom, double prix, String description, String nom_image, int quantite, ImageView photo) {
        this.id = id;
        this.categorie = categorie;
        this.nom = nom;
        this.prenom = prenom;
        this.prix = prix;
        this.description = description;
        this.nom_image = nom_image;
        this.quantite = quantite;
        this.photo = photo;
    }

    public Produit(int id, int categorie, String nom, String prenom, double prix, String description, String nom_image, int quantite) {
        this.id = id;
        this.categorie = categorie;
        this.nom = nom;
        this.prenom = prenom;
        this.prix = prix;
        this.description = description;
        this.nom_image = nom_image;
        this.quantite = quantite;
    }

    @Override
    public String toString() {
        return "Produit{" + "id=" + id + ", categorie=" + categorie + ", nom=" + nom + ", prenom=" + prenom + ", prix=" + prix + ", description=" + description + ", nom_image=" + nom_image + ", quantite=" + quantite + '}';
    }

    public int getCategorie() {
        return categorie;
    }

    public void setCategorie(int categorie) {
        this.categorie = categorie;
    }

    public Produit(int id, String nom, String prenom, double prix, String description, String nom_image, int quantite) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.prix = prix;
        this.description = description;
        this.nom_image = nom_image;
        this.quantite = quantite;
    }

    public Produit() {
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

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNom_image() {
        return nom_image;
    }

    public void setNom_image(String nom_image) {
        this.nom_image = nom_image;
    }

    public int getQuantite() {
        return quantite;
    }

    public ImageView getPhoto() {
        return photo;
    }
   

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public void setPhoto(ImageView photo) {
        this.photo=photo;
      
    }
    
    
    
    
}
