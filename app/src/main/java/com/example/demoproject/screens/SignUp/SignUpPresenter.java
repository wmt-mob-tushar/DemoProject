package com.example.demoproject.screens.SignUp;

import android.content.Intent;
import android.widget.Toast;

import com.example.demoproject.screens.Login.LoginActivity;
import com.example.demoproject.database.UserDatabaseHelper;

public class SignUpPresenter{

    static MainActivity Mainactivity;
    static SignupModel signupModel;
    static UserDatabaseHelper database;

    public SignUpPresenter(MainActivity Mainactivity, SignupModel signupModel) {
        SignUpPresenter.Mainactivity = Mainactivity;
        SignUpPresenter.signupModel = signupModel;
        database = new UserDatabaseHelper(Mainactivity);
    }

    public static void signup(String firstname, String lastname, String email, String password, String confirmPassword) {

        if (!signupModel.isValid()) {
            Toast.makeText(Mainactivity, "Invalid Input", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!signupModel.email()) {
            Toast.makeText(Mainactivity, "Invalid Email", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!signupModel.confirmPassword(confirmPassword)) {
            Toast.makeText(Mainactivity, "Passwords do not match", Toast.LENGTH_SHORT).show();
            return;
        }

//      insert into database
        signupModel.firstName = firstname;
        signupModel.lastName = lastname;
        signupModel.email = email;
        signupModel.password = password;

//      if user already exists
        if (database.checkUser(signupModel.email)) {
            Toast.makeText(Mainactivity, "User already exists", Toast.LENGTH_SHORT).show();
        } else {
            database.SignupInsertData(signupModel);

//          if(Mainactivity != null){
            Mainactivity.onSuccess("Signup Successful");
            Intent intent = new Intent(Mainactivity, LoginActivity.class);
            intent.putExtra("email", email);
            Mainactivity.startActivity(intent);
//            }else{
//                Mainactivity.onError("Signup Failed");
//            }
        }

    }

}


