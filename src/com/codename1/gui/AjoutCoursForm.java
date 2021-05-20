/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.gui;

import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.services.ServiceCours;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Command;
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
import com.codename1.ui.util.Resources;
import com.codename1.ui.validation.RegexConstraint;
import com.codename1.ui.validation.Validator;
import com.codename1.entities.Cours;



/**
 *
 * @author Lenovo
 */
public class AjoutCoursForm extends BaseForm {
    
    
    Form current;
    Validator v = new Validator();
    public AjoutCoursForm(Resources res ) {
        super("Ajouter cours", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Ajouter cours");
        getContentPane().setScrollVisible(false);
        super.addSideMenu(res);
        current = this ;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Ajouter cours");
        getContentPane().setScrollVisible(false);
        
        
        tb.addSearchCommand(e ->  {
            
        });
        
        Tabs swipe = new Tabs();
        
        Label s1 = new Label();
        Label s2 = new Label();
        
        addTab(swipe,s1, res.getImage("back-logo.jpeg"),"","",res);
        
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

        rbs[0].setSelected(true);
        swipe.addSelectionListener((i, ii) -> {
            if (!rbs[ii].isSelected()) {
                rbs[ii].setSelected(true);
            }
        });

        Component.setSameSize(radioContainer, s1, s2);
        add(LayeredLayout.encloseIn(swipe, radioContainer));

        ButtonGroup barGroup = new ButtonGroup();
        RadioButton cours = RadioButton.createToggle("Liste des Cours", barGroup);
        cours.setUIID("SelectBar");
        RadioButton reservations = RadioButton.createToggle(" Réservations ", barGroup);
        reservations.setUIID("SelectBar");
        RadioButton ajouter = RadioButton.createToggle("Ajouter un cours", barGroup);
        ajouter.setUIID("SelectBar");
        Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");
        

        cours.addActionListener((e) -> {;
            InfiniteProgress ip = new InfiniteProgress(); //Loading  after insert data
                
                    final Dialog iDialog = ip.showInfiniteBlocking();
                    
                    iDialog.dispose(); //na7io loading ba3d ma3mlna ajout
                    new CoursListForm(res).show();
                    refreshTheme();//Actualisation
        });
        reservations.addActionListener((e) -> {
            InfiniteProgress ip = new InfiniteProgress(); //Loading  after insert data
                
                    final Dialog iDialog = ip.showInfiniteBlocking();
                    
                    iDialog.dispose(); //na7io loading ba3d ma3mlna ajout
                    new ReservationsListForm(res).show();
                    refreshTheme();//Actualisation
        });

        add(LayeredLayout.encloseIn(
                GridLayout.encloseIn(3, cours, reservations, ajouter),
                FlowLayout.encloseBottom(arrow)
        ));
        

        ajouter.setSelected(true);
        arrow.setVisible(false);
        addShowListener(e -> {
            arrow.setVisible(true);
            updateArrowPosition(ajouter, arrow);
        });
        bindButtonSelection(cours, arrow);
        bindButtonSelection(reservations, arrow);
        bindButtonSelection(ajouter, arrow);
        // special case for rotation
        addOrientationListener(e -> {
            updateArrowPosition(barGroup.getRadioButton(barGroup.getSelectedIndex()), arrow);
        });
        TextField tfnumreservation = new TextField("", "Num reservation");
        tfnumreservation.setUIID("TextFieldBlack");
        addStringValue("Num reservation ",tfnumreservation);
        TextField tfnomcours = new TextField("", "Nom cours");
        tfnomcours.setUIID("TextFieldBlack");
        addStringValue("Nom cours ",tfnomcours);
        TextField tfnomcoach = new TextField("", "Nomcoach");
        tfnomcoach.setUIID("TextFieldBlack");
        addStringValue("Nom coach ",tfnomcoach);
        TextField tftype = new TextField("", "Type");
        tftype.setUIID("TextFieldBlack");
        addStringValue("Type ",tftype);
        TextField tfprix = new TextField("", "Prix");
        tfprix.setUIID("TextFieldBlack");
        addStringValue("Prix ",tfprix);
        Button btnValider = new Button("Ajouter Cours");
        addStringValue("", btnValider);
        
        v.addConstraint(tfnumreservation, new RegexConstraint("[1-9]", "Format NomCours Invalide"));
        v.addConstraint(tfnomcours, new RegexConstraint("[a-zA-Z]", "Format NomCours Invalide"));
        v.addConstraint(tfnomcoach, new RegexConstraint("[a-zA-Z]", "Format Prénom Invalide"));
        v.addConstraint(tftype, new RegexConstraint("[a-zA-Z]", "Format E-mail Invalide"));
        v.addConstraint(tfprix, new RegexConstraint("[1-9]", "Format E-mail Invalide"));
        v.addSubmitButtons(btnValider);
         btnValider.addActionListener((e) -> {
             try{
             if ((tfnumreservation.getText().length()==0)||(tfnomcours.getText().length()==0)||(tfnomcoach.getText().length()==0)||(tftype.getText().length()==0)|(tfprix.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                        Cours u = new Cours(Integer.parseInt(tfnumreservation.getText()), tfnomcours.getText(), tfnomcoach.getText(), tftype.getText(),Integer.parseInt(tfprix.getText()) );
                        InfiniteProgress ip = new InfiniteProgress(); //Loading  after insert data
                        final Dialog iDialog = ip.showInfiniteBlocking();
                        if( ServiceCours.getInstance().addCours(u)){
                            iDialog.dispose();
                            Dialog.show("Success","Cours Ajoutée",new Command("OK"));
                         }
                       
                        else{
                            iDialog.dispose();
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                        }
                        tfnumreservation.setText("");
                        tfnomcours.setText("");
                        tfnomcoach.setText("");
                        tfprix.setText("");
                        tftype.setText("");
                         refreshTheme();//Actualisation
                } 
                        
             }catch(Exception ex ) {
                Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                ex.printStackTrace();
            }
         });
        
    }
        private void addStringValue(String s, Component v) {
        
        add(BorderLayout.west(new Label(s,"PaddedLabel"))
        .add(BorderLayout.CENTER,v));
        add(createLineSeparator(0xeeeeee));
    }

    private void addTab(Tabs swipe, Label spacer , Image image, String string, String text, Resources res) {
        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());
        
        if(image.getHeight() < size) {
            image = image.scaledHeight(size);
        }
        
        
        
        if(image.getHeight() > Display.getInstance().getDisplayHeight() / 2 ) {
            image = image.scaledHeight(Display.getInstance().getDisplayHeight() / 2);
        }
        
        ScaleImageLabel imageScale = new ScaleImageLabel(image);
        imageScale.setUIID("Container");
        imageScale.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        
        Label overLay = new Label("","ImageOverlay");
        
        
        Container page1 = 
                LayeredLayout.encloseIn(
                imageScale,
                        overLay,
                        BorderLayout.south(
                        BoxLayout.encloseY(
                        new SpanLabel(text, "LargeWhiteText"),
                                        spacer
                        )
                    )
                );
        
        swipe.addTab("",res.getImage("back-logo.jpeg"), page1);
        
        
        
        
    }
    
    
    
    public void bindButtonSelection(Button btn , Label l ) {
        
        btn.addActionListener(e-> {
        if(btn.isSelected()) {
            updateArrowPosition(btn,l);
        }
    });
    }

    private void updateArrowPosition(Button btn, Label l) {
        
        l.getUnselectedStyle().setMargin(LEFT, btn.getX() + btn.getWidth()  / 2  - l.getWidth() / 2 );
        l.getParent().repaint();
    }
}
