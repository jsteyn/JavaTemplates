package database.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name="people")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="first_name")
    private String firstName;
    @Column(name="last_name")
    private String lastName;

    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
        name = "person_address",
        joinColumns = {@JoinColumn(name = "person_id")},
        inverseJoinColumns = {@JoinColumn(name = "address_id")}
    )
    private Set<Address> addresses;

    public Person() {

    }

    public Person(String firstName, String lastName) {
        this.firstName   = firstName;
        this.lastName    = lastName;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<Address> getAddresses() {
        return addresses;
    }

    public void clearAddresses() {
        addresses.clear();
    }

    public void addAddress(Address address) {
        addresses.add(address);
    }

    public void removeAddress(Address address) {
        addresses.remove(address);
    }

    @Override
    public String toString() {
        return String.format("Person{%s %s}", firstName, lastName);
    }

    public String toFullString() {
        return String.format("%s - %s", toString(), addresses);
    }
}
