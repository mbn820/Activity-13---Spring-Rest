package com.exist.ecc.core.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table (name = "PERSON")
public class Person {

    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "person_id_generator")
    @SequenceGenerator (name = "person_id_generator", sequenceName = "person_seq", allocationSize = 1)
    private int id;

    @Embedded
    private Name name;

    @Embedded
    private Address address;

    @Temporal (TemporalType.DATE)
    @Column (name = "birth_date")
    private Date birthDate;

    @Temporal (TemporalType.DATE)
    @Column (name = "date_hired")
    private Date dateHired;

    @Column (name = "currently_employed")
    private boolean currentlyEmployed;

    @Column (name = "gwa")
    private Double gwa;

    @ManyToMany (fetch = FetchType.LAZY)
    @JoinTable (name = "PERSON_ROLE",
                joinColumns = { @JoinColumn (name = "person_id" ) },
                inverseJoinColumns = { @JoinColumn (name = "role_id") })
    private Set<Role> roles;

    @OneToMany (fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn (name = "person_id")
    private Set<Contact> contacts;

    public Person() {}
    public Person(Name name, Address address, Date birthDate, Date dateHired,
                  boolean currentlyEmployed, double gwa, Set<Role> roles, Set<Contact> contacts) {

        this.name = name;
        this.address = address;
        this.birthDate = birthDate;
        this.dateHired = dateHired;
        this.currentlyEmployed = currentlyEmployed;
        this.gwa = gwa;
        this.roles = roles;
        this.contacts = contacts;
    }

    // getters
    public int getId() {
        return id;
    }

    public Name getName() {
        return name;
    }

    public Address getAddress() {
        return address;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public Date getDateHired() {
        return dateHired;
    }

    public boolean getCurrentlyEmployed() {
        return currentlyEmployed;
    }

    public Double getGwa() {
        return gwa;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public Set<Contact> getContacts() {
        return contacts;
    }

    // setters
    public void setId(int id) {
        this.id = id;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public void setDateHired(Date dateHired) {
        this.dateHired = dateHired;
    }

    public void setCurrentlyEmployed(boolean currentlyEmployed) {
        this.currentlyEmployed = currentlyEmployed;
    }

    public void setGwa(Double gwa) {
        this.gwa = gwa;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public void setContacts(Set<Contact> contacts) {
        this.contacts = contacts;
    }

    public void addRole(Role role) {
        if(roles == null) {
            roles = new HashSet<Role>();
        }
        roles.add(role);
        role.addPerson(this);
    }

    public void removeRole(Role role) {
        roles.remove(role);
        role.removePerson(this);
    }

    public void addContact(Contact contact) {
        contacts.add(contact);
    }

    public String toString() {
        return "Person: " + name.toString();
    }
}
