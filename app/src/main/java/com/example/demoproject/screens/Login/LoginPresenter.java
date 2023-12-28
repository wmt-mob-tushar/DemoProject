package com.example.demoproject.screens.Login;
import com.example.demoproject.database.SQLiteDbHelper;

public class LoginPresenter {
    static LoginActivity loginActivity;
    static LoginModal loginModal;
    static SQLiteDbHelper database;

    public LoginPresenter(LoginActivity loginActivity, LoginModal loginModal) {
        LoginPresenter.loginActivity = loginActivity;
        LoginPresenter.loginModal = loginModal;
        database = new SQLiteDbHelper(loginActivity);
    }

    public static void login(LoginModal loginModal) {
        if(!loginModal.email()){
            loginActivity.onError("Email is not valid");
            return;
        }
        if(!loginModal.password()){
            loginActivity.onError("Password is not valid");
            return;
        }

        if(!database.Login(loginModal)){
            loginActivity.onError("Invalid Credentials");
        }else{
            loginActivity.onSuccess("Login Successful");
        }
    }

}
