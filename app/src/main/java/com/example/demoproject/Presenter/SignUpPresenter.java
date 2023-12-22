package com.example.demoproject.Presenter;

import android.content.Intent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Toast;

import com.example.demoproject.Model.SignupModel;
import com.example.demoproject.View.activity.LoginActivity;
import com.example.demoproject.View.activity.MainActivity;
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
            Toast.makeText(Mainactivity, "Fill the proper detailed", Toast.LENGTH_SHORT).show();
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

        //insert into database
        database.SignupInsertData(signupModel);

        if(Mainactivity != null){
            Mainactivity.onSuccess("Signup Successful");
            Intent intent = new Intent(Mainactivity, LoginActivity.class);
            Mainactivity.startActivity(intent);
        }else{
            Mainactivity.onError("Signup Failed");
        }
    }

}


