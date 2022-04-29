package com.example.model;

import java.util.Comparator;

import javax.persistence.*;

import org.springframework.context.annotation.Description;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data       //ces 3 lignes permettent d'avoir les getteurs et setteurs automatiquement
@Getter
@Setter
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
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

    public Etape(int id, int rang, String label){
        super();
        this.id = id;
        this.rang = rang;
        this.label = label;
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

    public static Comparator<Etape> comparatorEtape = new Comparator<Etape>() {
        @Override
        public int compare(Etape e1, Etape e2){
            if(e1.getRang() == e2.getRang())
                return 0;   //il faudra mettre une erreur
            else
                return e1.getRang() > e2.getRang() ? -1 : 1;
        }
    };
}
