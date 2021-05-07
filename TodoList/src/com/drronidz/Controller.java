package com.drronidz;

import com.drronidz.model.TodoItem;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;


import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class Controller {

    @FXML
    public Label deadLineLabel;

    @FXML
    private ListView<TodoItem> todoListView;

    @FXML
    public TextArea itemDetailsTextArea;

    private List<TodoItem> todoItems;
    public void initialize(){

        TodoItem itemOne = new TodoItem(
                "Mail birthday card",
                "Buy a 30th birthday card for John",
                LocalDate.of(2016, Month.APRIL, 25));

        TodoItem itemTwo = new TodoItem(
                "Doctor's Appointment",
                "See Dr. Smith at 123 Main Street. Bring paperwork",
                LocalDate.of(2016, Month.MAY, 23));

        TodoItem itemThree = new TodoItem(
                "Finish design proposal for client",
                "I promised Mike I'd email website mockups by Friday 22nd April",
                LocalDate.of(2016, Month.APRIL, 22));

        TodoItem itemFour = new TodoItem(
                "Pickup Doug at the train station",
                "Doug's arriving on March 23 on the 5:00 train",
                LocalDate.of(2016, Month.APRIL, 23));

        TodoItem itemFive = new TodoItem(
                "Pick up dry cleaning",
                "The clothes should be ready by Wednesday",
                LocalDate.of(2016, Month.APRIL, 25));

        todoItems = new ArrayList<TodoItem>();

        todoItems.add(itemOne);
        todoItems.add(itemTwo);
        todoItems.add(itemThree);
        todoItems.add(itemFour);
        todoItems.add(itemFive);

        todoListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TodoItem>() {
            @Override
            public void changed(ObservableValue<? extends TodoItem> observable, TodoItem oldValue, TodoItem newValue) {
                if(newValue != null) {
                    TodoItem item = todoListView.getSelectionModel().getSelectedItem();
                    itemDetailsTextArea.setText(item.getDetails());
                }
            }
        });

        todoListView.getItems().setAll(todoItems);
        todoListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        todoListView.getSelectionModel().selectFirst();

    }

//    @FXML
//    public void handleClickListView() {
//        TodoItem item = todoListView.getSelectionModel().getSelectedItem();
//        //System.out.println("The selected item is " + item);
//        itemDetailsTextArea.setText(item.getDetails());
//        deadLineLabel.setText(item.getDeadline().toString());
////        StringBuilder stringBuilder = new StringBuilder(item.getDetails());
////        stringBuilder.append("\n\n\n\n");
////        stringBuilder.append("Due: ");
////        stringBuilder.append(item.getDeadline().toString());
////        itemDetailsTextArea.setText(stringBuilder.toString());
//    }
}