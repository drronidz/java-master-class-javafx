package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

public class Controller {

    @FXML
    private TextField nameField;

    @FXML
    private Button
                    helloButton,
                    byeButton;

    @FXML
    private CheckBox ourCheckBox;


    @FXML
    public void initialize() {
        helloButton.setDisable(true);
        byeButton.setDisable(true);
    }

    @FXML
    public void onButtonClicked (ActionEvent event){

        System.out.println("Hello, " + nameField.getText());
        if(event.getSource().equals(helloButton)) {
            System.out.println("Hello, " + nameField.getText());
        } else if(event.getSource().equals(byeButton)) {
            System.out.println("Bye, " + nameField.getText());
        }

        try {
            Thread.sleep(10000);
        } catch (InterruptedException interruptedException) {
            // we don't care bout this
        }
        if(ourCheckBox.isSelected()) {
            nameField.clear();
            helloButton.setDisable(true);
            byeButton.setDisable(true);
        }
    }

    @FXML
    public void handleKeyReleased() {
        String text = nameField.getText();
        boolean disableButtons = text.isEmpty() || text.trim().isEmpty();
        helloButton.setDisable(disableButtons);
        byeButton.setDisable(disableButtons);
    }

    @FXML
    public void handleChange() {
        System.out.println("The CheckBox is " + (ourCheckBox.isSelected() ? "checked" : "not checked"));
    }
}
