import database.model.Person;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    private static final ArrayList<Person> data = new ArrayList<>(Arrays.asList(
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
        printAllObjects(databaseManager.read(Person.class));
        databaseManager.close();

        databaseManager.open();
        System.out.println("---Insert People---");
        for (Person person : data) {
            databaseManager.create(person);
        }
        databaseManager.close();

        databaseManager.open();
        printAllObjects(databaseManager.read(Person.class));
        databaseManager.close();

        databaseManager.open();
        List<Person> weasleys = databaseManager.read(Person.class, "lastName = :name", new String[]{"name"}, new Object[]{"Weasley"});
        databaseManager.close();

        printAllObjects(weasleys);

        databaseManager.open();
        for (Person weasley : weasleys) {
            weasley.setLastName("Woosley");
            databaseManager.update(weasley);
        }
        databaseManager.close();

        databaseManager.open();
        printAllObjects(databaseManager.read(Person.class));
        databaseManager.close();

        databaseManager.open();
        Person voldemort = databaseManager.read(Person.class, "lastName = :name", new String[]{"name"}, new Object[]{"Voldemort"}).get(0);
        databaseManager.delete(voldemort);
        databaseManager.close();

        databaseManager.open();
        printAllObjects(databaseManager.read(Person.class));
        databaseManager.close();
    }

    public static <T> void printAllObjects(List<T> objects) {
        System.out.println("---Objects---");
        for (T obj : objects)
            System.out.println("    " + obj);
    }
}