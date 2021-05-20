/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.services;

import com.codename1.entities.Cours;
import com.codename1.entities.Reservation;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import com.codename1.utils.Statics;
import java.io.IOException;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
 

 
 

/**
 *
 * @author dell
 */
public class ServiceReservation {
     public ArrayList<Reservation> Reservations;
    public static ServiceReservation instance=null;
    private ConnectionRequest req;
    public boolean resultOK;
    private ServiceReservation() {
         req = new ConnectionRequest();
    }
    public static ServiceReservation getInstance() {
        if (instance == null) {
            instance = new ServiceReservation();
        }
        return instance;
    }  
    
     public ArrayList<Reservation> parseReservation(String jsonText) throws ParseException{
        try {
            Reservations=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Reservation u = new Reservation();
                 u.setNumSalles((int) Double.parseDouble(obj.get("numsalles").toString()));
                  u.setSpecialite(obj.get("specialite").toString());
                 
           String sDate1 = obj.get("date").toString();
                String Date2 =sDate1.substring(0, 10);
                u.setDate((Date) new SimpleDateFormat("yyyy-MM-dd").parse(Date2));
                u.setHorraire(obj.get("horraire").toString());
                u.setDuree(obj.get("duree").toString());
               
               
       
                Reservations.add(u);
            }
                     
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return Reservations;
    }
    public ArrayList<Reservation> getAllReservation(){
        String url = Statics.BASE_URL+"/liste1";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    Reservations = parseReservation(new String(req.getResponseData()));
                } catch (ParseException ex) {
                 }
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Reservations;
    }    
}
