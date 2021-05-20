/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.services;


import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.esprit.pidev.entities.Reclamation;
import com.esprit.pidev.utils.Statics;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
/**
 *
 * @author Lenovo
 */
public class ServiceReclamation {
    
    //singleton 
    public static ServiceReclamation instance = null ;
    
    public static boolean resultOk = true;
   
    ArrayList<Reclamation> reclamations = new ArrayList<>();

    //initilisation connection request 
    private ConnectionRequest req;
    
    
    public static ServiceReclamation getInstance() {
        if(instance == null )
            instance = new ServiceReclamation();
        return instance ;
    }
    
    
    
    public ServiceReclamation() {
        req = new ConnectionRequest();
        
    }
    
    
    //ajout 
    public void ajoutReclamation(Reclamation reclamation) {
        
        String url =Statics.BASE_URL+"/reclamation/addRecJSON?titre="+reclamation.getTitre()+"&description="+reclamation.getDescription();
        
        req.setUrl(url);
        req.addResponseListener((e) -> {
            
            String str = new String(req.getResponseData());//Reponse json hethi lyrinaha fi navigateur 9bila
            System.out.println("data == "+str);
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
        
    }
    
    public void exportPDF() {
        
        String url =Statics.BASE_URL+"/reclamation/export/pdf";
        
        req.setUrl(url);
        req.addResponseListener((e) -> {
            
            String str = new String(req.getResponseData());//Reponse json hethi lyrinaha fi navigateur 9bila
            System.out.println("data == "+str);
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
        
    }
    
    //affichage
    public ArrayList<Reclamation> parseRecs(String jsonText){
        
        try {
            reclamations=new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            System.out.println(list);
            //Parcourir la liste des tâches Json
            for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
                Reclamation rec = new Reclamation();
                float id = Float.parseFloat(obj.get("id").toString());
                rec.setId((int)id);
                rec.setTitre(obj.get("titre").toString());
                rec.setDescription(obj.get("description").toString());
                rec.setEtat(obj.get("etat").toString());
                rec.setReponse(obj.get("reponse")+"");
                rec.setImage(obj.get("image")+"");
                rec.setSatisfaction(obj.get("satisfaction")+"");
                String date = obj.get("datereclamation")+"";
                String y = date.substring(0, 4);
                String m = date.substring(5, 7);
                String d = date.substring(8, 10);
                rec.setDateRecString(d+"/"+m+"/"+y);
                try {
                rec.setDateReclamation(new SimpleDateFormat("dd/MM/yyyy").parse(rec.getDateRecString()));
                } catch (Exception e) {
                }
                date="";
                date = obj.get("datereponse")+"";
               // System.out.println(date);
                if(date.length()>4){
                  y = date.substring(0, 4);
                m = date.substring(5, 7);
                d = date.substring(8, 10);
                rec.setDateRepString(d+"/"+m+"/"+y);
                try {
                rec.setDateReponse(new SimpleDateFormat("dd/MM/yyyy").parse(rec.getDateRepString()));
                    //System.out.println(rec.getDateReponse());
                } catch (Exception e) {
                }  
                }
                
                
                //Ajouter la tâche extraite de la réponse Json à la liste
                reclamations.add(rec);
            }
            
            
        } catch (IOException ex) {
            
        }
        return reclamations;
    }
    //affichage
    public ArrayList<Reclamation> parseAllRecs(String jsonText){
        
        try {
            reclamations=new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            System.out.println(list);
            //Parcourir la liste des tâches Json
            for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
                Reclamation rec = new Reclamation();
                float id = Float.parseFloat(obj.get("id").toString());
                rec.setId((int)id);
                rec.setTitre(obj.get("titre").toString());
                rec.setDescription(obj.get("description").toString());
                //Ajouter la tâche extraite de la réponse Json à la liste
                reclamations.add(rec);
            }
            
            
        } catch (IOException ex) {
            
        }
        return reclamations;
    }
    
    public ArrayList<Reclamation>affichageReclamations() {
         String url = Statics.BASE_URL+"/reclamation/displayReclamations";
        req.setUrl(url);
        req.setPost(false);
        req.setHttpMethod("GET");
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //System.out.println("printing all data");
                //System.out.println(new String(req.getResponseData()));
                reclamations = parseAllRecs(new String(req.getResponseData()));
                //System.out.println(reclamations);
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return reclamations;
    }
    
    public ArrayList<Reclamation> getReclamationCloture() {
         String url = Statics.BASE_URL+"/reclamation/getRecClotureJSON";
        req.setUrl(url);
        req.setPost(false);
        req.setHttpMethod("GET");
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //System.out.println("printing all data");
                //System.out.println(new String(req.getResponseData()));
                reclamations = parseRecs(new String(req.getResponseData()));
                //System.out.println(reclamations);
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return reclamations;
    }
    public ArrayList<Reclamation> getReclamationActif() {
        
         String url = Statics.BASE_URL+"/reclamation/getRecActifsJSON";
        req.setUrl(url);
        req.setPost(false);
        req.setHttpMethod("GET");
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //System.out.println("printing all data");
                //System.out.println(new String(req.getResponseData()));
                reclamations = parseRecs(new String(req.getResponseData()));
                //System.out.println(reclamations);
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return reclamations;
    }
        
        
    
    
    
    //Detail Reclamation bensba l detail n5alihoa lel5r ba3d delete+update
    
    public Reclamation DetailRecalamation( int id , Reclamation reclamation) {
        
        String url = Statics.BASE_URL+"/detailReclamation?"+id;
        req.setUrl(url);
        
        String str  = new String(req.getResponseData());
        req.addResponseListener(((evt) -> {
        
            JSONParser jsonp = new JSONParser();
            try {
                
                Map<String,Object>obj = jsonp.parseJSON(new CharArrayReader(new String(str).toCharArray()));
                
                reclamation.setTitre(obj.get("titre").toString());
                reclamation.setDescription(obj.get("description").toString());
                reclamation.setEtat(obj.get("etat").toString());
                
            }catch(IOException ex) {
                System.out.println("error related to sql :( "+ex.getMessage());
            }
            
            
            System.out.println("data === "+str);
            
            
            
        }));
        
              NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha

              return reclamation;
        
        
    }
    
    
    //Delete 
    public boolean deleteReclamation(int id ) {
        String url = Statics.BASE_URL +"/reclamation/deleteRecJSON/"+id;
        
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                    
                    req.removeResponseCodeListener(this);
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return  resultOk;
    }
    
    
    
    //Update 
    public boolean modifierReclamation(Reclamation reclamation) {
        String url = Statics.BASE_URL +"/reclamation/editRecJSON?id="+reclamation.getId()+"&titre="+reclamation.getTitre()+"&description="+reclamation.getDescription();
        System.out.println(url);
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOk = req.getResponseCode() == 200 ;  // Code response Http 200 ok
                req.removeResponseListener(this);
            }
        });
        
    NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
    return resultOk;
        
    }
    public boolean setSatisfaction(Reclamation reclamation) {
        System.out.println(reclamation.getSatisfaction());
        String url = Statics.BASE_URL +"/reclamation/SatisfactiontReclamationQuery?id="+reclamation.getId()+"&satisfaction="+reclamation.getSatisfaction();
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOk = req.getResponseCode() == 200 ;  // Code response Http 200 ok
                req.removeResponseListener(this);
            }
        });
        
    NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
    return resultOk;
        
    }
    
    

    
}