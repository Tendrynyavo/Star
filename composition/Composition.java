package composition;

import connection.BddObject;

public class Composition extends BddObject {

    String idComposant; // ID de ce composant
    String nom;
    boolean premiere, produit;
    double prixUnitaire = 0; // Les matières première ont des prix unitaires
    String idComposition; // ID pour avoir la composition de ce composant
    double quantite = 0;
    Composition[] composants;

// Getter
    public String getIdComposant() { return idComposant; }
    public String getNom() { return nom; }
    public boolean isPremiere() { return premiere; }
    public boolean isProduit() { return produit; }
    public String getIdComposition() { return idComposition; }
    public double getQuantite() { return quantite; }
    public double getPrixUnitaire() throws Exception {
        if (isPremiere()) return prixUnitaire;
        double somme = 0;
        for (Composition composant : decomposer())
            somme += composant.getPrixUnitaire() * composant.getQuantite();
        return somme;
    }

// Setter
    public void setIdComposant(String idComposant) { this.idComposant = idComposant; }
    public void setNom(String nom) { this.nom = nom; }
    public void setPrixUnitaire(double prixUnitaire) throws Exception {
        if (prixUnitaire < 0) throw new Exception("Montant prix unitaire invalide");
        this.prixUnitaire = prixUnitaire;
    }
    public void setPremiere(boolean premiere) { this.premiere = premiere; }
    public void setProduit(boolean produit) { this.produit = produit; }
    public void setIdComposition(String idComposition) { this.idComposition = idComposition; }
    public void setComposants(Composition[] composants) { this.composants = composants; }

// Constructor
    public Composition() {
        // initialisation des attributs nécessaire pour BddObject
        setTable("Composants");
        setPrefix("C");
        setCountPK(4);
        setFunctionPK("getcomposantsPK()");
    }

// Function
    public static Composition[] convert(Object[] objects) {
        Composition[] compositions = new Composition[objects.length];
        for (int i = 0; i < objects.length; i++) {
            compositions[i] = (Composition) objects[i];
        }
        return compositions;
    }

    public Composition[] decomposer() throws Exception {
        Composition composition = new Composition();
        composition.setTable("Melange"); // Melange est un VIEW dans la base qui retourne toutes les compositions
        composition.setIdComposition(getIdComposant());
        Composition[] composants = convert(composition.getData(BddObject.getPostgreSQL(), null, "idComposition"));
        setComposants(composants);
        return composants;
    }

    public static Composition[] getProduits() throws Exception {
        Composition composition = new Composition();
        composition.setTable("Produits"); // VIEW pour avoir tous les produits
        return convert(composition.getData(BddObject.getPostgreSQL(), null));
    }
}
