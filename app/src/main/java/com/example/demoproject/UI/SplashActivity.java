package com.example.demoproject.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.example.demoproject.R;
import com.example.demoproject.database.UserDatabaseHelper;
import com.example.demoproject.screens.Home.HomeActivity;
import com.example.demoproject.screens.SignUp.MainActivity;

public class SplashActivity extends AppCompatActivity {

    UserDatabaseHelper database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

//        Intent splashtohome = new Intent(SplashActivity.this, MainActivity.class);
        new Handler().postDelayed(() -> {

//           getSharedPreference used to store data in key value pair
            SharedPreferences pref = getSharedPreferences("user_details", MODE_PRIVATE);
            Boolean check = pref.getBoolean("isFirstTime", false);

//                load database while splash screen is loading
            database = new UserDatabaseHelper(SplashActivity.this);

            Intent iNext = null;
//                if user is not logged in then it will redirect to login page
            if (!check) {
//                    sign up page
                iNext = new Intent(SplashActivity.this, MainActivity.class);

            }else{
//                    home page
                iNext = new Intent(SplashActivity.this, HomeActivity.class);

            }

            startActivity(iNext);
            finish();
        }, 4000);
    }
}