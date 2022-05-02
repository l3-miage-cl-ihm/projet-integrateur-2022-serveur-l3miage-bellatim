package com.example.model;

import java.util.Comparator;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type_etape")
@DiscriminatorValue("mere")
@Entity
@Table(name="etape", schema="public")
public class Etape {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    private int rang;

    @Column
    private String label;    

    // @ManyToOne(cascade = CascadeType.ALL)
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "defi_id")
    private Defi defi;

    public Etape(int rang, String label, Defi defi){
        super();
        this.rang = rang;
        this.label = label;
        this.defi = defi;
    }

    public Etape() {
        super();

    }

    public void setRang(int r){
        this.rang = r;
    }

    public void setLabel(String label){
        this.label = label;
    }

    public int getId(){
        return this.id;
    }

    public String getLabel(){
        return this.label;
    }

    public int getRang(){
        return this.rang;
    }

    public void setDefi(Defi defi){
        this.defi = defi;
    }

    public static Comparator<Etape> comparatorEtape = new Comparator<Etape>() {
        @Override
        public int compare(Etape e1, Etape e2){
            if(e1.getRang() == e2.getRang())
                return 0;   //il faudra mettre une erreur
            else
                return e1.getRang() > e2.getRang() ? -1 : 1;
        }
    };

    public String toString(){
        return "Etape : rang : " + this.rang + " label : " + this.label;
    }

    
}
