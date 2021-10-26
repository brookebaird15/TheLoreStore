package com.example.thelorestore.Panes.Tabs;

import javafx.scene.control.Tab;

public class MainTab extends Tab {
    private static MainTab tab;

    private MainTab() {
        this.setText("All Items");
    }

    public static MainTab getInstance() {
        if (tab == null) {
            tab = new MainTab();
        }
        return tab;
    }
}
