package com.example.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@DiscriminatorValue("question")
@Table(name="question", schema="public")
public class Question extends Etape {
    

    @Column
    private int point;

    public Question(int id, int rang, String label,Defi def, int point) {
        super( rang, label, def);
        this.point = point;
    }

    public Question(){
        super();
    }

}
