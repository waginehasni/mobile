/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.gui;

import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.esprit.pidev.entities.Reclamation;
import com.esprit.pidev.services.ServiceReclamation;

/**
 *
 * @author aymen
 */
public class ViewReclamation extends BaseForm{
 Form current;
    public ViewReclamation(Resources res ,Reclamation r) {
        super("Reclamations",BoxLayout.y()); //herigate men Newsfeed w l formulaire vertical
    
        Toolbar tb = new Toolbar(true);
        current = this ;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Ajout Reclamation");
        getContentPane().setScrollVisible(false);
        
        
        super.addSideMenu(res);
        
              
       Label ltitre = new Label("Titre  : "+r.getTitre(),"NewsTopLine2");
        Label ldesc = new Label("Description : "+r.getDescription(),"NewsTopLine2");
        Label ldateAjout = new Label("Date Reclamation : "+r.getDateRecString(),"NewsTopLine2" );
        Label ldateRep = new Label("Traité le  : "+r.getDateRepString(),"NewsTopLine2");
        Label lRep = new Label("Réponse : "+r.getReponse(),"NewsTopLine2");
        Label lEtat = new Label("Etat : "+r.getEtat(),"NewsTopLine2" );
        Label lSatisfaction = new Label("Etes vous Satisfait ?","NewsTopLine2" );
        
        
 
        //etat bch na3mlo comobbox bon lazm admin ya3mlleha approuver mais just chnwarikom ComboBox
        
        ComboBox etatCombo = new ComboBox();
        
        etatCombo.addItem("Satisfait");
        
        etatCombo.addItem("Non Satisfait");
        
        if("satisfait".equals(r.getSatisfaction()) ) {
            etatCombo.setSelectedIndex(0);
        }
        else 
            etatCombo.setSelectedIndex(1);
        
        
        
        
        
        
        Button btnModifier = new Button("Confirmer");
       btnModifier.setUIID("Button");
       
       //Event onclick btnModifer
       
       btnModifier.addPointerPressedListener(l ->   { 
          
           
           
           if(etatCombo.getSelectedIndex() == 0 ) {
               r.setSatisfaction("satisfait");
           }
           else 
               r.setSatisfaction("non satisfait");
     
       
       //appel fonction modfier reclamation men service
       
       if(ServiceReclamation.getInstance().setSatisfaction(r)) { // if true
           new ReclamationCloture(res).show();
       }
           
        });
       Button btnAnnuler = new Button("Retour");
       btnAnnuler.addActionListener(e -> {
           new ReclamationCloture(res).show();
       });
       
       
       
       Label l2 = new Label("");
       
       Label l3 = new Label("");
       
       Label l4 = new Label("");
       
       Label l5 = new Label("");
       
        Label l1 = new Label();
        
        Container content = BoxLayout.encloseY(
                l1, l2, 
                ltitre,
                ldesc,
                ldateAjout,
                ldateRep,
                lRep,
                lEtat,
                lSatisfaction,
                etatCombo,
                createLineSeparator(),//ligne de séparation
                btnModifier,
                btnAnnuler
                
               
        );
        
        add(content);
        show();
        
        
    }
    
    
    
    
}
