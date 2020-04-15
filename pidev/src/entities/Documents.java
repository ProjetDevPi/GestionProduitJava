/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Mehdi Tekaya
 */
public class Documents {
  private int id;
  private int categorie;
  private String nomdocument;
  private String nomcategorie;
  private String  etatdocument;
  private int emprunte;
     private String emptxt;
private String nom_image;
    public String getEmptxt() {
        return emptxt;
    }

    public void setEmptxt(String emptxt) {
        this.emptxt = emptxt;
    }

    public Documents(int id, int categorie, String nomdocument, String etatdocument, int emprunte, String nom_image) {
        this.id = id;
        this.categorie = categorie;
        this.nomdocument = nomdocument;
        this.etatdocument = etatdocument;
        this.emprunte = emprunte;
        this.nom_image = nom_image;
    }
    
  

    public Documents(int categorie, String nomdocument, String etatdocument, String nom_image) {
        this.categorie = categorie;
        this.nomdocument = nomdocument;
        this.etatdocument = etatdocument;
        this.nom_image = nom_image;
    }

    public Documents(int id, int categorie, String nomdocument, String etatdocument, String nom_image) {
        this.id = id;
        this.categorie = categorie;
        this.nomdocument = nomdocument;
        this.etatdocument = etatdocument;
        this.nom_image = nom_image;
    }
    public Documents(String nomdocument, int categorie, String etatdocument, int emprunte) {
        
        this.categorie = categorie;
        this.categorie = categorie;
        this.etatdocument = etatdocument;
        this.emprunte = emprunte;
    }    

    public Documents(int id, String nomdocument, String nomcategorie, String etatdocument, int emprunte, String emptxt) {
        this.id = id;
        this.nomdocument = nomdocument;
        this.nomcategorie = nomcategorie;
        this.etatdocument = etatdocument;
        this.emprunte = emprunte;
        this.emptxt = emptxt;
    }

    

  

    @Override
    public String toString() {
        return "Document{" + "id=" + id + ", categorie=" + categorie + ", nomdocument=" + nomdocument + ", etatdocument=" + etatdocument + ", emprunte=" + emprunte + ", nom_image=" + nom_image + '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategorie() {
        return categorie;
    }

    public void setCategorie(int categorie) {
        this.categorie = categorie;
    }

    public String getNomdocument() {
        return nomdocument;
    }

    public void setNomdocument(String nomdocument) {
        this.nomdocument = nomdocument;
    }

    public String getEtatdocument() {
        return etatdocument;
    }

    public void setEtatdocument(String etatdocument) {
        this.etatdocument = etatdocument;
    }

    public int getEmprunte() {
        return emprunte;
    }

    public void setEmprunte(int emprunte) {
        this.emprunte = emprunte;
    }

    public String getNom_image() {
        return nom_image;
    }

    public void setNom_image(String nom_image) {
        this.nom_image = nom_image;
    }

    public Documents(int id, int categorie, String nomdocument, String etatdocument, int emprunte) {
        this.id = id;
        this.categorie = categorie;
        this.nomdocument = nomdocument;
        this.etatdocument = etatdocument;
        this.emprunte = emprunte;
       
    }

    public Documents(int id, String nomdocument, String nomcategorie, String etatdocument, int emprunte) {
        this.id = id;
        this.nomdocument = nomdocument;
        this.nomcategorie = nomcategorie;
        this.etatdocument = etatdocument;
        this.emprunte = emprunte;
    }
    
    public Documents(int id, String nomdocument, int categorie, String etatdocument, String nom_image) {
        this.id = id;
        this.nomdocument = nomdocument;
        this.categorie = categorie;
        this.etatdocument = etatdocument;
        this.nom_image=nom_image;
    }
    
    public String getNomcategorie() {
        return nomcategorie;
    }

    public void setNomcategorie(String nomcategorie) {
        this.nomcategorie = nomcategorie;
    }
    
}
