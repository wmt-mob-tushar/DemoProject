package com.example.demoproject.screens.Splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.example.demoproject.R;
import com.example.demoproject.database.SQLiteDbHelper;
import com.example.demoproject.screens.Home.HomeActivity;
import com.example.demoproject.screens.SignUp.MainActivity;

public class SplashActivity extends AppCompatActivity {
    SQLiteDbHelper database;
    Boolean check;
    Intent iNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        database = new SQLiteDbHelper(this);

        new Handler().postDelayed(() -> {
            SharedPreferences pref = getSharedPreferences("user_details", MODE_PRIVATE);
            check = pref.getBoolean("isFirstTime", false);

            iNext = null;
            if (!check) {
                iNext = new Intent(SplashActivity.this, MainActivity.class);
            }else{
                iNext = new Intent(SplashActivity.this, HomeActivity.class);
            }
            startActivity(iNext);
            finish();
        }, 4000);
    }
}