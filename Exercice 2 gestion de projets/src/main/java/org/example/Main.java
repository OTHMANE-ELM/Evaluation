package org.example;

import org.example.entities.*;
import org.example.service.*;

import java.text.SimpleDateFormat;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {

        EmployeService employeService = new EmployeService();
        ProjetService projetService = new ProjetService();
        TacheService tacheService = new TacheService();
        EmployeTacheService employeTacheService = new EmployeTacheService();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        System.out.println("========== DÉMARRAGE DES TESTS ==========");

        Employe emp1 = new Employe("Idrissi", "Othmane", "0661234567");
        Employe emp2 = new Employe("Benali", "Mama", "0697654321");
        employeService.create(emp1);
        employeService.create(emp2);

        Projet projet1 = new Projet("Bibliothèque Numérique", sdf.parse("2024-01-10"), sdf.parse("2024-11-30"), emp1);
        Projet projet2 = new Projet("Plateforme E-learning", sdf.parse("2024-04-01"), sdf.parse("2025-03-31"), emp2);
        projetService.create(projet1);
        projetService.create(projet2);

        Tache tache1 = new Tache("Analyse des besoins", sdf.parse("2024-01-15"), sdf.parse("2024-01-25"), 1800.0, projet1);
        Tache tache2 = new Tache("Conception de la base", sdf.parse("2024-02-01"), sdf.parse("2024-02-15"), 950.0, projet1);
        Tache tache3 = new Tache("Développement backend", sdf.parse("2024-03-01"), sdf.parse("2024-04-15"), 2400.0, projet1);
        Tache tache4 = new Tache("Intégration frontend", sdf.parse("2024-05-01"), sdf.parse("2024-07-01"), 1300.0, projet2);
        tacheService.create(tache1);
        tacheService.create(tache2);
        tacheService.create(tache3);
        tacheService.create(tache4);

        EmployeTache et1 = new EmployeTache(sdf.parse("2024-01-15"), sdf.parse("2024-01-25"), emp1, tache1);
        EmployeTache et2 = new EmployeTache(sdf.parse("2024-02-01"), sdf.parse("2024-02-15"), emp1, tache2);
        EmployeTache et3 = new EmployeTache(sdf.parse("2024-03-01"), sdf.parse("2024-04-15"), emp1, tache3);
        EmployeTache et4 = new EmployeTache(sdf.parse("2024-05-05"), null, emp2, tache4);
        employeTacheService.create(et1);
        employeTacheService.create(et2);
        employeTacheService.create(et3);
        employeTacheService.create(et4);

        emp1   = employeService.findById(emp1.getId());
        emp2   = employeService.findById(emp2.getId());
        tache1 = tacheService.findById(tache1.getId());
        tache2 = tacheService.findById(tache2.getId());
        tache3 = tacheService.findById(tache3.getId());
        tache4 = tacheService.findById(tache4.getId());

        projet1 = projetService.findById(projet1.getId());
        emp1    = employeService.findById(emp1.getId());

        System.out.println("\n>> Liste des tâches assignées à " + emp1.getPrenom() + " " + emp1.getNom());
        List<Tache> tachesEmp1 = employeService.findTachesByEmploye(emp1);
        for (Tache t : tachesEmp1) {
            System.out.println("  [" + t.getId() + "] " + t.getNom() + " — " + t.getPrix() + " DH");
        }

        System.out.println("\n>> Projets auxquels participe " + emp1.getPrenom() + " " + emp1.getNom());
        List<Projet> projetsEmp1 = employeService.findProjetsByEmploye(emp1);
        for (Projet p : projetsEmp1) {
            System.out.println("  [" + p.getId() + "] " + p.getNom());
        }

        System.out.println("\n>> Tâches rattachées au projet : " + projet1.getNom());
        List<Tache> tachesProjet1 = projetService.findTachesByProjet(projet1);
        for (Tache t : tachesProjet1) {
            System.out.println("  [" + t.getId() + "] " + t.getNom() + " — " + t.getPrix() + " DH");
        }

        System.out.println("\n>> Tâches effectivement réalisées sur : " + projet1.getNom());
        List<EmployeTache> tachesRealisees = projetService.findTachesRealiseesByProjet(projet1);
        for (EmployeTache et : tachesRealisees) {
            System.out.println("  [" + et.getTache().getId() + "] " + et.getTache().getNom()
                    + " | Début : " + new SimpleDateFormat("dd/MM/yyyy").format(et.getDateDebutReelle())
                    + " | Fin : "   + new SimpleDateFormat("dd/MM/yyyy").format(et.getDateFinReelle()));
        }

        System.out.println("\n>> Tâches dont le coût dépasse 1000 DH");
        List<Tache> tachesExpensives = tacheService.findTachesExpensives();
        for (Tache t : tachesExpensives) {
            System.out.println("  [" + t.getId() + "] " + t.getNom() + " — " + t.getPrix() + " DH");
        }

        System.out.println("\n>> Tâches planifiées entre le 01/01/2024 et le 01/05/2024");
        List<Tache> tachesBetween = tacheService.findTachesBetweenDates(
                sdf.parse("2024-01-01"),
                sdf.parse("2024-05-01")
        );
        for (Tache t : tachesBetween) {
            System.out.println("  [" + t.getId() + "] " + t.getNom()
                    + " | Date de fin : " + new SimpleDateFormat("dd/MM/yyyy").format(t.getDateFin()));
        }

        System.out.println("\n========== FIN DES TESTS ==========");
    }
}