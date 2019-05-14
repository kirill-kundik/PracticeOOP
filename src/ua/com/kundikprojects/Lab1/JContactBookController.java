package ua.com.kundikprojects.Lab1;

import java.util.ArrayList;

public class JContactBookController {

    private ArrayList<Contact> contacts;

    JContactBookController() {
        this.contacts = new ArrayList<>();
    }

    ArrayList<Contact> getContacts() {
        return contacts;
    }

    void addContact(Contact contact) {
        this.contacts.add(contact);
    }

    void editContactByName(String nameOld, String nameNew, ArrayList<String> numbers) {
        for (Contact contact : contacts)
            if (contact.getName().equals(nameOld)) {
                contact.setName(nameNew);
                contact.setNumbers(numbers);
                return;
            }
    }

    void deleteContactByName(String name) {
        for (Contact contact : contacts)
            if (contact.getName().equals(name)) {
                contacts.remove(contact);
                return;
            }
    }

    ArrayList<Contact> searchByName(String name) {
        ArrayList<Contact> result = new ArrayList<>();

        for (Contact contact : contacts)
            if (contact.getName().contains(name) || contact.getName().equals(name))
                result.add(contact);

        return result;
    }

    ArrayList<Contact> searchByNumber(String number) {
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
