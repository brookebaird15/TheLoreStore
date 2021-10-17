package Panes;

import Scenes.AddItemScene;
import com.example.thelorestore.Launcher;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

public class MainTablePane extends StackPane {

    public MainTablePane() {

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

        this.getChildren().addAll(buttons);

    }
}
