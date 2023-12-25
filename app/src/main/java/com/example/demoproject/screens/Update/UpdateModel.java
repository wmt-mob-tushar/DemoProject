package com.example.demoproject.screens.Update;

public class UpdateModel {
    public String firstName;
    public String lastName;
    public String email;
    public String password;

    public UpdateModel(String firstname, String lastname, String email, String password) {
        this.firstName = firstname;
        this.lastName = lastname;
        this.email = email;
        this.password = password;
    }

    public UpdateModel() {

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

    public boolean isValid() {
        return firstName() && lastName() && email() && password();
    }
}
