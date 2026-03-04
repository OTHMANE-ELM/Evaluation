package org.example.entities;

import javax.persistence.*;
import java.util.List;

@Entity

@NamedQueries({
        @NamedQuery(name = "findByCategorie", query = "from Produit where categorie = :categorie"),
        @NamedQuery(name = "findBetweenDates", query = "select l.produit from LigneCommandeProduit l where l.commande.date between :d1 and :d2"),
        @NamedQuery(name = "findByCommande", query = "select l.produit from LigneCommandeProduit l where l.commande =:commande")
})

@NamedNativeQuery(name = "findByPrix", query = "select * from Produit where prix > :prix", resultClass = Produit.class)

public class Produit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String reference;
    private double prix;

    @ManyToOne
    private Categorie categorie;

    public Produit() {
    }

    public Produit(String reference, double prix, Categorie categorie) {
        this.reference = reference;
        this.prix = prix;
        this.categorie = categorie;
    }

    public int getId() {
        return id;
    }
    public String getReference() {
        return reference;
    }
    public double getPrix() {
        return prix;
    }
    public Categorie getCategorie() {
        return categorie;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setReference(String reference) {
        this.reference = reference;
    }
    public void setPrix(double prix) {
        this.prix = prix;
    }
    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }
}