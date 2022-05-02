package com.example.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@DiscriminatorValue("indice")
@Table(name="indice", schema = "public")
public class Indice extends Etape{
    

    @Column
    private int cout;

    @ManyToOne
    private Question question;

    public Indice(int id, int rang, String label, Defi def, int cout, Question question){
        super(id, rang, label, def);
        this.cout = cout;
        this.question = question;
    }

    public Indice(){
        super();
    }

    public int getCout(){
        return cout;
    }

    public Question getQuestion(){
        return question;
    }

    public void setCout(int cout){
        this.cout = cout;
    }

}
