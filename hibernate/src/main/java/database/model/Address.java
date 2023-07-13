package database.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="address")
    private String address;
    @Column(name="city")
    private String city;
    @Column(name="country")
    private String country;

    @ManyToMany(mappedBy = "addresses")
    private Set<Person> people;

    public Address() {

    }

    public Address(String address, String city, String country) {
        this.address = address;
        this.city = city;
        this.country = country;
    }

    public int getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Set<Person> getPeople() {
        return people;
    }

    public void clearPeople() {
        people.clear();
    }

    public void addPerson(Person address) {
        people.add(address);
    }

    public void removePerson(Person address) {
        people.remove(address);
    }

    @Override
    public String toString() {
        return String.format("Address{%s, %s, %s}", address, city, country);
    }

    public String toFullString() {
        return String.format("%s - %s", toString(), people);
    }
}
