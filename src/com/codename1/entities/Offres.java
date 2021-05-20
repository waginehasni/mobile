/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.entities;

/**
 *
 * @author asus
 */
 public class Offres {
    
    private int idOffre;
    private String  nomOffre;
    private String  type;
    private String  description;
   
    public Offres() {
    }
    
    public Offres(int idOffre,String nomOffre,String type,String description) {
        this.idOffre = idOffre;
        this.nomOffre = nomOffre;
        this.type = type;
        this.description = description;
    }

    public Offres(String nomOffre, String type, String description) {
        this.nomOffre = nomOffre;
        this.type = type;
        this.description = description;
    }
    

    public int getIdOffre() {
        return idOffre;
    }

        public String getNomOffre() {
        return nomOffre;
    }

    public String getType() {
        return type;
    }
    
    
    public String getDescription() {
        return description;
    }

    public void setIdOffre(int idOffre) {
        this.idOffre = idOffre;
    }

  
    public void setNomOffre(String nomOffre) {
        this.nomOffre = nomOffre;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDescription(String description) {
        this.description = description;
    }

   

    @Override
    public String toString() {
        return "Offres{" + "idOffre=" + idOffre + ", nomOffre=" + nomOffre + ", type=" + type + ", description=" + description + '}';
    }}
