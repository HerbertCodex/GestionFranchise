package ci.miage.controller;

import ci.miage.modele.produits;
import ci.miage.utilis.ConnectionMysql;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AjouterArcticleController implements Initializable {
    Connection connection; //cnx
    public PreparedStatement preparedStatement; //st
    public ResultSet resultSet; //resul
    private Parent fxml;

    @FXML
    private MFXComboBox<String> colCategorieProduit;

    @FXML
    private TextArea colDescriptionProduit;

    @FXML
    private MFXTextField colFourniseurProduit;

    @FXML
    private MFXTextField colNomProduit;

    @FXML
    private MFXTextField colPrixAchat;

    @FXML
    private MFXTextField colPrixVente;

    @FXML
    private MFXTextField colQuantiteProduit;

    @FXML
    private TableColumn<produits, Void> tbColActionProduit;

    @FXML
    private TableColumn<produits, String> tbColCategorieProduit;

    @FXML
    private TableColumn<produits, String> tbColFournisseur;

    @FXML
    private TableColumn<produits, Integer> tbColIdProduit;

    @FXML
    private TableColumn<produits, String> tbColNomProduit;

    @FXML
    private TableColumn<produits, Integer> tbColPrixAchatProduit;

    @FXML
    private TableColumn<produits, Integer> tbColPrixVenteProduit;

    @FXML
    private TableColumn<produits, Integer> tbColQuantiteProduit;
    @FXML
    private TableView<produits> tableProduit;
    public ObservableList<String> data = FXCollections.observableArrayList();
    public ObservableList<produits> data2 = FXCollections.observableArrayList();


    @FXML
    void AjouterProduit(MouseEvent event) {
        String nom_produit = colNomProduit.getText();
        String categorie_produit = colCategorieProduit.getText();
        String prix_achat = colPrixAchat.getText();
        String prix_vente =colPrixVente.getText();
        String fournisseur_produit = colFourniseurProduit.getText();
        String quantite = colQuantiteProduit.getText();
        String decription_produit = colDescriptionProduit.getText();

        String sql = "insert into produits(nom_produit, categorie_produit, prix_achat_produit, prix_vente_produit, fournisseur_produit, quantite_produit, description_produit) values (?,?,?,?,?,?,?)";
        if (!nom_produit.equals("") && !prix_achat.equals("") && !prix_vente.equals("") && !fournisseur_produit.equals("")){
            try {
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setObject(1,nom_produit);
                preparedStatement.setObject(2,categorie_produit);
                preparedStatement.setInt(3, Integer.parseInt(prix_achat));
                preparedStatement.setInt(4, Integer.parseInt(prix_vente));
                preparedStatement.setObject(5,fournisseur_produit);
                preparedStatement.setInt(6, Integer.parseInt(quantite));
                preparedStatement.setObject(7,decription_produit);
                preparedStatement.executeUpdate();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Produit Ajouter avec success", ButtonType.OK);
                alert.showAndWait();
                colNomProduit.clear();
                colCategorieProduit.clear();
                colPrixAchat.clear();
                colPrixVente.clear();
                colFourniseurProduit.clear();
                colQuantiteProduit.clear();
                colDescriptionProduit.clear();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING,"Veuillez remplir tous les champs", ButtonType.OK);
            alert.showAndWait();        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        connection = ConnectionMysql.connectionDB();
        remplir();
        show();
    }

    public void remplir(){
        String sql = "SELECT * FROM categorie_produit";
        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                String libelle = resultSet.getString("lib");
                data.add(libelle);
                colCategorieProduit.setItems(data);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void show(){
        String sql = "select * from produits";
        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                produits produits = new produits();
                produits.setId_produit(resultSet.getInt("id_produit"));
                produits.setNom_produit(resultSet.getString("nom_produit"));
                produits.setCategorie_produit(resultSet.getString("categorie_produit"));
                produits.setPrix_achat(resultSet.getInt("prix_achat_produit"));
                produits.setPrix_vente(resultSet.getInt("prix_vente_produit"));
                produits.setFournisseur(resultSet.getString("fournisseur_produit"));
                produits.setQuantite(resultSet.getInt("quantite_produit"));
                //addButton();
                data2.add((produits));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        tbColIdProduit.setCellValueFactory(new PropertyValueFactory<>("id_produit"));
        tbColNomProduit.setCellValueFactory(new PropertyValueFactory<>("nom_produit"));
        tbColCategorieProduit.setCellValueFactory(new PropertyValueFactory<>("categorie_produit"));
        tbColPrixAchatProduit.setCellValueFactory(new PropertyValueFactory<>("prix_achat"));
        tbColPrixVenteProduit.setCellValueFactory(new PropertyValueFactory<>("prix_vente"));
        tbColFournisseur.setCellValueFactory(new PropertyValueFactory<>("fournisseur"));
        tbColQuantiteProduit.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        tableProduit.setItems(data2);
    }

    private void addButton(){
        Callback<TableColumn<produits, Void>, TableCell<produits, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<produits, Void> call(final TableColumn<produits, Void> param) {
                final TableCell<produits, Void> cell = new TableCell<>() {
                    private Button btnAccepter = new Button("Mise à jour");
                    public ObservableList<produits> data1 = FXCollections.observableArrayList();
                    {
                        btnAccepter.setStyle(
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
                                        "-fx-font-weight:bold;" +
                                        "    -fx-font-size: 10px;" +
                                        "    -fx-padding: 10 20 10 20;" +
                                        "    -fx-padding: 10 20 10 20;" + " -fx-cursor: hand ;"
                        );

                        //Action
                        btnAccepter.setOnMouseClicked((MouseEvent event) -> {
                           try {
                                TablePosition selectedCells = tableProduit.getSelectionModel().getSelectedCells().get(0);
                                int row = selectedCells.getRow();
                                produits produit = tableProduit.getItems().get(row);
                                TableColumn tableColumn = selectedCells.getTableColumn();
                                Object data =  tableColumn.getCellObservableValue(produit).getValue();
                                System.out.println(data);

                                String nom = colNomProduit.getText();
                                try {
                                    preparedStatement = connection.prepareStatement("update produits set nom_produit= ? where id_produit = " + data);
                                    preparedStatement.setString(1,nom);
                                    preparedStatement.executeUpdate();
                                    resultSet = preparedStatement.getResultSet();
                                    colNomProduit.setText(resultSet.getString("nom_produit"));
                                }catch (Exception e){

                                }


                            } catch (Exception e) {
                                e.getMessage();
                            }
                        });
                        /////
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            HBox managebtn = new HBox(btnAccepter);
                            managebtn.setStyle("-fx-alignment:center");
                            HBox.setMargin(btnAccepter, new Insets(2, 2, 0, 3));
                            setGraphic(managebtn);
                        }
                    }
                };
                return cell;
            }
        };
        tbColActionProduit.setCellFactory(cellFactory);
    }
}