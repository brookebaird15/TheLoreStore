package com.example.thelorestore.Tabs;

import com.example.thelorestore.Pojo.Status;
import com.example.thelorestore.Tables.BookTable;
import com.example.thelorestore.Tables.StatusTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.layout.BorderPane;

import java.util.ArrayList;

public class StatusStatsTab extends Tab {
    private static StatusStatsTab tab;
    public PieChart statusChart;

    private StatusStatsTab() {
        this.setText("Status Stats");
        BorderPane root = new BorderPane();
        root.setId("status-stats");

        //generate chart
        statusChart = new PieChart();
        statusChart.setTitle("Completion Status");
        statusChart.setLabelsVisible(true);
        root.setCenter(statusChart);
        generateStatusChart();
        this.setContent(root);
    }

    /**
     * Generates a pie chart depicting the status distribution of books
     */
    public void generateStatusChart() {
        StatusTable statusTable = new StatusTable();
        BookTable bookTable = new BookTable();

        //list of all statuses
        ArrayList<Status> statuses = statusTable.getAllStatus();

        ArrayList<PieChart.Data> data = new ArrayList<>();
        for(Status status: statuses) {
            //for each status, count the number of occurrences in book table
            double count = bookTable.getStatusCount(status.getId());
            if (count > 0) {
                //add status name and count to data
                data.add(new PieChart.Data(status.getName().toUpperCase(), count));
            }
        }

        ObservableList<PieChart.Data> statusData = FXCollections.observableArrayList(data);
        statusChart.setData(statusData);
    }

    public static StatusStatsTab getInstance() {
        if (tab == null) {
            tab = new StatusStatsTab();
        }
        return tab;
    }
}
