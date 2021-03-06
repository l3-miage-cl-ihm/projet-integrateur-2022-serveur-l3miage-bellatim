package com.example.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
// import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "visite", schema = "public")
public class Visite {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToMany
    private List<Chami> joueurs;

    // @OneToMany
    // @Cascade(CascadeType.SAVE_UPDATE)
    @OneToMany(cascade=CascadeType.ALL)
    private List<Reponse> reponses;

    @ManyToOne
    private Defi defi;

    @Column
    private int rang; // pour reprendre le jeu en cas de besoin

    @Column(columnDefinition = "TIMESTAMP") // type TIMESTAMP dans la base de données
    private LocalDateTime dateDebut;

    @Column(columnDefinition = "TIMESTAMP") // type TIMESTAMP dans la base de données
    private LocalDateTime dateFin;

    public Visite() {
        super();
        joueurs = new ArrayList<>();
        reponses = new ArrayList<>();
        this.dateDebut=LocalDateTime.now();
    }

    public Visite(List<Chami> j, Defi d, int rang,LocalDateTime date) {
        super();
        this.joueurs = j;
        this.defi = d;
        this.rang = rang;
        this.dateDebut=date;
        this.dateFin=null;
        this.reponses= new ArrayList<>();
    }

    public List<Reponse> getReponses(){
        return reponses;
    }

    public void addReponse(Reponse reponse){
        this.reponses.add(reponse);
    }

    public void setReponses(List<Reponse> reponses){
        this.reponses=reponses;
    }


    public LocalDateTime getDateDebut(){
        return dateDebut;
    }

    public LocalDateTime getDateFin(){
        return dateFin;
    }

    public void setDateFin(LocalDateTime date){
        this.dateFin=date;
    }

    public void addChami(Chami c) {
        joueurs.add(c);
    }

    public void removeChami(Chami c) {
        joueurs.remove(c);
    }

    public List<Chami> getJoueurs() {
        return joueurs;
    }

    public void setJoueur(List<Chami> l) {
        this.joueurs = l;
    }

    public int getId() {
        return id;
    }

    public int getRang() {
        return rang;
    }

    public void setRang(int r) {
        this.rang = r;
    }

    public Defi getDefi(){
        return this.defi;
    }

    // public void finir(){
    //     this.dateFin = LocalDateTime.now();
    // }
}
