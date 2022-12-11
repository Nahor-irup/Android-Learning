package com.rohan.loginapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.rohan.loginapp.db.TestDatabase;
import com.rohan.loginapp.db.User;
import com.rohan.loginapp.db.UserDao;

public class MainActivity extends AppCompatActivity {
    private EditText em;
    private EditText pass;
    private Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        em= (EditText) findViewById(R.id.txt_email);
        pass = (EditText) findViewById(R.id.txt_password);
        btnLogin=(Button) findViewById(R.id.btn_login);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=em.getText().toString().trim();
                String password=pass.getText().toString();

                if(email.isEmpty()){
                    Toast.makeText(MainActivity.this, "Enter Email", Toast.LENGTH_SHORT).show();
                }else if(!email.matches("^(.+)@(.+)$")){
                    Toast.makeText(MainActivity.this, "Invalid Email Format!!", Toast.LENGTH_SHORT).show();
                }else if(password.isEmpty())
                {
                    Toast.makeText(MainActivity.this, "Enter Password", Toast.LENGTH_SHORT).show();
                }else {
                    if (email.equals("admin@gmail.com") && password.equals("admin")) {
                        Toast.makeText(MainActivity.this, "Login Success", Toast.LENGTH_SHORT).show();

                        SharedPreferences sharedPreferences=getSharedPreferences("Login_pref", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor= sharedPreferences.edit();
                        editor.putString("user_email",email);
                        editor.putString("user_password",password);
                        editor.putBoolean("is_logged_in",true);
                        editor.apply();

                        //Writing in SqliteDB
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                storeDataInDb(email,password);
                            }
                        }).start();

                        Intent intent = new Intent(MainActivity.this,DashboardActivity.class);
//                        intent.putExtra(DashboardActivity.KEY_USER_EMAIL,email);
//                        intent.putExtra(DashboardActivity.KEY_USER_PASSWORD,password);
//                        UserCredentials userCredentials =new UserCredentials(email,password);
//                        intent.putExtra(DashboardActivity.KEY_USER_CREDENTIALS,userCredentials);
                        startActivity(intent);
                        finish();

                    } else {
                        Toast.makeText(MainActivity.this, "Invalid Credential", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }

    private void storeDataInDb(String email, String password){
        TestDatabase database = Room.databaseBuilder(getApplicationContext(),
                TestDatabase.class,"test.db").build();
        UserDao userDao = database.getUserDao();

        User user = new User();
        user.email=email;
        user.password=password;
        userDao.insertUser(user);
    }
}