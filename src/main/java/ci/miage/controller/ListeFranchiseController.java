package ci.miage.controller;

import ci.miage.modele.Franchise;
import ci.miage.utilis.ConnectionMysql;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import org.controlsfx.glyphfont.FontAwesome;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ListeFranchiseController implements Initializable {
    Connection connection; //cnx
    public PreparedStatement preparedStatement; //st
    public ResultSet resultSet; //resul*
    public ObservableList<Franchise> data = FXCollections.observableArrayList();
    @FXML
    private TableView<Franchise> tableFranchise;

    @FXML
    private TableColumn<Franchise, Void> colAction;

    @FXML
    private TableColumn<Franchise, String> colCommune;

    @FXML
    private TableColumn<Franchise, String> colNom;

    @FXML
    private TableColumn<Franchise, String> colNomDemande;

    @FXML
    private TableColumn<Franchise, String> colPiece;

    @FXML
    private TableColumn<Franchise, String> colPrenom;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        connection = ConnectionMysql.connectionDB();
        show();
    }

    public void show() {
        String sql = "select * from administrateurFranchise,commune,demande,piece";
        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Franchise franchise = new Franchise();
                franchise.setNom(resultSet.getString("nom"));
                franchise.setPrenom(resultSet.getString("prenom"));
                franchise.setCommune(resultSet.getString("nomCommune"));
                franchise.setNomDemande(resultSet.getString("nomDemande"));
                franchise.setPiece(resultSet.getString("nomPiece"));
                addButton();
                data.add((franchise));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        colNom.setCellValueFactory(new PropertyValueFactory<Franchise, String>("nom"));
        colPrenom.setCellValueFactory(new PropertyValueFactory<Franchise, String>("prenom"));
        colCommune.setCellValueFactory(new PropertyValueFactory<Franchise, String>("commune"));
        colNomDemande.setCellValueFactory(new PropertyValueFactory<Franchise, String>("nomDemande"));
        colPiece.setCellValueFactory(new PropertyValueFactory<Franchise, String>("piece"));
        tableFranchise.setItems(data);
    }

    private void addButton(){
        Callback<TableColumn<Franchise, Void>, TableCell<Franchise, Void>> cellFactory = new Callback<TableColumn<Franchise, Void>, TableCell<Franchise, Void>>() {
            @Override
            public TableCell<Franchise, Void> call(final TableColumn<Franchise, Void> param) {
                final TableCell<Franchise, Void> cell = new TableCell<Franchise, Void>() {
                   private Button btn = new Button("Accepter");
                   private Button btn1 = new Button("Refuser");
                    {
                        btn1.setStyle(
                                        "-fx-background-color:" +
                                                "        #090a0c," +
                                                "        linear-gradient(#38424b 0%, #1f2429 20%, #191d22 100%)," +
                                                "        linear-gradient(#ff5400, #be1d00)," +
                                                "        radial-gradient(center 50% 0%, radius 100%, rgba(223,27,27), rgba(255,255,255,0));" +
                                                "    -fx-background-radius: 5,4,3,5;" +
                                                "    -fx-background-insets: 0,1,2,0;" +
                                                "    -fx-text-fill: white;" +
                                                "    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );" +
                                                "    -fx-font-family: \"Arial\";" +
                                                "    -fx-text-fill: linear-gradient(white, #d0d0d0);" +
                                                "-fx-font-weight:bold;"+
                                                "    -fx-font-size: 12px;" +
                                                "    -fx-padding: 10 20 10 20;"+" -fx-cursor: hand ;"
                        );
                        btn1.setOnAction((ActionEvent e) ->{
                            System.out.println("btn 1");
                        });
                        btn.setStyle(
                                "-fx-background-color:" +
                                        "        #090a0c," +
                                        "        linear-gradient(#38424b 0%, #1f2429 20%, #191d22 100%)," +
                                        "        linear-gradient(#008000, #008000)," +
                                        "        radial-gradient(center 50% 0%, radius 100%, rgba(0, 150, 0), rgba(255,255,255,0));" +
                                        "    -fx-background-radius: 5,4,3,5;" +
                                        "    -fx-background-insets: 0,1,2,0;" +
                                        "    -fx-text-fill: white;" +
                                        "    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );" +
                                        "    -fx-font-family: \"Arial\";" +
                                        "    -fx-text-fill: linear-gradient(white, #d0d0d0);" +
                                        "-fx-font-weight:bold;"+
                                        "    -fx-font-size: 12px;" +
                                        "    -fx-padding: 10 20 10 20;" +
                                        "    -fx-padding: 10 20 10 20;"+" -fx-cursor: hand ;"
                        );
                        btn.setOnAction((ActionEvent event) -> {
                            System.out.println("print");
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            HBox managebtn = new HBox(btn, btn1);
                            managebtn.setStyle("-fx-alignment:center");
                            HBox.setMargin(btn, new Insets(2, 2, 0, 3));
                            HBox.setMargin(btn1, new Insets(2, 3, 0, 2));
                            setGraphic(managebtn);

                        }
                    }
                };
                return cell;
            }
        };
        colAction.setCellFactory(cellFactory);
    }

}
