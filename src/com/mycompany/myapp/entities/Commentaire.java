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
public class Commentaire {
    private int idCommentaire;
    private String pseudo;
    private String contenu;
    private String dateCommentaire;
    private int idPublication;
    private String image;

    public Commentaire(int idCommentaire, String pseudo, String contenu, String dateCommentaire, int idPublication, String image) {
        this.idCommentaire = idCommentaire;
        this.pseudo = pseudo;
        this.contenu = contenu;
        this.dateCommentaire = dateCommentaire;
        this.idPublication = idPublication;
        this.image = image;
    }

    public Commentaire() {
    }
    
    
    public Commentaire(String pseudo, String contenu, String image, int idPublication) {
        this.pseudo = pseudo;
        this.contenu = contenu;
        this.image = image;
        this.idPublication = idPublication;
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

    public int getIdCommentaire() {
        return idCommentaire;
    }

    public void setIdCommentaire(int idCommentaire) {
        this.idCommentaire = idCommentaire;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public String getDateCommentaire() {
        return dateCommentaire;
    }

    public void setDateCommentaire(String dateCommentaire) {
        this.dateCommentaire = dateCommentaire;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Commentaire{" + "idCommentaire=" + idCommentaire + ", pseudo=" + pseudo + ", contenu=" + contenu + ", dateCommentaire=" + dateCommentaire + ", idPublication=" + idPublication + ", image=" + image + '}';
    }
    
    
   

    
    
}
