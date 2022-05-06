package com.example.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@DiscriminatorValue("media")
@Table(name = "media", schema = "public")
public class Media extends Etape {

    @Column
    private String URL;

    public Media(int rang, String label, String url) {
        super(rang, label);
        this.URL = url;
    }

    public Media() {
        super();
    }
}
