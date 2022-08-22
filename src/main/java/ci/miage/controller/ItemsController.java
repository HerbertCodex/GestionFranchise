package ci.miage.controller;

import ci.miage.modele.Responsable;
import ci.miage.utilis.ConnectionMysql;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

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
    public ObservableList<Responsable> datatest = FXCollections.observableArrayList();
    @FXML
    private MFXTextField btntelephone;

    @FXML
    private MFXTextField btnpassword;

    @FXML
    private TableColumn<Responsable, String> colADRESSE;

    @FXML
    private TableColumn<Responsable, String> colEMAIL;

    @FXML
    private TableColumn<Responsable, Integer> colID;

    @FXML
    private TableColumn<Responsable, String> colNOM;

    @FXML
    private TableColumn<Responsable, String> colPRENOM;

    @FXML
    private TableColumn<Responsable, String> colSERVICE;

    @FXML
    private TableColumn<Responsable, String> colTELEPHONE;
    @FXML
    private TableView<Responsable> tableAccountMod;

    @FXML
    void modification(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        connection = ConnectionMysql.connectionDB();
        remplir();
        //show();
    }
    public void show(){
        String sql = "select * from responsable";
        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Responsable responsable = new Responsable();
                responsable.setIdR(resultSet.getInt("idR"));
                responsable.setNom(resultSet.getString("nom"));
                responsable.setPrenom(resultSet.getString("prenom"));
                responsable.setEmail(resultSet.getString("email"));
                responsable.setAdresse(resultSet.getString("adresse"));
                responsable.setTelephone(resultSet.getString("telephone"));
                responsable.setService(resultSet.getString("service"));
                datatest.add(responsable);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        colID.setCellValueFactory(new PropertyValueFactory<>("idR"));
        colNOM.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colPRENOM.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        colEMAIL.setCellValueFactory(new PropertyValueFactory<>("email"));
        colADRESSE.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        colTELEPHONE.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        colSERVICE.setCellValueFactory(new PropertyValueFactory<>("service"));
        tableAccountMod.setItems(datatest);
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



   /* public void table(){
        String sql = "SELECT nom, prenom, email, adresse, telephone, service FROM responsable";
        ObservableList<Responsable> dataRespo = FXCollections.observableArrayList();
        try {
            preparedStatement = connection.prepareStatement("sql");
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                Responsable responsable = new Responsable();
                responsable.setNom(resultSet.getString("nom"));
                dataRespo.add(responsable);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        tableAccountMod.setItems(dataRespo);
        colNOM.setCellValueFactory(new PropertyValueFactory<Responsable, String>("nom"));

    } */
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
}
