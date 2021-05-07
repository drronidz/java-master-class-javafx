package com.drronidz;/*
    CREATED BY : ABD EL HALIM
    PROJECT NAME : {IntelliJ IDEA}
    CREATED ON : 5/7/2021 , 
    CREATED ON : 4:51 PM
*/

import com.drronidz.model.TodoData;
import com.drronidz.model.TodoItem;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.time.LocalDate;

public class DialogController {

    @FXML
    public DatePicker deadlinePicker;

    @FXML
    public TextArea detailsArea;

    @FXML
    public TextField shortDescription;

    public TodoItem processResult() {
        String shortDesc = shortDescription.getText().trim();
        String details = detailsArea.getText().trim();
        LocalDate deadLineValue = deadlinePicker.getValue();

        TodoItem newItem = new TodoItem(shortDesc, details, deadLineValue);
        TodoData.getInstance().addTodoItem(newItem);
        return newItem;
    }

}
