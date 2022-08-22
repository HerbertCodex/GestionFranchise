package ci.miage.modele;

public class produits {
    private int id_produit;
    private static int compt;
    private String nom_produit;

    private String categorie_produit;
    private int prix_achat;
    private int prix_vente;
    private String fournisseur;
    private int quantite;
    private String decription_produit;

    public produits(){};

    public produits(String nom_produit, String categorie_produit, int prix_achat, int prix_vente, String fournisseur, int quantite, String decription_produit) {
        this.id_produit = compt++;
        this.nom_produit = nom_produit;
        this.categorie_produit = categorie_produit;
        this.prix_achat = prix_achat;
        this.prix_vente = prix_vente;
        this.fournisseur = fournisseur;
        this.quantite = quantite;
        this.decription_produit = decription_produit;
    }

    public int getId_produit() {
        return id_produit;
    }

    public void setId_produit(int id_produit) {
        this.id_produit = id_produit;
    }

    public String getNom_produit() {
        return nom_produit;
    }

    public void setNom_produit(String nom_produit) {
        this.nom_produit = nom_produit;
    }



    public String getCategorie_produit() {
        return categorie_produit;
    }

    public void setCategorie_produit(String categorie_produit) {
        this.categorie_produit = categorie_produit;
    }

    public int getPrix_achat() {
        return prix_achat;
    }

    public void setPrix_achat(int prix_achat) {
        this.prix_achat = prix_achat;
    }

    public int getPrix_vente() {
        return prix_vente;
    }

    public void setPrix_vente(int prix_vente) {
        this.prix_vente = prix_vente;
    }

    public String getFournisseur() {
        return fournisseur;
    }

    public void setFournisseur(String fournisseur) {
        this.fournisseur = fournisseur;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public String getDecription_produit() {
        return decription_produit;
    }

    public void setDecription_produit(String decription_produit) {
        this.decription_produit = decription_produit;
    }
}
