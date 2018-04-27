package com.exist.ecc.core.model.dto;

public class NameDto {
    private String firstName;
    private String lastName;
    private String middleName;
    private String suffix;
    private String title;

    public NameDto() {}
    public NameDto(String firstName, String middleName, String lastName, String suffix, String title) {
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
