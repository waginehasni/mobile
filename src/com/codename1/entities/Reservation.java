/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.entities;

import java.util.Date;

/**
 *
 * @author dell
 */
public class Reservation {
    private int numReservation;
    private int numSalles;
   
    private String  specialite;
    private Date date;
    private String  horraire;
    private String  duree;

    

    public Reservation() {
    
    
}
     public Reservation( int numSalles, String specialite, Date date, String horraire, String duree) {
      
        this.numSalles = numSalles;
        this.specialite = specialite;
        this.date = date;
        this.horraire = horraire;
        this.duree = duree;
    }
  

    public Reservation(int numReservation, int numSalles, String specialite, Date date, String horraire, String duree) {
        this.numReservation = numReservation;
        this.numSalles = numSalles;
        this.specialite = specialite;
        this.date = date;
        this.horraire = horraire;
        this.duree = duree;
    }

    @Override
    public String toString() {
        return "Reservation{" + "numReservation=" + numReservation + ", numSalles=" + numSalles + ", specialite=" + specialite + ", date=" + date + ", horraire=" + horraire + ", duree=" + duree + '}';
    }

    public void setNumReservation(int numReservation) {
        this.numReservation = numReservation;
    }

    public void setNumSalles(int numSalles) {
        this.numSalles = numSalles;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setHorraire(String horraire) {
        this.horraire = horraire;
    }

    public void setDuree(String duree) {
        this.duree = duree;
    }

    public int getNumReservation() {
        return numReservation;
    }

    public int getNumSalles() {
        return numSalles;
    }

    public String getSpecialite() {
        return specialite;
    }

    public Date getDate() {
        return date;
    }

    public String getHorraire() {
        return horraire;
    }

    public String getDuree() {
        return duree;
    }

    
}
