package ci.miage.modele;

public class Responsable {
    private int IdR;
    private static int compt;
    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private String password;
    private String lieunaissance;
    private String adresse;
    private String service;

    public Responsable(){};
    public Responsable(String nom, String prenom, String email, String telephone, String password, String lieunaissance, String adresse) {
        IdR = compt++;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
        this.password = password;
        this.lieunaissance = lieunaissance;
        this.adresse = adresse;
        this.service = service;
    }

    public int getIdR() {
        return IdR;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLieunaissance() {
        return lieunaissance;
    }

    public void setLieunaissance(String lieunaissance) {
        this.lieunaissance = lieunaissance;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }
}
