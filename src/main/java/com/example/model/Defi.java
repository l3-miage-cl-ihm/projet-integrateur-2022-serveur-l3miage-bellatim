package com.example.model;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.time.LocalDateTime;

@Entity
@Table(name="defis", schema="public")
/*@JsonIdentityInfo(
        generator = ObjectIdGenerators.StringIdGenerator.class,
        property="auteur")*/
public class Defi {
    @Id
    private String id;

    @Column
    private String titre;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime dateDeCreation;

    @Column
    private String description;

    @ManyToOne(cascade = CascadeType.ALL)
    private Chami auteur;

    public Defi() {
        super();
    }

    public Defi(String id, String titre, LocalDateTime dateDeCreation, String description, Chami auteur) {
        super(); // XXX ajout
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