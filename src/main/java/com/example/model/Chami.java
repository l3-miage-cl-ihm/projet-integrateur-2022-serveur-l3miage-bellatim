package com.example.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;

//ajouter lambok
@Entity
@Table(name="chamis", schema="public")
public class Chami {

    //le login est la clé d'un chami
    @Id
    private String login;

    @Column
    private int age;

    //liste des défis créés
    @OneToMany(mappedBy="auteur", cascade = CascadeType.ALL)    //mapped avec la colonne auteur dans la class Défi (clé étrangère)
    //cascade répercute modifications
    @JsonManagedReference       //evite de boucler à l'infini car un chamis à des défis et un défis à un auteur qui a des défis qui a u auteur...
    private List<Defi> defis;

    @Column(unique = true)  //email ets unique car il provient de firebase
    private String email;

    public Chami() {
        super();
        defis = new ArrayList<Defi>();
    }

    public Chami(String login, int age, String email) {
        super();
        this.login = login;
        this.age = age;
        this.email = email;
        defis = new ArrayList<Defi>();
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
        return defis;
    }

    public void addDefis(Defi defi){
        defis.add(defi);
    }

    public void removeDefis(Defi defi){
        defis.remove(defi);
    }

    /*public void addDefisSimple(Defi defi){
        defisCree.add(defi);
    }*/

    public String getEmail(){
        return email;
    }

    /*@Override
    public boolean equals(Object obj) {
        // TODO Auto-generated method stub
        Chami chami = (Chami) obj;
        return this.login.equals(chami.getLogin()) && this.age == chami.getAge() && this.email.equals(chami.getEmail());
    }*/
}
