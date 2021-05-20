/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.codename1.io.Preferences;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.util.Resources;
import com.mycompany.gui.ProfileForm;
import com.mycompany.gui.SessionManager;
import com.mycompany.gui.SignInForm;
import com.mycompany.utils.Statics;
import java.util.Map;
import java.util.Vector;

/**
 *
 * @author hedii
 */
public class ServicesUtilisateur {
        public static ServicesUtilisateur instance=null;
    private ConnectionRequest req ; 
    public static ServicesUtilisateur getInstance()
    {
        if(instance==null)
        {   
            instance = new ServicesUtilisateur();
                
        }
        return instance;
    }
    public ServicesUtilisateur()
    {
        req = new ConnectionRequest();
    }
    //    public void signup(TextField tf_email,TextField tf_nom,TextField tf_prenom,TextField tf_pseudo,TextField tf_password,TextField tf_addresse,TextField tf_rib,TextField tf_cin,TextField tf_tel,ComboBox <String> VRole, Resources Res)

    public void signup(TextField tf_username,TextField tf_password,TextField tf_email,TextField tf_tel,ComboBox <String> VRole, Resources Res)
    {
       
        
        String Url = Statics.BASE_URL+"User_add_JSON?username="+tf_username.getText().toString()+
                                      "&email="+tf_email.getText().toString()+
                                      "&Telphone="+tf_tel.getText().toString()+
                                      "&roles="+VRole.getSelectedItem()+"&password="+tf_password.getText().toString();
        req.setUrl(Url);
        
        if(tf_email.getText().equals("")&& tf_password.getText().equals(""))
        {
            Dialog.show("Erreur","Veuillez Remplir les Champs ", "ok",null);
            
        }
        req.addResponseListener(e->{
            byte[]data=(byte[]) e.getMetaData();
            String responseData = new String(data);
            System.out.println("Data ====> "+responseData);
            
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
      public void Edit(TextField tf_username,TextField tf_password,TextField tf_email,TextField tf_Tel, Resources Res,int id)
    {
  
        String Url = Statics.BASE_URL+"update_JSON/"+id+"?username="+tf_username.getText().toString()+
                                      "&email="+tf_email.getText().toString()+
                                      "&telephone="+tf_Tel.getText().toString()+
                                      "&password="+tf_password.getText().toString();
        req.setUrl(Url);
        //12?username=hedi&email=mohamedhedi.benhamed@esprit.tn&Telephone=27253677&password=hedi
        if(tf_email.getText().equals("")&& tf_password.getText().equals(""))
        {
            Dialog.show("Erreur","Veuillez Remplir les Champs ", "ok",null);
            
        }
        req.addResponseListener(e->{
            byte[]data=(byte[]) e.getMetaData();
            String responseData = new String(data);
            System.out.println("Data ====> "+responseData);
            
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
      
      public void singin(TextField mail, TextField motdepasse, Resources res){
        String url = Statics.BASE_URL+"User_Access_JSON?email="+mail.getText().toString()
                    +"&password="+motdepasse.getText().toString();
        req = new ConnectionRequest(url, false);
        req.setUrl(url);
        
        req.addResponseListener((e)->{
            JSONParser j= new JSONParser();
            String json = new String(req.getResponseData()) + "";
            
        try{   
            if(json.equals("failed")){
                Dialog.show("failure","E-mail or password incorrect","OK",null);
            }else{
                System.out.println("data == "+json);
                
                Map<String,Object> user = j.parseJSON(new CharArrayReader(json.toCharArray()));
                // Session  Service
                if(user.get("isVerified")!=null)
                {System.out.println("********************");
                float id = Float.parseFloat(user.get("id").toString());
                SessionManager.setId((int)id);//jibt id ta3 user ly3ml login w sajltha fi session ta3i
                
                SessionManager.setPassowrd(user.get("password").toString());
                SessionManager.setUserName(user.get("username").toString());
                //System.out.println("Num :----------->"+user.get("telephone").toString());
                //SessionManager.setTelephone(user.get("telephone").toString());
                SessionManager.setEmail(user.get("email").toString());
                System.out.println("Current User Is : "+SessionManager.getEmail()+"Nom : "+SessionManager.getUserName()+"Num :"+SessionManager.getTelphone());
//                //photo 
//                
//                if(user.get("photo") != null)
//                    SessionManager.setPhoto(user.get("photo").toString());
                if(user.size()>0){
                    new ProfileForm(res).show();
                }
                }
                else
                {
                    Dialog.show("Failed","You Must ACTIVATE Your Account","OK",null);
                }
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        
    } 
    
    public void resetPassword(TextField motdepasse, Resources res){
        String phone = Preferences.get("phone", "NotFound");
        String url = Statics.BASE_URL+"ResetPswJSON?password="+motdepasse.getText().toString()+"&phone="+phone;
        req = new ConnectionRequest(url, false);
        req.setUrl(url);
        
         req.addResponseListener((e)->{
            JSONParser j= new JSONParser();
            String json = new String(req.getResponseData()) + "";
            
        try{   
            if(json.equals("no")){
                Dialog.show("failure","no such user was found by phone","OK",null);
               new SignInForm(res).show();
            }else{
                Dialog.show("Success","Password reseted","OK",null);
                new SignInForm(res).show();
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
}

    