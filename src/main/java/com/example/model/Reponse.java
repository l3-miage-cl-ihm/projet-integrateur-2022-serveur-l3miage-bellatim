package com.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Reponse", schema = "public")
public class Reponse {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    private Boolean valide;

    @Column
    private TypeReponse typeReponse;

    @Column
    private String value;

    @ManyToOne
    private Question question;


    public Reponse() {
        super();
    }

    // public Reponse(Boolean valide, TypeReponse type, String val, Question question, Visite visite) {
    public Reponse(Boolean valide, TypeReponse type, String val, Question question) {
        super();
        this.valide = valide;
        this.typeReponse = type;
        this.value = val;
        this.question = question;
        // this.visite = visite;
    }

    public Boolean getValide() {
        return valide;
    }

    public void setValide(Boolean val) {
        this.valide = val;
    }

    public TypeReponse getTypeReponse() {
        return typeReponse;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String val) {
        this.value = val;
    }

    public Question getQuestion() {
        return question;
    }

    // public Visite getVisite() {
    //     return visite;
    // }

}
