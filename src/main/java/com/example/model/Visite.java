package com.example.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

@Entity
@Table(name = "visite", schema = "public")
public class Visite {
    

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Chami> joueurs;

    @ManyToOne(cascade = CascadeType.ALL)
    private Defi defi;

    @Column
    private int rang; //pour reprendre le jeu en cas de besoin


    public Visite(){
        super();
        joueurs = new ArrayList<>();
    }

    public Visite(List<Chami> j, Defi d, int rang){
        this.joueurs = j;
        this.defi = d;
        this.rang = rang;
    }

    public void addChami(Chami c){
        joueurs.add(c);
    }

    public void removeChami(Chami c){
        joueurs.remove(c);
    }

    public List<Chami> getJoueurs(){
        return joueurs;
    }

    public void setJoueur(List<Chami> l){
        this.joueurs = l;
    }

    public int getId(){
        return id;
    }

    public int getRang(){
        return rang;
    }

    public void setRang(int r){
        this.rang = r;
    }
}
