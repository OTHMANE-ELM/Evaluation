package org.example.entities;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrimaryKeyJoinColumn;
import java.util.Date;

@Entity
@PrimaryKeyJoinColumn(name = "id")

@NamedQueries({
        @NamedQuery(name = "findEpouses", query = "select m.femme from Mariage m where m.homme.id = :h_id and m.pk.DateDebut between :d1 and :d2"),
        @NamedQuery(name = "findMariagesByHomme", query = "select m from Mariage m where m.homme.id = :h_id")
})

public class Homme extends Personne{

    public Homme() {
    }

    public Homme(String nom, String prenom, String telephone, String adresse, Date dateNaissance) {
        super(nom, prenom, telephone, adresse, dateNaissance);
    }


}