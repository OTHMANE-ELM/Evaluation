package org.example.entities;

import javax.persistence.Entity;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQuery;
import javax.persistence.PrimaryKeyJoinColumn;
import java.sql.ResultSet;
import java.util.Date;

@Entity
@PrimaryKeyJoinColumn(name = "id")

@NamedNativeQuery(name = "findEnfants", query = "select sum(m.nbrEnfant) from Mariage m where m.femme = :f_id and m.DateDebut between :d1 and :d2")
@NamedQuery(name = "findFemmesMarieesAuMoinsDeux", query = "select m.femme from Mariage m group by m.femme having count(m.femme) >= 2")
public class Femme extends Personne{

    public Femme() {
    }

    public Femme(String nom, String prenom, String telephone, String adresse, Date dateNaissance) {
        super(nom, prenom, telephone, adresse, dateNaissance);
    }
}