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
public class rating {
    private int id;
    private String nom;
    private Double rat;
private int user;
private String commentaire;

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
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

    public Double getRat() {
        return rat;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.id;
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
        final rating other = (rating) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "rating{" + "id=" + id + ", nom=" + nom + ", rat=" + rat + '}';
    }

    public void setRat(Double rat) {
        this.rat = rat;
    }

    public rating() {
    }

    public rating(int id, String nom, Double rat) {
        this.id = id;
        this.nom = nom;
        this.rat = rat;
    }
    
    
}
