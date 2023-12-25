package com.example.demoproject.screens.SignUp;

public class SignupModel {
    public String firstName;
    public String lastName;
    public String email;
    public String password;
    private String confirmPassword;

    public SignupModel(String firstname, String lastname, String email, String password) {
        this.firstName = firstname;
        this.lastName = lastname;
        this.email = email;
        this.password = password;
    }

    public SignupModel() {

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

    public boolean confirmPassword(String confirmPassword) {
        return confirmPassword != null && confirmPassword.length() > 0 && confirmPassword.equals(password);
    }
    public boolean isValid() {
        return firstName() && lastName() && email() && password();
    }

}
