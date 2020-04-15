/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author HP
 */
public class Sponsor {
    private int id;
    private String nomsponsor;
    private String typeprod;
    

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomsponsor() {
        return nomsponsor;
    }

    public void setNomsponsor(String nomsponsor) {
        this.nomsponsor = nomsponsor;
    }

    public String getTypeprod() {
        return typeprod;
    }

    public void setTypeprod(String typeprod) {
        this.typeprod = typeprod;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + this.id;
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
        final Sponsor other = (Sponsor) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    public Sponsor() {
    }

    public Sponsor(int id, String nomsponsor, String typeprod) {
        this.id = id;
        this.nomsponsor = nomsponsor;
        this.typeprod = typeprod;
    }

    public Sponsor(String nomsponsor, String typeprod) {
        this.nomsponsor = nomsponsor;
        this.typeprod = typeprod;
    }
    
    
    
}
