package com.example.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "visite", schema = "public")
public class Visite {
    

    @Column
    private int id;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Chami> joueurs;

    @OneToMany(cascade = CascadeType.ALL)
    private Defi defi;

    public Visite(){
        super();
        joueurs = new ArrayList<>();
    }

    public Visite(List<Chami> j, Defi d){
        this.joueurs = j;
        this.defi = d;
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
}
