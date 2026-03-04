package org.example.entities;

import javax.persistence.*;

@Entity
public class LigneCommandeProduit {

    @EmbeddedId
    private CommandeProduitPk pk;
    private int quantite;

    @ManyToOne
    @JoinColumn(name = "produit", referencedColumnName = "id", insertable = false, updatable = false)
    private Produit produit;

    @ManyToOne
    @JoinColumn(name = "commande", referencedColumnName = "id", insertable = false, updatable = false)
    private Commande commande;

    public LigneCommandeProduit() {
    }

    public LigneCommandeProduit(int quantite, Produit produit, Commande commande) {
        this.quantite = quantite;
        this.produit = produit;
        this.commande = commande;
        this.pk = new CommandeProduitPk(commande.getId(), produit.getId()); // ✅
    }

    public CommandeProduitPk getPk() {
        return pk;
    }

    public void setPk(CommandeProduitPk pk) {
        this.pk = pk;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }
}