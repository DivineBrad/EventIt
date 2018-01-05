package com.example.bradj.eventit;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.bradj.eventit.Utilities.LoginUtil;

public class SplashActivity extends AppCompatActivity {

    LoginUtil loginUtil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        loginUtil= LoginUtil.getInstance();

        if (loginUtil.isLoggedIn(getApplicationContext())){
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public void onClickLogin(View view){
        Intent myIntent = new Intent(SplashActivity.this,
                LoginActivity.class);
        startActivity(myIntent);

    }

    public void onClickRegister(View view){
        Intent myIntent = new Intent(SplashActivity.this,
                RegisterActivity.class);
        startActivity(myIntent);
        finish();

    }
}
