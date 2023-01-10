package fabrication;

import java.sql.Date;
import composition.Composition;
import connection.BddObject;

public class Fabrication extends BddObject {

// Field 
    String idFabrication;
    String idComposition;
    double prixUnitaire;
    double quantite;
    Date date;
    
// Setter
    public void setIdFabrication(String idFabrication) { this.idFabrication = idFabrication; }
    public void setIdComposition(String idComposition) { this.idComposition = idComposition; }
    public void setQuantite(double quantite) { this.quantite = quantite; }
    public void setPrixUnitaire(double prixUnitaire) { this.prixUnitaire = prixUnitaire; }
    public void setDate(Date date) { this.date = date; }

// Getter
    public String getIdFabrication() { return idFabrication; }
    public String getIdComposition() { return idComposition; }
    public double getQuantite() { return quantite; }
    public double getPrixUnitaire() { return prixUnitaire; }
    public Date getDate() { return date; }

// Constructor
    public Fabrication() {
        // initialisation des attributs nécessaire pour BddObject
        setTable("Fabrications");
        setPrefix("FAB");
        setCountPK(7);
        setFunctionPK("getfabricationPK()");
    }

    public Fabrication(Composition produit, double quantite) throws Exception {
        this();
        setIdFabrication(buildPrimaryKey(getPostgreSQL()));
        setIdComposition(produit.getIdComposant());
        setQuantite(quantite);
        setPrixUnitaire(produit.getPrixUnitaire());
        setDate(new Date(System.currentTimeMillis()));
    }
}
