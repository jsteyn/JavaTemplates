public class Person {

    int id;
    String firstnames;
    String surname;

    private Person() {
        // To prevent from instantiating an empty object from this class
    }

    public Person(int id, String firstnames, String surname) {
        this.id = id;
        this.firstnames = firstnames;
        this.surname = surname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstnames() {
        return firstnames;
    }

    public void setFirstnames(String firstnames) {
        this.firstnames = firstnames;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
