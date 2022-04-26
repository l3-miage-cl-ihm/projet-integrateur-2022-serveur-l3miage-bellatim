package com.example.model;

import java.time.LocalDateTime;

public class Defi {

    private String id;
    private String titre;
    private LocalDateTime dateDeCreation;
    private String description;
    private Chami auteur;

    public Defi() {
        super();
    }

    public Defi(String id, String titre, LocalDateTime dateDeCreation, String description, Chami auteur) {
        this.id = id;
        this.titre = titre;
        this.dateDeCreation = dateDeCreation;
        this.description = description;

        this.auteur = auteur;
        //auteur.addDefis(this);    
    }

    public String getId() {
        return this.id;
    }

    /*public void setId(String id) {
        this.id = id;
    }*/

    public String getTitre() {
        return this.titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }
    
    public LocalDateTime getDateDeCreation() {
        return this.dateDeCreation;
    }

    /*public void setDateDeCreation(LocalDateTime dateDeCreation) {
        this.dateDeCreation = dateDeCreation;
    }*/

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Chami getAuteur() {
        return this.auteur;
    }

    /*public void setAuteur(Chami auteur) {
        this.auteur = auteur;
        auteur.addDefisSimple(this);
    }

    public void setSimpleAuteur(Chami auteur) {
        this.auteur = auteur;
    }*/
}