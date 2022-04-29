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

    public Question(int id, int rang, String label, int point, Defi defi) {
        super(id, rang, label, defi);
        this.point = point;
    }

    public Question(){
        super();
    }

}
