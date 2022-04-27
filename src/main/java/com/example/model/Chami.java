package com.example.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

//ajouter lambok
@Entity
@Table(name="leschamis")
public class Chami {

    @Id
    private String login;

    @Column
    private int age;

    @OneToMany(mappedBy="auteur")
    private List<Defi> defisCree;

    public Chami() {
        super();
        defisCree = new ArrayList<Defi>();
    }

    public Chami(String login, int age) {
        this.login = login;
        this.age = age;
        defisCree = new ArrayList<Defi>();
    }

    public String getLogin() {
        return this.login;
    }

    public void setLogin(String login) {
        this.login = login;
        
    }
    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return this.age;
    }

    public List<Defi> getDefis(){
        return defisCree;
    }

    public void addDefis(Defi defi){
        defisCree.add(defi);
    }

    public void removeDefis(Defi defi){
        defisCree.remove(defi);
    }

    /*public void addDefisSimple(Defi defi){
        defisCree.add(defi);
    }*/
}
