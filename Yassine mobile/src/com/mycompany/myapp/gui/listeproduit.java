/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.Services.ProduitService;
import com.mycompany.myapp.entities.Produits;

import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Firas
 */
public class listeproduit extends Form{
Form current;
ArrayList<Produits> data = new ArrayList<>();

public listeproduit(Form previous) {
    setTitle("Listes  Des Produits");
    data = ProduitService.getInstance().getAllCours();
    Container y = new Container(new BoxLayout(BoxLayout.Y_AXIS));

    for (int i=0;i<data.size();i++){
        Container x = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Container xx = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Produits u = new Produits();
        u.setId(data.get(i).getId());
        
        
        u.setLibelle(data.get(i).getLibelle());
        u.setType(data.get(i).getType());
        u.setQuantites(data.get(i).getQuantites());
        u.setPrix(data.get(i).getPrix());
       
        
        Label separation = new Label("----------------------------");
         Button modif = new Button("Modifier");
       Button supp = new Button("Supprimer");
       Button reserver = new Button("Reserver");
        
         
        Label libelle = new Label("libelle: " + data.get(i).getLibelle());
        Label type = new Label("type : "+ data.get(i).getType());
        Label quantites  = new Label("quantites : "+ data.get(i).getQuantites());
        Label prix = new Label("prix : "+ data.get(i).getPrix());
    

        //CheckBox box = new CheckBox();
        
        
//        modif.addActionListener(e -> new ModifierAbonnementForm(current,ab).show());
        supp.addActionListener(new ActionListener(){
        @Override
       public void actionPerformed(ActionEvent evt) {
            ProduitService.getInstance().deleteProduits(u.getId()); 
            Dialog.show("Success", "produit Deleted Successfully.", "OK", "Cancel");
            new HomeForm().show();
       }
       });
         modif.addActionListener(new ActionListener(){
        @Override
       public void actionPerformed(ActionEvent evt) {
             
            new modifierproduit(u);
       }
       });

          
        x.addAll( libelle ,type,quantites,prix,supp,modif);
        //xx.addAll(supp,modif);
        y.addAll(x,xx,separation);
    }
    
    
    
    Button Ajout = new Button("Ajouter produit");
    
    addAll(y);
    getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
                , e-> previous.showBack()); // Revenir vers l'interface pr??c??dente
   getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_ADD, e-> new addproduit(current).show());
}
}

