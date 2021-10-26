package com.example.thelorestore.Panes.Tabs;

import javafx.scene.control.Tab;

public class AuthorTab extends Tab {
    private static AuthorTab tab;

    private AuthorTab() {
        this.setText("Author");
    }

    public static AuthorTab getInstance() {
        if (tab == null) {
            tab = new AuthorTab();
        }
        return tab;
    }
}
