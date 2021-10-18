package Panes;

import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class UpdateItemPane extends StackPane {
    public UpdateItemPane() {
        //A vbox to hold all the input boxes
        VBox inputFieldsBox = new VBox();

        //Vbox to hold title of book
        VBox title = new VBox();

        //Vbox to hold author
        VBox author = new VBox();
        //Vbox to hold genre
        VBox genre = new VBox();
        //Vbox to hold publisher
        VBox publisher = new VBox();
        //Vbox to hold year published
        VBox yearPublished = new VBox();
        //Vbox to hold stock amount
        VBox stockQuantity = new VBox();
    }
}
