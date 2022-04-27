package com.example.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="defis", schema="public")
public class Defi {

    @Id
    private String id;

    @Column
    private String titre;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime date_de_creation;

    @Column
    private String description;

    @ManyToOne
    private Chami auteur_login;

    public Defi() {
        super();
    }

    public Defi(String id, String titre, LocalDateTime dateDeCreation, String description, Chami auteur) {
        this.id = id;
        this.titre = titre;
        this.date_de_creation = dateDeCreation;
        this.description = description;

        this.auteur_login = auteur;
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
        return this.date_de_creation;
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
        return this.auteur_login;
    }

    /*public void setAuteur(Chami auteur) {
        this.auteur = auteur;
        auteur.addDefisSimple(this);
    }

    public void setSimpleAuteur(Chami auteur) {
        this.auteur = auteur;
    }*/
}