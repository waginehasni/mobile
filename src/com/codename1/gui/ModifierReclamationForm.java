/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.gui;

import com.codename1.components.FloatingHint;
import com.codename1.services.ServiceReclamation;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.esprit.pidev.entities.Reclamation;

/**
 *
 * 
 * @author Lenovo
 */
public class ModifierReclamationForm extends BaseForm {
    
    Form current;
    public ModifierReclamationForm(Resources res , Reclamation r) {
         super("Reclamations",BoxLayout.y()); //herigate men Newsfeed w l formulaire vertical
    
        Toolbar tb = new Toolbar(true);
        current = this ;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Ajout Reclamation");
        getContentPane().setScrollVisible(false);
        
        
        super.addSideMenu(res);
        
        TextField objet = new TextField(r.getTitre(), "Titre" , 20 , TextField.ANY);
        TextField description = new TextField(r.getDescription(), "Description" , 20 , TextField.ANY);
         TextField etat = new TextField(String.valueOf(r.getEtat()) , "Etat" , 20 , TextField.ANY);
  
        objet.setUIID("NewsTopLine");
        description.setUIID("NewsTopLine");
        etat.setUIID("NewsTopLine");
        
        objet.setSingleLineTextArea(true);
        description.setSingleLineTextArea(true);
        etat.setSingleLineTextArea(true);
        
        Button btnModifier = new Button("Modifier");
       btnModifier.setUIID("Button");
       
       //Event onclick btnModifer
       
       btnModifier.addPointerPressedListener(l ->   { 
           if(objet.getText().equals("") || description.getText().equals("")) {
                    Dialog.show("Veuillez vérifier les données","","Annuler", "OK");
            }else{
           r.setTitre(objet.getText());
           r.setDescription(description.getText());
       
       //appel fonction modfier reclamation men service
       
       if(ServiceReclamation.getInstance().modifierReclamation(r)) { // if true
           new ReclamationActif(res).show();
       }
           }
        });
       Button btnAnnuler = new Button("Annuler");
       btnAnnuler.addActionListener(e -> {
           new ReclamationActif(res).show();
       });
       
       
       
       Label l2 = new Label("");

       
        Label l1 = new Label();
        
        Container content = BoxLayout.encloseY(
                l1, l2, 
                new FloatingHint(objet),
                createLineSeparator(),
                new FloatingHint(description),
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
