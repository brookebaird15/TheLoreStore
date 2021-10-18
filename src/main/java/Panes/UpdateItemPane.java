package Panes;

import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

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

        //Texts for each input
        Text titleTxt = new Text("Title");
        Text authorTxt = new Text("Author");
        Text genreTxt = new Text("Genre");
        Text publisherTxt = new Text("Publisher");
        Text yearTxt = new Text("Year Published");
        Text stockTxt = new Text("Stock Qty");

        //Textfields to allow for input of each category
        TextField titleTextField = new TextField();
        TextField authorTextField = new TextField();
        TextField genreTextField = new TextField();
        TextField publisherTextField = new TextField();
        TextField yearTextField = new TextField();
        TextField stockTextField = new TextField();
    }
}
