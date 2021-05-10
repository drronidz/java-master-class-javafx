package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.GridPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    public Label label;

    @FXML
    public GridPane gridPane;

    @FXML
    public WebView webView;

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

        FileChooser fileChooser = new FileChooser();
        //fileChooser.setTitle("Save Application File");

        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Zip","*.zip"),
                new FileChooser.ExtensionFilter("PDF","*.pdf"),
                new FileChooser.ExtensionFilter("Image Files","*.jpg","*.png","*.gif"),
                new FileChooser.ExtensionFilter("All Files","*.*")
        );

//        fileChooser.showOpenDialog(gridPane.getScene().getWindow());
//        DirectoryChooser directoryChooser = new DirectoryChooser();

        List<File> files = fileChooser.showOpenMultipleDialog(gridPane.getScene().getWindow());
        if(files != null) {
            for (File value : files) {
                System.out.println(value);
            }
            //System.out.println(files.getPath());
        } else {
            System.out.println("Chooser was cancelled");
        }
    }

    @FXML
    public void handleLinkClick(ActionEvent event) {
//        System.out.println("The link was clicked");
//        try {
//            Desktop.getDesktop().browse(new URI("http://www.javafx.com"));
//        } catch (IOException | URISyntaxException e) {
//            e.printStackTrace();
//        }
        WebEngine webEngine = webView.getEngine();
        webEngine.load("http://www.javafx.com");
    }
}
