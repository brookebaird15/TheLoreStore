package Panes;

import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class AddItemPane extends StackPane {

    public AddItemPane() {
        VBox inputFields = new VBox();

        TextField title = new TextField();
        TextField author = new TextField();
        TextField genre = new TextField();
        TextField publisher = new TextField();
        TextField publishYear = new TextField();
        TextField quantity = new TextField();

        inputFields.getChildren().addAll(title, author, genre, publisher, publishYear, quantity);

        this.getChildren().addAll(inputFields);

    }
}
