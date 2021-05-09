package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.GridPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    public Label label;

    @FXML
    public GridPane gridPane;

    @FXML
    private Button buttonFour;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        buttonFour.setEffect(new DropShadow());
    }

    @FXML
    public void handleMouseEnter() {
        label.setScaleX(2.0);
        label.setScaleY(2.0);
    }

    @FXML
    public void handleMouseExit() {
        label.setScaleX(1.0);
        label.setScaleY(1.0);
    }

    @FXML
    public void handleClick(ActionEvent event) {
//        FileChooser fileChooser = new FileChooser();
//        fileChooser.showOpenDialog(gridPane.getScene().getWindow());
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File file = directoryChooser.showDialog(gridPane.getScene().getWindow());
        if(file != null) {
            System.out.println(file.getPath());
        } else {
            System.out.println("Chooser was cancelled");
        }
    }
}
