package com.example.demoproject.screens.SignUp;
import android.util.Log;

import com.example.demoproject.database.SQLiteDbHelper;

public class SignUpPresenter{

    static MainActivity Mainactivity;
    static SignupModel signupModel;
    static SQLiteDbHelper database;

    public SignUpPresenter(MainActivity Mainactivity, SignupModel signupModel) {
        SignUpPresenter.Mainactivity = Mainactivity;
        SignUpPresenter.signupModel = signupModel;
        database = new SQLiteDbHelper(Mainactivity);
    }

    public static void signup(SignupModel signupModel) {
        if (!signupModel.firstName()) {
            Mainactivity.onError("First Name is not valid");
            return;
        }
        if (!signupModel.lastName()) {
            Mainactivity.onError("Last Name is not valid");
            return;
        }
        if (!signupModel.email()) {
            Mainactivity.onError("Email is not valid");
            return;
        }
        if (!signupModel.password()) {
            Mainactivity.onError("Password is not valid");
            return;
        }
        if (!signupModel.confirmPassword(signupModel.getConfirmPassword())) {
            Mainactivity.onError("Password and Confirm Password is not same");
            return;
        }
        if (database.checkUser(signupModel)) {
            Mainactivity.onError("Email already exists");
            return;
        }

        database.SignupInsertData(signupModel);

        Mainactivity.onSuccess("Signup Successful");

    }
}


