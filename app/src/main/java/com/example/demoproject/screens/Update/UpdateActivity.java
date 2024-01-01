package com.example.demoproject.screens.Update;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.demoproject.R;
import com.example.demoproject.database.SQLiteDbHelper;
import com.example.demoproject.screens.Home.HomeActivity;
import com.example.demoproject.screens.Login.LoginActivity;
import com.example.demoproject.screens.SignUp.MainActivity;

public class UpdateActivity extends AppCompatActivity implements UpdateListener, View.OnClickListener{
    Button updatebtn, logoutbtn;
    EditText firstnameEditView, lastnameEditView, emailEditView, passwordEditView;
    String firstname, lastname, email, password;
    Integer id;
    SQLiteDbHelper database;
    SharedPreferences pref;
    Intent intent;
    UpdateModel updateModel, user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        this.initializeView();

        pref = this.getSharedPreferences("user_details", 0);
        id = pref.getInt("id", 0);

        database = new SQLiteDbHelper(this);
        user = database.getUserDataOnID(id);

        firstnameEditView.setText(user.getFirstName());
        lastnameEditView.setText(user.getLastName());
        emailEditView.setText(user.getEmail());
        passwordEditView.setText(user.getPassword());

        updatebtn.setOnClickListener(this);
        logoutbtn.setOnClickListener(this);
    }

    private void initializeView() {
        updatebtn = findViewById(R.id.btn_update);
        logoutbtn = findViewById(R.id.btn_logout);

        firstnameEditView = findViewById(R.id.et_first_name_update);
        lastnameEditView = findViewById(R.id.et_last_name_update);
        emailEditView = findViewById(R.id.et_email_update);
        passwordEditView = findViewById(R.id.et_password_update);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_update:
                UpdateView();
                break;
            case R.id.btn_logout:
                pref = this.getSharedPreferences("user_details", MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                editor.putBoolean("isFirstTime", false);
                editor.apply();

                intent = new Intent(this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
                break;
        }
    }

    public void UpdateView() {
        firstname = firstnameEditView.getText().toString();
        lastname = lastnameEditView.getText().toString();
        email = emailEditView.getText().toString();
        password = passwordEditView.getText().toString();

        updateModel = new UpdateModel();

        updateModel.setId(id);
        updateModel.setFirstName(firstname);
        updateModel.setLastName(lastname);
        updateModel.setEmail(email);
        updateModel.setPassword(password);

        //Presenter
        UpdatePresenter updatePresenter = new UpdatePresenter(this, updateModel);

        updatePresenter.update(updateModel);
    }

    @Override
    public void onSuccess(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        intent = new Intent(this, HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    @Override
    public void onError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}