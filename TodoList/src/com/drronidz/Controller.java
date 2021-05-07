package com.drronidz;

import com.drronidz.model.TodoData;
import com.drronidz.model.TodoItem;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.util.Callback;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class Controller {

    @FXML
    public Label deadLineLabel;

    @FXML
    public BorderPane mainBorderPane;

    @FXML
    private ListView<TodoItem> todoListView;

    @FXML
    public TextArea itemDetailsTextArea;

    @FXML
    private ContextMenu listContextMenu;

//    private List<TodoItem> todoItems;
    public void initialize(){

//        TodoItem itemOne = new TodoItem(
//                "Mail birthday card",
//                "Buy a 30th birthday card for John",
//                LocalDate.of(2016, Month.APRIL, 25));
//
//        TodoItem itemTwo = new TodoItem(
//                "Doctor's Appointment",
//                "See Dr. Smith at 123 Main Street. Bring paperwork",
//                LocalDate.of(2016, Month.MAY, 23));
//
//        TodoItem itemThree = new TodoItem(
//                "Finish design proposal for client",
//                "I promised Mike I'd email website mockups by Friday 22nd April",
//                LocalDate.of(2016, Month.APRIL, 22));
//
//        TodoItem itemFour = new TodoItem(
//                "Pickup Doug at the train station",
//                "Doug's arriving on March 23 on the 5:00 train",
//                LocalDate.of(2016, Month.APRIL, 23));
//
//        TodoItem itemFive = new TodoItem(
//                "Pick up dry cleaning",
//                "The clothes should be ready by Wednesday",
//                LocalDate.of(2016, Month.APRIL, 25));
//
//        todoItems = new ArrayList<TodoItem>();
//
//        todoItems.add(itemOne);
//        todoItems.add(itemTwo);
//        todoItems.add(itemThree);
//        todoItems.add(itemFour);
//        todoItems.add(itemFive);
//
//        TodoData.getInstance().setTodoItems(todoItems);

        listContextMenu = new ContextMenu();
        MenuItem deleteMenuItem = new MenuItem("Delete");
        deleteMenuItem.setOnAction(event -> {
            TodoItem item = todoListView.getSelectionModel().getSelectedItem();
            deleteItem(item);
        });

        listContextMenu.getItems().addAll(deleteMenuItem);
        todoListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue != null) {
                TodoItem item = todoListView.getSelectionModel().getSelectedItem();
                itemDetailsTextArea.setText(item.getDetails());
                // here we find all the patterns of DateTime
                //https://docs.oracle.com/javase/8/docs/api/java/time/format/DateTimeFormatter.html
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy");
                deadLineLabel.setText(dateTimeFormatter.format(item.getDeadline()));
            }
        });

        todoListView.setItems(TodoData.getInstance().getTodoItems());
        todoListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        todoListView.getSelectionModel().selectFirst();

        todoListView.setCellFactory(new Callback<>() {
            @Override
            public ListCell<TodoItem> call(ListView<TodoItem> param) {
                ListCell<TodoItem> cell = new ListCell<>() {
                    @Override
                    protected void updateItem(TodoItem item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setText(null);
                        } else {
                            setText(item.getShortDescription());
                            if (item.getDeadline().isBefore(LocalDate.now())) {
                                setTextFill(Color.GREEN);
                            } else if (item.getDeadline().isAfter(LocalDate.now().plusDays(1))) {
                                setTextFill(Color.GOLDENROD);
                            } else if (item.getDeadline().isEqual(LocalDate.now())) {
                                setTextFill(Color.RED);
                            }
                        }
                    }
                };

                cell.emptyProperty().addListener(
                        (obs, wasEmpty, isNowEmpty) -> {
                            if (isNowEmpty) {
                                cell.setContextMenu(null);
                            } else {
                                cell.setContextMenu(listContextMenu);
                            }
                        }
                );
                return cell;
            }
        });
    }

    @FXML
    public void showNewItemDialog() {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainBorderPane.getScene().getWindow());
        dialog.setTitle("Add New Todo Item");
        dialog.setHeaderText("Use this dialog to create a new todo item");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("todoItemDialog.fxml"));

        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException ioException) {
            System.out.println("Couldn't load the dialog");
            ioException.printStackTrace();
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Optional<ButtonType> result = dialog.showAndWait();

        if(result.isPresent() && result.get() == ButtonType.OK) {
            DialogController controller = fxmlLoader.getController();
            TodoItem newItem = controller.processResult();
            todoListView.getSelectionModel().select(newItem);
        }
    }

    public void deleteItem(TodoItem item) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Todo Item");
        alert.setHeaderText("Delete item: " + item.getShortDescription());
        alert.setContentText("Are you sure? Press OK to confirm, or cancel to Back out.");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && (result.get() == ButtonType.OK)) {
            TodoData.getInstance().deleteTodoItem(item);
        }
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
