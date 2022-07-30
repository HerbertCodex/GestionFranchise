package ci.miage.utilis;
import java.sql.*;

public class ConnectionMysql {
    public Connection con = null;

    public static Connection connectionDB(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gestion_franchise_back","herbert","root");
            System.out.println("Connexion reussie");
            return con;
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Connexion echouée");
            e.printStackTrace();
            return null;
        }
        }

    }

