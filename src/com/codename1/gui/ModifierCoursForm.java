/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.gui;

import com.codename1.components.FloatingHint;
import com.codename1.entities.Cours;
import com.codename1.services.ServiceCours;
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
public class ModifierCoursForm extends BaseForm{
    Form current;
    public ModifierCoursForm(Resources res , Cours cours,Form prev) {
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
        
        
        TextField tfnumcours = new TextField(cours.getNumCours()+"", "Numero cours" , 20 , TextField.ANY);
        TextField tfnumreservation = new TextField(cours.getNumReservation()+"", "Numero réservation" , 20 , TextField.ANY);
        TextField tfnomcours = new TextField(cours.getNomCours()+"", "Nom cours" , 20 , TextField.ANY);
        TextField tfnomcoach = new TextField(cours.getNomCoach() , "Nom coach" , 20 , TextField.ANY);
        TextField tftype = new TextField(cours.getType() , "Type" , 20 , TextField.ANY);
        TextField tfprix = new TextField(String.valueOf(cours.getPrix()) , "Prix" , 20 , TextField.ANY);
  
        tfnumcours.setUIID("NewsTopLine");
        tfnumreservation.setUIID("NewsTopLine");
        tfnomcours.setUIID("NewsTopLine");
        tfnomcoach.setUIID("NewsTopLine");
        tftype.setUIID("NewsTopLine");
        tfprix.setUIID("NewsTopLine");
        
        tfnumcours.setSingleLineTextArea(true);
        tfnumreservation.setSingleLineTextArea(true);
        tfnomcours.setSingleLineTextArea(true);
        tfnomcoach.setSingleLineTextArea(true);
        tftype.setSingleLineTextArea(true);
        tfprix.setSingleLineTextArea(true);
        
        Button btnModifier = new Button("Modifier");
       btnModifier.setUIID("Button");
       
       //Event onclick btnModifer
       
       btnModifier.addPointerPressedListener(l ->   { 
         cours.setNumReservation((int)Integer.parseInt(tfnumreservation.getText().toString()));
         cours.setNomCours(tfnomcours.getText().toString());
         cours.setNomCoach(tfnomcoach.getText().toString());
         cours.setType(tftype.getText().toString());
         cours.setPrix((int)Integer.parseInt(tfprix.getText().toString()));
         if(ServiceCours.getInstance().modifierCours(cours)){
             Dialog.show("Succes", "Cours Updated", new Command("OK"));
             new CoursListForm(res).show();
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
                new FloatingHint(tfnumcours),
                createLineSeparator(),
                new FloatingHint(tfnumreservation),
                createLineSeparator(),
                new FloatingHint(tfnomcours),
                createLineSeparator(),
                new FloatingHint(tfnomcoach),
                createLineSeparator(),
                new FloatingHint(tftype),
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
