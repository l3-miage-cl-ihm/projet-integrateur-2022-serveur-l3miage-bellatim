package com.example.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="question", schema="public")
@Data
@Getter
@Setter
public class Question extends Etape {
    

    @Column
    private int point;

    @OneToMany(mappedBy = "id")
    private List<Indice> indices;


    public Question(int id, int rang, String label, int point) {
        super(id, rang, label);
        this.point = point;
        indices = new ArrayList<>();
    }

    public Question(){
        super();
    }

}
