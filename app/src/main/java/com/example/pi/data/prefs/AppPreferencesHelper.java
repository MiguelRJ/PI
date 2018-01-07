package com.example.pi.data.prefs;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import com.example.pi.ui.pi.PIApplication;
import com.example.pi.ui.utils.AppConstants;

/**
 * Created by
 * @author Miguel Rodriguez Jimenez
 * @date 04/01/18
 * http://envyandroid.com/android-detect-preference-changes/
 * http://www.sgoliver.net/blog/preferencias-en-android-ii-preferenceactivity/
 */

public class AppPreferencesHelper implements AccountPreferencesHelper {

    public interface AppPreferencesListener{
        public void onSharedPreferencesChanged();
    }

    private final SharedPreferences preferences;
    private static AppPreferencesHelper instance;
    private SharedPreferences.OnSharedPreferenceChangeListener listener;
    private String TAG="AppPreferencesHelper";

    private AppPreferencesHelper() {
        this.preferences = ((Application) PIApplication.getContext()).getSharedPreferences(AppConstants.PREF_NAME, Context.MODE_PRIVATE);
        listener = new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

                Log.i(TAG,"onSharedPreferenceChanged: Se ha cambiado la key "+key);
            }
        };
    }

    /**
     * Metodo de acceso a la instancia de la clase AppPreferencesHelper
     * @return
     */
    public static AppPreferencesHelper getInstance(){
        if (instance == null) {
            instance = new AppPreferencesHelper();
        }
        return instance;
    }

    public long getCurrentUserId() {
        long id=preferences.getLong(PREF_KEY_CURRENT_USER_ID, AppConstants.NULL_INDEX);
        return id;
    }

    public String getCurrentUserName() {
        String name=preferences.getString(PREF_KEY_CURRENT_USER_NAME, null);
        return name;
    }

    public String getCurrentUserPassword() {
        String password=preferences.getString(PREF_KEY_CURRENT_USER_PASSWORD, null);
        return password;
    }

    public boolean getCurrentRemember() {
        boolean remember=preferences.getBoolean(PREF_KEY_CURRENT_REMEMBER, false);
        return remember;
    }

    public int getIcon() {
        int numIcon = preferences.getInt(PREF_KEY_ICON,0);
        return numIcon;
    }

    public boolean getIconShow() {
        boolean iconShow = preferences.getBoolean(PREF_KEY_ICON_SHOW,false);
        return iconShow;
    }

    public void setCurrentUserId(long id) {
        preferences.edit().putLong(PREF_KEY_CURRENT_USER_ID,id).apply();
    }

    public void setCurrentUserName(String name) {
        preferences.edit().putString(PREF_KEY_CURRENT_USER_NAME,name).apply();
    }

    public void setCurrentUserPassword(String password) {
        preferences.edit().putString(PREF_KEY_CURRENT_USER_PASSWORD,password).apply();
    }

    public void setCurrentRemember(boolean remember) {
        preferences.edit().putBoolean(PREF_KEY_CURRENT_REMEMBER,remember).apply();
    }

    public void setIcon(int numIcon) {
        preferences.edit().putInt(PREF_KEY_ICON,numIcon).apply();
    }

    public void setIconShow(boolean iconShow) {
        preferences.edit().putBoolean(PREF_KEY_ICON_SHOW,iconShow).apply();
    }

}
