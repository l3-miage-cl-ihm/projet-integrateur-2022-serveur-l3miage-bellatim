package com.example.model;

public enum TypeMEDIA {

    VIDEO("vidéo"), PHOTO("photo");

    private String value;

    private TypeMEDIA(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }

}
