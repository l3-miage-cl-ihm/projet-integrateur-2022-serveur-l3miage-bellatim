package com.example.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonTypeName;

@Entity
@DiscriminatorValue("question")
//@Table(name = "question", schema = "public")
@JsonTypeName("question")
public class Question extends Etape {

    @Column
    private int point;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Indice> listeIndice;

    @Column
    private String reponse_attendu;

    @Column
    private TypeReponse type_reponse_attendu;

    public Question(int rang, String label, int point, List<Indice> l, String repAttendu, TypeReponse typeRepAttendu) {
        super(rang, label);
        this.point = point;
        this.listeIndice = l;
        this.reponse_attendu = repAttendu;
        this.type_reponse_attendu = typeRepAttendu;
    }

    public Question() {
        super();
    }

    public void addIndice(Indice i) {
        listeIndice.add(i);
    }

    public void removeIndice(Indice i) {
        listeIndice.remove(i);
    }

    public void setReponseAttendu(String rep) {
        this.reponse_attendu = rep;
    }

    public int getPoint(){
        return this.point;
    }

    public List<Indice> getListeIndice(){
        return this.listeIndice;
    }

    public String getReponseAttendu() {
        return reponse_attendu;
    }

    public TypeReponse getTypeReponseAttendu(){
        return this.type_reponse_attendu;
    }

}
