package ci.miage.controller;

import ci.miage.utilis.utilitaires;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdministratifController implements Initializable {

    @FXML
    private AnchorPane pnl;
    private Parent fxml;

    @FXML
    void demande(MouseEvent event) throws IOException {
        fxml = FXMLLoader.load(getClass().getResource(utilitaires.urllistefranchisescreen));
        pnl.getChildren().removeAll();
        pnl.getChildren().setAll(fxml);
    }

    @FXML
    void franchiseA(MouseEvent event) {

    }

    @FXML
    void franchiseR(MouseEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            fxml = FXMLLoader.load(getClass().getResource(utilitaires.urllistefranchisescreen));
            pnl.getChildren().removeAll();
            pnl.getChildren().setAll(fxml);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
