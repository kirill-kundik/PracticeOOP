package ua.com.kundikprojects.Lab1;

import java.util.ArrayList;

public class JContactBookController {

    private ArrayList<Contact> contacts;

    public JContactBookController(ArrayList<Contact> contacts) {
        this.contacts = contacts;
    }

    public JContactBookController() {
        this.contacts = new ArrayList<Contact>();
    }

    public JContactBookController(Contact contact) {
        this();
        this.contacts.add(contact);
    }

    public ArrayList<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(ArrayList<Contact> contacts) {
        this.contacts = contacts;
    }

    public void addContact(Contact contact) {
        this.contacts.add(contact);
    }

    public Contact getContact(int index) {
        return contacts.get(index);
    }

    public void editContact(int index, String name, ArrayList<String> numbers) {
        this.contacts.get(index).setName(name);
        this.contacts.get(index).setNumbers(numbers);
    }

    public void deleteContact(int index) {
        this.contacts.remove(index);
    }
}
