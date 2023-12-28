package com.example.demoproject.screens.Update;

public class UpdateModel {
    public Integer id;
    public String firstName;
    public String lastName;
    public String email;
    public String password;

    public UpdateModel() {

    }

    //getter and setter
    public Integer getId() {
        return id;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
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
    public void setFirstName(String firstName) {
        this.firstName= firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setEmail(String email) {
        this.email= email;
    }
    public void setPassword(String password) {
        this.password= password;
    }

    //validation
    public boolean firstName() {
        return firstName != null && firstName.length() > 0;
    }
    public boolean lastName() {
        return lastName != null && lastName.length() > 0;
    }
    public boolean email() {
        return email != null && email.length() > 0 && email.contains("@");
    }
    public boolean password() {
        return password != null && password.length() > 0;
    }
}
