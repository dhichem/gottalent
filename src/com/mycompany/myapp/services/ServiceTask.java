/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Task;
import com.mycompany.myapp.entities.Publication;
import com.mycompany.myapp.entities.Commentaire;
import com.mycompany.myapp.entities.Login;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author bhk
 */
public class ServiceTask {

    public ArrayList<Publication> publications;
    public ArrayList<Commentaire> commentaires;
    public ArrayList<Login> logins;

    
    public static ServiceTask instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceTask() {
         req = new ConnectionRequest();
    }

    public static ServiceTask getInstance() {
        if (instance == null) {
            instance = new ServiceTask();
        }
        return instance;
    }

    public boolean addCommentaire(Commentaire c) {
        String url = Statics.BASE_URL + "Publication/mobileAjoutCommentaire/" + c.getPseudo()+ "/" + c.getContenu() + "/" + c.getImage() + "/" + c.getIdPublication();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public ArrayList<Publication> parsePublication(String jsonText){
        try {
            publications=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Publication t = new Publication();
                //float id = Float.parseFloat(obj.get("id").toString());
                //t.setId((int)id);
                //t.setStatus(((int)Float.parseFloat(obj.get("status").toString())));
                //t.setName(obj.get("name").toString());
                //tasks.add(t);
                t.setIdPublication(((int)Float.parseFloat(obj.get("id").toString())));
                t.setPseudo(obj.get("pseudo").toString());
                t.setTitre(obj.get("titre").toString());
                t.setVideo(obj.get("video").toString());
                t.setDatePublication(obj.get("datePublication").toString());
                t.setCategorie(obj.get("categorie").toString());
                t.setNbreVue(((int)Float.parseFloat(obj.get("nbreVue").toString())));
                t.setImage(obj.get("image").toString());
                t.setThumbnail(obj.get("thumbnail").toString());
                
                publications.add(t);
                
            }
            
            
        } catch (IOException ex) {
            
        }
        return publications;
    }
    
    public ArrayList<Publication> getAllTasks(){
        String url = Statics.BASE_URL+"Publication/pubAff";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                publications = parsePublication(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return publications;
    }
    
    
    
    
    
    public ArrayList<Commentaire> parseComm(String jsonText){
        try {
            commentaires=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Commentaire t = new Commentaire();
                //float id = Float.parseFloat(obj.get("id").toString());
                //t.setId((int)id);
                //t.setStatus(((int)Float.parseFloat(obj.get("status").toString())));
                //t.setName(obj.get("name").toString());
                //tasks.add(t);
                t.setIdCommentaire(((int)Float.parseFloat(obj.get("id").toString())));
                t.setPseudo(obj.get("pseudo").toString());
                t.setContenu(obj.get("contenu").toString());
                t.setDateCommentaire(obj.get("dateCommentaire").toString());
                t.setIdPublication(((int)Float.parseFloat(obj.get("idPublication").toString())));
                t.setImage(obj.get("image").toString());
               
                commentaires.add(t);
                
            }
            
            
        } catch (IOException ex) {
            
        }
        return commentaires;
    }
    
    
    
    
    public ArrayList<Commentaire> getComm(int idPublication){
        String url = Statics.BASE_URL+"Publication/commAff/"+idPublication;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                commentaires = parseComm(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return commentaires;
    }
    
    
    
//    public boolean Login(String username, String pwd) {
//        String url = Statics.BASE_URL + "Publication/mobileLogin/" + username + "/" + pwd;
//        req.setUrl(url);
//        req.addResponseListener(new ActionListener<NetworkEvent>() {
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
//                req.removeResponseListener(this);
//                
//            }
//        });
//        NetworkManager.getInstance().addToQueueAndWait(req);
//        return resultOK;
//    }
    
    
    
    
    public ArrayList<Login> parseLogin(String jsonText){
        try {
            logins=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Login login = new Login();
                //float id = Float.parseFloat(obj.get("id").toString());
                //t.setId((int)id);
                //t.setStatus(((int)Float.parseFloat(obj.get("status").toString())));
                //t.setName(obj.get("name").toString());
                //tasks.add(t);
                login.setId(((int)Float.parseFloat(obj.get("id").toString())));
                login.setUsername(obj.get("username").toString());
                login.setEmail(obj.get("email").toString());
                login.setPassword(obj.get("password").toString());
                login.setImage(obj.get("image").toString());
                login.setType_compte(obj.get("typeCompte").toString());
               
                
                logins.add(login);
            }
            
            
        } catch (IOException ex) {
            
        }
        return logins;
    }
    
    
    
    
    public ArrayList<Login> getLogin(String username, String pwd){
        String url = Statics.BASE_URL+"Publication/mobileLogin/" + username + "/" + pwd;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                logins = parseLogin(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return logins;
    }
    
    
    
    
    public ArrayList<Publication> getChaine(String pseudo){
        String url = Statics.BASE_URL+"Publication/mobileChaine/" + pseudo;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                publications = parsePublication(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return publications;
    }
    
    
    
    
    
    public boolean deleteCommentaire(int id) {
        String url = Statics.BASE_URL + "Publication/mobileSupprimerCommentaire/" + id;
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    
    
    
    
    public boolean addPublication(Publication p) {
        String url = Statics.BASE_URL + "Publication/mobileAjoutPublication/" + p.getPseudo()+ "/" + p.getTitre()+ "/" 
                + p.getVideo()+ "/" + p.getCategorie()+ "/" + p.getImage()+ "/" + p.getThumbnail();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    
    
    
    
    
    public boolean modifyCommentaire(int id, String contenu) {
        String url = Statics.BASE_URL + "Publication/mobileModifierCommentaire/" + id+ "/" +contenu;
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    
    
    
    
    public boolean addVue(int id) {
        String url = Statics.BASE_URL + "Publication/mobileVideoVue/" + id;
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    
    
    
    
    public ArrayList<Publication> getAllSearch(String search){
        String url = Statics.BASE_URL+"Publication/mobilechercherVideo/" + search;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                publications = parsePublication(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return publications;
    }
    
    
    
    
}
