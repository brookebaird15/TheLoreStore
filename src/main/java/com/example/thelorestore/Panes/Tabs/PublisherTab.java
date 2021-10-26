package com.example.thelorestore.Panes.Tabs;

import javafx.scene.control.Tab;

public class PublisherTab extends Tab {
    private static PublisherTab tab;

    private PublisherTab() {
        this.setText("Publisher");
    }

    public static PublisherTab getInstance() {
        if (tab == null) {
            tab = new PublisherTab();
        }
        return tab;
    }
}
