/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author Yassine
 */
public class HomeForm extends Form{
    Form current;
    public HomeForm(){
        current = this;
        setTitle("Home Page");
        setLayout(BoxLayout.y());

        add(new Label("Choose an option"));
        Button btnAddTask = new Button("Ajouter un produit");
         Button btnListTasks = new Button("Liste des produits");
         Button btnListfrontTasks = new Button("Liste des produits front");

         
       

        btnAddTask.addActionListener(e -> new addproduit(current).show());
         btnListTasks.addActionListener(e -> new listeproduit(current).show());
                  btnListfrontTasks.addActionListener(e -> new Listproduitfront(current).show());

        addAll( btnAddTask,btnListTasks,btnListfrontTasks);
    }
}
