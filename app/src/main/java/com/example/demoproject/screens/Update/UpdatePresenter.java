package com.example.demoproject.screens.Update;

import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.example.demoproject.database.SQLiteDbHelper;
import com.example.demoproject.screens.SignUp.MainActivity;

public class UpdatePresenter {
    static UpdateModel UpdateModel;
    static UpdateActivity UpdateActivity;
    static SQLiteDbHelper database;

    public UpdatePresenter(UpdateActivity UpdateActivity, UpdateModel UpdateModel) {
        UpdatePresenter.UpdateActivity = UpdateActivity;
        UpdatePresenter.UpdateModel = UpdateModel;
        database = new SQLiteDbHelper(UpdateActivity);
    }

    public void update(UpdateModel UpdateModel){
        if(!UpdateModel.firstName()){
            UpdateActivity.onError("Invalid First Name");
            return;
        }
        if(!UpdateModel.lastName()){
            UpdateActivity.onError("Invalid Last Name");
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
        database.updateData(UpdateModel);
        UpdateActivity.onSuccess("Update Successful");
    }
}
