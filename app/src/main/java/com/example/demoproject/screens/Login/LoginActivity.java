package com.example.demoproject.screens.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.demoproject.R;
import com.example.demoproject.screens.Home.HomeActivity;

public class LoginActivity extends AppCompatActivity implements LoginListener, View.OnClickListener{

    Button loginbutton;
    TextView emailEditView, passwordEditView;
    LoginModal loginModel;
    LoginPresenter loginPresenter;
    Intent intent;
    String email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.initializeView();

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        intent = getIntent();
        emailEditView.setText(intent.getStringExtra("email"));

        loginbutton.setOnClickListener(this);
    }

    public void initializeView() {
        loginbutton = findViewById(R.id.btn_login);
        emailEditView = findViewById(R.id.edt_email);
        passwordEditView = findViewById(R.id.edt_password);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                LoginView();
                break;
        }
    }

    public void LoginView() {
        email = emailEditView.getText().toString();
        password = passwordEditView.getText().toString();

        loginModel = new LoginModal();

        loginModel.setEmail(email);
        loginModel.setPassword(password);
        //Presenter
        loginPresenter = new LoginPresenter(this, loginModel);
        //call login method
        LoginPresenter.login(loginModel);
    }

    @Override
    public void onSuccess(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

        SharedPreferences pref = this.getSharedPreferences("user_details", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("isFirstTime", true);
        editor.putInt("id", loginModel.getId());
        editor.apply();

        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
    @Override
    public void onError(String invalidInput) {
        Toast.makeText(this, invalidInput, Toast.LENGTH_SHORT).show();
    }

}