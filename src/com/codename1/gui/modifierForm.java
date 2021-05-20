/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.gui;

import com.codename1.components.FloatingHint;
import com.codename1.entities.Offres;
import com.codename1.services.ServiceOffres;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;

/**
 *
 * @author asus
 */
public class modifierForm extends BaseForm{
    Form current;
    public modifierForm(Resources res , Offres o,Form prev) {
         super("Reclamations",BoxLayout.y()); //herigate men Newsfeed w l formulaire vertical
    
        Toolbar tb = new Toolbar(true);
        current = this ;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Modifier");
        getContentPane().setScrollVisible(false);
        tb.addSearchCommand(e ->  {
            
        });
        
        
        super.addSideMenu(res);
        
        TextField tfidoffre = new TextField(o.getIdOffre()+"", "ID" , 20 , TextField.ANY);
        TextField tfnom = new TextField(o.getNomOffre(), "Nom" , 20 , TextField.ANY);
        TextField tfdescription = new TextField(o.getDescription(), "Description" , 20 , TextField.ANY);
         TextField tftype = new TextField(String.valueOf(o.getType()) , "Type" , 20 , TextField.ANY);
  
        tfidoffre.setUIID("NewsTopLine");
        tfdescription.setUIID("NewsTopLine");
        tftype.setUIID("NewsTopLine");
        tfnom.setUIID("NewsTopLine");
        
        tfidoffre.setSingleLineTextArea(true);
        tfdescription.setSingleLineTextArea(true);
        tftype.setSingleLineTextArea(true);
        tfnom.setSingleLineTextArea(true);
        
        Button btnModifier = new Button("Modifier");
       btnModifier.setUIID("Button");
       
       //Event onclick btnModifer
       
       btnModifier.addPointerPressedListener(l ->   { 
           
         o.setNomOffre(tfnom.getText());
         o.setDescription(tfdescription.getText());
         o.setType(tftype.getText().toString());
         if(ServiceOffres.getInstance().modifierOffres(o)){
             Dialog.show("Succes", "Offre Updated", new Command("OK"));
             new OffreListForm(res).show();
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
                new FloatingHint(tfidoffre),
                createLineSeparator(),
                new FloatingHint(tfnom),
                createLineSeparator(),
                new FloatingHint(tfdescription),
                createLineSeparator(),
                new FloatingHint(tftype),
                createLineSeparator(),
                //etatCombo,
                createLineSeparator(),//ligne de séparation
                btnModifier,
                btnAnnuler
                
               
        );
        
        add(content);
        show();
        
        
    }
//    public modifierForm(Resources res,Offres Offres){
//         setTitle("Modifier l'offre");
//       
//        TextField tfidoffre = new TextField(Offres.getIdOffre());
//        tfidoffre.setUIID("TextFieldBlack");
//        addStringValue("Id :",tfidoffre);
//        
//        TextField tfnomoffre = new TextField(Offres.getNomOffre(),"nom offre");
//        tfnomoffre.setUIID("TextFieldBlack");
//        addStringValue("Nom :",tfnomoffre);
//        
//        TextField tfdescription = new TextField(Offres.getDescription(),"description");
//        tfdescription.setUIID("TextFieldBlack");
//        addStringValue("Description :",tfdescription);
//        
//        TextField tftype = new TextField(Offres.getType());
//        tftype.setUIID("TextFieldBlack");
//        addStringValue("Type :",tfidoffre);
//        
//        Button btnU = new Button("Update");
//        btnU.setUIID("Button");
//     btnU.addPointerPressedListener(l->{
//         
//         Offres.setNomOffre(tfnomoffre.getText().toString());
//         Offres.setDescription(tfdescription.getText().toString());
//    
//         Offres.setType(tftype.getText().toString());
//       
//     
//     
//     if(ServiceOffres.getInstance().modifierOffres(Offres)){
//         Dialog.show("Succes", "Offre Updated", new Command("OK"));
//         new HomeForm().show();
//     }
//     
//     });
//       
//        Label l1= new Label();
//        Label l2= new Label();
//        Label l3= new Label();
//       
//        
//        Container content = BoxLayout.encloseY(
//                l1,l2,l3,
//                
//                new FloatingHint(tfnomoffre),
//                new FloatingHint(tfdescription),
//                new FloatingHint(tftype),
//            
//                btnU
//        );
//                
//        
//        
//        
//        add(content);
//        show();
//        
//       
//       
//    }
//    private void addStringValue(String s, Component v) {
//        
//        add(BorderLayout.west(new Label(s,"PaddedLabel"))
//        .add(BorderLayout.CENTER,v));
//        add(createLineSeparator(0xeeeeee));
//    }
    }
