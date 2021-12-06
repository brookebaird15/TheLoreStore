package com.example.thelorestore.Tabs;

import com.example.thelorestore.Pojo.Publisher;
import com.example.thelorestore.Tables.PublisherTable;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

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

        Text warningText = new Text("Publisher name must be between 0 and 50 characters");
        warningText.setVisible(false);

        //editButtons box holds add, save and update buttons
        HBox editButtons = new HBox();

        //cancel button to cancel add/update
        cancelButton = new Button("Cancel");
        cancelButton.setOnAction(e-> {
            publisherField.setVisible(false);
            saveButton.setVisible(false);
            cancelButton.setVisible(false);
            updatePubButton.setDisable(false);
            addPubButton.setDisable(false);
        });
        cancelButton.setVisible(false);


        //save button to commit changes
        saveButton = new Button("Save");
        saveButton.setOnAction(e-> {
            boolean changesSaved = false;

            if(updating) {
                //check for valid input length
                if (publisherField.getText().length() <= 50 && publisherField.getText().length() != 0) {
                    Publisher selection = (Publisher) tableView.getSelectionModel().getSelectedItem();
                    selection.setCompanyName(publisherField.getText());
                    publisherTable.updatePublisher(selection);
                    changesSaved = true;
                } else {
                    warningText.setVisible(true);
                }
            } else {
                //check for valid input length
                if (publisherField.getText().length() <= 50 && publisherField.getText().length() != 0) {
                    Publisher publisher = new Publisher(publisherField.getText());
                    publisherTable.createPublisher(publisher);
                    changesSaved = true;
                } else {
                    warningText.setVisible(true);
                }
            }

            if(changesSaved) {
                //hide fields and buttons
                updatePubButton.setDisable(false);
                addPubButton.setDisable(false);
                saveButton.setVisible(false);
                publisherField.setVisible(false);
                publisherField.setText("");
                cancelButton.setVisible(false);
                warningText.setVisible(false);

                //refresh table
                refreshPubTable();

                //set updating back to false
                updating = false;
            }
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

        HBox confirmButtons = new HBox();
        confirmButtons.getChildren().addAll(saveButton, cancelButton);
        confirmButtons.setAlignment(Pos.CENTER);

        editButtons.getChildren().addAll(addPubButton, updatePubButton);
        editButtons.setAlignment(Pos.CENTER);

        //pubFields box holds buttons and field for entry
        VBox publisherFields = new VBox();
        publisherFields.getChildren().addAll(publisherField, editButtons, confirmButtons, warningText);
        publisherFields.setAlignment(Pos.CENTER);

        root.setBottom(publisherFields);

        this.setContent(root);
    }

    /**
     * Refreshes table with new or updated publisher
     */
    public void refreshPubTable() {
        PublisherTable table = new PublisherTable();
        tableView.getItems().clear();
        tableView.getItems().addAll(table.getAllPublishers());
    }

    /**
     * Generates new tab if one does not exist
     * @return PublisherTab
     */
    public static PublisherTab getInstance() {
        if (tab == null) {
            tab = new PublisherTab();
        }
        return tab;
    }
}
