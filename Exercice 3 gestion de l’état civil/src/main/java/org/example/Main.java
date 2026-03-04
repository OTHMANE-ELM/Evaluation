package org.example;

import org.example.entities.Femme;
import org.example.entities.Homme;
import org.example.entities.Mariage;
import org.example.service.FemmeService;
import org.example.service.HommeService;
import org.example.service.MariageService;

import java.text.SimpleDateFormat;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        FemmeService   femmeService   = new FemmeService();
        HommeService   hommeService   = new HommeService();
        MariageService mariageService = new MariageService();

        System.out.println("========== Initialisation des données ==========");

        Femme f1  = new Femme("Cherkaoui", "Meryem",   "0611223344", "Casablanca", sdf.parse("1980-03-15"));
        Femme f2  = new Femme("Tahiri",    "Houda",    "0622334455", "Rabat",      sdf.parse("1975-07-22"));
        Femme f3  = new Femme("Moussaoui", "Chaymae",  "0633445566", "Fes",        sdf.parse("1990-01-10"));
        Femme f4  = new Femme("Ghazali",   "Asmae",    "0644556677", "Marrakech",  sdf.parse("1985-11-05"));
        Femme f5  = new Femme("Lahlou",    "Samira",   "0655667788", "Agadir",     sdf.parse("1970-06-30"));
        Femme f6  = new Femme("Bouazza",   "Kawtar",   "0666778899", "Tanger",     sdf.parse("1995-09-18"));
        Femme f7  = new Femme("Rahmouni",  "Hafsa",    "0677889900", "Meknes",     sdf.parse("1988-04-25"));
        Femme f8  = new Femme("Benhaddou", "Zineb",    "0688990011", "Oujda",      sdf.parse("1992-12-03"));
        Femme f9  = new Femme("Filali",    "Naoual",   "0699001122", "Kenitra",    sdf.parse("1983-08-14"));
        Femme f10 = new Femme("Oulhaj",    "Ikram",    "0610112233", "Tetouan",    sdf.parse("1978-02-28"));

        Homme h1 = new Homme("Othmani",  "Bilal",   "0621122334", "Casablanca", sdf.parse("1972-05-10"));
        Homme h2 = new Homme("Boutaleb", "Hamza",   "0632233445", "Rabat",      sdf.parse("1968-09-20"));
        Homme h3 = new Homme("Mernissi", "Anas",    "0643344556", "Fes",        sdf.parse("1980-03-05"));
        Homme h4 = new Homme("Rifai",    "Tariq",   "0654455667", "Marrakech",  sdf.parse("1975-11-15"));
        Homme h5 = new Homme("Mansouri", "Zakaria", "0665566778", "Agadir",     sdf.parse("1985-07-25"));

        femmeService.create(f1);  femmeService.create(f2);  femmeService.create(f3);
        femmeService.create(f4);  femmeService.create(f5);  femmeService.create(f6);
        femmeService.create(f7);  femmeService.create(f8);  femmeService.create(f9);
        femmeService.create(f10);

        hommeService.create(h1); hommeService.create(h2); hommeService.create(h3);
        hommeService.create(h4); hommeService.create(h5);

        System.out.println(">> 10 femmes et 5 hommes enregistrés avec succès.");

        Mariage m1  = new Mariage(sdf.parse("2000-06-01"), sdf.parse("2005-01-01"), 2, h1, f1);
        Mariage m2  = new Mariage(sdf.parse("2006-03-15"), sdf.parse("2010-07-20"), 1, h1, f2);
        Mariage m3  = new Mariage(sdf.parse("2011-09-10"), sdf.parse("2015-04-30"), 3, h1, f3);
        Mariage m4  = new Mariage(sdf.parse("2016-02-14"), null,                   2, h1, f4);
        Mariage m5  = new Mariage(sdf.parse("2005-05-20"), sdf.parse("2012-08-15"), 1, h2, f5);
        Mariage m6  = new Mariage(sdf.parse("2003-11-01"), sdf.parse("2009-03-10"), 2, h3, f5);
        Mariage m7  = new Mariage(sdf.parse("2010-07-07"), null,                   0, h3, f6);
        Mariage m8  = new Mariage(sdf.parse("2008-01-20"), sdf.parse("2014-06-30"), 1, h4, f6);
        Mariage m9  = new Mariage(sdf.parse("2015-03-22"), null,                   2, h4, f7);
        Mariage m10 = new Mariage(sdf.parse("2018-09-05"), null,                   1, h5, f8);

        mariageService.create(m1);  mariageService.create(m2);
        mariageService.create(m3);  mariageService.create(m4);
        mariageService.create(m5);  mariageService.create(m6);
        mariageService.create(m7);  mariageService.create(m8);
        mariageService.create(m9);  mariageService.create(m10);

        System.out.println(">> Mariages enregistrés avec succès.\n");

        System.out.println("========== Registre complet des femmes ==========");
        List<Femme> femmes = femmeService.findAll();
        for (Femme f : femmes) {
            System.out.println("  [" + f.getPrenom() + " " + f.getNom() + "]"
                    + " | Naissance : " + f.getDateNaissance()
                    + " | Tél : "       + f.getTelephone()
                    + " | Ville : "     + f.getAdresse());
        }

        System.out.println("\n========== Épouses de Bilal Othmani entre 2000 et 2020 ==========");
        List<Femme> epouses = hommeService.findEpouses(
                h1.getId(),
                sdf.parse("2000-01-01"),
                sdf.parse("2020-12-31")
        );
        for (Femme f : epouses) {
            System.out.println("  >> " + f.getPrenom() + " " + f.getNom());
        }

        System.out.println("\n========== Enfants de Meryem Cherkaoui entre 1999 et 2010 ==========");
        int nbEnfants = femmeService.findEnfants(
                f1.getId(),
                sdf.parse("1999-01-01"),
                sdf.parse("2010-12-31")
        );
        System.out.println("  >> Nombre d'enfants enregistrés : " + nbEnfants);

        System.out.println("\n========== Femmes ayant contracté au moins 2 mariages ==========");
        List<Femme> femmesDeuxFois = femmeService.findFemmesMarieesAuMoinsDeux();
        for (Femme f : femmesDeuxFois) {
            System.out.println("  >> " + f.getPrenom() + " " + f.getNom());
        }

        System.out.println("\n========== Hommes ayant épousé 4 femmes entre 2000 et 2020 ==========");
        long nbHommes = hommeService.getNbrHommesMariesQuatreFemmes(
                sdf.parse("2000-01-01"),
                sdf.parse("2020-12-31")
        );
        System.out.println("  >> Total trouvé : " + nbHommes + " homme(s)");

        System.out.println("\n========== Détail des mariages de Bilal Othmani ==========");
        hommeService.afficherMariagesByHomme(h1.getId());
    }
}