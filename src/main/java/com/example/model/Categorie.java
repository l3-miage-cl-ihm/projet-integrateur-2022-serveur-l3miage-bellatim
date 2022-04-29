package com.example.model;

public enum Categorie {
    SPORTIF("sportif"), CULTUREL("culturel");

    private String value;

    private Categorie(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }

    public void setValue(String val){
        this.value = val;
    }
}
