package Panes;

import Scenes.MainTableScene;
import com.example.thelorestore.Launcher;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class AddItemPane extends StackPane {

    public AddItemPane() {
        VBox inputFields = new VBox();

        TextField title = new TextField();
        title.setPromptText("Title");
        TextField author = new TextField();
        author.setPromptText("Author");
        TextField genre = new TextField();
        genre.setPromptText("Genre");
        TextField publisher = new TextField();
        publisher.setPromptText("Publisher");
        TextField publishYear = new TextField();
        publishYear.setPromptText("Year Published");
        TextField quantity = new TextField();
        quantity.setPromptText("Stock Qty");

        HBox buttons = new HBox();

        //Add button saves info and returns user to Main Table
        Button addButton = new Button("Add Item");
        addButton.setOnAction(e -> {
            //TODO: create add button function
            Launcher.mainStage.setScene(new MainTableScene());
        });

        //Cancel button returns user to Main Table
        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction(e -> {
            Launcher.mainStage.setScene(new MainTableScene());
        });
        buttons.getChildren().addAll(addButton, cancelButton);

        inputFields.getChildren().addAll(title, author, genre, publisher, publishYear, quantity, buttons);
        inputFields.setAlignment(Pos.CENTER);




        this.getChildren().addAll(inputFields);

    }
}
