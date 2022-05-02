package com.example.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

//ajouter lambok
@Entity
@Table(name="chamis", schema="public")
/*@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property="login")*/
public class Chami {

    //le login est la clé d'un chami
    @Id
    private String login;

    @Column
    private int age;

    @OneToMany(mappedBy="auteur", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Defi> defis;

    @Column(unique = true)  //email est unique car il provient de firebase
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

    public String toString(){
        return login;
    }
    /*@Override
    public boolean equals(Object obj) {
        // TODO Auto-generated method stub
        Chami chami = (Chami) obj;
        return this.login.equals(chami.getLogin()) && this.age == chami.getAge() && this.email.equals(chami.getEmail());
    }*/
}
