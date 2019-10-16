package test.toDeleteAPI.demo.COMPLETLYnewPROJECT.user;

import test.toDeleteAPI.demo.COMPLETLYnewPROJECT.employee.Employee;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "principal_details")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", unique = true)
    private Long userID;

    @Column(name = "username", length = 20, nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "isActive", columnDefinition = "int(1)")
    private int active;


    @ManyToMany(targetEntity = Role.class, fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinTable(name = "assigned_roles",
            joinColumns = {@JoinColumn(name = "user_id")},//, referencedColumnName = "user_id"
            inverseJoinColumns = {@JoinColumn(name = "role_id")})//, referencedColumnName = "role_id"
    private Set<Role> roles;

    @OneToOne(targetEntity = Employee.class, fetch = FetchType.LAZY, mappedBy = "user")
    private Employee employee;

    public User() {
    }

    public User(String username, String password, Set<Role> roles) {
        this.username = username;
        this.password = password;
        this.active = 1;            //while creating user is active
        this.roles = roles;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}

