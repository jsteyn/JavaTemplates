import java.util.ArrayList;

public class JSONFileOfPersons {

    ArrayList<Person> persons = new ArrayList();

    public void add(Person person) {
        persons.add(person);
    }

    public ArrayList<Person> getPersons() {
        return persons;
    }

    public void setPersons(ArrayList<Person> persons) {
        this.persons = persons;
    }
}
