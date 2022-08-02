package ci.miage.controller;

import ci.miage.utilis.ConnectionMysql;
import ci.miage.utilis.utilitaires;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    Connection connection; //cnx
    public PreparedStatement preparedStatement; //st
    public ResultSet resultSet; //resul
    private Parent fxml;
    @FXML
    private MFXButton btnadmin;
    @FXML
    private MFXComboBox<String> btnservice;

    @FXML
    private MFXButton btnconnexion;

    @FXML
    private MFXTextField champ_email;

    @FXML
    private MFXPasswordField champ_password;

    @FXML
    void connexion(ActionEvent event) {
        String sql = "select * from responsable where email = ? and password = ? and service = ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1,champ_email.getText());
            preparedStatement.setObject(2, champ_password.getText());
            preparedStatement.setObject(3, btnservice.getSelectedItem());

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                Alert alert = new Alert(Alert.AlertType.ERROR,"email ou mot de passe correct !", ButtonType.OK);
                alert.showAndWait();
                btnconnexion.getScene().getWindow().hide();
                Stage stageL = new Stage();
                try {
                    if (btnservice.getSelectedItem().equals("FINANCIER")){
                        fxml = FXMLLoader.load(getClass().getResource(utilitaires.urlfinancescreen));
                        Scene sceneL = new Scene(fxml);
                        stageL.setScene(sceneL);
                        stageL.setResizable(false);
                        stageL.show();
                        stageL.centerOnScreen();
                    } else if(btnservice.getSelectedItem().equals("ADMINISTRATIF")){
                        fxml = FXMLLoader.load(getClass().getResource(utilitaires.urlAdministratifscreen));
                        Scene sceneL = new Scene(fxml);
                        stageL.setScene(sceneL);
                        stageL.setResizable(false);
                        stageL.show();
                        stageL.centerOnScreen();
                    } else {
                        fxml = FXMLLoader.load(getClass().getResource(utilitaires.urlcommercialscreen));
                        Scene sceneL = new Scene(fxml);
                        stageL.setScene(sceneL);
                        stageL.setResizable(false);
                        stageL.show();
                        stageL.centerOnScreen();
                    }

                } catch (IOException e){
                    e.printStackTrace();
                }
            }else {
                Alert alert = new Alert(Alert.AlertType.ERROR,"email ou mot de passe incorrect !", ButtonType.OK);
                alert.showAndWait();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public ObservableList<String> data = FXCollections.observableArrayList();
    @FXML
    void openAdminscreen(ActionEvent event) throws IOException {
        Stage stage = (Stage) btnadmin.getScene().getWindow();
        stage.close();
        Stage adminStage = new Stage();
        fxml = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(utilitaires.urlloginAdminscreen)));
        adminStage.setResizable(false);
        Scene adminScenne = new Scene(fxml);
        adminStage.setScene(adminScenne);
        adminStage.setTitle("LoginAdmin");
        adminStage.show();
        adminStage.centerOnScreen();
    }

    public void remplir(){
        String sql = "SELECT * FROM service_responsable";
        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                String libelle = resultSet.getString("libelle");
                data.add(libelle);
                btnservice.setItems(data);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        connection = ConnectionMysql.connectionDB();
        remplir();
    }
}
