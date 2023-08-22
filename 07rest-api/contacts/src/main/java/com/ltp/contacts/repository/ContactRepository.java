package com.ltp.contacts.repository;

//import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ltp.contacts.pojo.Contact;

@Repository
public class ContactRepository {

    private List<Contact> contacts = Arrays.asList(
            new Contact("123", "John Snow", "4199999999"),
            new Contact("456", "Hermione Granger", "4899999999"),
            new Contact("123", "Harry Potter", "8499999999"));

    public List<Contact> getContacts() {
        return contacts;
    }

    public Contact getContact(int index) {
        return contacts.get(index);
    }

    public void saveContact(Contact contact) {
        contacts.add(contact);
    }

    public void updateContact(int index, Contact contact) {
        contacts.set(index, contact);
    }

    public void deleteContact(int index) {
        contacts.remove(index);
    }

}
