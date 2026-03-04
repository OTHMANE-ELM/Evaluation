package org.example.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity

@NamedQueries({
        @NamedQuery(name = "findTachesByEmploye", query = "SELECT et.tache FROM EmployeTache et WHERE et.employe = :employe"),
        @NamedQuery(name = "findTachesRealiseesByProjet", query = "SELECT et FROM EmployeTache et WHERE et.tache.projet = :projet AND et.dateFinReelle IS NOT NULL")
})


public class EmployeTache {
    @EmbeddedId
    private EmployeTachePk pk;

    @Temporal(TemporalType.DATE)
    private Date dateDebutReelle;

    @Temporal(TemporalType.DATE)
    private Date dateFinReelle;

    @ManyToOne
    @JoinColumn(name = "employe", insertable = false, updatable = false)
    private Employe employe;

    @ManyToOne
    @JoinColumn(name = "tache", insertable = false, updatable = false)
    private Tache tache;

    public EmployeTache() {
    }

    public EmployeTache(Date dateDebutReelle, Date dateFinReelle, Employe employe, Tache tache) {
        this.dateDebutReelle = dateDebutReelle;
        this.dateFinReelle = dateFinReelle;
        this.employe = employe;
        this.tache = tache;
        this.pk = new EmployeTachePk(employe.getId(), tache.getId());
    }

    public EmployeTachePk getPk() {
        return pk;
    }

    public void setPk(EmployeTachePk pk) {
        this.pk = pk;
    }

    public Date getDateDebutReelle() {
        return dateDebutReelle;
    }

    public void setDateDebutReelle(Date dateDebutReelle) {
        this.dateDebutReelle = dateDebutReelle;
    }

    public Date getDateFinReelle() {
        return dateFinReelle;
    }

    public void setDateFinReelle(Date dateFinReelle) {
        this.dateFinReelle = dateFinReelle;
    }

    public Employe getEmploye() {
        return employe;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }

    public Tache getTache() {
        return tache;
    }

    public void setTache(Tache tache) {
        this.tache = tache;
    }

}