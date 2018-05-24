package com.exist.ecc.core.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Name {

    @Column (name = "first_name")
    private String firstName;

    @Column (name = "last_name")
    private String lastName;

    @Column (name = "middle_name")
    private String middleName;

    @Column (name = "suffix")
    private String suffix;

    @Column (name = "title")
    private String title;

    public Name() {}
    public Name(String firstName, String middleName, String lastName, String suffix, String title) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.suffix = suffix;
        this.title = title;
    }

    // getters
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getSuffix() {
        return suffix;
    }

    public String getTitle() {
        return title;
    }

    //setters
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String toString() {
        return String.format("%s %c. %s", firstName, middleName.charAt(0), lastName);
    }
    
}
