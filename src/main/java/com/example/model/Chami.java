package com.example.model;

import java.util.List;

public class Chami {

    private String login;
    private int age;
    private List<Defi> lesDefis;

    public Chami() {
        super();
    }

    public Chami(String login, int age) {
        this.login = login;
        this.age = age;
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
}
