package ci.miage.controller;

import ci.miage.utilis.utilitaires;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class AdminController implements Initializable {

    @FXML
    private AnchorPane pnl;

    private Parent fxml;
    @FXML
    private MFXButton btnDeconnexion;

    @FXML
    private Label lblHeure;

    @FXML
    private Label lblUsername;

    @FXML
    void account(MouseEvent event) throws IOException {
        fxml = FXMLLoader.load(getClass().getResource(utilitaires.urlitemsscreen));
        pnl.getChildren().removeAll();
        pnl.getChildren().setAll(fxml);
    }

    @FXML
    void listeaccount(MouseEvent event) throws IOException {
        fxml = FXMLLoader.load(getClass().getResource(utilitaires.urllisteaccountscreen));
        pnl.getChildren().removeAll();
        pnl.getChildren().setAll(fxml);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblUsername.setText("admin");
        Runnable clock = new Runnable() {
            @Override
            public void run() {
                runClock();
            }
        };
        Thread newClock = new Thread(clock); //Creating new thread
        newClock.setDaemon(true); //Thread will automatically close on applications closing
        newClock.start(); //Starting Thread

        try {
            fxml = FXMLLoader.load(getClass().getResource(utilitaires.urlitemsscreen));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        pnl.getChildren().removeAll();
            pnl.getChildren().setAll(fxml);
    }

    public void deconnexion(ActionEvent actionEvent) {

        Stage current = (Stage)btnDeconnexion.getScene().getWindow();
        current.close();
        try {
            // Setting login window
            Parent root = FXMLLoader.load(getClass().getResource(utilitaires.urlloginAdminscreen));
            Scene scene = new Scene(root);
            Stage logInPrompt = new Stage();
            logInPrompt.setScene(scene);
            logInPrompt.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void runClock() {
        while (true) {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    // Getting the system time in a string
                    String time = LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm:ss a"));
                    // Setting the time in a label
                    lblHeure.setText(time);
                }
            });

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
