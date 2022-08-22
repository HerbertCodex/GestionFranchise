package ci.miage.modele;

public class ServiceResponsable {
    private int id;
    private String libele;
    private static int comp;
    public ServiceResponsable(String libele) {
        this.id= comp++;
        this.libele = libele;
    }

    public String getLibele() {
        return libele;
    }

}
