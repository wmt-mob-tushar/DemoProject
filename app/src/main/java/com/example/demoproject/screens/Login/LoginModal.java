package com.example.demoproject.screens.Login;

public class LoginModal {

    public String email;
    public String password;

    public LoginModal(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public LoginModal() {

    }

    public Object getEmail() {
        return email;
    }

    public Object getPassword() {
        return password;
    }

    public boolean email() {
        return email != null && email.length() > 0 && email.contains("@");
    }

    public boolean password() {
        return password != null && password.length() > 0;
    }

    public boolean isValid() {
        return email() && password();
    }

}
