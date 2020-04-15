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
public class absence {
  private  int id;
  private  int eleve_id;
   private String justification;
 private   String etat;
 private   String date;

    public absence() {
    }

    public absence(int id, int eleve_id, String justification, String etat, String date) {
        this.id = id;
        this.eleve_id = eleve_id;
        this.justification = justification;
        this.etat = etat;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEleve_id() {
        return eleve_id;
    }

    public void setEleve_id(int eleve_id) {
        this.eleve_id = eleve_id;
    }

    public String getJustification() {
        return justification;
    }

    public void setJustification(String justification) {
        this.justification = justification;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "absence{" + "id=" + id + ", eleve_id=" + eleve_id + ", justification=" + justification + ", etat=" + etat + ", date=" + date + '}';
    }
    
}
