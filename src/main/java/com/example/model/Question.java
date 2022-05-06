package com.example.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@DiscriminatorValue("question")
@Table(name = "question", schema = "public")
public class Question extends Etape {

    @Column
    private int point;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Indice> listeIndice;

    @Column
    private String reponseAttendu;

    public Question(int rang, String label, int point, List<Indice> l, String repAttendu) {
        super(rang, label);
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

    public String getReponseAttendu() {
        return reponseAttendu;
    }

}
