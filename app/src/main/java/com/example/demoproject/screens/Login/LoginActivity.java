package com.example.demoproject.screens.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.demoproject.R;

public class LoginActivity extends AppCompatActivity implements LoginListener{

    Button loginbutton;
    TextView emailEditView, passwordEditView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.initializeView();

        //this line of code prevents the keyboard from pushing the layout up
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

//        set email
        Intent intent = getIntent();
        emailEditView.setText(intent.getStringExtra("email"));

        loginbutton.setOnClickListener(view -> {
            String email = emailEditView.getText().toString();
            String password = passwordEditView.getText().toString();

          //just for debugging
//         System.out.println("LoginActivityDebugger : email : " + email + " password : " + password);

            LoginModal loginModel = new LoginModal(email, password);

            //Presenter
            LoginPresenter LoginPresenter = new LoginPresenter(this, loginModel);

            //call login method
            LoginPresenter.login(email, password);
        });
    }

    public void initializeView() {
//      log in button
        loginbutton = findViewById(R.id.btn_login);

        emailEditView = findViewById(R.id.edt_email);
        passwordEditView = findViewById(R.id.edt_password);
    }

    @Override
    public void onError(String invalidInput) {
        Toast.makeText(this, invalidInput, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccess(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}