package ci.miage.controller;

import ci.miage.utilis.ConnectionMysql;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ItemsController implements Initializable {
    Connection connection; //cnx
    public PreparedStatement preparedStatement; //st
    public ResultSet resultSet; //resul
    @FXML
    private MFXTextField btnadresse;

    @FXML
    private MFXDatePicker btndatenaissance;

    @FXML
    private MFXTextField btnemail;

    @FXML
    private MFXTextField btnlieunaissance;

    @FXML
    private MFXTextField btnnom;

    @FXML
    private MFXTextField btnprenom;

    @FXML
    private MFXComboBox<String> btnservice;
    public ObservableList<String> data = FXCollections.observableArrayList();
    @FXML
    private MFXTextField btntelephone;

    @FXML
    private MFXButton btnvalide;
    @FXML
    private MFXTextField btnpassword;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        connection = ConnectionMysql.connectionDB();
        remplir();
    }
    @FXML
    public void valide() {

        String nom = btnnom.getText();
        String prenom = btnprenom.getText();
        String email = btnemail.getText();
        String telephone = btntelephone.getText();
        String password = btnpassword.getText();
        String lieunaissance = btnlieunaissance.getText();
        String adresse = btnadresse.getText();
        String service = btnservice.getSelectedItem();

        String sql = "INSERT INTO responsable (nom,prenom,email,telephone,password,lieunaissance,adresse,service) VALUES (?,?,?,?,?,?,?,?)";
        if (!nom.equals("") && !prenom.equals("") && !email.equals("")) {
            try {
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setObject(1, nom);
                preparedStatement.setObject(2, prenom);
                preparedStatement.setObject(3, email);
                preparedStatement.setObject(4,telephone);
                preparedStatement.setObject(5,password);
                preparedStatement.setObject(6,lieunaissance);
                preparedStatement.setObject(7,adresse);
                preparedStatement.setObject(8,service);
                preparedStatement.executeUpdate();

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Merci",ButtonType.OK);
                alert.showAndWait();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING,"Veuillez remplir tous les champs", ButtonType.OK);
            alert.showAndWait();        }
    }



    public void remplir(){
        String sql = "SELECT * FROM service_responsable";
        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                String libelle = resultSet.getString("libelle").toString();
                data.add(libelle);
                btnservice.setItems(data);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
