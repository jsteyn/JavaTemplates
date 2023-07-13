import database.DatabaseManager;
import database.model.Address;
import database.model.Person;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Main {
    private static final ArrayList<Person> peopleData = new ArrayList<>(Arrays.asList(
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
    private static final ArrayList<Address> addressData = new ArrayList<>(Arrays.asList(
        new Address("4 Privet Drive, Little Whinging", "Surrey", "United Kingdom"),
        new Address("Number 12, Grimmauld Place", "London", "United Kingdom"),
        new Address("The Burrow", "Devon", "United Kingdom"),
        new Address("The Hog's Head", "", "United Kingdom"),
        new Address("", "Godric's Hollow", "United Kingdom"),
        new Address("", "London", "United Kingdom"),
        new Address("", "Little Hangleton", "United Kingdom")
    ));

    public static void main(String[] args) {
        DatabaseManager databaseManager = new DatabaseManager();

        initDataset(databaseManager);

        databaseManager.open();
        printAllPeople(databaseManager.read(Person.class));
        databaseManager.close();

        updateWeasleys(databaseManager);

        databaseManager.open();
        printAllPeople(databaseManager.read(Person.class));
        databaseManager.close();

        orphanHarry(databaseManager);

        databaseManager.open();
        printAllPeople(databaseManager.read(Person.class));
        databaseManager.close();

        deleteVoldemort(databaseManager);

        databaseManager.open();
        printAllPeople(databaseManager.read(Person.class));
        printAllAddresses(databaseManager.read(Address.class));
        databaseManager.close();
    }

    public static void printAllPeople(List<Person> objects) {
        System.out.println("People:");
        for (Person obj : objects)
            System.out.println("    " + obj.toFullString());
    }

    public static void printAllAddresses(List<Address> objects) {
        System.out.println("Addresses:");
        for (Address obj : objects)
            System.out.println("    " + obj.toFullString());
    }

    private static void initDataset(DatabaseManager databaseManager) {
        HashMap<String, Object> parameters;

        databaseManager.open();
        printAllPeople(databaseManager.read(Person.class));
        databaseManager.close();

        databaseManager.open();
        System.out.println("\n---Insert initial dataset---\n");
        for (Person person : peopleData) {
            databaseManager.create(person);
        }
        for (Address address : addressData) {
            databaseManager.create(address);
        }
        databaseManager.close();

        databaseManager.open();
        // Set address for harry
        parameters = new HashMap<>();
        parameters.put("address", "Number 12, Grimmauld Place");
        Address grimmauld = databaseManager.read(Address.class, "address = :address", parameters).get(0);

        parameters = new HashMap<>();
        parameters.put("name", "Harry");
        Person harry = databaseManager.read(Person.class, "firstName = :name", parameters).get(0);
        harry.addAddress(grimmauld);
        databaseManager.update(harry);
        // Set address for the Potters
        parameters = new HashMap<>();
        parameters.put("address", "4 Privet Drive, Little Whinging");
        Address privetDrive = databaseManager.read(Address.class, "address = :address", parameters).get(0);

        parameters = new HashMap<>();
        parameters.put("name", "Potter");
        parameters.put("notname", "Harry");
        for (Person potter : databaseManager.read(Person.class, "lastName = :name and firstName != :notname", parameters)) {
            potter.addAddress(privetDrive);
            databaseManager.update(potter);
        }
        // Set address for Hermione
        parameters = new HashMap<>();
        parameters.put("address", "");
        parameters.put("city", "London");
        Address london = databaseManager.read(Address.class, "city = :city and address = :address", parameters).get(0);

        parameters = new HashMap<>();
        parameters.put("name", "Granger");
        Person hermione = databaseManager.read(Person.class, "lastName = :name", parameters).get(0);
        hermione.addAddress(london);
        databaseManager.update(hermione);
        // Set address for the Dumbledores
        parameters = new HashMap<>();
        parameters.put("address", "The Hog's Head");
        Address hogsHead = databaseManager.read(Address.class, "address = :address", parameters).get(0);

        parameters = new HashMap<>();
        parameters.put("name", "Dumbledore");
        for (Person dumbledore : databaseManager.read(Person.class, "lastName = :name", parameters)) {
            dumbledore.addAddress(hogsHead);
            databaseManager.update(dumbledore);
        }
        // Set address for the Weasleys
        parameters = new HashMap<>();
        parameters.put("address", "The Burrow");
        Address theBurrow = databaseManager.read(Address.class, "address = :address", parameters).get(0);

        parameters = new HashMap<>();
        parameters.put("name", "Weasley");
        for (Person weasley : databaseManager.read(Person.class, "lastName = :name", parameters)) {
            weasley.addAddress(theBurrow);
            databaseManager.update(weasley);
        }
        // Set address for He who should probably be referred to with discretion
        parameters = new HashMap<>();
        parameters.put("city", "Little Hangleton");
        Address hangleton = databaseManager.read(Address.class, "city = :city", parameters).get(0);

        parameters = new HashMap<>();
        parameters.put("name", "Voldemort");
        Person voldemort = databaseManager.read(Person.class, "lastName = :name", parameters).get(0);
        voldemort.addAddress(hangleton);
        databaseManager.update(voldemort);

        databaseManager.close();
    }

    static void updateWeasleys(DatabaseManager databaseManager) {
        HashMap<String, Object> parameters;

        databaseManager.open();
        System.out.println("\n---Find all Weasleys and replace them with Woosleys---\n");
        parameters = new HashMap<>();
        parameters.put("name", "Weasley");
        List<Person> weasleys = databaseManager.read(Person.class, "lastName = :name", parameters);

        printAllPeople(weasleys);

        for (Person weasley : weasleys) {
            weasley.setLastName("Woosley");
            databaseManager.update(weasley);
        }
        databaseManager.close();
    }

    static void orphanHarry(DatabaseManager databaseManager) {
        HashMap<String, Object> parameters;

        databaseManager.open();
        System.out.println("\n---Orphaning Harry---\n");
        parameters = new HashMap<>();
        parameters.put("city", "Surrey");
        Address surrey = databaseManager.read(Address.class, "city = :city", parameters).get(0);
        parameters = new HashMap<>();
        parameters.put("address", surrey);
        List<Person> parents = databaseManager.read(Person.class, "p", "Person p join p.addresses a", "a = :address", parameters);
        for (Person potter : parents) {
            databaseManager.delete(potter);
        }
        databaseManager.close();
    }

    static void deleteVoldemort(DatabaseManager databaseManager) {
        HashMap<String, Object> parameters;

        databaseManager.open();
        System.out.println("\n---Eliminate the dark lord---\n");
        parameters = new HashMap<>();
        parameters.put("name", "Voldemort");
        Person voldemort = databaseManager.read(Person.class, "lastName = :name", parameters).get(0);
        databaseManager.delete(voldemort);
        databaseManager.close();
    }
}
