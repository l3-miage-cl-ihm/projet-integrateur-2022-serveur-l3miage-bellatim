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
    private String reponseAttendu;

    public Question(int rang, String label, Defi def, int point, List<Indice> l, String repAttendu) {
        super(rang, label, def);
        this.point = point;
        this.listeIndice = l;
        this.reponseAttendu = repAttendu;
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
        this.reponseAttendu = rep;
    }

    public int getPoint(){
        return this.point;
    }

    public List<Indice> getListeIndice(){
        return this.listeIndice;
    }

    public String getReponseAttendu() {
        return reponseAttendu;
    }

}
