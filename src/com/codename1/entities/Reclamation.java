package com.esprit.pidev.entities;

import java.util.Date;

public class Reclamation {
    private int id ;
    private int idUser;
    private String titre ;
    private String description ;
    private String image;

    private String dateRecString ;
    private String dateRepString ;
    private String Etat ;
    private String Reponse ;
    private Date dateReclamation;
    private Date dateReponse ;
    private String satisfaction ;

    public Reclamation() {
    }

    public Reclamation(int id, int idUser, String titre, String description) {
        this.id = id;
        this.idUser = idUser;
        this.titre = titre;
        this.description = description;
    }

    public Reclamation(int id, int idUser, String titre, String description, String Etat, String Reponse) {
        this.id = id;
        this.idUser = idUser;
        this.titre = titre;
        this.description = description;
        this.Etat = Etat;
        this.Reponse = Reponse;
    }
    

    public Reclamation(int id, int idUser, String titre, String description, String Etat, String Reponse, Date dateReclamation, Date dateReponse,String satisfaction,String image ) {
        this.id = id;
        this.idUser = idUser;
        this.titre = titre;
        this.description = description;
        this.Etat = Etat;
        this.Reponse = Reponse;
        this.dateReclamation = dateReclamation;
        this.dateReponse = dateReponse;
        this.satisfaction=satisfaction;
        this.image = image ;
    }

    public Reclamation(int id, int idUser, String titre, String description, String image, String Etat, String Reponse, Date dateReclamation, Date dateReponse, String satisfaction) {
        this.id = id;
        this.idUser = idUser;
        this.titre = titre;
        this.description = description;
        this.image = image;
        this.Etat = Etat;
        this.Reponse = Reponse;
        this.dateReclamation = dateReclamation;
        this.dateReponse = dateReponse;
        this.satisfaction = satisfaction;
    }
    
    
    
    

    public Reclamation(int id, String titre, String description) {
        this.id = id;
        this.titre = titre;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEtat() {
        return Etat;
    }

    public void setEtat(String Etat) {
        this.Etat = Etat;
    }

    public String getReponse() {
        return Reponse;
    }

    public void setReponse(String Reponse) {
        this.Reponse = Reponse;
    }

    public Date getDateReclamation() {
        return dateReclamation;
    }

    public void setDateReclamation(Date dateReclamation) {
        this.dateReclamation = dateReclamation;
    }

    public Date getDateReponse() {
        return dateReponse;
    }

    public void setDateReponse(Date dateReponse) {
        this.dateReponse = dateReponse;
    }

    public String getSatisfaction() {
        return satisfaction;
    }

    public void setSatisfaction(String satisfaction) {
        this.satisfaction = satisfaction;
    }
    
 public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDateRecString() {
        return dateRecString;
    }

    public void setDateRecString(String dateRecString) {
        this.dateRecString = dateRecString;
    }

    public String getDateRepString() {
        return dateRepString;
    }

    public void setDateRepString(String dateRepString) {
        this.dateRepString = dateRepString;
    }
    
    @Override
    public String toString() {
        return "Reclamation{" + "id=" + id + ", idUser=" + idUser + ", titre=" + titre + ", description=" + description + ", Etat=" + Etat + ", Reponse=" + Reponse + ", dateReclamation=" + dateReclamation + ", dateReponse=" + dateReponse + '}';
    }
    
    

    
}
