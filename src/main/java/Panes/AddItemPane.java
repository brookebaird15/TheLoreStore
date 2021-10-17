package Panes;

import Scenes.MainTableScene;
import com.example.thelorestore.Launcher;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class AddItemPane extends StackPane {

    public AddItemPane() {
        VBox inputFields = new VBox();

        VBox title = new VBox();
        Text titleText = new Text("Title");
        TextField titleInput = new TextField();
        title.getChildren().addAll(titleText, titleInput);

        VBox author = new VBox();
        Text authorText = new Text("Author");
        TextField authorInput = new TextField();
        author.getChildren().addAll(authorText, authorInput);

        VBox genre = new VBox();
        Text genreText = new Text("Genre");
        TextField genreInput = new TextField();
        genre.getChildren().addAll(genreText, genreInput);

        VBox publisher = new VBox();
        Text publisherText = new Text("Publisher");
        TextField publisherInput = new TextField();
        publisher.getChildren().addAll(publisherText, publisherInput);

        VBox year = new VBox();
        Text yearText = new Text("Year");
        TextField yearInput = new TextField();
        year.getChildren().addAll(yearText, yearInput);

        VBox quantity = new VBox();
        Text quantityText = new Text("Stock Qty");
        TextField qtyInput = new TextField();
        quantity.getChildren().addAll(quantityText, qtyInput);


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
        buttons.setSpacing(50);

        inputFields.getChildren().addAll(title, author, genre, publisher, year, quantity, buttons);
        inputFields.setAlignment(Pos.CENTER);
        inputFields.setMaxWidth(500);
        inputFields.setSpacing(20);




        this.getChildren().addAll(inputFields);

    }
}
