package ci.miage.modele;

public class FranchiseRefusee {
    private int id;
    private static int compt;
    private String nom;
    private String prenom;
    private String localisation;
    private String email;
    private String password;
    private String numero;
    private String profession;
    private String annee;

    public FranchiseRefusee(){};
    public FranchiseRefusee(String nom, String prenom, String localisation, String email, String password, String numero,String profession,String annee) {
        this.id = compt++;
        this.nom = nom;
        this.prenom = prenom;
        this.localisation = localisation;
        this.email = email;
        this.password = password;
        this.numero = numero;
        this.profession = profession;
        this.annee = annee;
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

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getAnnee() {
        return annee;
    }

    public void setAnnee(String annee) {
        this.annee = annee;
    }
}
