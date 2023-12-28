package com.example.demoproject.screens.SignUp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.demoproject.R;
import com.example.demoproject.screens.Login.LoginActivity;

public class MainActivity extends AppCompatActivity implements SignupListener, View.OnClickListener{
    Button button,signuptologinbutton;
    TextView firstnameEditView, lastnameEditView, emailEditView, passwordEditView, confirmPasswordEditView;
    String firstname, lastname, email, password, confirmPassword;
    SignupModel signupModel;
    SignUpPresenter signUpPresenter;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.initializeView();

        //this line of code prevents the keyboard from pushing the layout up
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        button.setOnClickListener(this);
        signuptologinbutton.setOnClickListener(this);
    }

    public void initializeView() {
        button = (Button) findViewById(R.id.btn_signup);

        signuptologinbutton = (Button) findViewById(R.id.btn_signup_to_login);

        firstnameEditView = (TextView) findViewById(R.id.edt_first);
        lastnameEditView = (TextView) findViewById(R.id.edt_lastname);
        emailEditView = (TextView) findViewById(R.id.edt_email);
        passwordEditView = (TextView) findViewById(R.id.edt_password);
        confirmPasswordEditView = (TextView) findViewById(R.id.edt_confirm);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_signup:
                SignUpView();
                break;
            case R.id.btn_signup_to_login:
                intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                break;
        }
    }

    public void SignUpView() {
        firstname = firstnameEditView.getText().toString();
        lastname = lastnameEditView.getText().toString();
        email = emailEditView.getText().toString();
        password = passwordEditView.getText().toString();
        confirmPassword = confirmPasswordEditView.getText().toString();

        signupModel = new SignupModel();

        signupModel.setFirstName(firstname);
        signupModel.setLastName(lastname);
        signupModel.setEmail(email);
        signupModel.setPassword(password);
        signupModel.setConfirmPassword(confirmPassword);

        signUpPresenter = new SignUpPresenter(this, signupModel);
        signUpPresenter.signup(signupModel);
    }

    public void onSuccess(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

        firstnameEditView.setText("");
        lastnameEditView.setText("");
        emailEditView.setText("");
        passwordEditView.setText("");
        confirmPasswordEditView.setText("");

        intent = new Intent(this, LoginActivity.class);
        intent.putExtra("email", email);
        startActivity(intent);
    }

    public void onError(String invalidInput) {
        Toast.makeText(this, invalidInput, Toast.LENGTH_SHORT).show();
    }
}