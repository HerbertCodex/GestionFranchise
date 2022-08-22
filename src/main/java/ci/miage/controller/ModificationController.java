package ci.miage.controller;

import ci.miage.modele.produits;
import ci.miage.utilis.ConnectionMysql;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ModificationController implements Initializable {
    @FXML
    private MFXTextField txtNomModification;

    @FXML
    private MFXTextField txtPrixVenteModification;

    @FXML
    private MFXTextField txtQuantiteModification;
    public ObservableList<String> data = FXCollections.observableArrayList();

    Connection connection; //cnx
    public PreparedStatement preparedStatement; //st
    public ResultSet resultSet; //resul

    @FXML
    void btnModification(MouseEvent event) throws SQLException {
        produits produit = new produits();
        String nom = txtNomModification.getText();
        int prixVente = Integer.parseInt(txtPrixVenteModification.getText());
        int quantite = Integer.parseInt(txtQuantiteModification.getText());
        String sql = "update produits set nom_produit = '"+nom+"', quantite_produit = '"+quantite+"', prix_vente_produit = '"+prixVente+"' where id_produit = '"+produit.getId_produit()+"' ";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.executeUpdate();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        connection = ConnectionMysql.connectionDB();
    }
}
