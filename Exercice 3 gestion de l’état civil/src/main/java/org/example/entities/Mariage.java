package org.example.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Mariage {

    @EmbeddedId
    private MariagePk pk;

    @Temporal(TemporalType.DATE)
    private Date dateFin;

    private int nbrEnfant;

    @ManyToOne
    @JoinColumn(name = "homme", referencedColumnName = "id", insertable = false, updatable = false)
    private Homme homme;

    @ManyToOne
    @JoinColumn(name = "femme", referencedColumnName = "id", insertable = false, updatable = false)
    private Femme femme;

    public Mariage() {}

    public Mariage(Date dateDebut, Date dateFin, int nbrEnfant, Homme homme, Femme femme) {
        this.dateFin = dateFin;
        this.nbrEnfant = nbrEnfant;
        this.homme = homme;
        this.femme = femme;
        this.pk = new MariagePk(homme.getId(), femme.getId(), dateDebut);
    }

    public Date getDateDebut() { return pk.getDateDebut(); }

    public MariagePk getPk() { return pk; }
    public void setPk(MariagePk pk) { this.pk = pk; }

    public Date getDateFin() { return dateFin; }
    public void setDateFin(Date dateFin) { this.dateFin = dateFin; }

    public int getNbrEnfant() { return nbrEnfant; }
    public void setNbrEnfant(int nbrEnfant) { this.nbrEnfant = nbrEnfant; }

    public Homme getHomme() { return homme; }
    public void setHomme(Homme homme) { this.homme = homme; }

    public Femme getFemme() { return femme; }
    public void setFemme(Femme femme) { this.femme = femme; }
}