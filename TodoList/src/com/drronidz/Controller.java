package com.drronidz;

import com.drronidz.model.TodoItem;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class Controller {

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

    }
}
