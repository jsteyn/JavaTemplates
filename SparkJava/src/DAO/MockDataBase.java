package DAO;

import Model.Person;

import java.util.Hashtable;

public class MockDataBase {

    static MockDataBase mockdb;
    private static Hashtable<Integer, Person> people = new Hashtable<>();

    public static MockDataBase getInstance() {
        if (mockdb == null) {
            mockdb = new MockDataBase();
        }
        return mockdb;
    }

    public MockDataBase() {
        people.put(0, (new Person(1, "Benjamin", "Sisko")));
        people.put(1, (new Person(1, "Nog", "")));
        people.put(2, (new Person(2, "Odo", "")));
        people.put(3, (new Person(3, "Jadzia", "Dax")));
        people.put(4, (new Person(4, "Jake", "Sisko")));
        people.put(5, (new Person(5, "Miles", "O'Brien")));
        people.put(6, (new Person(6, "Quark", "")));
        people.put(7, (new Person(7, "Julian", "Bashir")));
        people.put(8, (new Person(8, "Kira", "Nerys")));
        people.put(9, (new Person(9, "Worf", "")));
        people.put(10, (new Person(10, "Rom", "")));
    }

    public static Person getPerson(Integer id) {
        return people.get(id);
    }
    public static String getAllPeople() {
        StringBuilder sb = new StringBuilder();
        people.forEach((key,value) -> {sb.append(value.toString() + "\n");});
        return sb.toString();
    }

    public static boolean addPerson(String firstname, String lastname) {
        people.put(people.size(), new Person(people.size(), firstname, lastname));
        return true;
    }
}
