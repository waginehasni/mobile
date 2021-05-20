/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entity;
 
import java.util.Date;


/**
 *
 * @author infoevo
 */
public class evenement {

    public evenement(int num_salle, String nom_offre, String specialite, String nom) {
        this.num_salle = num_salle;
        this.nom_offre = nom_offre;
        this.specialite = specialite;
        this.nom = nom;
    }
     private int id_evenement;
    private int num_salle;
    private String nom_offre;

    private String date_debut;
    private String date_fin;
     private String specialite;
          private String nom;


    public evenement() {
    }

    public evenement(int num_salle, String nom_offre, String date_debut, String date_fin, String specialite) {
        this.num_salle = num_salle;
        this.nom_offre = nom_offre;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.specialite = specialite;
    }
   
     public evenement(int num_salle, String nom_offre, String date_debut, String date_fin, String specialite, String nom) {
        this.num_salle = num_salle;
        this.nom_offre = nom_offre;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.specialite = specialite;
        this.nom = nom;
    }

    public evenement(int id_evenement, int num_salle, String nom_offre, String date_debut, String date_fin, String specialite) {
        this.id_evenement = id_evenement;
        this.num_salle = num_salle;
        this.nom_offre = nom_offre;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.specialite = specialite;
    }

    public evenement(int aInt, int aInt0, String string, String date, String date0, String string0, String string1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   

    public int getId_evenement() {
        return id_evenement;
    }

    public int getNum_salle() {
        return num_salle;
    }

    public String getNom_offre() {
        return nom_offre;
    }

    public String getDate_debut() {
        return date_debut;
    }

    public String getDate_fin() {
        return date_fin;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setId_evenement(int id_evenement) {
        this.id_evenement = id_evenement;
    }

    public void setNum_salle(int num_salle) {
        this.num_salle = num_salle;
    }

    public void setNom_offre(String nom_offre) {
        this.nom_offre = nom_offre;
    }

    public void setDate_debut(String date_debut) {
        this.date_debut = date_debut;
    }

    public void setDate_fin(String date_fin) {
        this.date_fin = date_fin;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

   
    

    @Override
    public String toString() {
        return "evenement{" + "id_evenement=" + id_evenement + ", num_salle=" + num_salle + ", nom_offre=" + nom_offre + ", date_debut=" + date_debut + ", date_fin=" + date_fin + ", specialite=" + specialite + '}';
    }
     

    
    
}

