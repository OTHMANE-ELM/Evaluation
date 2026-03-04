package org.example;

import org.example.entities.*;
import org.example.service.*;

import java.text.SimpleDateFormat;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {

        CategorieService cs = new CategorieService();
        ProduitService ps = new ProduitService();
        CommandeService coms = new CommandeService();
        LigneCommandeProduitService ls = new LigneCommandeProduitService();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        System.out.println("=========== TEST COMMANDE ===========");

        Categorie c = new Categorie("LIBRAIRIE", "Livres et littérature française");
        cs.create(c);

        Produit p1 = new Produit("Les Misérables - Victor Hugo", 350, c);
        Produit p2 = new Produit("Germinal - Émile Zola", 280, c);

        ps.create(p1);
        ps.create(p2);

        Commande cmd = new Commande();
        cmd.setDate(sdf.parse("2024-03-10"));
        coms.create(cmd);

        LigneCommandeProduit l1 = new LigneCommandeProduit(3, p1, cmd);
        LigneCommandeProduit l2 = new LigneCommandeProduit(2, p2, cmd);

        ls.create(l1);
        ls.create(l2);

        System.out.println("\n--- Produits commandés entre 2022 et 2025 ---");

        List<Produit> produits =
                ps.findBetweenDates(
                        sdf.parse("2022-01-01"),
                        sdf.parse("2025-12-31")
                );

        for (Produit p : produits) {
            System.out.println("Produit : " + p.getReference());
        }

        System.out.println("\n=========== FIN TEST ===========");
    }
}