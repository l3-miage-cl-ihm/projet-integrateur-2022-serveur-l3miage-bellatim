package com.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="indice", schema = "public")
public class Indice extends Etape{
    

    @Column
    private int cout;

    @ManyToOne
    private Question question;

    public Indice(int id, int rang, String label, int cout, Question question){
        super(id, rang, label);
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
