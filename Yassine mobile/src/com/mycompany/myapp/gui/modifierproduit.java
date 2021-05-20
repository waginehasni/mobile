/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.FloatingHint;
import com.codename1.components.InfiniteProgress;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.Services.ProduitService;
import com.mycompany.myapp.entities.Produits;


/**
 *
 * @author asus
 */
public class modifierproduit extends Form{
    Form current;
    public modifierproduit(Produits produit){
         setTitle("Modifier le produit");
       
        TextField tfid = new TextField(produit.getId());
        TextField tflibelle = new TextField(produit.getLibelle(),"libelle");
        
        TextField tftype = new TextField(produit.getType(),"type");
        
        TextField tfquantites = new TextField(String.valueOf(produit.getQuantites()),"quantites");
         TextField tfprix = new TextField(String.valueOf(produit.getPrix()),"prix");
         
        
        Button btnU = new Button("Update");
        btnU.setUIID("Button");
     btnU.addPointerPressedListener(l->{
         
         produit.setLibelle(tflibelle.getText().toString());
         produit.setType(tftype.getText().toString());
         produit.setQuantites((int)Integer.parseInt(tfquantites.getText().toString()));
                  produit.setPrix((int)Integer.parseInt(tfprix.getText().toString()));

        
     
     
     if(ProduitService.getInstance().modifierProduits(produit)){
         new HomeForm().show();
     }
     
     });
       
        Label l1= new Label();
        Label l2= new Label();
        Label l3= new Label();
        Label l4= new Label();
        Label l5= new Label();
        
        Container content = BoxLayout.encloseY(
                l1,l2,l3,l4,l5,
                
                new FloatingHint(tflibelle),
                new FloatingHint(tftype),
                new FloatingHint(tfquantites),
                new FloatingHint(tfprix),
              
                btnU
        );
                
        
        
        
        add(content);
        show();
        
       
       
    }
    }
    

