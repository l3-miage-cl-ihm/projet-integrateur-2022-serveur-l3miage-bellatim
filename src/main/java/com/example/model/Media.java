package com.example.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonTypeName;

@Entity
@DiscriminatorValue("media")
//@Table(name = "media", schema = "public")
@JsonTypeName("media")
public class Media extends Etape {

    @Column
    private String url;

    public Media(int rang, String label, String url) {
        super(rang, label);
        this.url = url;
    }

    public Media() {
        super();
    }
    public String getURL(){
        return this.url;
    }
}
