package com;/*
    CREATED BY : ABD EL HALIM
    PROJECT NAME : {IntelliJ IDEA}
    CREATED ON : 5/11/2021 , 
    CREATED ON : 1:00 AM
*/

import com.model.Contact;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class ContactController {

    @FXML
    public TextField firstNameField;

    @FXML
    public TextField lastNameField;

    @FXML
    public TextField phoneNumberField;

    @FXML
    public TextField notesField;

    public Contact getNewContact() {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String phoneNumber = phoneNumberField.getText();
        String notes = notesField.getText();

        return new Contact(firstName,lastName,phoneNumber,notes);
    }

    public void editContact(Contact contact) {
        firstNameField.setText(contact.getFirstName());
        lastNameField.setText(contact.getLastName());
        phoneNumberField.setText(contact.getPhoneNumber());
        notesField.setText(contact.getNotes());
    }

    public void updateContact(Contact contact) {
        contact.setFirstName(firstNameField.getText());
        contact.setLastName(lastNameField.getText());
        contact.setPhoneNumber(phoneNumberField.getText());
        contact.setNotes(notesField.getText());
    }
}
