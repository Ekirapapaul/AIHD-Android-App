package org.aihdint.aihd.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.HashMap;

/**
 * Developed by Rodney on 15/02/2018.
 */

public class SessionManager {
    // LogCat tag
    private static String TAG = SessionManager.class.getSimpleName();

    // Shared Preferences
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    // Shared preferences file name
    private static final String PREF_NAME = "AIHD";

    private static final String KEY_IS_LOGGEDIN = "isLoggedIn";
    private static final String KEY_USER_ID = "user_id";
    private static final String KEY_USER = "user";
    private static final String KEY_LOCATION = "location_id";
    private static final String KEY_MFL_CODE = "mfl_code";

    public SessionManager(Context context) {
        int PRIVATE_MODE = 0;
        pref = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
        editor.apply();
    }

    public void setLogin(boolean isLoggedIn) {

        editor.putBoolean(KEY_IS_LOGGEDIN, isLoggedIn);

        // commit changes
        editor.commit();

        Log.d(TAG, "User login session modified!");
    }

    public boolean isLoggedIn(){
        return pref.getBoolean(KEY_IS_LOGGEDIN, false);
    }

    public void createLogin(String user_id, String name, String location_id, String mfl_code) {
        editor.putString(KEY_USER_ID,user_id);
        editor.putString(KEY_USER, name);
        editor.putString(KEY_LOCATION, location_id);
        editor.putString(KEY_MFL_CODE, mfl_code);
        editor.putBoolean(KEY_IS_LOGGEDIN, true);
        editor.commit();
    }

    public HashMap<String, String> getUserDetails() {
        HashMap<String, String> profile = new HashMap<>();
        profile.put("user_id", pref.getString(KEY_USER_ID, null));
        profile.put("name", pref.getString(KEY_USER, null));
        profile.put("location_id", pref.getString(KEY_LOCATION, null));
        profile.put("mfl_code", pref.getString(KEY_MFL_CODE, null));
        return profile;
    }
}
