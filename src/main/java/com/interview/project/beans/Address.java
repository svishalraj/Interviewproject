package com.interview.project.beans;

/**
 * Contains the Address of the shipment.
 */
public class Address {

    private String street;
    private String country;
    private String zipCode;

    public Address(String street, String country, String zipCode) {
        this.street = street;
        this.country = country;
        this.zipCode = zipCode;
}

    public String getStreet() {
        return street;
    }

    public String getCountry() {
        return country;
    }

    public String getZipCode() {
        return zipCode;
    }


}
