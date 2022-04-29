package com.example.model;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.time.LocalDateTime;

@Entity
@Table(name="defis", schema="public")
public class Defi// implements Comparator<Etape>{
{

    @Id
    private String id;

    @Column
    private String titre;

    @Column(columnDefinition = "TIMESTAMP")         //type TIMESTAMP dans la base de données
    private LocalDateTime dateDeCreation;

    @OneToMany(mappedBy = "id")
    private List<Etape> description;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonBackReference
    @JoinColumn(name="auteur_login")    //évite de donner tout le défis. Seul le login de l'auteur est necessaire
    private Chami auteur;

    @Column
    private Categorie categorie;

    public Defi() {
        super();
        this.description = new ArrayList<Etape>();

    }

    public Defi(String id, String titre, LocalDateTime dateDeCreation, Chami auteur) {
        super(); 
        this.id = id;
        this.titre = titre;
        this.dateDeCreation = dateDeCreation;
        this.description = new ArrayList<Etape>();
        
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

    public List<Etape> getDescription() {
        //getDescriptionTriee();
        return this.description;
    }

    public void addEtape(Etape etape){
        description.add(etape);
    }

    public void removeEtape(Etape etape){
        description.remove(etape);
    }
    public void setDescription(List<Etape> description) {
        this.description = description;
    }

    public void getDescriptionTriee(){
        Collections.sort(description, Etape.comparatorEtape);
    }

    public Chami getAuteur() {
        return this.auteur;
    }

    public static Comparator<Etape> comparatorEtape = new Comparator<Etape>() {
        @Override
        public int compare(Etape e1, Etape e2){
            if(e1.getRang() == e2.getRang())
                return 0;   //il faudra mettre une erreur
            else
                return e1.getRang() > e2.getRang() ? -1 : 1;
        }
    };


    /*public void setAuteur(Chami auteur) {
        this.auteur = auteur;
        auteur.addDefisSimple(this);
    }

    public void setSimpleAuteur(Chami auteur) {
        this.auteur = auteur;
    }*/
}