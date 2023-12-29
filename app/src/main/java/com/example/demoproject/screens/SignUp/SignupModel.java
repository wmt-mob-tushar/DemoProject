package com.example.demoproject.screens.SignUp;

public class SignupModel {
    public Integer id;
    public String firstName;
    public String lastName;
    public String email;
    public String password;
    private String confirmPassword;

    public SignupModel() {}

    public Integer getId() {
        return id;
    }
    //getter and setter
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
    public String getConfirmPassword() {
        return confirmPassword;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public void setFirstName(String firstname) {
        this.firstName = firstname;
    }
    public void setLastName(String lastname) {
        this.lastName = lastname;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPassword(String password) {
        this.password= password;
    }
    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
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
    public boolean confirmPassword(String confirmPassword) {
        return confirmPassword != null && confirmPassword.length() > 0 && confirmPassword.equals(password);
    }
}
