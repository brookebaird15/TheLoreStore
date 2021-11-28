package com.example.thelorestore.Tabs;

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
    private Button cancelButton;
    private Button saveButton;
    private Boolean updating = false;
//    private Button saveUpdateButton;

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
        tableView.getSelectionModel().selectFirst();

        root.setCenter(tableView);

        //textfield to allow user to input publisher info
        TextField publisherField = new TextField();
        publisherField.setVisible(false);

        //editButtons box holds add, save and update buttons
        HBox editButtons = new HBox();


        cancelButton = new Button("Cancel");
        cancelButton.setOnAction(e-> {
            publisherField.setVisible(false);
            saveButton.setVisible(false);
            cancelButton.setVisible(false);
            updatePubButton.setDisable(false);
            addPubButton.setDisable(false);
        });
        cancelButton.setVisible(false);


        //saveAddButton saves changes made with the add button
        saveButton = new Button("Save");
        saveButton.setOnAction(e-> {
            //TODO - catch exception where user entry is too long
            if(updating) {
                //TODO - need to retrieve item based on id - how?
                int index = tableView.getSelectionModel().getSelectedIndex() + 1;
                String pubName = publisherField.getText();
                Publisher publisher = new Publisher(index, pubName);
                publisherTable.updatePublisher(publisher);
                addPubButton.setDisable(false);
            } else {
                Publisher publisher = new Publisher(publisherField.getText());
                publisherTable.createPublisher(publisher);
                updatePubButton.setDisable(false);
            }
            //hide fields and buttons
            saveButton.setVisible(false);
            publisherField.setVisible(false);
            cancelButton.setVisible(false);

            //refresh table
            refreshPubTable();

            //set updating back to false
            updating = false;
        });
        saveButton.setVisible(false);

        //addPubButton allows user to add a publisher
        addPubButton = new Button("Add Publisher");
        addPubButton.setOnAction(e -> {
            publisherField.setVisible(true);
            saveButton.setVisible(true);
            updatePubButton.setDisable(true);
            cancelButton.setVisible(true);
        });

        //updatePubButton allows user to update a publisher
        updatePubButton = new Button("Update Publisher");
        updatePubButton.setOnAction(e -> {
            updating = true;
            publisherField.setText(tableView.getSelectionModel().getSelectedItem().toString());
            publisherField.setVisible(true);
            addPubButton.setDisable(true);
            cancelButton.setVisible(true);
            saveButton.setVisible(true);
        });

        editButtons.getChildren().addAll(addPubButton, saveButton, cancelButton, updatePubButton);
        editButtons.setAlignment(Pos.CENTER);

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
