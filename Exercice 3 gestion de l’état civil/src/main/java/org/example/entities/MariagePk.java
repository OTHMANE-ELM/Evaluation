package org.example.entities;

import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Embeddable
public class MariagePk implements Serializable {

    private Integer homme;
    private Integer femme;

    @Temporal(TemporalType.DATE)
    private Date DateDebut;

    public MariagePk() {}

    public MariagePk(Integer homme, Integer femme, Date dateDebut) {
        this.homme = homme;
        this.femme = femme;
        this.DateDebut = dateDebut;
    }

    public Integer getHomme() { return homme; }
    public void setHomme(Integer homme) { this.homme = homme; }

    public Integer getFemme() { return femme; }
    public void setFemme(Integer femme) { this.femme = femme; }

    public Date getDateDebut() { return DateDebut; }
    public void setDateDebut(Date dateDebut) { this.DateDebut = dateDebut; }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        MariagePk mariagePk = (MariagePk) o;
        return Objects.equals(homme, mariagePk.homme)
                && Objects.equals(femme, mariagePk.femme)
                && Objects.equals(DateDebut, mariagePk.DateDebut);
    }

    @Override
    public int hashCode() {
        return Objects.hash(homme, femme, DateDebut);
    }
}