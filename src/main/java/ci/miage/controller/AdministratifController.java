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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class AdministratifController implements Initializable {

    @FXML
    private AnchorPane pnl;
    private Parent fxml;
    @FXML
    private MFXButton btnDeconnexion;
    @FXML
    private Label lblHeure;
    @FXML
    private Label lblUsername;
    Connection connection; //cnx
    public PreparedStatement preparedStatement; //st
    public ResultSet resultSet; //resul
    private static String username = "";
    private static String accessLevel = "";


    @FXML
    void demande(MouseEvent event) throws IOException {
        fxml = FXMLLoader.load(getClass().getResource(utilitaires.urllistefranchisescreen));
        pnl.getChildren().removeAll();
        pnl.getChildren().setAll(fxml);
    }

    @FXML
    void franchiseA(MouseEvent event) throws IOException {
        fxml = FXMLLoader.load(getClass().getResource(utilitaires.urllistefranchiscreenacceptee));
        pnl.getChildren().removeAll();
        pnl.getChildren().setAll(fxml);
    }

    @FXML
    void franchiseR(MouseEvent event) throws IOException {
        fxml = FXMLLoader.load(getClass().getResource(utilitaires.urllistefranchiscreenrefusee));
        pnl.getChildren().removeAll();
        pnl.getChildren().setAll(fxml);
    }

    @FXML
    void ListeFranchises(MouseEvent event) throws IOException {
        fxml = FXMLLoader.load(getClass().getResource(utilitaires.urllistetoutefranchise));
        pnl.getChildren().removeAll();
        pnl.getChildren().setAll(fxml);
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
    @FXML
    void deconnexion(ActionEvent event) {
        Stage current = (Stage)btnDeconnexion.getScene().getWindow();
        current.close();
        try {
            // Setting login window
            Parent root = FXMLLoader.load(getClass().getResource(utilitaires.urlloginscreen));
            Scene scene = new Scene(root);
            Stage logInPrompt = new Stage();
            logInPrompt.setScene(scene);
            logInPrompt.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        username = LoginController.loggerUsername;
        accessLevel = LoginController.loggerAccessLevel;
        lblUsername.setText(username.toUpperCase());
        try {
            Runnable clock = new Runnable() {
                @Override
                public void run() {
                    runClock();
                }
            };
            Thread newClock = new Thread(clock); //Creating new thread
            newClock.setDaemon(true); //Thread will automatically close on applications closing
            newClock.start(); //Starting Thread
            fxml = FXMLLoader.load(getClass().getResource(utilitaires.urllistefranchisescreen));
            pnl.getChildren().removeAll();
            pnl.getChildren().setAll(fxml);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
