package com.example.bradj.eventit.Utilities;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by ajibd on 1/4/2018.
 */

public class LoginUtil extends AppCompatActivity {
    private static LoginUtil instance;
    public static final String PREFS_NAME = "default_preferences";

    public synchronized static LoginUtil getInstance() {
        if (instance == null) {
            instance = new LoginUtil();
        }
        return instance;
    }

    private LoginUtil() {
    }


    public boolean isLoggedIn(@NonNull Context context) {
        return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
                .getBoolean("LOGGED", false);
    }

    public void setLoggedIn(@NonNull Context context, boolean value) {
        if(!value)
            context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE).edit().remove("user id");
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
                .edit().putBoolean("LOGGED", value).apply();
    }

    public void createUserSession(@NonNull Context context, int id){
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
                .edit().putInt("user id", id).apply();
        this.setLoggedIn(context, true);
    }

}
