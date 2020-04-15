/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author wejdene
 */
public class classe {
   private int id ;
   private String nom_classe;
   private int nbre_eleve;
   private int capacite;
   private String salle;

    public classe(int id, String nom_classe, int nbre_eleve, int capacite, String salle) {
        this.id = id;
        this.nom_classe = nom_classe;
        this.nbre_eleve = nbre_eleve;
        this.capacite = capacite;
        this.salle = salle;
    }

    public classe() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom_classe() {
        return nom_classe;
    }

    public void setNom_classe(String nom_classe) {
        this.nom_classe = nom_classe;
    }

    public int getNbre_eleve() {
        return nbre_eleve;
    }

    public void setNbre_eleve(int nbre_eleve) {
        this.nbre_eleve = nbre_eleve;
    }

    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    public String getSalle() {
        return salle;
    }

    public void setSalle(String salle) {
        this.salle = salle;
    }

    @Override
    public String toString() {
        return "classe{" + "id=" + id + ", nom_classe=" + nom_classe + ", nbre_eleve=" + nbre_eleve + ", capacite=" + capacite + ", salle=" + salle + '}';
    }
    
}
