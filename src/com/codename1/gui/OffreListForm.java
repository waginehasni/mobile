/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.gui;

import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.entities.Offres;
import com.codename1.services.ServiceOffres;
import com.codename1.social.FacebookConnect;
import com.codename1.social.Login;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import java.util.ArrayList;

/**
 *
 * @author aymen
 */
public class OffreListForm extends BaseForm {
     Form current;
ArrayList<Offres> data = new ArrayList<>();
    public OffreListForm(Resources res) {
        super("Offres", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Offres");
        getContentPane().setScrollVisible(false);
        super.addSideMenu(res);
        current = this ;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Offres");
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
        RadioButton offres = RadioButton.createToggle("Liste des offres", barGroup);
        offres.setUIID("SelectBar");
        RadioButton ajouter = RadioButton.createToggle("Ajouter une offre", barGroup);
        ajouter.setUIID("SelectBar");
        Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");
        

        offres.addActionListener((e) -> {
            InfiniteProgress ip = new InfiniteProgress(); //Loading  after insert data
                
                    final Dialog iDialog = ip.showInfiniteBlocking();
                    
                    iDialog.dispose(); 
                    refreshTheme();//Actualisation
        });
        ajouter.addActionListener((e) -> {
            InfiniteProgress ip = new InfiniteProgress(); //Loading  after insert data
                
                    final Dialog iDialog = ip.showInfiniteBlocking();
                    
                    iDialog.dispose(); 
                    new AjoutOffreForm(res).show();
                    refreshTheme();//Actualisation
        });
        
        add(LayeredLayout.encloseIn(
                GridLayout.encloseIn(2, offres, ajouter),
                FlowLayout.encloseBottom(arrow)
        ));

        offres.setSelected(true);
        arrow.setVisible(false);
        addShowListener(e -> {
            arrow.setVisible(true);
            updateArrowPosition(offres, arrow);
        });
        bindButtonSelection(offres, arrow);
        bindButtonSelection(ajouter, arrow);
        // special case for rotation
        addOrientationListener(e -> {
            updateArrowPosition(barGroup.getRadioButton(barGroup.getSelectedIndex()), arrow);
        });
          Button Facebook = new Button("Facebook");
          addStringValue("", Facebook);
           Facebook.addActionListener(new ActionListener(){
        @Override
       public void actionPerformed(ActionEvent evt) {
             
            String clientId = "1171134366245722";
                String redirectURI = "http://www.codenameone.com/";
                String clientSecret = "XXXXXXXXXXXXXXXXXXXXXXXXXX";
                Login fb = FacebookConnect.getInstance();
                fb.setClientId(clientId);
                fb.setRedirectURI(redirectURI);
                fb.setClientSecret(clientSecret);
                if(!fb.isUserLoggedIn()){
                    fb.doLogin();
                }else{
                    //get the token and now you can query the facebook API
                    String token = fb.getAccessToken().getToken();
                }
       }
       });
   
        //Appel affichage methode
        ArrayList<Offres> list = ServiceOffres.getInstance().getAllOffres();
        
        for(Offres o : list ) {
             String urlImage ="back-logo.jpeg";//image statique pour le moment ba3d taw fi  videos jayin nwarikom image 
            
             Image placeHolder = Image.createImage(120, 90);
             EncodedImage enc =  EncodedImage.createFromImage(placeHolder,false);
             URLImage urlim = URLImage.createToStorage(enc, urlImage, urlImage, URLImage.RESIZE_SCALE);
             
                addButton(urlim,o,res);
        
                ScaleImageLabel image = new ScaleImageLabel(urlim);
                
                Container containerImg = new Container();
                
                image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        }
        
    }
   
    private void addButton(Image img,Offres o , Resources res) {
        
        int height = Display.getInstance().convertToPixels(11.5f);
        int width = Display.getInstance().convertToPixels(14f);
        
        Button image = new Button(img.fill(width, height));
        image.setUIID("Label");
        Container cnt = BorderLayout.west(image);
        
        
        //kif nzidouh  ly3endo date mathbih fi codenamone y3adih string w y5alih f symfony dateTime w ytab3ni cha3mlt taw yjih
        Label objetTxt = new Label("Nom  : "+o.getNomOffre(),"NewsTopLine2");
        Label dateTxt = new Label("Description : "+o.getDescription(),"NewsTopLine2");
        Label etatTxt = new Label("Type : "+o.getType(),"NewsTopLine2" );
        
        createLineSeparator();
       // etatTxt.setText(rec.getEtat());
       
        
        //supprimer button
        Label lSupprimer = new Label(" ");
        lSupprimer.setUIID("NewsTopLine");
        Style supprmierStyle = new Style(lSupprimer.getUnselectedStyle());
        supprmierStyle.setFgColor(0xf21f1f);
        
        FontImage suprrimerImage = FontImage.createMaterial(FontImage.MATERIAL_DELETE, supprmierStyle);
        lSupprimer.setIcon(suprrimerImage);
        lSupprimer.setTextPosition(RIGHT);
        
        //click delete icon
        lSupprimer.addPointerPressedListener(l -> {
            
            Dialog dig = new Dialog("Suppression");
            
            if(dig.show("Suppression","Vous voulez supprimer ce reclamation ?","Annuler","Oui")) {
                dig.dispose();
            }
            else {
                dig.dispose();//n3ayto l suuprimer men service Reclamation
                ServiceOffres.getInstance().deleteOffres(o.getIdOffre()); 
                Dialog.show("Success", "Stade Deleted Successfully.", "OK", "Cancel");
                new OffreListForm(res).show();
                 }
                
           
        });
        
        //Update icon 
        Label lModifier = new Label(" ");
        lModifier.setUIID("NewsTopLine");
        Style modifierStyle = new Style(lModifier.getUnselectedStyle());
        modifierStyle.setFgColor(0xf7ad02);
        
        FontImage mFontImage = FontImage.createMaterial(FontImage.MATERIAL_MODE_EDIT, modifierStyle);
        lModifier.setIcon(mFontImage);
        lModifier.setTextPosition(LEFT);
        
        
        lModifier.addPointerPressedListener(l -> {
            //System.out.println("hello update");
            new modifierForm(res,o,current).show();
            //new ModifierReclamationForm(res,rec).show();
        });
        
        
        cnt.add(BorderLayout.CENTER,BoxLayout.encloseY(
                
                BoxLayout.encloseX(objetTxt),
                BoxLayout.encloseX(dateTxt),
                BoxLayout.encloseX(etatTxt),
                BoxLayout.encloseX(lModifier,lSupprimer)));
        
        
        
        add(cnt);
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
