/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;
import javafx.scene.image.ImageView;

/**
 *
 * @author HP
 */
public class Contrat {
    private int id;
    private int numcontrat;
    private int sponsor_id;
    private int evenement_id;
    private String yassine;
    private String event;
    private String image;
    private ImageView photo;
    private Date dateevent;

    public Date getDateevent() {
        return dateevent;
    }

    public void setDateevent(Date dateevent) {
        this.dateevent = dateevent;
    }

    public ImageView getPhoto() {
        return photo;
    }

    public void setPhoto(ImageView photo) {
        this.photo = photo;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
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

    public int getNumcontrat() {
        return numcontrat;
    }

    public void setNumcontrat(int numcontrat) {
        this.numcontrat = numcontrat;
    }

    public int getSponsor_id() {
        return sponsor_id;
    }

    public void setSponsor_id(int sponsor_id) {
        this.sponsor_id = sponsor_id;
    }

    public int getEvenement_id() {
        return evenement_id;
    }

    public void setEvenement_id(int evenement_id) {
        this.evenement_id = evenement_id;
    }
    
}
