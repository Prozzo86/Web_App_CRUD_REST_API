package com.soft_con.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name ="customer")
public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (nullable = false, unique = true, length = 50, name = "email")
    private String email;

    @Column(nullable = false, length = 50, name = "first_name")
    private String firstName;

    @Column (nullable = false, length = 50, name = "last_name")
    private String lastName;

    @ManyToMany
    @JoinTable(name = "customer_computer",
            joinColumns = { @JoinColumn(name = "fk_customer") },
            inverseJoinColumns = { @JoinColumn(name = "fk_computer") })
    private Set<Computer> computers = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Set<Computer> getComputers() {
        return computers;
    }

    public Customer addComputer(Computer computer) {
        this.computers.add(computer);
        computer.getCustomers().add(this);
        return this;
    }

    public Customer removeComputer(Computer computer) {
        this.computers.remove(computer);
        computer.getCustomers().remove(this);
        return this;
    }

    public void setComputers(Set<Computer> computers) {
        this.computers = computers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return id.equals(customer.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
