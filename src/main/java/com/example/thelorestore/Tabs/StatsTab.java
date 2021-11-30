package com.example.thelorestore.Tabs;

import com.example.thelorestore.Pojo.Genre;
import com.example.thelorestore.Tables.GenreTable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Tab;

import java.util.ArrayList;

public class StatsTab extends Tab {
    private static StatsTab tab;
    public PieChart genreChart;
    public PieChart statusChart;

    private StatsTab() {
        this.setText("Statistics");
    }

    public void generateChart() {
        GenreTable genreTable = new GenreTable();

        ArrayList<Genre> genres = genreTable.getAllGenres();

        ArrayList<PieChart.Data> data = new ArrayList<>();

//        for()
    }

    public static StatsTab getInstance() {
        if (tab == null) {
            tab = new StatsTab();
        }
        return tab;
    }
}
