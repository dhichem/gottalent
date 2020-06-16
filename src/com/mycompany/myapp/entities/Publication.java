/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author House
 */
public class Publication {
    private int idPublication;
    private String pseudo;
    private String titre;
    private String video;
    private String datePublication;
    private String categorie;
    private int nbreVue;
    private String image;
    private String thumbnail;

    public Publication(int idPublication, String pseudo, String titre, String video, String datePublication, String categorie, int nbreVue, String image, String thumbnail) {
        this.idPublication = idPublication;
        this.pseudo = pseudo;
        this.titre = titre;
        this.video = video;
        this.datePublication = datePublication;
        this.categorie = categorie;
        this.nbreVue = nbreVue;
        this.image = image;
        this.thumbnail = thumbnail;
    }
    
    
    public Publication(String pseudo, String titre, String video, String categorie, String image, String thumbnail) {
        this.pseudo = pseudo;
        this.titre = titre;
        this.video = video;
        this.categorie = categorie;
        this.image = image;
        this.thumbnail = thumbnail;
    }
    

    public Publication() {
    }
    

    public int getIdPublication() {
        return idPublication;
    }

    public void setIdPublication(int idPublication) {
        this.idPublication = idPublication;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getDatePublication() {
        return datePublication;
    }

    public void setDatePublication(String datePublication) {
        this.datePublication = datePublication;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getNbreVue() {
        return nbreVue;
    }

    public void setNbreVue(int nbreVue) {
        this.nbreVue = nbreVue;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
    
    
    
    

    @Override
    public String toString() {
        return "Publication{" + "idPublication=" + idPublication + ", pseudo=" + pseudo + ", titre=" + titre + ", video=" + video + ", datePublication=" + datePublication + ", categorie=" + categorie + "nbreVue" + nbreVue + ", image=" + image + '}';
    }
    
    
    

    
    
}
