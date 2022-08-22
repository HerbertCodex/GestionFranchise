package ci.miage.modele;

public class FranchiseAcceptee {
    private int id;
    private static int compt;
    private String nom;
    private String prenom;
    private String localisation;
    private String email;
    private String password;
    private String numero;
    private String nom_franchise;

    public FranchiseAcceptee(){};

    public FranchiseAcceptee(String nom, String prenom, String localisation, String email, String password, String numero, String nom_franchise) {
        this.id = compt++;
        this.nom = nom;
        this.prenom = prenom;
        this.localisation = localisation;
        this.email = email;
        this.password = password;
        this.numero = numero;
        this.nom_franchise = nom_franchise;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getNom_franchise() {
        return nom_franchise;
    }

    public void setNom_franchise(String nom_franchise) {
        this.nom_franchise = nom_franchise;
    }
}
