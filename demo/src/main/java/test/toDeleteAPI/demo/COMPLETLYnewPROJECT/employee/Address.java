package test.toDeleteAPI.demo.COMPLETLYnewPROJECT.employee;

import javax.persistence.Embeddable;

@Embeddable
public class Address {

    private String street;

    private String houseNumber;

    private String location;

    private String postCode;

    private String country;

    public Address() {
    }

    public Address(String street, String houseNumber, String location, String postCode, String country) {
        this.street = street;
        this.houseNumber = houseNumber;
        this.location = location;
        this.postCode = postCode;
        this.country = country;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
