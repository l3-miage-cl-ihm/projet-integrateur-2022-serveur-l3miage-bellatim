package com.example.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import java.time.LocalDateTime;


/*
 * @JsonIdentityInfo(
 * generator = ObjectIdGenerators.PropertyGenerator.class,
 * property="id")
 */
@Entity
@Table(name = "defi", schema = "public")
public class Defi {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    private String titre;

    @Column(columnDefinition = "TIMESTAMP") // type TIMESTAMP dans la base de données
    private LocalDateTime dateDeCreation;

    @ManyToOne
    private Chami auteur;

    @Column
    private String description;

    @Column
    @Enumerated(EnumType.STRING)
    private Categorie categorie;
    /*@Column
    private String categorie;*/

    @Column
    private String image; //url de l'image

    @Column
    private String coordonnees;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Etape> listEtape;

    public Defi() {
        super();
        listEtape = new ArrayList<>();
    }

    public Defi(int id, String titre, LocalDateTime dateDeCreation, String description, Chami auteur, Categorie cat,
            List<Etape> listEtape, String img, String coord) {
        super();
        this.id = id;
        this.titre = titre;
        this.dateDeCreation = dateDeCreation;
        this.description = description;
        this.listEtape = listEtape;
        this.categorie = cat;
        this.auteur = auteur;
        this.image = img;
        this.coordonnees = coord;
        // auteur.addDefis(this);
    }

    public int getId() {
        return this.id;
    }

    /*
     * public void setId(String id) {
     * this.id = id;
     * }
     */

    public String getTitre() {
        return this.titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setDateDeCreation(LocalDateTime date) {
        this.dateDeCreation = date;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public LocalDateTime getDateDeCreation() {
        return this.dateDeCreation;
    }
    public void setDateCreation(LocalDateTime d){
      this.dateDeCreation = d;
    }
    public List<Etape> getEtape() {
        return listEtape;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setEtape(List<Etape> l) {
        this.listEtape = l;
    }

    public void addEtape(Etape etape) {
        listEtape.add(etape);
    }

    public Chami getAuteur() {
        return this.auteur;
    }

    public void setImage(String i){
        this.image = i;
    }

    public void setCoordonnees(String c){
        this.coordonnees = c;
    }

    public String getCoordonnees(){
        return coordonnees;
    }
    public static Comparator<Etape> comparatorEtape = new Comparator<Etape>() {
        @Override
        public int compare(Etape e1, Etape e2) {
            if (e1.getRang() == e2.getRang())
                return 0; // il faudra mettre une erreur
            else
                return e1.getRang() > e2.getRang() ? -1 : 1;
        }
    };

    public String getImage(){
        return this.image;
    }

    /*
     * public void setAuteur(Chami auteur) {
     * this.auteur = auteur;
     * auteur.addDefisSimple(this);
     * }
     *
     * public void setSimpleAuteur(Chami auteur) {
     * this.auteur = auteur;
     * }
     */
}
