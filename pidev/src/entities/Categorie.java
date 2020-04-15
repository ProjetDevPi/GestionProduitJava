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
public class Categorie {
    private int id;
    private String nomcategorie;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomcategorie() {
        return nomcategorie;
    }

  
    public void setNomCategorie(String nomcategorie) {
        this.nomcategorie = nomcategorie;
    }
      @Override
    public String toString() {
        return "Categorie{" + "id=" + id + ", nomcategorie=" + nomcategorie + '}';
    }

    public Categorie(int id, String nomcategorie) {
        this.id = id;
        this.nomcategorie = nomcategorie;
    }

    public Categorie(String nomcategorie) {
        this.nomcategorie = nomcategorie;
    }
    

}
