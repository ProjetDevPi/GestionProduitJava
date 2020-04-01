/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import javafx.scene.image.ImageView;

/**
 *
 * @author fakhreddine
 */
public class personne {
    
    private int id;
    private String nom;
    private String prenom;
    private int idlogin; //avec fos user
    private String file;
    private int user;  //drinks
     private ImageView photo;

    
    private String yassine;

    public personne(int aInt, String string, String string0, int aInt0) {
       
    }

    public personne(int aInt, String string, String string0, int aInt0, String string1, int aInt1) {
    }

   

    public String getYassine() {
        return yassine;
    }

    public void setYassine(String yassine) {
        this.yassine = yassine;
    }
   
   

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public int getIdlogin() {
        return idlogin;
    }

    public void setIdlogin(int idlogin) {
        this.idlogin = idlogin;
    }

    public personne() {
          this.idlogin=idlogin;
    }

    public personne(int id, String nom, String prenom,int idlogin,int user) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.idlogin=idlogin;
        this.user=user;
    }
    
     public personne(int id, String nom, String prenom) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
       
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
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

    @Override
    public String toString() {
        return "Personne{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + '}';
    }

    public void setPhoto(ImageView photo) {
    }
    
    
    
}
