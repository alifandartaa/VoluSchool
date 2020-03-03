package com.example.voluschool.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

public class AppPreference {
    private static final String PREFERENCE_NAME = "preference_name";
    private static final String TOKEN = "token";
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @SuppressLint("CommitPrefEdits")
    public AppPreference(Context context) {

        sharedPreferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }


    public boolean isLoggedIn() {
        return sharedPreferences.getString(TOKEN, null) != null;
    }

    public void saveToken(String token) {
        editor.putString(TOKEN, token);
        editor.apply();
    }

    public String getToken() {
        return sharedPreferences.getString(TOKEN, null);
    }

    public void logout() {
        editor.clear();
        editor.apply();
    }
}
