/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.services;

import com.codename1.entities.Cours;
import com.codename1.io.ConnectionRequest;
import com.codename1.entities.Cours;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.codename1.utils.Statics;
import java.util.ArrayList;
import com.codename1.io.JSONParser;
import java.util.Map;
import com.codename1.io.CharArrayReader;
import java.io.IOException;
import java.util.List;
/**
 *
 * @author Firas
 */
public class ServiceCours {
    
    
    public ArrayList<Cours> Cours;
    public static ServiceCours instance=null;
    private ConnectionRequest req;
    public boolean resultOK;
    private ServiceCours() {
         req = new ConnectionRequest();
    }
    public static ServiceCours getInstance() {
        if (instance == null) {
            instance = new ServiceCours();
        }
        return instance;
    }
    
    public boolean addCours(Cours u) {
String url = Statics.BASE_URL + "/addcours/" + u.getNumReservation()+ "/" + u.getNomCours() + "/" + u.getNomCoach()+ "/" + u.getType() + "/" + u.getPrix();        
        System.out.println(url);        
req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200;
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    
//    public boolean updateAbonnement(Abonnement t) {
//        String url = Statics.BASE_URL + "/abonnement/updateAbonnements/"+t.getId_ab()+"?" +"titre="+t.getTitre_ab() + "&type=" + t.getType_ab()+ "&prix=" + t.getPrix_ab()+ "&descr_ab=" + t.getDesc_ab();
//        
//        req.setUrl(url);
//        req.addResponseListener(new ActionListener<NetworkEvent>() {
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//                resultOK = req.getResponseCode() == 200;
//                req.removeResponseListener(this);
//            }
//        });
//        NetworkManager.getInstance().addToQueueAndWait(req);
//        return resultOK;
//    }
//    
//    public boolean deleteAbonnement(Abonnement t) {
//        String url = Statics.BASE_URL + "/abonnement/deleteAbonnements/"+t.getId_ab();
//        req.setUrl(url);
//        req.addResponseListener(new ActionListener<NetworkEvent>() {
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//                resultOK = req.getResponseCode() == 200;
//                req.removeResponseListener(this);
//            }
//        });
//        NetworkManager.getInstance().addToQueueAndWait(req);
//        return resultOK;
//    }
     
     
     
        public ArrayList<Cours> parseCours(String jsonText){
        try {
            Cours=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Cours u = new Cours();
                 u.setNumCours((int) Double.parseDouble(obj.get("numcours").toString()));
                u.setNumReservation((int) Double.parseDouble(obj.get("numreservation").toString()));
                u.setNomCours(obj.get("nomcours").toString());
                u.setNomCoach(obj.get("nomcoach").toString());
                u.setType(obj.get("type").toString());
               
                u.setPrix((int) Double.parseDouble(obj.get("prix").toString()));
       
                Cours.add(u);
            }
                     
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return Cours;
    }
        
        
    public ArrayList<Cours> getAllCours(){
        String url = Statics.BASE_URL+"/cours/liste/";
        System.out.println(url);
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Cours = parseCours(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Cours;
    }
    public boolean deleteCours(int numcours){
        String url = Statics.BASE_URL+"/deletecours?numcours="+numcours;
        req.setUrl(url);
         req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               req.removeResponseCodeListener(this);
            }
        });
         NetworkManager.getInstance().addToQueueAndWait(req);
         return resultOK;
    }
    public boolean modifierCours(Cours Cours){
        String url = Statics.BASE_URL+"/updatecours?numcours="+Cours.getNumCours()+"&numreservation="+Cours.getNumReservation()+"&nomcours="+Cours.getNomCours()+"&nomcoach="+Cours.getNomCoach()+"&type="+Cours.getType()+"&prix="+Cours.getPrix();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
              resultOK=req.getResponseCode() == 200;
              req.removeResponseListener(this);
            }
        });
         NetworkManager.getInstance().addToQueueAndWait(req);
         return resultOK;
    }
}
