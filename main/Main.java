package main;

import composition.Composition;

public class Main {

    public static void main(String[] args) {
        try {
    // Code de Test de BddObject
            // Composition[] compositions = new Composition[10];
            // compositions[0] = new Composition("Maïs", true, false, 2000);
            // compositions[1] = new Composition("Levure", true, false, 5000);
            // compositions[2] = new Composition("Conservateur", true, false, 5000);
            // compositions[3] = new Composition("Orge", false, false, 0);
            // compositions[4] = new Composition("Sucre", true, false, 4000);
            // compositions[5] = new Composition("Eaux gazeux", false, false, 0);
            // compositions[6] = new Composition("Bière", false, true, 0);
            // compositions[7] = new Composition("Eau", true, false, 1000);
            // compositions[8] = new Composition("Gaz", true, false, 2000);
            // compositions[9] = new Composition("Arôme", true, false, 3000);
            // for (Composition composition : compositions) {
            //     composition.insert(null);
            // }
            Composition[] produits = Composition.getProduits(); // Tous les Produits dans la base de donnée
            for (Composition produit : produits) {
                System.out.println(produit.getPrixUnitaire());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
}
