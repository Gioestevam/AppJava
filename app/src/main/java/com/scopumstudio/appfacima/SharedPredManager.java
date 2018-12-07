package com.scopumstudio.appfacima;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPredManager {

    private static SharedPredManager mInstance;
    private static Context mCtx;

    private static final String SHARED_PREF_NAME    = "mysharedpref12";
    public static final String KEY_USERNAME         = "user_username";
    public static final String KEY_USER_EMAIL       = "user_email";
    public static final String KEY_USER_ID          = "user_id";

    private SharedPredManager(Context context) {
        mCtx = context;
    }

    public static synchronized SharedPredManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPredManager(context);
        }
        return mInstance;
    }

    public boolean userLogin(int id, String email, String username) {

        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt(KEY_USER_ID, id);
        editor.putString(KEY_USERNAME, username);
        editor.putString(KEY_USER_EMAIL, email);

        editor.apply();

        return true;
    }

    public boolean isLoggedIn() {

        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

        if (sharedPreferences.getString(KEY_USERNAME, null) != null) {
            return true;
        }

        return false;
    }

    public boolean logout() {

        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.clear();
        editor.apply();

        return true;
    }

    public String getUsername() {

        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

        return sharedPreferences.getString(KEY_USERNAME, null);
    }

    public String getEmail() {

        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

        return sharedPreferences.getString(KEY_USER_EMAIL, null);
    }

    public Integer getIdUser() {

        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

        return Integer.parseInt(sharedPreferences.getString(KEY_USER_ID, null));
    }

}
