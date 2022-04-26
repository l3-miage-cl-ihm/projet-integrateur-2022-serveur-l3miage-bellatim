package com.example.model;

import java.util.ArrayList;
import java.util.List;

public class Chami {

    private String login;
    private int age;
    private List<Defi> defisCree;

    public Chami() {
        super();
    }

    public Chami(String login, int age) {
        this.login = login;
        this.age = age;
        defisCree = new ArrayList<>();
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
