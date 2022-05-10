package com.example.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonTypeName;

@Entity
@DiscriminatorValue("question")
//@Table(name = "question", schema = "public")
@JsonTypeName("question")
public class Question extends Etape {

    //pas d'id au question

    @Column
    private int point;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Indice> liste_indice;

    @Column
    private String reponse_attendu;

    @Column
    @Enumerated(EnumType.STRING)
    private TypeReponse type_reponse_attendu;

    public Question(int rang, String label, int point, List<Indice> l, String repAttendu, TypeReponse typeRepAttendu) {
        super(rang, label);
        this.point = point;
        this.liste_indice = l;
        this.reponse_attendu = repAttendu;
        this.type_reponse_attendu = typeRepAttendu;
    }

    public Question() {
        super();
        //this.liste_indice = new ArrayList<Indice>();
    }

    public void addIndice(Indice i) {
        liste_indice.add(i);
    }

    public void removeIndice(Indice i) {
        liste_indice.remove(i);
    }

    public void setReponseAttendu(String rep) {
        this.reponse_attendu = rep;
    }

    public void setTypeReponseAttendu(TypeReponse t){
      this.type_reponse_attendu = t;
    }

    public int getPoint(){
        return this.point;
    }

    public List<Indice> getListeIndice(){
        return this.liste_indice;
    }

    public void setListeIndice(List<Indice> l){
      this.liste_indice = l;
    }

    public String getReponseAttendu() {
        return reponse_attendu;
    }

    public TypeReponse getTypeReponseAttendu(){
        return this.type_reponse_attendu;
    }

}
