package ua.com.kundikprojects.Lab1;

import java.util.ArrayList;

public class Contact {

    private String name;
    private ArrayList<String> numbers;

    public Contact(String name, String number) {

        this.name = name;
        this.numbers = new ArrayList<>();
        this.numbers.add(number);

    }

    public Contact(String name, ArrayList<String> numbers) {

        this.name = name;
        this.numbers = numbers;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getNumbers() {
        return numbers;
    }

    public void setNumbers(ArrayList<String> numbers) {
        this.numbers = numbers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return name.equals(contact.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return "Contact {" +
                "name='" + name + '\'' +
                ", numbers='" + numbers.toString() + '\'' +
                '}';
    }
}
