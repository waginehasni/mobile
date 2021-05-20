/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.entities;

import java.util.Date;

/**
 *
 * @author Yassine
 */
public class Stock {
    int id;
    int idp;
    int quantitesstock;
    Date date ;

    public Stock() {
    }

    public Stock(int id, int idp, int quantitesstock, Date date) {
        this.id = id;
        this.idp = idp;
        this.quantitesstock = quantitesstock;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public int getIdp() {
        return idp;
    }

    public int getQuantitesstock() {
        return quantitesstock;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdp(int idp) {
        this.idp = idp;
    }

    public void setQuantitesstock(int quantitesstock) {
        this.quantitesstock = quantitesstock;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
