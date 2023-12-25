package com.example.demoproject.screens.Update;

import android.content.Intent;
import android.widget.Toast;

import com.example.demoproject.database.UserDatabaseHelper;
import com.example.demoproject.screens.Home.HomeActivity;

public class UpdatePresenter {
    static UpdateModel UpdateModel;
    static UpdateActivity UpdateActivity;
    static UserDatabaseHelper database;

    public UpdatePresenter(UpdateActivity UpdateActivity, UpdateModel UpdateModel) {
        UpdatePresenter.UpdateActivity = UpdateActivity;
        UpdatePresenter.UpdateModel = UpdateModel;
        database = new UserDatabaseHelper(UpdateActivity);
    }

    public void update(String firstName, String lastName, String email, String password) {

        if(!UpdateModel.isValid()){
            UpdateActivity.onError("Invalid Input");
            return;
        }
        if(!UpdateModel.email()){
            UpdateActivity.onError("Invalid Email");
            return;
        }
        if(!UpdateModel.password()){
            UpdateActivity.onError("Invalid Password");
            return;
        }

        UpdateModel.firstName = firstName;
        UpdateModel.lastName = lastName;
        UpdateModel.email = email;
        UpdateModel.password = password;

        database.updateData(UpdateModel, UpdateModel.email);

        Intent intent = new Intent(UpdateActivity, HomeActivity.class);
        UpdateActivity.startActivity(intent);

        UpdateActivity.onSuccess("Update Successful");
    }
}
