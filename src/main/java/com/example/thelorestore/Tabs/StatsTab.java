package com.example.thelorestore.Tabs;

import javafx.scene.chart.PieChart;
import javafx.scene.control.Tab;

public class StatsTab extends Tab {
    private static StatsTab tab;
    public PieChart genreChart;
    public PieChart statusChart;

    private StatsTab() {
        this.setText("Statistics");
    }

    public static StatsTab getInstance() {
        if (tab == null) {
            tab = new StatsTab();
        }
        return tab;
    }
}
