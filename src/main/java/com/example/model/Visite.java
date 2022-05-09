package com.example.model;

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
import javax.persistence.Table;

@Entity
@Table(name = "visite", schema = "public")
public class Visite {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToMany
    private List<Chami> joueurs;

    @ManyToOne
    private Defi defi;

    @Column
    private int rang; // pour reprendre le jeu en cas de besoin

    @Column
    private LocalDateTime dateDebut;

    @Column
    private LocalDateTime dateFin;

    public Visite() {
        super();
        joueurs = new ArrayList<>();
    }

    public Visite(List<Chami> j, Defi d, int rang) {
        super();
        this.joueurs = j;
        this.defi = d;
        this.rang = rang;
        this.dateDebut=LocalDateTime.now();
        this.dateFin=null;
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
}
