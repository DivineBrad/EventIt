package com.example.bradj.eventit.Utilities;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.bradj.eventit.Model.Entity.User;
import com.google.gson.Gson;
import com.pixplicity.easyprefs.library.Prefs;

/**
 * Created by ajibd on 1/4/2018.
 */

public class LoginUtil  {
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
//        return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
//                .getBoolean("LOGGED", false);
        return Prefs.getBoolean("LOGGED", false);
    }

    public void setLoggedIn(@NonNull Context context, boolean value) {
        if(!value){
//            context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE).edit().remove("user id");
            Prefs.remove("user id");
            Prefs.remove("user object");
        }

//        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
//                .edit().putBoolean("LOGGED", value).apply();
        Prefs.putBoolean("LOGGED", value);
    }

    public void createUserSession(@NonNull Context context, int id, User user){
//        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
//                .edit().putInt("user id", id).apply();
        Gson gson=new Gson();
        String userString= gson.toJson(user);
        Prefs.putInt("user id", id);
        Prefs.putString("user object", userString);
        this.setLoggedIn(context, true);
    }

}
