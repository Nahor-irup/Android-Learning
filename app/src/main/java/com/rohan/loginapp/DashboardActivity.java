package com.rohan.loginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

public class DashboardActivity extends AppCompatActivity {
    public static String KEY_USER_EMAIL="user_email";
    public static String KEY_USER_PASSWORD="password";
    public static String KEY_USER_CREDENTIALS="user_credentials";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        String defaultEmail="rohan@gmail.com";
//        Intent intent = getIntent();
//        UserCredentials userCredentials= (UserCredentials) intent.getSerializableExtra(KEY_USER_CREDENTIALS);
//
//        String userEmail=userCredentials.getUserEmail();
//        String userPassword=userCredentials.getUserPassword();
//        Toast.makeText(DashboardActivity.this,
//                userEmail+", Pass:"+userPassword,
//                Toast.LENGTH_LONG).show();

        //Reading from shared preferences
        SharedPreferences sharedPreferences = getSharedPreferences("Login_pref", Context.MODE_PRIVATE);
        String email=sharedPreferences.getString("user_email","");
        String password=sharedPreferences.getString("user_password","");
        Toast.makeText(DashboardActivity.this, "Email: "+email+",Password: "+password, Toast.LENGTH_LONG).show();
    }
}