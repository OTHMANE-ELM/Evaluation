package org.example.service;

import org.example.entities.Personne;

public class PersonneService extends AbstractFacade<Personne>{

    public PersonneService() {
        super(Personne.class);
    }
}