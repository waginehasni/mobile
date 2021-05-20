/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.capture.Capture;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.Services.ProduitService;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;

import com.mycompany.myapp.entities.Produits;
import java.io.IOException;
import com.teknikindustries.bulksms.SMS;

/**
 *
 * @author asus
 */
public class addproduit extends Form {

    public addproduit(Form previous) {
        setTitle("Ajouter un nouveau produit");
       
        
        TextField tflibelle = new TextField("","libelle");
        
        TextField tftype = new TextField("","type");
        
        TextField tfquantites = new TextField("","quantites");
        TextField tfprix = new TextField("","prix");
         
        
        Button btnValider = new Button("Add");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                
               
            InfiniteProgress ip = new InfiniteProgress();
            final Dialog idialog = ip.showInfiniteBlocking();
            Produits s = new Produits(String.valueOf(tflibelle.getText()
                    .toString()).toString(),String.valueOf(tftype.getText().toString()).toString()
            ,Integer.parseInt(tfquantites.getText().toString()),
           Integer.parseInt(tfquantites.getText().toString()));
                    System.err.println("data produits=="+s);
                    ProduitService.getInstance().addProduit(s);
                    idialog.dispose();  
                    ToastBar.showErrorMessage("Produit ajoute", FontImage.MATERIAL_ERROR);
                    SMS sms=new SMS();
                 String nt= "+21697747425";
                 sms.SendSMS("yassinetest","Yassinetest123", "Produit ajouté", nt ,"https://bulksms.vsms.net/eapi/submission/send_sms/2/2.0");

        }
            
        });
      
        addAll(tflibelle,tftype,tfquantites,tfprix,btnValider);
        
    }
    
}
