package ci.miage.controller;

import ci.miage.modele.FranchiseAcceptee;
import ci.miage.modele.FranchiseRefusee;
import ci.miage.utilis.ConnectionMysql;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ListefranchiseControllerRefusee implements Initializable {

    @FXML
    private TableColumn<FranchiseRefusee, String> colAnneeExperience;

    @FXML
    private TableColumn<FranchiseRefusee, String> colEmail;

    @FXML
    private TableColumn<FranchiseRefusee, Integer> colId;

    @FXML
    private TableColumn<FranchiseRefusee, String> colLocalisation;

    @FXML
    private TableColumn<FranchiseRefusee, String> colNom;

    @FXML
    private TableColumn<FranchiseRefusee, String> colNumero;

    @FXML
    private TableColumn<FranchiseRefusee, String> colPrenom;

    @FXML
    private TableColumn<FranchiseRefusee, String> colProfession;

    @FXML
    private TableView<FranchiseRefusee> tableFranchise;

    Connection connection; //cnx
    public PreparedStatement preparedStatement; //st
    public ResultSet resultSet; //resul*
    public ObservableList<FranchiseRefusee> data = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        connection = ConnectionMysql.connectionDB();
        show();
    }

    public void show() {
        String sql = "select * from Demande_R";
        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                FranchiseRefusee franchise = new FranchiseRefusee();
                franchise.setId(resultSet.getInt("id_refusee"));
                franchise.setNom(resultSet.getString("nom"));
                franchise.setPrenom(resultSet.getString("prenom"));
                franchise.setLocalisation(resultSet.getString("lieu_implantation"));
                franchise.setEmail(resultSet.getString("email"));
                franchise.setProfession(resultSet.getString("profession"));
                franchise.setNumero(resultSet.getString("telephone"));
                franchise.setAnnee(resultSet.getString("annee_exp"));
                data.add((franchise));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        colLocalisation.setCellValueFactory(new PropertyValueFactory<>("localisation"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colProfession.setCellValueFactory(new PropertyValueFactory<>("profession"));
        colNumero.setCellValueFactory(new PropertyValueFactory<>("numero"));
        colAnneeExperience.setCellValueFactory(new PropertyValueFactory<>("annee"));
        tableFranchise.setItems(data);
    }
}
