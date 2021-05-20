/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.SimpleDateFormat;
import static com.codename1.media.AsyncMedia.MediaErrorType.Network;
import com.codename1.ui.events.ActionListener;
import com.mycompany.entity.Reclamation;
import com.mycompany.entity.evenement;
import com.mycompany.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
/**
 *
 * @author hedii
 */
public class ServicesEvenement {
    public static ServicesEvenement instance=null;
    private ConnectionRequest req ; 
    private final ConnectionRequest cr;
    public int resultCode;
    public static ServicesEvenement getInstance()
    {
        if(instance==null)
        {   
            instance = new ServicesEvenement();
                
        }
        return instance;
    }
    public ServicesEvenement()
    {
        cr = new ConnectionRequest();
    }
//    public void ajouterEvenement(evenement ev,int idEvenement)
//    {
//        String Url =Statics.BASE_URL+"/addEvenementJ/"+idEvenement+"?numsalle"+ev.getNum_salle()+"&nomoffre="+ev.getNom_offre()
//                
//                +"&specialite="+ev.getSpecialite()+"&nom="+ev.getNom()+"&datedebut="+ev.getDate_debut()+"&datefin="+ev.getDate_fin();
//      req.setUrl(Url);
//      req.addResponseListener((e)-> {
//          String st = new String (req.getResponseData());
//          System.out.println("data == "+st);
//      });
//      NetworkManager.getInstance().addToQueueAndWait(req);
//    }
    public int addEvenement(evenement m) {
        System.out.println("1");
             cr.setUrl(Statics.BASE_URL + "/addEvenementJ");
       cr.addArgument("numsalle",String.valueOf(m.getNum_salle()));
       System.out.println("2");
        cr.addArgument("nomoffre",m.getNom_offre());
        System.out.println("3");
        
        cr.addArgument("datedebut", m.getDate_debut());
        System.out.println("4");
        cr.addArgument("datefin", m.getDate_fin());
        System.out.println("5");
        
        
        cr.addArgument("specialite", m.getSpecialite());
             System.out.println("6");
         cr.addArgument("nom", m.getNom());
           System.out.println("7");
 
        cr.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                  System.out.println("8");
                resultCode = cr.getResponseCode();
                  System.out.println("9");
                cr.removeResponseListener(this);

            }
        });
          System.out.println("10");
        NetworkManager.getInstance().addToQueueAndWait(cr);
          System.out.println("11");
        return resultCode;
    }
    public ArrayList<Reclamation>AfficherReclamation(int id)
    {
         ArrayList<Reclamation> result = new  ArrayList();
        String Url = Statics.BASE_URL+"AfficheRC_MY_JSON/"+id;
        req.setUrl(Url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp;
                jsonp = new JSONParser();
                try{
                    Map<String,Object>mapReclamation = jsonp.parseJSON(new CharArrayReader(new String (req.getResponseData()).toCharArray()));
                    List<Map<String,Object>>listOfMaps = (List<Map<String,Object>>) mapReclamation.get("root");
                    
                    for(Map<String,Object>obj : listOfMaps)
                    {
                        Reclamation Rec = new Reclamation();
                        
                         //float identifiantSource =Float.parseFloat(obj.get("identifiantSource").toString());
                         String text = obj.get("text").toString();
                         String subject = obj.get("subject").toString();
                        //int identifiantSource = Integer.parseInt(obj.get("identifiantSource").toString());
                        
                        
                        //String dateReclamation = obj.get("dateReclamation").toString().substring(obj.get("dateReclamation").toString().indexOf("timestamp")+10,obj.get("dateReclamation").toString().lastIndexOf("}"));
//                        Date current  = new Date(Double.valueOf(dateReclamation).longValue()*1000);
//                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//                        String dateString = formatter.format(current);
                        
                        
                       // Rec.setIdentifiantSource(identifiantSource);
                        Rec.setSubject(subject);
                        Rec.setText(text);
                        
                        //Rec.setDateReclamation(dateString);
                        result.add(Rec);
                    }
                    
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        
    });
         NetworkManager.getInstance().addToQueueAndWait(req);
        return result;
    }
}
/*
  private void addButton(Image img, String subject,String date,Reclamation r) 
{
       
       int height =Display.getInstance().convertToPixels(11.5f);
       int width =Display.getInstance().convertToPixels(14f);
       
       Button image = new Button(img.fill(width, height));
       image.setUIID("Label");
       Container cnt = BorderLayout.west(image);
       
       Label SubjectTxt = new Label("Subject : "+subject,"NewsTopLine2");
       Label DatetTxt = new Label("Date : "+date,"NewsTopLine2");

       cnt.add(BorderLayout.CENTER,BoxLayout.encloseY(BoxLayout.encloseX(DatetTxt),BoxLayout.encloseX(SubjectTxt)));
       add(cnt);
      
    }


*/