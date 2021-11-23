package com.example.thelorestore.Panes.Tabs;

import com.example.thelorestore.Pojo.Publisher;
import com.example.thelorestore.Tables.PublisherTable;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class PublisherTab extends Tab {
    private static PublisherTab tab;
    public TableView tableView;
    private Button addPubButton;
    private Button updatePubButton;

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

        //textfield to allow user to input publisher info
        TextField publisherField = new TextField();
        publisherField.setVisible(false);

        //editButtons box holds add, save and update buttons
        HBox editButtons = new HBox();

        //TODO - add button to cancel add and update

        //saveAddButton saves changes made with the add button
        Button saveAddButton = new Button("Save");
        saveAddButton.setOnAction(e-> {
            Publisher publisher = new Publisher(publisherField.getText());
            publisherTable.createPublisher(publisher);
            saveAddButton.setVisible(false);
            publisherField.setVisible(false);
            refreshPubTable();
            updatePubButton.setDisable(false);
        });
        saveAddButton.setVisible(false);

        //addPubButton allows user to add a publisher
        addPubButton = new Button("Add Publisher");
        addPubButton.setOnAction(e -> {
            publisherField.setVisible(true);
            saveAddButton.setVisible(true);
            updatePubButton.setDisable(true);
        });

        //saveUpdateButton saves changes made with the update button
        Button saveUpdateButton = new Button("Save");
        saveUpdateButton.setOnAction(e-> {
            int index = tableView.getSelectionModel().getSelectedIndex() + 1;
            String pubName = publisherField.getText();
            Publisher publisher = new Publisher(index, pubName);
            publisherTable.updatePublisher(publisher);
            saveAddButton.setVisible(false);
            publisherField.setVisible(false);
            refreshPubTable();
            addPubButton.setDisable(false);
        });
        saveUpdateButton.setVisible(false);

        //updatePubButton allows user to update a publisher
        updatePubButton = new Button("Update Publisher");
        updatePubButton.setOnAction(e -> {
            publisherField.setText(tableView.getSelectionModel().getSelectedItem().toString());
            publisherField.setVisible(true);
            saveUpdateButton.setVisible(true);
            addPubButton.setDisable(true);
        });

        editButtons.getChildren().addAll(addPubButton, saveAddButton, saveUpdateButton, updatePubButton);
        editButtons.setAlignment(Pos.CENTER);
        editButtons.requestFocus();

        //pubFields box holds buttons and field for entry
        VBox publisherFields = new VBox();
        publisherFields.getChildren().addAll(publisherField, editButtons);

        root.setBottom(publisherFields);

        this.setContent(root);
    }

    public void refreshPubTable() {
        PublisherTable table = new PublisherTable();
        tableView.getItems().clear();
        tableView.getItems().addAll(table.getAllPublishers());
    }

    public static PublisherTab getInstance() {
        if (tab == null) {
            tab = new PublisherTab();
        }
        return tab;
    }
}
