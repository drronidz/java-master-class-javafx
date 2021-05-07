package com.drronidz.model;/*
    CREATED BY : ABD EL HALIM
    PROJECT NAME : {IntelliJ IDEA}
    CREATED ON : 5/7/2021 , 
    CREATED ON : 4:11 PM
*/

import javafx.collections.FXCollections;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;

public class TodoData {
    private static TodoData instance = new TodoData();
    private static String fileName = "TodoListItems.txt";

    private List<TodoItem> todoItems;
    private DateTimeFormatter dateTimeFormatter;


    private TodoData() {
        dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    }

    public List<TodoItem> getTodoItems() {
        return todoItems;
    }

    public void setTodoItems(List<TodoItem> todoItems) {
        this.todoItems = todoItems;
    }

    public static TodoData getInstance() {
        return instance;
    }

    public void loadTodoItems() throws IOException {
        todoItems = FXCollections.observableArrayList();
        Path path = Paths.get(fileName);
        BufferedReader bufferedReader = Files.newBufferedReader(path);

        String input;

        try {
            while((input = bufferedReader.readLine() )!= null) {
                String [] itemPieces = input.split("\t");

                String shortDescription = itemPieces[0];
                String details = itemPieces[1];
                String dataString = itemPieces[2];

                LocalDate date = LocalDate.parse(dataString, dateTimeFormatter);
                TodoItem todoItem = new TodoItem(shortDescription, details, date);
                todoItems.add(todoItem);
            }
        } finally {
            bufferedReader.close();
        }
    }

    public void storeTodoItems() throws IOException {
        Path path = Paths.get(fileName);
        BufferedWriter bufferedWriter = Files.newBufferedWriter(path);
        try {
            Iterator<TodoItem> itemIterator = todoItems.iterator();
            while (itemIterator.hasNext()) {
                TodoItem item = itemIterator.next();
                bufferedWriter.write(String.format("%s\t%S\t%s",
                        item.getShortDescription(),
                        item.getDetails(),
                        item.getDeadline().format(dateTimeFormatter)));
                bufferedWriter.newLine();
            }
        } finally {
            if(bufferedWriter != null) {
                bufferedWriter.close();
            }
        }
    }
}
