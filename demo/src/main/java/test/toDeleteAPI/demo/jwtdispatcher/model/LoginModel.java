package test.toDeleteAPI.demo.jwtdispatcher.model;

import javax.validation.constraints.Pattern;

public class LoginModel {

    //@Pattern(regexp = "^[A-Za-z]*$")
    private String username;


    private String password;

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
}
