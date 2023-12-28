package com.example.demoproject.screens.Login;

public class LoginModal {
    public Integer id;
    public String email;
    public String password;

    public LoginModal() {

    }

    //getter and setter
    public Integer getId() {
        return id;
    }
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPassword(String password) {
        this.password= password;
    }

    //validation
    public boolean email() {
        return email != null && email.length() > 0 && email.contains("@");
    }
    public boolean password() {
        return password != null && password.length() > 0;
    }
}
