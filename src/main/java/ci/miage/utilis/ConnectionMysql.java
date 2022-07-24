package ci.miage.utilis;
import java.sql.*;

public class ConnectionMysql {
    public Connection con = null;

    public static Connection connectionDB(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gestion_franchise_back","herbert","root");
            System.out.println("connexion reussie");
            return con;
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Connection echoué");
            e.printStackTrace();
            return null;
        }
        }

    }

