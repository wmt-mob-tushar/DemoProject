package com.example.demoproject.screens.SignUp;

public interface SignupListener {

        void onEmailError();

        void onError(String message);

        void onSuccess(String message);
}
