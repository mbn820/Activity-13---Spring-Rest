package com.exist.ecc.core.model;

import javax.persistence.Embeddable;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Embeddable
public class Address {

    @Column (name = "street_number")
    private Integer streetNumber;

    @Column (name = "barangay")
    private String barangay;

    @Column (name = "municipality")
    private String municipality;

    @Column (name = "zipcode")
    private String zipcode;

    public Address() {}
    public Address(int streetNumber, String barangay, String municipality, String zipcode) {
        this.streetNumber = streetNumber;
        this.barangay = barangay;
        this.municipality = municipality;
        this.zipcode = zipcode;
    }

    // getters
    public Integer getStreetNumber() {
        return streetNumber;
    }

    public String getBarangay() {
        return barangay;
    }

    public String getMunicipality() {
        return municipality;
    }

    public String getZipcode() {
        return zipcode;
    }

    // setters
    public void setStreetNumber(Integer streetNumber) {
        this.streetNumber = streetNumber;
    }

    public void setBarangay(String barangay) {
        this.barangay = barangay;
    }

    public void setMunicipality(String municipality) {
        this.municipality = municipality;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String toString() {
        return String.format("no. %d, brgy. %s, %s, zipcode: %s", streetNumber, barangay, municipality, zipcode);
    }
}
