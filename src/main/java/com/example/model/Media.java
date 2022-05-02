package com.example.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@DiscriminatorValue("media")
@Table(name="media", schema = "public")
public class Media extends Etape{

    @Column
    private String URL;

    @Column
    private TypeMEDIA typeMedia;

    public Media(int rang, String label, Defi def, String url, TypeMEDIA type) {
        super(rang, label, def);
        this.typeMedia = type;
        this.URL = url;
    }

    public Media(){
        super();
    }
}
