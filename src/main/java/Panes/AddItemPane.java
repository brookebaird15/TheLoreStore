package Panes;

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
        Button addButton = new Button("Add Item");
        Button cancelButton = new Button("Cancel");
        buttons.getChildren().addAll(addButton, cancelButton);

        inputFields.getChildren().addAll(title, author, genre, publisher, publishYear, quantity, buttons);
        inputFields.setAlignment(Pos.CENTER);




        this.getChildren().addAll(inputFields);

    }
}
