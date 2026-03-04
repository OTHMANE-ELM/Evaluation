package org.example;

import org.example.entities.Produit;
import org.example.service.ProduitService;

import java.util.List;

public class TestCriteria {
    public static void main(String[] args) {
        ProduitService ps = new ProduitService();
        List<Produit> list = ps.findByNomCriteria("Les Misérables - Victor Hugo");

        for (Produit p : list) {
            System.out.println(p.getId() + " - " + p.getReference());
        }
    }
}