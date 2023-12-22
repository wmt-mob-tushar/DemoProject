package com.example.demoproject.View.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.demoproject.Model.SignupModel;
import com.example.demoproject.Presenter.SignUpPresenter;
import com.example.demoproject.R;
import com.example.demoproject.View.listener.SignupListener;

public class MainActivity extends AppCompatActivity implements SignupListener {

    Button button,signuptologinbutton;
    TextView firstnameEditView, lastnameEditView, emailEditView, passwordEditView, confirmPasswordEditView;

    SignUpPresenter signUpPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.initializeView();

        //this line of code prevents the keyboard from pushing the layout up
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        button.setOnClickListener(view -> {
//            SignUp activity using MVP Structure

            String firstname = firstnameEditView.getText().toString();
            String lastname = lastnameEditView.getText().toString();
            String email = emailEditView.getText().toString();
            String password = passwordEditView.getText().toString();
            String confirmPassword = confirmPasswordEditView.getText().toString();

            //just for debugging
            System.out.println("SignUpActivityDebugger : firstname : " + firstname + " lastname : " + lastname + " email : " + email + " password : " + password + " confirmPassword : " + confirmPassword);

            SignupModel signupModel = new SignupModel(firstname, lastname, email, password);

            //Presenter
            signUpPresenter = new SignUpPresenter(this, signupModel);

            //call signup method
            SignUpPresenter.signup(firstname, lastname, email, password, confirmPassword);
        });

        signuptologinbutton.setOnClickListener(view -> {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        });
    }

    public void initializeView() {
        //sign up button
        button = (Button) findViewById(R.id.btn_signup);

        //signup to login btn
        signuptologinbutton = (Button) findViewById(R.id.btn_signup_to_login);

        //name, email, password, confirm password
        firstnameEditView = (TextView) findViewById(R.id.edt_first);
        lastnameEditView = (TextView) findViewById(R.id.edt_lastname);
        emailEditView = (TextView) findViewById(R.id.edt_email);
        passwordEditView = (TextView) findViewById(R.id.edt_password);
        confirmPasswordEditView = (TextView) findViewById(R.id.edt_confirm);
    }

    @Override
    public void onEmailError() {
        Toast.makeText(this, "Email error", Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onSuccess(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onError(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}