package com.example.demoproject.View.listener;

public interface SignupListener {

        void onEmailError();

        void onError(String message);

        void onSuccess(String message);
}
