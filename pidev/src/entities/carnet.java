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
public class carnet {
    private int id;
    private int note ;
    private String appreciation;
    private String date;
    private int  eleve_id;
    private String semestre;
  private String yassine;
  private int parent;

    public int getParent() {
        return parent;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }

    public String getYassine() {
        return yassine;
    }

    public void setYassine(String yassine) {
        this.yassine = yassine;
    }
    public carnet() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public String getAppreciation() {
        return appreciation;
    }

    public void setAppreciation(String appreciation) {
        this.appreciation = appreciation;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getEleve_id() {
        return eleve_id;
    }

    public void setEleve_id(int eleve_id) {
        this.eleve_id = eleve_id;
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }

    public carnet(int id, int note, String appreciation, String date, int eleve_id, String semestre) {
        this.id = id;
        this.note = note;
        this.appreciation = appreciation;
        this.date = date;
        this.eleve_id = eleve_id;
        this.semestre = semestre;
    }

    @Override
    public String toString() {
        return "carnet{" + "id=" + id + ", note=" + note + ", appreciation=" + appreciation + ", date=" + date + ", eleve_id=" + eleve_id + ", semestre=" + semestre + '}';
    }
    
    
    
}
