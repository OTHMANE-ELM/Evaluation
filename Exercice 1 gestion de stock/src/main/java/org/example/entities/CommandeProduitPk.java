package org.example.entities;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CommandeProduitPk implements Serializable {

    private Integer commande;
    private Integer produit;

    public CommandeProduitPk() {
    }

    public CommandeProduitPk(Integer commande, Integer produit) {
        this.commande = commande;
        this.produit = produit;
    }

    public Integer getCommande() {
        return commande;
    }

    public void setCommande(Integer commande) {
        this.commande = commande;
    }

    public Integer getProduit() {
        return produit;
    }

    public void setProduit(Integer produit) {
        this.produit = produit;
    }

    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        CommandeProduitPk that = (CommandeProduitPk) object;
        return java.util.Objects.equals(commande, that.commande) && java.util.Objects.equals(produit, that.produit);
    }

    public int hashCode() {
        return Objects.hash(super.hashCode(), commande, produit);
    }
}