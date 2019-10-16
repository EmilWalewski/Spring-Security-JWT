package test.toDeleteAPI.demo.COMPLETLYnewPROJECT.employee;

import test.toDeleteAPI.demo.COMPLETLYnewPROJECT.user.User;

import javax.persistence.*;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Long employeeid;

    @Column(name = "name", length = 12)
    private String name;

    @Column(name = "surname", length = 20)
    private String surname;

    @Column(name = "email", length = 40, nullable = false)
    private String email;

    @Column(name = "phoneNumber", length = 13, unique = true)
    private int phoneNumber;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "account_id")
    private User user;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "street", column = @Column(name = "h_street")),
        @AttributeOverride(name = "houseNumber", column = @Column(name = "h_houseNumber")),
        @AttributeOverride(name = "location", column = @Column(name = "h_location")),
        @AttributeOverride(name = "postCode", column = @Column(name = "h_postCode")),
        @AttributeOverride(name = "country", column = @Column(name = "h_country"))
    })
    private Address address;


    public Employee() {
    }

    public Employee(String name, String surname, String email, int phoneNumber) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public Employee(EmpModel empModel) {
        this.employeeid = empModel.getId();
        this.name = empModel.getName();
        this.surname = empModel.getSurname();
        this.email = empModel.getSurname();
        this.phoneNumber = empModel.getPhoneNumber();
        this.user = empModel.getUser();
        this.address = empModel.getAddress();

    }

    public Long getId() {
        return employeeid;
    }

    public void setId(Long employeeid) {
        this.employeeid = employeeid;
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
