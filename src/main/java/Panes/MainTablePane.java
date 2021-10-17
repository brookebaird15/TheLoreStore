package Panes;

import Scenes.AddItemScene;
import com.example.thelorestore.Launcher;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class MainTablePane extends StackPane {

    public MainTablePane() {
        VBox contents = new VBox();

        Rectangle tablePlaceholder = new Rectangle(700, 500, Paint.valueOf("black"));
//        tablePlaceholder.setStroke();

        HBox buttons = new HBox();
        Button addItemButton = new Button("Add Item");
        addItemButton.setOnAction(e -> {
            Launcher.mainStage.setScene(new AddItemScene());
        });

        Button viewItemButton = new Button("View Item");
        viewItemButton.setOnAction(e -> {
            //TODO - connect button to update/view scene
        });
        buttons.getChildren().addAll(addItemButton, viewItemButton);
        buttons.setAlignment(Pos.CENTER);
        buttons.setSpacing(500);

        contents.getChildren().addAll(tablePlaceholder, buttons);
        contents.setAlignment(Pos.CENTER);

        this.getChildren().addAll(contents);
    }
}
