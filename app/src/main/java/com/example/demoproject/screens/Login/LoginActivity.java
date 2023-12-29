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
import com.example.demoproject.screens.SignUp.MainActivity;

public class LoginActivity extends AppCompatActivity implements LoginListener, View.OnClickListener{

    Button loginbutton, logintosignupbutton;
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

        intent = getIntent();
        emailEditView.setText(intent.getStringExtra("email"));

        loginbutton.setOnClickListener(this);
        logintosignupbutton.setOnClickListener(this);
    }

    public void initializeView() {
        loginbutton = findViewById(R.id.btn_login);
        emailEditView = findViewById(R.id.edt_email);
        passwordEditView = findViewById(R.id.edt_password);
        logintosignupbutton = findViewById(R.id.btn_login_to_signup);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                LoginView();
                break;
            case R.id.btn_login_to_signup:
                intent = new Intent(this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
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

        emailEditView.setText("");
        passwordEditView.setText("");

        SharedPreferences pref = this.getSharedPreferences("user_details", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("isFirstTime", true);
        editor.putInt("id", loginModel.getId());
        editor.apply();

        Intent intent = new Intent(this, HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
    @Override
    public void onError(String invalidInput) {
        Toast.makeText(this, invalidInput, Toast.LENGTH_SHORT).show();
    }
}