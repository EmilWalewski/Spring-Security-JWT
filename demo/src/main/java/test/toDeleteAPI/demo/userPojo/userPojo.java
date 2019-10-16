package test.toDeleteAPI.demo.userPojo;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//@Entity
//@Table(name = "test_user_table_to_delete")
public class userPojo {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

//    @Column(nullable = false, precision = 10)
    private String username;

//    @Column(nullable = false, precision = 15)
    private String password;

    private int active;

    private String roles = "";

    private String authorities = "";

    public userPojo(String username, String password, String roles, String authorities) {
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.authorities = authorities;
        this.active = 1;
    }

    public List<String> converRolesToArray(){
        if (roles.length() > 0) return Arrays.asList(this.roles.split(","));
        else return new ArrayList<>();
    }

    public List<String> converAuthoritiesToArray(){
        if (authorities.length() > 0) return Arrays.asList(this.authorities.split(","));
        else return new ArrayList<>();
    }

//    public userPojo(String username, String password) {
//        this.username = username;
//        this.password = password;
//          //user jest aktywny
//    }

    public userPojo() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getAuthorities() {
        return authorities;
    }

    public void setAuthorities(String authorities) {
        this.authorities = authorities;
    }
}
