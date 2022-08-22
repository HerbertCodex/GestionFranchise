package ci.miage.controller;

import ci.miage.utilis.ConnectionMysql;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DashboardFinance implements Initializable {

    Connection connection; //cnx
    public PreparedStatement preparedStatement; //st
    public ResultSet resultSet; //resul*
    @FXML
    private Label lblChiffreAffaire;
    @FXML
    private NumberAxis amountAxis;
    @FXML
    private AnchorPane panelgraph1;

    @FXML
    private CategoryAxis dateAxis;
    @FXML
    private LineChart<String , Integer> lineChart;



    void showLabel(){
        connection = ConnectionMysql.connectionDB();
        try {
            preparedStatement = connection.prepareStatement("select sum(c.solde_jour) from caisse c");
            PreparedStatement preparedStatement1 = connection.prepareStatement("select sum(v.montant) from vente_jour v");
            ResultSet resultSet1 = preparedStatement1.executeQuery();
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                lblChiffreAffaire.setText(resultSet.getString(1));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void generateLineChart(){
        lineChart.getData().clear();
        connection = ConnectionMysql.connectionDB();
        try {
            preparedStatement = connection.prepareStatement("select date_solde, sum(solde_jour) from caisse group by date_solde order by unix_timestamp(date_solde) desc limit 15");
            resultSet = preparedStatement.executeQuery();

            XYChart.Series chartData = new XYChart.Series<>();
            while (resultSet.next()){
                chartData.getData().add(new XYChart.Data<>(resultSet.getString(1), resultSet.getInt(2)));
            }
            lineChart.getData().addAll(chartData);
            panelgraph1.getChildren().setAll(lineChart);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showLabel();
        generateLineChart();
    }
}
