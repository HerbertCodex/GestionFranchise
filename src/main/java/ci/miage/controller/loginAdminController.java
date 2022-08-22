package ci.miage.controller;

import ci.miage.utilis.ConnectionMysql;
import ci.miage.utilis.utilitaires;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

public class loginAdminController implements Initializable {

    Connection connection; //cnx
    public PreparedStatement preparedStatement; //st
    public ResultSet resultSet; //result

    @FXML
    private MFXButton btn_connection;

    @FXML
    private MFXTextField txt_email;

    @FXML
    private MFXPasswordField txx_password;
    private Parent fxml;
    @FXML
    private AnchorPane adminpane;
    @FXML
    private MFXButton btnResponsable;

    @FXML
    void openHome(MouseEvent event) {
        String email = txt_email.getText();
        String pass = txx_password.getText();
        String sql = "select email, password from admin";
        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                if (email.equals(resultSet.getString("email"))&&pass.equals(resultSet.getString("password"))){
                    adminpane.getScene().getWindow().hide();
                    Stage stageA = new Stage();
                    try {
                        fxml = FXMLLoader.load(getClass().getResource(utilitaires.urladminscreen));
                        Scene sceneA = new Scene(fxml);
                        stageA.setScene(sceneA);
                        stageA.setResizable(false);
                        stageA.show();
                        stageA.centerOnScreen();
                    } catch (IOException e){
                        e.printStackTrace();
                }
            }else{
                    Alert alert = new Alert(Alert.AlertType.ERROR,"email ou mot de passe incorrect !", ButtonType.OK);
                    alert.showAndWait();
                }
        }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }


    @FXML
    void openResponsableScreen(ActionEvent event) throws IOException {
        Stage stage = (Stage) btnResponsable.getScene().getWindow();
        stage.close();
        Stage adminStage = new Stage();
        fxml = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(utilitaires.urlloginscreen)));
        adminStage.setResizable(false);
        Scene adminScenne = new Scene(fxml);
        adminStage.setScene(adminScenne);
        adminStage.setTitle("LoginAdmin");
        adminStage.show();
        adminStage.centerOnScreen();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        connection = ConnectionMysql.connectionDB();
    }
}
