/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.services;

import com.codename1.io.ConnectionRequest;
import com.codename1.entities.Offres;
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
public class ServiceOffres {
    
    
    public ArrayList<Offres> Offres;
    public static ServiceOffres instance=null;
    private ConnectionRequest req;
    public boolean resultOK;
    private ServiceOffres() {
         req = new ConnectionRequest();
    }
    public static ServiceOffres getInstance() {
        if (instance == null) {
            instance = new ServiceOffres();
        }
        return instance;
    }
    
    public boolean addOffres(Offres u) {
String url = Statics.BASE_URL + "/addoffres/" + u.getNomOffre() + "/" + u.getDescription() + "/" + u.getType();        
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
     
     
     
        public ArrayList<Offres> parseOffres(String jsonText){
        try {
            Offres=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Offres u = new Offres();
                u.setIdOffre((int) Double.parseDouble(obj.get("idoffre").toString()));
                 u.setNomOffre(obj.get("nomoffre").toString());
                u.setDescription(obj.get("description").toString());
                u.setType(obj.get("type").toString());
               
       
                Offres.add(u);
            }
                     
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return Offres;
    }
        
        
    public ArrayList<Offres> getAllOffres(){
        String url = Statics.BASE_URL+"/liste2/";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Offres = parseOffres(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Offres;
    }
    
    public boolean deleteOffres(int idoffre){
        String url = Statics.BASE_URL+"/deleteOffres?idoffre="+idoffre;
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
    public boolean modifierOffres(Offres Offres){
        String url = Statics.BASE_URL+"/updateOffres?idoffre="+Offres.getIdOffre()+"&nomoffre="+Offres.getNomOffre()+"&description="+Offres.getDescription()+"&type="+Offres.getType();
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
