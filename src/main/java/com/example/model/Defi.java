package com.example.model;
import javax.persistence.*;
import javax.persistence.metamodel.PluralAttribute.CollectionType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Entity
@Table(name="defis", schema="public")
/*@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property="id")*/
public class Defi {
    @Id
    private String id;

    @Column
    private String titre;

    @Column(columnDefinition = "TIMESTAMP")         //type TIMESTAMP dans la base de données
    private LocalDateTime dateDeCreation;

    @ManyToOne
    private Chami auteur;

    //garder desccription

    @Column
    @Enumerated(EnumType.STRING)
    private Categorie categorie;

    @OneToMany(mappedBy="defi", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Etape> listEtape;

    public Defi() {
        super();
        listEtape = new ArrayList<>();
    }

<<<<<<< HEAD
=======

>>>>>>> 2b71b15cd3b3b18900df8be58a8fc947853a81dd
    public Defi(String id, String titre, LocalDateTime dateDeCreation, Chami auteur, Categorie cat, List<Etape> listEtape) {
        super(); 
        this.id = id;
        this.titre = titre;
        this.dateDeCreation = dateDeCreation;
        this.listEtape = listEtape;
        this.categorie = cat;
        
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

    public List<Etape> getEtape(){
        return listEtape;
    }

    public Categorie getCategorie(){
        return categorie;
    }

    public void setEtape(List<Etape> l){
        this.listEtape = l;
    }

    public void addEtape(Etape etape){
        listEtape.add(etape);
    }
    /*public void setDateDeCreation(LocalDateTime dateDeCreation) {
        this.dateDeCreation = dateDeCreation;
    }*/

<<<<<<< HEAD
    /*public List<Etape> getDescription() {
        //getDescriptionTriee();
        return this.description;
    }

    public void removeEtape(Etape etape){
        description.remove(etape);
    }
    public void setDescription(List<Etape> description) {
        this.description = description;
    }
=======
>>>>>>> 2b71b15cd3b3b18900df8be58a8fc947853a81dd

    public void getDescriptionTriee(){
        Collections.sort(description, Etape.comparatorEtape);
    }*/

    public Chami getAuteur() {
        return this.auteur;
    }

<<<<<<< HEAD
=======
    public void setEtape(List<Etape> l){
        this.listEtape = l;
    }


>>>>>>> 2b71b15cd3b3b18900df8be58a8fc947853a81dd
    public static Comparator<Etape> comparatorEtape = new Comparator<Etape>() {
        @Override
        public int compare(Etape e1, Etape e2){
            if(e1.getRang() == e2.getRang())
                return 0;   //il faudra mettre une erreur
            else
                return e1.getRang() > e2.getRang() ? -1 : 1;
        }
    };


<<<<<<< HEAD
=======

>>>>>>> 2b71b15cd3b3b18900df8be58a8fc947853a81dd
    /*public void setAuteur(Chami auteur) {
        this.auteur = auteur;
        auteur.addDefisSimple(this);
    }

    public void setSimpleAuteur(Chami auteur) {
        this.auteur = auteur;
    }*/
}