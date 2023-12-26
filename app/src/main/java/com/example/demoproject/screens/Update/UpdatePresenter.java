package com.example.demoproject.screens.Update;

import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.example.demoproject.database.UserDatabaseHelper;
import com.example.demoproject.screens.Home.HomeActivity;
import com.example.demoproject.screens.SignUp.MainActivity;

public class UpdatePresenter {
    static UpdateModel UpdateModel;
    static UpdateActivity UpdateActivity;
    static UserDatabaseHelper database;

    public UpdatePresenter(UpdateActivity UpdateActivity, UpdateModel UpdateModel) {
        UpdatePresenter.UpdateActivity = UpdateActivity;
        UpdatePresenter.UpdateModel = UpdateModel;
        database = new UserDatabaseHelper(UpdateActivity);
    }

    public void update(Integer id,String firstName, String lastName, String email, String password) {
        if(!UpdateModel.isValid()){
//            UpdateActivity.onError("Invalid Input");
            Toast.makeText(UpdateActivity, "Invalid Input", Toast.LENGTH_SHORT).show();
            return;
        }
        if(!UpdateModel.email()){
//            UpdateActivity.onError("Invalid Email");
            Toast.makeText(UpdateActivity, "Invalid Email", Toast.LENGTH_SHORT).show();
            return;
        }
        if(!UpdateModel.password()){
//            UpdateActivity.onError("Invalid Password");
            Toast.makeText(UpdateActivity, "Invalid Password", Toast.LENGTH_SHORT).show();
            return;
        }

        UpdateModel.id = id;
        UpdateModel.firstName = firstName;
        UpdateModel.lastName = lastName;
        UpdateModel.email = email;
        UpdateModel.password = password;

//        database.updateData(UpdateModel, UpdateModel.email);
        database.updateData(UpdateModel, UpdateModel.id);

//        UpdateActivity.onSuccess("Update Successful");
        Toast.makeText(UpdateActivity, "Update Successful", Toast.LENGTH_SHORT).show();

        SharedPreferences pref = UpdateActivity.getSharedPreferences("user_details", UpdateActivity.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("isFirstTime", false);
        editor.apply();

        Intent intent = new Intent(UpdateActivity, MainActivity.class);
        UpdateActivity.startActivity(intent);
    }
}
