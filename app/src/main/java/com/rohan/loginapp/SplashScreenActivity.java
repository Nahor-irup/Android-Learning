package com.rohan.loginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                checkUserLogin();
            }
        },3000);
    }
    private void checkUserLogin(){

        SharedPreferences sharedPreferences=getSharedPreferences("Login_pref", Context.MODE_PRIVATE);
        boolean defaultValue=false;
        boolean isLoggedIn=sharedPreferences.getBoolean("is_logged_in",defaultValue);

        Intent intent;
        if(isLoggedIn){
            intent = new Intent(SplashScreenActivity.this, DashboardActivity.class);
        }else{
            intent = new Intent(SplashScreenActivity.this, MainActivity.class);
        }
        startActivity(intent);
        finish();
    }
}

