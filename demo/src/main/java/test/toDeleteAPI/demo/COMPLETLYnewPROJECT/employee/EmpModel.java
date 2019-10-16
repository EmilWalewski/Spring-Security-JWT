package test.toDeleteAPI.demo.COMPLETLYnewPROJECT.employee;

import test.toDeleteAPI.demo.COMPLETLYnewPROJECT.user.User;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class EmpModel {

    @NotBlank
    @Pattern(regexp = "[1-9]+")
    private Long id;

    @NotBlank
    @Pattern(regexp = "[A-Z][a-z]{2,}")
    private String name;

    @NotBlank
    @Pattern(regexp = "[A-Z][a-z]{2,20}")
    private String surname;

    @NotBlank
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$")
    private String email;

    @NotBlank
    @Pattern(regexp = "^[1-9][0-9]{2,}")
    private int phoneNumber;

    @NotBlank
    private User user;

    @NotBlank
    private Address address;

    public EmpModel() {
    }

    public EmpModel(Employee employee) {
        this.id = employee.getId();
        this.name = employee.getName();
        this.surname = employee.getSurname();
        this.email = employee.getEmail();
        this.phoneNumber = employee.getPhoneNumber();
        this.user = employee.getUser();
        this.address = employee.getAddress();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
