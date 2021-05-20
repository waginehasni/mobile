/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.gui;

import com.codename1.components.FloatingHint;
import com.codename1.entities.Produits;
import com.codename1.services.ProduitService;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;

/**
 *
 * @author asus
 */
public class ModifierProduitForm extends BaseForm{
    Form current;
    public ModifierProduitForm(Resources res , Produits p,Form prev) {
         super("Produit",BoxLayout.y()); //herigate men Newsfeed w l formulaire vertical
    
        Toolbar tb = new Toolbar(true);
        current = this ;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Modifier");
        getContentPane().setScrollVisible(false);
        tb.addSearchCommand(e ->  {
            
        });
        
        
        super.addSideMenu(res);
        
        TextField tflibelle = new TextField(p.getLibelle()+"", "Libelle" , 20 , TextField.ANY);
        TextField tftype = new TextField(p.getType(), "Type" , 20 , TextField.ANY);
        TextField tfquantite = new TextField(p.getQuantites()+"", "Qauantite" , 20 , TextField.ANY);
         TextField tfprix = new TextField(String.valueOf(p.getPrix()) , "Prix" , 20 , TextField.ANY);
  
        tflibelle.setUIID("NewsTopLine");
        tftype.setUIID("NewsTopLine");
        tfquantite.setUIID("NewsTopLine");
        tfprix.setUIID("NewsTopLine");
        
        tflibelle.setSingleLineTextArea(true);
        tftype.setSingleLineTextArea(true);
        tfquantite.setSingleLineTextArea(true);
        tfprix.setSingleLineTextArea(true);
        
        Button btnModifier = new Button("Modifier");
       btnModifier.setUIID("Button");
       
       //Event onclick btnModifer
       
       btnModifier.addPointerPressedListener(l ->   { 
           
         p.setLibelle(tflibelle.getText());
         p.setType(tftype.getText());
         p.setQuantites((int)Integer.parseInt(tfquantite.getText().toString()));
         p.setPrix((int)Integer.parseInt(tfprix.getText().toString()));
         if(ProduitService.getInstance().modifierProduits(p)){
             Dialog.show("Succes", "Product Updated", new Command("OK"));
             new ProduitsListForm(res).show();
         }
                
        });
       Button btnAnnuler = new Button("Annuler");
       btnAnnuler.addActionListener(e -> {
           prev.showBack();
       });
       
       
       
       Label l2 = new Label("");
       
       Label l3 = new Label("");
       
       Label l4 = new Label("");
       
       Label l5 = new Label("");
       
        Label l1 = new Label();
        
        Container content = BoxLayout.encloseY(
                l1, l2, 
                new FloatingHint(tflibelle),
                createLineSeparator(),
                new FloatingHint(tftype),
                createLineSeparator(),
                new FloatingHint(tfquantite),
                createLineSeparator(),
                new FloatingHint(tfprix),
                createLineSeparator(),
                //etatCombo,
                createLineSeparator(),//ligne de séparation
                btnModifier,
                btnAnnuler
                
               
        );
        
        add(content);
        show();
        
        
    }
    }
