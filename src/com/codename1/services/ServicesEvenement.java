/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.services;
import com.codename1.entities.evenement;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.SimpleDateFormat;
import static com.codename1.media.AsyncMedia.MediaErrorType.Network;
import com.codename1.ui.events.ActionListener;
import com.codename1.utils.Statics;
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
}
