package com.drronidz;

import com.drronidz.model.TodoData;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("MainWindow.fxml"));
        primaryStage.setTitle("Todo List");
        primaryStage.setScene(new Scene(root, 900, 500));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void stop() throws Exception {
        try {
            TodoData.getInstance().storeTodoItems();

        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }

    }

    @Override
    public void init() throws Exception {
        try {
            TodoData.getInstance().loadTodoItems();

        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }

    }
}
