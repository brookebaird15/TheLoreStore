package com.example.thelorestore.Panes.Tabs;

import javafx.scene.control.Tab;

public class GenreTab extends Tab {
    private static GenreTab tab;

    private GenreTab() {
        this.setText("Genre");
    }

    public static GenreTab getInstance() {
        if (tab == null) {
            tab = new GenreTab();
        }
        return tab;
    }
}
