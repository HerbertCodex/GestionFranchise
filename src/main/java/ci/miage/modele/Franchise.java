package ci.miage.modele;

public class Franchise {
    private String nom;
    private String prenom;
    private String commune;
    private String nomDemande;
    private String piece;

    public Franchise(){};
    public Franchise(String nom, String prenom, String commune, String nomDemande, String piece) {
        this.nom = nom;
        this.prenom = prenom;
        this.commune = commune;
        this.nomDemande = nomDemande;
        this.piece = piece;

    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getCommune() {
        return commune;
    }

    public void setCommune(String commune) {
        this.commune = commune;
    }

    public String getNomDemande() {
        return nomDemande;
    }

    public void setNomDemande(String nomDemande) {
        this.nomDemande = nomDemande;
    }

    public String getPiece() {
        return piece;
    }

    public void setPiece(String piece) {
        this.piece = piece;
    }

}
