package ci.miage.controller;

import ci.miage.modele.Responsable;
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

public class ListeaccountController implements Initializable {
    Connection connection; //cnx
    public PreparedStatement preparedStatement; //st
    public ResultSet resultSet; //result

    //@FXML
    //private TableColumn<Responsable, String> colAction;
    @FXML
    private TableView<Responsable> tableAccount;

    @FXML
    private TableColumn<Responsable, String> colAdresse;

    @FXML
    private TableColumn<Responsable, String> colEmail;

    @FXML
    private TableColumn<Responsable, String> colNom;

    @FXML
    private TableColumn<Responsable, String> colPrenom;

    @FXML
    private TableColumn<Responsable, String> colService;

    @FXML
    private TableColumn<Responsable, String> colTelephone;
    public ObservableList<Responsable> data = FXCollections.observableArrayList();

    public void showResponsable(){
        String sql = "SELECT * FROM responsable";
        try{
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
               Responsable responsable = new Responsable();
               responsable.setNom(resultSet.getString("nom"));
                responsable.setPrenom(resultSet.getString("prenom"));
                responsable.setEmail(resultSet.getString("email"));
                responsable.setAdresse(resultSet.getString("adresse"));
                responsable.setTelephone(resultSet.getString("telephone"));
                responsable.setService(resultSet.getString("service"));
                data.add(responsable);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        colNom.setCellValueFactory(new PropertyValueFactory<Responsable,String>("nom"));
        colPrenom.setCellValueFactory(new PropertyValueFactory<Responsable,String>("prenom"));
        colEmail.setCellValueFactory(new PropertyValueFactory<Responsable,String>("email"));
        colAdresse.setCellValueFactory(new PropertyValueFactory<Responsable,String>("adresse"));
        colTelephone.setCellValueFactory(new PropertyValueFactory<Responsable,String>("telephone"));
        colService.setCellValueFactory(new PropertyValueFactory<Responsable,String>("service"));
        tableAccount.setItems(data);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        connection = ConnectionMysql.connectionDB();
        showResponsable();

    }
}
