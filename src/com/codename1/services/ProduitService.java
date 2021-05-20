/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.services;

import com.codename1.entities.Produits;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;

import com.codename1.ui.events.ActionListener;
import com.codename1.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;

/**
 *
 * @author Yassine
 */
public class ProduitService {
    public ArrayList<Produits> produits;
    public static ProduitService instance = null;
    private ConnectionRequest req;
    public static boolean resultOK;
    
    
    public static ProduitService getInstance(){
        if(instance==null)
            instance = new ProduitService();
         return instance;
        
    }
    
    
    public ProduitService(){
        req = new ConnectionRequest();
    }
    public void addProduit(Produits produit){
        String url = Statics.BASE_URL+"/add?libelle="+produit.getLibelle()+"&type="+produit.getType()+"&quantites="+produit.getQuantites()+"&prix="+produit.getPrix();
        req.setUrl(url);
        req.addResponseListener(a->{
            String str = new String(req.getResponseData());
            System.err.println("data=="+str);
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
  
    public ArrayList<Produits> Afficherproduit(){
        ArrayList<Produits> result = new ArrayList<>();
        String url = Statics.BASE_URL+"/liste";
        req.setUrl(url);
        req.setPost(false);
        req.setDuplicateSupported(true);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp;
                jsonp = new JSONParser();
                try{
                    Map<String,Object>mapProduit=jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    List< Map<String,Object>> listOfMap = (List< Map<String,Object>>) mapProduit.get("root");
                    
                    for(Map<String,Object> obj : listOfMap){
                        Produits p = new Produits();
                        float id = Float.parseFloat(obj.get("id").toString());
                        String libelle = obj.get("libelle").toString();
                         String type = obj.get("type").toString();
                         int quantites = Integer.parseInt(obj.get("quantites").toString());
                        int prix = Integer.parseInt(obj.get("prix").toString());

                         p.setId((int) id);
                         p.setLibelle(libelle);
                         p.setType(type);
                         p.setQuantites(quantites);
                         p.setPrix(prix);
                         
                         result.add(p);
                         
                    }
                    
                }catch(Exception ex){
                    ex.printStackTrace();
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return result;
    }
    public Produits detailsProduits(int id , Produits produit){
        String url = Statics.BASE_URL+"/detail/"+id;
        req.setUrl(url);
        String str = new String(req.getResponseData());
        req.addResponseListener((evt) -> {
            
            JSONParser jsonp =new JSONParser();
            try{
                Map<String,Object>obj=jsonp.parseJSON(new CharArrayReader(new String(str).toCharArray()));
                produit.setLibelle(obj.get("libelle").toString());
                produit.setType(obj.get("type").toString());
                produit.setQuantites(Integer.parseInt( obj.get("quantites").toString()));
                produit.setPrix(Integer.parseInt( obj.get("prix").toString()));

            }catch(IOException ex){
                    System.err.println("error"+ex.getMessage());
                }
            System.err.println("data=="+str);
            
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return produit;
        
    }
    public ArrayList<Produits> parseCours(String jsonText){
        try {
            produits=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Produits u = new Produits();
               
               u.setId((int) Float.parseFloat(obj.get("id").toString()));
                u.setLibelle(obj.get("libelle").toString());
                u.setType(obj.get("type").toString());
                u.setQuantites((int) Double.parseDouble(obj.get("quantites").toString()));
                   u.setPrix((int) Double.parseDouble(obj.get("prix").toString()));
                
                
                
       
                produits.add(u);
            }
                     
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return produits;
    }
        
        
    public ArrayList<Produits> getAllCours(){
        String url = Statics.BASE_URL+"/liste/";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                produits = parseCours(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return produits;
    }
    public boolean deleteProduits(int id){
        System.out.println(id);
        String url = Statics.BASE_URL+"/deleteproduit?id="+id;
        System.out.println(url);
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
    public boolean modifierProduits(Produits produits){
        String url = Statics.BASE_URL+"/updateproduit?id="+produits.getId()+"&libelle="+produits.getLibelle()+"&type="+produits.getType()+"&quantites="+produits.getQuantites()+"&prix="+produits.getPrix();
        System.out.println(url);
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
