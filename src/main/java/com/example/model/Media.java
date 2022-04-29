package com.example.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
@Entity
@Table(name="media", schema = "public")
public class Media extends Etape{

    public Media(int id, int rang, String label) {
        super(id, rang, label);
        //TODO Auto-generated constructor stub
    }

    public Media(){
        super();
    }

    @Column
    private String URL;

    @Column
    private TypeMEDIA typeMedia;
}
