package ci.miage.controller;

import ci.miage.modele.Franchise;
import ci.miage.utilis.ConnectionMysql;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
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
    private TableColumn<Franchise, Integer> colId;

    @FXML
    private TableColumn<Franchise, String> colPrenom;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        connection = ConnectionMysql.connectionDB();
        show();
    }

    public void show() {
        //String sql = "select b.id_administrateur, b.nom, b.prenom, d.lib_demande, p.lib_piece, C2.nom_commune from AdministrateurFranchise b left join Compte c on b.id_administrateur = c.Administrateur_id_administrateur left join Demande d on c.Demande_id_demande = d.id_demande left join Piece p on d.Piece_id_piece = p.id_piece left join Franchise F on c.Franchise_id_franchise = F.Commune_id_commune left join Commune C2 on F.Commune_id_commune = C2.id_commune";
        String sql = "select id_utilisateur, nom,prenom, lieu_implantation,numero_rccm,type_piece from demande_utilisateur";
        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Franchise franchise = new Franchise();
                franchise.setNom(resultSet.getString("nom"));
                franchise.setPrenom(resultSet.getString("prenom"));
                franchise.setCommune(resultSet.getString("lieu_implantation"));
                franchise.setNomDemande(resultSet.getString("numero_rccm"));
                franchise.setPiece(resultSet.getString("type_piece"));
                franchise.setId((resultSet.getInt("id_utilisateur")));
                addButton();
                data.add((franchise));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        colCommune.setCellValueFactory(new PropertyValueFactory<>("commune"));
        colNomDemande.setCellValueFactory(new PropertyValueFactory<>("nomDemande"));
        colPiece.setCellValueFactory(new PropertyValueFactory<>("piece"));
        tableFranchise.setItems(data);
    }

    private void addButton(){
        Callback<TableColumn<Franchise, Void>, TableCell<Franchise, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<Franchise, Void> call(final TableColumn<Franchise, Void> param) {
                final TableCell<Franchise, Void> cell = new TableCell<>() {
                    private Button btnAccepter = new Button("Accepter");
                    private Button btnRefuser = new Button("Refuser");
                    public ObservableList<Franchise> data1 = FXCollections.observableArrayList();
                    {
                        btnRefuser.setStyle(
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
                                        "-fx-font-weight:bold;" +
                                        "    -fx-font-size: 12px;" +
                                        "    -fx-padding: 10 20 10 20;" + " -fx-cursor: hand ;"
                        );
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
                                        "    -fx-font-size: 12px;" +
                                        "    -fx-padding: 10 20 10 20;" +
                                        "    -fx-padding: 10 20 10 20;" + " -fx-cursor: hand ;"
                        );

                        //Action
                        btnAccepter.setOnMouseClicked((MouseEvent event) -> {
                            try {
                                TablePosition selectedCells = tableFranchise.getSelectionModel().getSelectedCells().get(0);
                                int row = selectedCells.getRow();
                                Franchise franchise = tableFranchise.getItems().get(row);
                                TableColumn tableColumn = selectedCells.getTableColumn();
                                Object data =  tableColumn.getCellObservableValue(franchise).getValue();
                                System.out.println(data);
                               try {
                                   if (btnAccepter.getText().equals("Accepter")){
                                       String sql1 = "insert into Demande_A  (nom_demande,nom,prenom,date_naissance,lieu_naissance,genre,situation_mat,lieu_hab,pays,email,profession,telephone,piece1,type_piece,piece2,lieu_implantation,numero_rccm,piece3,annee_exp,pass) select nom_demande,nom,prenom,date_naissance,lieu_naissance,genre,situation_mat,lieu_hab,pays,email,profession,telephone,piece1,type_piece,piece2,lieu_implantation,numero_rccm,piece3,annee_exp,pass from demande_utilisateur  where id_utilisateur = " + data;
                                       String sql2 = "delete from demande_utilisateur where id_utilisateur = " + data;
                                       preparedStatement = connection.prepareStatement(sql1);
                                       PreparedStatement preparedStatement2 = connection.prepareStatement(sql2);
                                       preparedStatement.executeUpdate();
                                       preparedStatement2.executeUpdate();
                                       System.out.println("test");

                                   }
                               } catch (Exception e){
                                   e.printStackTrace();
                               }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        });
                        /////
                        btnRefuser.setOnAction((ActionEvent e) -> {
                            try {
                                TablePosition selectedCells = tableFranchise.getSelectionModel().getSelectedCells().get(0);
                                int row = selectedCells.getRow();
                                Franchise franchise = tableFranchise.getItems().get(row);
                                TableColumn tableColumn = selectedCells.getTableColumn();
                                Object data =  tableColumn.getCellObservableValue(franchise).getValue();
                                System.out.println(data);
                                try {
                                    if (btnRefuser.getText().equals("Refuser")){
                                        String sql1 = "insert into Demande_R  (nom_demande,nom,prenom,date_naissance,lieu_naissance,genre,situation_mat,lieu_hab,pays,email,profession,telephone,piece1,type_piece,piece2,lieu_implantation,numero_rccm,piece3,annee_exp,pass) select nom_demande,nom,prenom,date_naissance,lieu_naissance,genre,situation_mat,lieu_hab,pays,email,profession,telephone,piece1,type_piece,piece2,lieu_implantation,numero_rccm,piece3,annee_exp,pass from demande_utilisateur  where id_utilisateur = " + data;
                                        String sql2 = "delete from demande_utilisateur where id_utilisateur = " + data;
                                        preparedStatement = connection.prepareStatement(sql1);
                                        PreparedStatement preparedStatement2 = connection.prepareStatement(sql2);
                                        preparedStatement.executeUpdate();
                                        preparedStatement2.executeUpdate();
                                        System.out.println("Refuser");
                                    }
                                } catch (Exception ex){
                                    ex.printStackTrace();
                                }
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            HBox managebtn = new HBox(btnAccepter, btnRefuser);
                            managebtn.setStyle("-fx-alignment:center");
                            HBox.setMargin(btnAccepter, new Insets(2, 2, 0, 3));
                            HBox.setMargin(btnRefuser, new Insets(2, 3, 0, 2));
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

