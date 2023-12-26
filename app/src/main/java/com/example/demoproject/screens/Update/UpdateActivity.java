package com.example.demoproject.screens.Update;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.demoproject.R;
import com.example.demoproject.database.UserDatabaseHelper;
import com.example.demoproject.screens.SignUp.MainActivity;
import com.example.demoproject.screens.SignUp.SignupModel;

public class UpdateActivity extends AppCompatActivity implements UpdateListener {

    Toolbar toolbar;

    Button updatebtn, logoutbtn;

    TextView UserID;

    EditText firstnameEditView, lastnameEditView, emailEditView, passwordEditView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        //create a custom toolbar (title and user profile button)
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Update Profile");

        this.initializeView();

        SharedPreferences pref = this.getSharedPreferences("user_details", 0);
        String email = pref.getString("email", null);

//        get data user datafrome database and set it to edit text
        UserDatabaseHelper database = new UserDatabaseHelper(this);
        UpdateModel user = database.getUserDataOnEmail(email);

        firstnameEditView.setText(user.getFirstName());
        lastnameEditView.setText(user.getLastName());
        emailEditView.setText(user.getEmail());
        passwordEditView.setText(user.getPassword());

        UserID.setText("User ID: " + user.getId());

//      update button
        updatebtn.setOnClickListener(view -> {
            String firstname = firstnameEditView.getText().toString();
            String lastname = lastnameEditView.getText().toString();
            String email1 = emailEditView.getText().toString();
            String password = passwordEditView.getText().toString();
            Integer id = user.getId();

//            UpdateModel UpdateModel = new UpdateModel(firstname, lastname, email1, password);
            UpdateModel UpdateModel = new UpdateModel(id, firstname, lastname, email1, password);
            //Presenter
            UpdatePresenter updatePresenter = new UpdatePresenter(this, UpdateModel);

            updatePresenter.update(id,firstname, lastname, email1, password);
        });

//      log out
        logoutbtn.setOnClickListener(view -> {
            //set setpreference true if user is logged in
            SharedPreferences.Editor editor = pref.edit();
            editor.putBoolean("isFirstTime", false);
            editor.putString("email", null);
            editor.apply();

            Intent logout = new Intent(this, MainActivity.class);
            startActivity(logout);
        });
    }

    private void initializeView() {
        updatebtn = findViewById(R.id.btn_update);
        logoutbtn = findViewById(R.id.btn_logout);

        firstnameEditView = findViewById(R.id.et_first_name_update);
        lastnameEditView = findViewById(R.id.et_last_name_update);
        emailEditView = findViewById(R.id.et_email_update);
        passwordEditView = findViewById(R.id.et_password_update);

        UserID = findViewById(R.id.tv_user_id);
    }

    @Override
    public void onSuccess(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}