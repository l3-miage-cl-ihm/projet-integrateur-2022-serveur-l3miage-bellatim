package com.example.model;

import java.util.List;

public class VisiteDTO {
    

    
    private String joueur;

    private int defi;

    private int rang;


 
    public VisiteDTO(String joueur, int defi, int rang){
        super();
        this.joueur = joueur;
        this.defi=defi;
        this.rang=rang;
    }
 

    public String getJoueur() {
        return this.joueur;
    }



    public int getDefi() {
        return this.defi;
    }

    public void setDefi(int defi) {
        this.defi = defi;
    }

    public int getRang() {
        return this.rang;
    }

    public void setRang(int rang) {
        this.rang = rang;
    }

}
