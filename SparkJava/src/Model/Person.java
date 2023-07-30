package Model;

public class Person {
    int person_id;
    String first_name;
    String last_name;

    public Person(int person_id, String first_name, String last_name) {
        this.person_id = person_id;
        this.first_name = first_name;
        this.last_name = last_name;
    }

    @Override
    public String toString() {
        return person_id + "," + first_name + "," + last_name;
    }
}
