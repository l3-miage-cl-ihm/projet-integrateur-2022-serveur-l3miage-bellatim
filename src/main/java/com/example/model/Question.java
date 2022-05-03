package com.example.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@DiscriminatorValue("question")
@Table(name="question", schema="public")
public class Question extends Etape {
    

    @Column
    private int point;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Indice> listeIndice;

    public Question(int rang, String label,Defi def, int point, List<Indice> l) {
        super(rang, label, def);
        this.point = point;
        this.listeIndice = l;
    }

    public Question(){
        super();
    }

    public void addIndice(Indice i){
        listeIndice.add(i);
    }

    public void removeIndice(Indice i){
        listeIndice.remove(i);
    }

}
