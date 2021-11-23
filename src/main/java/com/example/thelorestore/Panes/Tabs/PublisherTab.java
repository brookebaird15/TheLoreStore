package com.example.thelorestore.Panes.Tabs;

import com.example.thelorestore.Pojo.Publisher;
import com.example.thelorestore.Tables.PublisherTable;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;

public class PublisherTab extends Tab {
    private static PublisherTab tab;
    public TableView tableView;

    private PublisherTab() {
        this.setText("Publisher");
        PublisherTable publisherTable = new PublisherTable();
        BorderPane root = new BorderPane();
        tableView = new TableView();

        TableColumn<Publisher, String> publisherColumn = new TableColumn<>("Publisher");
        publisherColumn.setCellValueFactory(e-> new SimpleStringProperty(e.getValue().getCompanyName()));
        publisherColumn.setPrefWidth(1000);

        tableView.getColumns().addAll(publisherColumn);
        tableView.getItems().addAll(publisherTable.getAllPublishers());

        root.setCenter(tableView);

        this.setContent(root);
    }

    public static PublisherTab getInstance() {
        if (tab == null) {
            tab = new PublisherTab();
        }
        return tab;
    }
}
