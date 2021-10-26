package Panes;

import Scenes.AddItemScene;
import Scenes.UpdateItemScene;
import com.example.thelorestore.Launcher;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class MainTablePane extends StackPane {

    public MainTablePane() {
        //contents box holds all pane contents
        VBox contents = new VBox();

        //TODO - replace placeholder with table(s)
        Rectangle tablePlaceholder = new Rectangle(700, 500, Paint.valueOf("black"));

        //tableOptionButtons box holds all buttons that allow user to change which table they are viewing
        HBox tableOptionButtons = new HBox();

        //mainTableButton displays the main inventory table if the user is viewing the author or genre tables
        Button mainTableButton = new Button("All Items");
        mainTableButton.setOnAction(e-> {
            tablePlaceholder.setFill(Paint.valueOf("black"));
        });

        //authorButton displays the author table
        Button authorButton = new Button("Author");
        authorButton.setOnAction(e-> {
            //TODO - add action to switch to author menu
            tablePlaceholder.setFill(Paint.valueOf("green"));
        });

        //genreButton displays the genre table
        Button genreButton = new Button("Genre");
        genreButton.setOnAction(e-> {
            //TODO - add action to switch to genre menu
            tablePlaceholder.setFill(Paint.valueOf("red"));
        });
        tableOptionButtons.getChildren().addAll(mainTableButton, authorButton, genreButton);

        //searchInput allows the user to search by SKU
        TextField searchInput = new TextField();
        searchInput.setPromptText("Search SKU");
        searchInput.setPrefWidth(300);
        searchInput.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.ENTER) {
                //TODO - add search function
            }
        });

        //menuSearch box holds table menu buttons and search buttons
        HBox menuSearch = new HBox();
        menuSearch.getChildren().addAll(tableOptionButtons, searchInput);
        menuSearch.setAlignment(Pos.CENTER);
        menuSearch.setSpacing(235);

        //editButtons box holds addItem and viewItem buttons
        HBox editButtons = new HBox();

        //addItemButton directs user to AddItemPane
        Button addItemButton = new Button("Add Item");
        addItemButton.setOnAction(e -> {
            Launcher.mainStage.setScene(new AddItemScene());
        });

        //viewItemButton directs user to ViewItemPane
        Button viewItemButton = new Button("View Item");
        viewItemButton.setOnAction(e -> {
            viewItemButton.setOnAction(event -> {
                Launcher.mainStage.setScene(new UpdateItemScene());
            });
        });
        editButtons.getChildren().addAll(addItemButton, viewItemButton);
        editButtons.setAlignment(Pos.CENTER);
        editButtons.setSpacing(500);
        editButtons.requestFocus();

        contents.getChildren().addAll(menuSearch, tablePlaceholder, editButtons);
        contents.setAlignment(Pos.CENTER);

        this.getChildren().addAll(contents);
    }
}
