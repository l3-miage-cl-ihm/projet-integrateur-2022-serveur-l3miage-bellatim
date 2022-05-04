package com.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Reponse", schema="public")
public class Reponse {
    
    @Id
    private int id;

    @Column
    private Boolean valide;

    @Column
    private TypeReponse typeReponse;

    @Column
    private String value;

    @ManyToOne
    private Question question;

    @ManyToOne
    private Visite visite;

    public Reponse(){
        super();
    }

    public Reponse(Boolean valide, TypeReponse type, String val, Question question, Visite visite){
        super();
        this.valide = valide;
        this.typeReponse = type;
        this.value = val;
        this.question = question;
        this.visite = visite;
    }

    public Boolean isValidate(){
        return valide;
    }

    public void setValidate(Boolean val){
        this.valide = val;
    }

    public TypeReponse getTypeReponse(){
        return typeReponse;
    }

    public String getValue(){
        return value;
    }

    public void setValue(String val){
        this.value = val;
    }

    public Question getQuestion(){
        return question;
    }

    public Visite getVisite(){
        return visite;
    }

}
