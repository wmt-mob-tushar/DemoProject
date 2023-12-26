package com.example.demoproject.screens.Login;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

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
//            loginActivity.onError("Invalid Input");
            Toast.makeText(loginActivity, "Invalid Input", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!loginModal.email()) {
//            loginActivity.onError("Invalid Email");
            Toast.makeText(loginActivity, "Invalid Email", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!loginModal.password()) {
//            loginActivity.onError("Invalid Password");
            Toast.makeText(loginActivity, "Invalid Password", Toast.LENGTH_SHORT).show();
            return;
        }

        loginModal.email = email;
        loginModal.password = password;

        if(!database.checkUserLogin(loginModal.email, loginModal.password)){
//            loginActivity.onError("Invalid Credentials");
            Toast.makeText(loginActivity, "Invalid Credentials", Toast.LENGTH_SHORT).show();
        }else{
//            loginActivity.onSuccess("Login Successful");
            Toast.makeText(loginActivity, "Login successful", Toast.LENGTH_SHORT).show();

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
