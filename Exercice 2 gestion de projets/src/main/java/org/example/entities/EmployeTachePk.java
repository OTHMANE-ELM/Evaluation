package org.example.entities;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class EmployeTachePk implements Serializable {
    private Integer employe;
    private Integer tache;

    public EmployeTachePk() {
    }

    public EmployeTachePk(Integer employe, Integer tache) {
        this.employe = employe;
        this.tache = tache;
    }

    public Integer getEmploye() {
        return employe;
    }

    public void setEmploye(Integer employe) {
        this.employe = employe;
    }

    public Integer getTache() {
        return tache;
    }

    public void setTache(Integer tache) {
        this.tache = tache;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        EmployeTachePk that = (EmployeTachePk) o;
        return Objects.equals(employe, that.employe) && Objects.equals(tache, that.tache);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employe, tache);
    }
}