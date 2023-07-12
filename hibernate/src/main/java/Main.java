import database.model.Person;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    private static ArrayList<Person> data = new ArrayList(Arrays.asList(
        new Person("Harry", "Potter"),
        new Person("James", "Potter"),
        new Person("Lilly", "Potter"),
        new Person("Hermione", "Granger"),
        new Person("Arthur", "Weasley"),
        new Person("Bill", "Weasley"),
        new Person("Fred", "Weasley"),
        new Person("George", "Weasley"),
        new Person("Ginny", "Weasley"),
        new Person("Hugo", "Weasley"),
        new Person("Molly", "Weasley"),
        new Person("Percy", "Weasley"),
        new Person("Ron", "Weasley"),
        new Person("Rose", "Weasley"),
        new Person("Aberforth", "Dumbledore"),
        new Person("Albus", "Dumbledore"),
        new Person("Ariana", "Dumbledore"),
        new Person("Kendra", "Dumbledore"),
        new Person("Percival", "Dumbledore"),
        new Person("Hewhoshouldnotbenamed", "Voldemort")
    ));

    public static void main(String[] args) {
        DatabaseManagerImpl databaseManager = new DatabaseManagerImpl();

        databaseManager.open();
        for (Person person : data) {
            databaseManager.insert(person);
        }
        databaseManager.close();


    }
}