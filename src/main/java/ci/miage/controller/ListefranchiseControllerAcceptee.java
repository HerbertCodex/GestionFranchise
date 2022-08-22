package ci.miage.controller;


import ci.miage.modele.Franchise;
import ci.miage.modele.FranchiseAcceptee;
import ci.miage.utilis.ConnectionMysql;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

public class ListefranchiseControllerAcceptee implements Initializable {

    @FXML
    private TableColumn<FranchiseAcceptee, Void> colAction;

    @FXML
    private TableColumn<FranchiseAcceptee, String> colEmail;

    @FXML
    private TableColumn<FranchiseAcceptee, Integer> colId;

    @FXML
    private TableColumn<FranchiseAcceptee, String> colLocalisation;

    @FXML
    private TableColumn<FranchiseAcceptee, String> colNom;

    @FXML
    private TableColumn<FranchiseAcceptee, String> colNumero;

    @FXML
    private TableColumn<FranchiseAcceptee, String> colPassword;

    @FXML
    private TableColumn<FranchiseAcceptee, String> colPrenom;

    @FXML
    private TableView<FranchiseAcceptee> tableFranchise;
    @FXML
    private TableColumn<FranchiseAcceptee, String> colNomFranchise;
    Connection connection; //cnx
    public PreparedStatement preparedStatement; //st
    public ResultSet resultSet; //resul*
    public ObservableList<FranchiseAcceptee> data = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        connection = ConnectionMysql.connectionDB();
        show();
    }

    public void show() {
        String sql = "select id_utilisateur, nom,prenom, lieu_implantation,email,nom_franchise,telephone from Demande_A";
        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                FranchiseAcceptee franchise = new FranchiseAcceptee();
                franchise.setId(resultSet.getInt("id_utilisateur"));
                franchise.setNom(resultSet.getString("nom"));
                franchise.setPrenom(resultSet.getString("prenom"));
                franchise.setLocalisation(resultSet.getString("lieu_implantation"));
                franchise.setEmail(resultSet.getString("email"));
                franchise.setNom_franchise(resultSet.getString("nom_franchise"));
                franchise.setNumero(resultSet.getString("telephone"));

                addButton();
                data.add((franchise));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        colLocalisation.setCellValueFactory(new PropertyValueFactory<>("localisation"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
       colNomFranchise.setCellValueFactory(new PropertyValueFactory<>("nom_franchise"));
        colNumero.setCellValueFactory(new PropertyValueFactory<>("numero"));
        tableFranchise.setItems(data);
    }

    private void addButton(){
        Callback<TableColumn<FranchiseAcceptee, Void>, TableCell<FranchiseAcceptee, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<FranchiseAcceptee, Void> call(final TableColumn<FranchiseAcceptee, Void> param) {
                final TableCell<FranchiseAcceptee, Void> cell = new TableCell<>() {
                    private Button btnAccepter = new Button("Creer");
                    public ObservableList<Franchise> data1 = FXCollections.observableArrayList();
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
                                        "    -fx-font-size: 12px;" +
                                        "    -fx-padding: 10 20 10 20;" +
                                        "    -fx-padding: 10 20 10 20;" + " -fx-cursor: hand ;"
                        );

                        //Action
                        btnAccepter.setOnMouseClicked((MouseEvent event) -> {
                            try {
                                TablePosition selectedCells = tableFranchise.getSelectionModel().getSelectedCells().get(0);
                                int row = selectedCells.getRow();
                                FranchiseAcceptee franchise = tableFranchise.getItems().get(row);
                                TableColumn tableColumn = selectedCells.getTableColumn();
                                Object data =  tableColumn.getCellObservableValue(franchise).getValue();
                                System.out.println(data);
                                try {
                                    String sql1 = "select * from Demande_A where id_utilisateur = " + data;
                                    preparedStatement = connection.prepareStatement(sql1);
                                    resultSet = preparedStatement.executeQuery();
                                    if (resultSet.next()){
                                        String sql2 = "update Demande_A set nom_franchise =concat('franchise',' ',nom,' ',prenom,' ',numero_rccm) where id_utilisateur = " + data;
                                        String sql3 = "insert into franchise(email, lieu_implantation, numero_rccm,nom,prenom,pays,telephone,profession) select email, lieu_implantation,numero_rccm,nom,prenom,pays,telephone,profession from Demande_A where id_utilisateur = " + data;
                                        PreparedStatement preparedStatement1 = connection.prepareStatement(sql2);
                                        PreparedStatement preparedStatement2 = connection.prepareStatement(sql3);
                                        preparedStatement1.executeUpdate();
                                        preparedStatement2.executeUpdate();
                                        Alert alert = new Alert(Alert.AlertType.INFORMATION,"Franchise " + franchise.getNom_franchise().toUpperCase()+ " créée",ButtonType.OK);
                                        alert.showAndWait();
                                        System.out.println("Franchise créée");
                                    }

                                } catch (Exception e){
                                    e.printStackTrace();
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
        colAction.setCellFactory(cellFactory);
    }



   /* public void sendMail(String recepient) throws SQLException {
        System.out.println("Envoie de mail.....");
        Properties properties = new Properties();
        //activation de l'authentification
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.ssl.trust", "*");
        //L'adresse mail de la franchise
        final String myAccountEmail = "krakoffi57@gmail.com";
        final String password = "57126843Aa@";

        //creer une session
        Session session = Session.getDefaultInstance(properties,new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password);
            }});
        //prepare message
        Message message = preparedMessage(session, myAccountEmail,recepient);
        //envoie
        try {
            Transport.send(message);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Envoie de mail en cours",ButtonType.OK);
            alert.showAndWait();
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }*/
    /*private  Message preparedMessage(Session session, String myAccountEmail, String recepient) throws SQLException {
        FranchiseAcceptee franchiseAcceptee = new FranchiseAcceptee();
        String sql = "select * from Demande_A";
        String password = "";
        preparedStatement = connection.prepareStatement(sql);
        resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            password = resultSet.getString("pass");
        }
        String text = "votre mot de passe est: " + password;
        String object= "Recuperation de mot de passe";
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recepient));
            message.setSubject(object);
            String htmlcode = "<h1> " +text+"</h1> <h2><b> </b></h2>";
            message.setContent(htmlcode,"text/html");
            return message;
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }*/
}
