package com.example.model;

import com.google.api.client.util.DateTime;

public class Defi {

    private String id;
    private String titre;
    private DateTime dateDeCreation;
    private String description;
    private Chami auteur;

    public Defi() {
        super();
    }

    public Defi(String id, String titre, DateTime dateDeCreation, String description, Chami auteur) {
        this.id = id;
        this.titre = titre;
        this.dateDeCreation = dateDeCreation;
        this.description = description;
        this.auteur = auteur;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitre() {
        return this.titre;
    }

    public void setTItre(String titre) {
        this.id = titre;
    }
    
    public DateTime getDateDeCreation() {
        return this.dateDeCreation;
    }

    public void setDateDeCreation(DateTime dateDeCreation) {
        this.dateDeCreation = dateDeCreation;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Chami getAuteur() {
        return this.auteur;
    }

    public void setId(Chami auteur) {
        this.auteur = auteur;
    }
}