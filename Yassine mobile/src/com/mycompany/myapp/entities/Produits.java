/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author Yassine
 */
public class Produits {
      private int id ; 
      private String libelle ;
      private String type   ;
      private int quantites ;
      private int prix ; 

    public Produits() {
    }
      
      
      
      
     

    public Produits(int id, String libelle, String type, int quantites , int prix ) {
        this.id = id;
        this.libelle = libelle;
        this.type = type;
        this.quantites = quantites;
        this.prix=prix;
        
      
    }

    public Produits(String libelle, String type, int quantites, int prix) {
        this.libelle = libelle;
        this.type = type;
        this.quantites = quantites;
        this.prix = prix;
    }



   

    public int getId() {
        return id;
    }

    public String getLibelle() {
        return libelle;
    }

    public int getQuantites() {
        return quantites;
    }

    public String getType() {
        return type;
    }

    public int getPrix() {
        return prix;
    }
    

    

    public void setId(int id) {
        this.id = id;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public void setQuantites(int quantites) {
        this.quantites = quantites;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "Produits{" + "id=" + id + ", libelle=" + libelle + ", type=" + type + ", quantites=" + quantites + ", prix=" + prix + '}';
    }
    

   
    
    
    
      
      
      
      
    
}
