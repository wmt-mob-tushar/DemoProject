package com.example.demoproject.screens.Login;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;

import com.example.demoproject.database.UserDatabaseHelper;
import com.example.demoproject.screens.Home.HomeActivity;

public class LoginPresenter {
    static LoginActivity loginActivity;
    static LoginModal loginModal;
    static UserDatabaseHelper database;

    public LoginPresenter(LoginActivity loginActivity, LoginModal loginModal) {
        LoginPresenter.loginActivity = loginActivity;
        LoginPresenter.loginModal = loginModal;
        database = new UserDatabaseHelper(loginActivity);
    }

    public static void login(String email, String password) {

        if (!loginModal.isValid()) {
            loginActivity.onError("Invalid Input");
            return;
        }

        if (!loginModal.email()) {
            loginActivity.onError("Invalid Email");
            return;
        }

        if (!loginModal.password()) {
            loginActivity.onError("Invalid Password");
            return;
        }

        loginModal.email = email;
        loginModal.password = password;

        if(!database.checkUserLogin(loginModal.email, loginModal.password)){
            loginActivity.onError("Invalid Credentials");
        }else{
            loginActivity.onSuccess("Login Successful");

            Intent intent = new Intent(loginActivity, HomeActivity.class);
            loginActivity.startActivity(intent);

            //set setpreference true if user is logged in
            SharedPreferences pref = loginActivity.getSharedPreferences("user_details", MODE_PRIVATE);
            SharedPreferences.Editor editor = pref.edit();
            editor.putBoolean("isFirstTime", true);
            editor.putString("email", loginModal.email);
            editor.apply();
        }
    }

}
