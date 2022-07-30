package ci.miage.controller;

import ci.miage.utilis.utilitaires;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    private Parent fxml;
    @FXML
    private MFXButton btnadmin;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
