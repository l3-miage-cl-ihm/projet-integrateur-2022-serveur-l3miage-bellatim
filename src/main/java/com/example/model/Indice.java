package com.example.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@DiscriminatorValue("indice")
@Table(name="indice", schema = "public")
public class Indice extends Etape{
    

    @Column
    private int cout;


    public Indice(int rang, String label, Defi def, int cout){
        super(rang, label, def);
        this.cout = cout;
    }

    public Indice(){
        super();
    }

    public int getCout(){
        return cout;
    }

    public void setCout(int cout){
        this.cout = cout;
    }

}
