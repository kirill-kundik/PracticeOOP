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

    public void editContactByName (String nameOld, String nameNew, ArrayList<String> numbers) {
        for (Contact contact : contacts)
            if (contact.getName().equals(nameOld)) {
                contact.setName(nameNew);
                contact.setNumbers(numbers);
                return;
            }
    }

    public void deleteContact(int index) {
        this.contacts.remove(index);
    }

    public void deleteContactByName (String name) {
        for (Contact contact : contacts)
            if (contact.getName().equals(name)) {
                contacts.remove(contact);
                return;
            }
    }

    public ArrayList<Contact> searchByName (String name) {
        ArrayList<Contact> result = new ArrayList<>();

        for (Contact contact : contacts)
            if (contact.getName().contains(name) || contact.getName().equals(name))
                result.add(contact);

        return result;
    }

    public ArrayList<Contact> searchByNumber (String number) {
        ArrayList<Contact> result = new ArrayList<>();

        System.out.println("Here");

        for (Contact contact : contacts)
            for (String num : contact.getNumbers())
                if (num.equals(number) || num.contains(number))
                    result.add(contact);

        System.out.println(result.toString());
        return result;
    }

    public String toString() {
        return contacts.toString();
    }
}
