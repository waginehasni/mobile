/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.spinner.DateSpinner;
import com.codename1.ui.util.Resources;
import com.mycompany.entity.Reclamation;
import com.mycompany.entity.evenement;
import com.mycompany.services.ServicesEvenement; 
import static java.lang.Integer.parseInt;
import java.util.Date;

/**
 *
 * @author hedii
 */
public class AjoutEvenementForm extends BaseForm{
    Form current;
    public AjoutEvenementForm(Resources Res)
    {
        super("Newfeed",BoxLayout.y());
        Toolbar Tb = new Toolbar(true);
        current = this ; 
        setToolbar(Tb);
        getTitleArea().setUIID("Container");
        setTitle("Ajouter Evenement ");
        getContentPane().setScrollVisible(false);
        Tb.addSearchCommand(e->{
            
        });
        Tabs swipe = new Tabs();
        Label s1 = new Label();
        Label s2 = new Label();
        
//        addTab(swipe,s1,Res.getImage("img-05.jpg"),"","",Res);
        //
        
        swipe.setUIID("Container");
        swipe.getContentPane().setUIID("Container");
        swipe.hideTabs();

        ButtonGroup bg = new ButtonGroup();
        int size = Display.getInstance().convertToPixels(1);
        Image unselectedWalkthru = Image.createImage(size, size, 0);
        Graphics g = unselectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAlpha(100);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        Image selectedWalkthru = Image.createImage(size, size, 0);
        g = selectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        RadioButton[] rbs = new RadioButton[swipe.getTabCount()];
        FlowLayout flow = new FlowLayout(CENTER);
        flow.setValign(BOTTOM);
        Container radioContainer = new Container(flow);
        for (int iter = 0; iter < rbs.length; iter++) {
            rbs[iter] = RadioButton.createToggle(unselectedWalkthru, bg);
            rbs[iter].setPressedIcon(selectedWalkthru);
            rbs[iter].setUIID("Label");
            radioContainer.add(rbs[iter]);
        }

//        rbs[0].setSelected(true);
        swipe.addSelectionListener((i, ii) -> {
            if (!rbs[ii].isSelected()) {
                rbs[ii].setSelected(true);
            }
        });

        Component.setSameSize(radioContainer, s1, s2);
        add(LayeredLayout.encloseIn(swipe, radioContainer));

        ButtonGroup barGroup = new ButtonGroup();
        RadioButton mesListes = RadioButton.createToggle("Mes evenements", barGroup);
        mesListes.setUIID("SelectBar");
        RadioButton liste = RadioButton.createToggle("Autres", barGroup);
        liste.setUIID("SelectBar");
        RadioButton partage = RadioButton.createToggle("Reclamer", barGroup);
        partage.setUIID("SelectBar");
        Label arrow = new Label(Res.getImage("news-tab-down-arrow.png"), "Container");


        mesListes.addActionListener((e) -> {
               InfiniteProgress ip = new InfiniteProgress();
        final Dialog ipDlg = ip.showInifiniteBlocking();
        
        //  ListReclamationForm a = new ListReclamationForm(Res);
           // a.show();
            refreshTheme();
        });

        add(LayeredLayout.encloseIn(
                GridLayout.encloseIn(3, mesListes, liste, partage),
                FlowLayout.encloseBottom(arrow)
        ));

        partage.setSelected(true);
        arrow.setVisible(false);
        addShowListener(e -> {
            arrow.setVisible(true);
            updateArrowPosition(partage, arrow);
        });
        bindButtonSelection(mesListes, arrow);
        bindButtonSelection(liste, arrow);
        bindButtonSelection(partage, arrow);
        // special case for rotation
        addOrientationListener(e -> {
            updateArrowPosition(barGroup.getRadioButton(barGroup.getSelectedIndex()), arrow);
        });

       
        
        //
        DateSpinner date = new DateSpinner();
         date.setUIID("datedebut");
        addStringValue("datedebut",date);
         DateSpinner date1 = new DateSpinner();
         date1.setUIID("datefin");
        addStringValue("datefin",date1);
        TextField numsalle = new TextField(""," num salle");
        numsalle.setUIID("TextFieldBlack");
        addStringValue("numsalle",numsalle);
        
        TextField nomoffre = new TextField("","nom offre");
        nomoffre.setUIID("TextFieldBlack");
        addStringValue("nomoffre",nomoffre);
        
        
        
        TextField specialite = new TextField("","specialite");
        specialite.setUIID("TextFieldBlack");
        addStringValue("specialite",specialite);
        
        TextField nom= new TextField("","nom ");
        nom.setUIID("TextFieldBlack");
        addStringValue("nom",nom);
        
        
        Button Btn = new Button("Ajouter");
        addStringValue("",Btn);
        
        Btn.addActionListener((e)->{
            try {
                if(specialite.getText().equals("") || nom.getText().equals(""))
                    Dialog.show("Veuillez Verfier les données","","Annuler","OK");
                else
                    { System.out.println("522");
                        String day = String.valueOf(date.getCurrentDay());
                        String month = String.valueOf(date.getCurrentMonth());
                        String year = String.valueOf(date.getCurrentYear());
                        
                        InfiniteProgress ip = new InfiniteProgress();
                        final Dialog iDialog = ip.showInfiniteBlocking(); 
//                        evenement ev   = new evenement(
//                                
//                             parseInt(numsalle.getText()), 
//                                nomoffre.getText(), 
//                                day + "/" + month + "/" + year,
//                                day + "/" + month + "/" + year,
//                               specialite.getText(), 
//                               nom.getText());
//                              
                     
                       // System.out.println("data reclamation ====="+rec);
                        System.out.println("cccc");
                       
                         evenement ev   = new evenement(1,"aaaa","test","abc");
                         System.out.println("bbb");
                        ServicesEvenement.getInstance().addEvenement(ev);
                        System.out.println("a");
                        iDialog.dispose(); 
                         
                        refreshTheme();
                    }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        
    }

    private void addStringValue(String s, Component  v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).add(BorderLayout.CENTER,v));
        add(createLineSeparator(0xeeeeee));
        
    }

    private void addTab(Tabs swipe,Label spacer, Image image, String string, String text, Resources Res) {
        int size= Math.min(Display.getInstance().getDisplayWidth(),Display.getInstance().getDisplayHeight());
        
        if(image.getHeight()<size)
        {
            image= image.scaledHeight(size);
        }
         if(image.getHeight()>Display.getInstance().getDisplayHeight()/2)
        {
            image= image.scaledHeight(Display.getInstance().getDisplayHeight()/2);
        }
         ScaleImageLabel imageScale = new ScaleImageLabel(image);
         imageScale.setUIID("container");
         imageScale.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
         Label overlay = new Label("","ImageOverlay");
         Container page1 = 
                 LayeredLayout.encloseIn(
                         imageScale,
                         overlay,
                         BorderLayout.south(
                                 BoxLayout.encloseY(
                                         new SpanLabel(text,"LargeWhiteText"),
                                         spacer
                                 )
                         )
                 );
         swipe.addTab("",Res.getImage("img-05.jpg"),page1);
    }
    public void bindButtonSelection(Button Btn , Label l)
    {
        Btn.addActionListener(e->{
        if(Btn.isSelected())
        {
            updateArrowPosition(Btn,l);
        }
        });
    }

    private void updateArrowPosition(Button Btn, Label l) {
        l.getUnselectedStyle().setMargin(LEFT,Btn.getX()+Btn.getWidth()/2 - l.getWidth() / 2);
        l.getParent().repaint();
    }
    
    
}
