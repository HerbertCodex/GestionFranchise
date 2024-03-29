package ci.miage.controller;

import ci.miage.utilis.utilitaires;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class SplashController implements Initializable {
    private AnchorPane borderPane;
    @FXML
    private AnchorPane pane;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        splash();
    }

    private void splash(){
       new Thread(new Runnable() {
           @Override
           public void run() {
               try {
                   Thread.sleep(4000);
                   Platform.runLater(new Runnable() {
                       @Override
                       public void run() {
                           try {
                               borderPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(utilitaires.urlloginscreen)));
                               Stage stageLogin = new Stage();
                               Scene sceneLogin = new Scene(borderPane);
                               stageLogin.setTitle("Login");
                               stageLogin.setResizable(false);
                               stageLogin.setScene(sceneLogin);
                               stageLogin.show();
                               pane.getScene().getWindow().hide();
                           } catch (IOException e) {
                               e.printStackTrace();
                           }
                       }
                   });
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
       }).start();
    }
}
