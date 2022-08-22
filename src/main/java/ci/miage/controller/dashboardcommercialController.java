package ci.miage.controller;

import ci.miage.utilis.ConnectionMysql;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.chart.*;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class dashboardcommercialController implements Initializable {
    @FXML
    private PieChart pieChart;
    @FXML
    private AnchorPane panelGraph;
    @FXML
    private NumberAxis amountAxis;

    @FXML
    private CategoryAxis dateAxis;
    @FXML
    private Label lblStock;
    @FXML
    private Label lblTotalVentes;
    Connection connection; //cnx
    public PreparedStatement preparedStatement; //st
    public ResultSet resultSet; //resul*

    @FXML
    private LineChart<String, Integer> lineChart;
    private Parent fxml;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<PieChart.Data> pieChart =  FXCollections.observableArrayList(
                new PieChart.Data("Parfumerie",13),
                new PieChart.Data("Alimentaire",13),
                new PieChart.Data("Soins",13),
                new PieChart.Data("Beauté",13));
        PieChart pieChart1 = new PieChart(pieChart);
        pieChart1.setTitle("Quantité Categorie");
       panelGraph.getChildren().setAll(pieChart1);
       generateLineChart();
       showLabel();

    }
    private void generateLineChart(){
        lineChart.getData().clear();
        connection = ConnectionMysql.connectionDB();
        try {
            preparedStatement = connection.prepareStatement("select jour_vente, sum(montant) from vente_jour group by jour_vente order by unix_timestamp(jour_vente) desc limit 15");
            resultSet = preparedStatement.executeQuery();

            XYChart.Series chartData = new XYChart.Series<>();
            while (resultSet.next()){
                chartData.getData().add(new XYChart.Data<>(resultSet.getString(1), resultSet.getInt(2)));
            }
            lineChart.getData().addAll(chartData);
            panelGraph.getChildren().add(lineChart);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    void showLabel(){
        connection = ConnectionMysql.connectionDB();
        try {
            preparedStatement = connection.prepareStatement("select sum(p.quantite_produit) from produits p");
            PreparedStatement preparedStatement1 = connection.prepareStatement("select sum(v.montant) from vente_jour v");
            ResultSet resultSet1 = preparedStatement1.executeQuery();
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                lblStock.setText(resultSet.getString(1));
            }
            if (resultSet1.next()){
                lblTotalVentes.setText(resultSet1.getString(1) + " FCFA");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}