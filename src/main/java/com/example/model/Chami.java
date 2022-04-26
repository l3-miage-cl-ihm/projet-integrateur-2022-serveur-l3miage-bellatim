package com.example.model;

import java.util.ArrayList;
import java.util.List;

public class Chami {

    private String login;
    private int age;
    private ArrayList<Defi> defisCree;

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
        System.out.println("++++++++++++++++++++++++++++");
        defisCree.add(defi);
        System.out.println("///////////////////////////");
    }

    public void removeDefis(Defi defi){
        defisCree.remove(defi);
    }

    /*public void addDefisSimple(Defi defi){
        defisCree.add(defi);
    }*/
}
