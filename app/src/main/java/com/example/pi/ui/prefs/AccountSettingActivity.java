package com.example.pi.ui.prefs;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.example.pi.R;
import com.example.pi.data.prefs.AccountPreferencesHelper;
import com.example.pi.data.prefs.AppPreferencesHelper;

import java.lang.reflect.Field;

/**
 * Created by Miguel on 19/11/2017.
 */

public class AccountSettingActivity extends PreferenceActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);

        Preference remember = findPreference(AppPreferencesHelper.PREF_KEY_CURRENT_REMEMBER);
        remember.setDefaultValue(AppPreferencesHelper.getInstance().getCurrentRemember());
        remember.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                AppPreferencesHelper.getInstance().setCurrentRemember((boolean)newValue);
                Toast.makeText(getApplicationContext(),String.valueOf(AppPreferencesHelper.getInstance().getCurrentRemember()),Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        Preference user = findPreference(AppPreferencesHelper.PREF_KEY_CURRENT_USER_NAME);
        user.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                Toast.makeText(getApplicationContext(),AppPreferencesHelper.getInstance().getCurrentUserName(),Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        Preference password = findPreference(AppPreferencesHelper.PREF_KEY_CURRENT_USER_PASSWORD);
        password.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                Toast.makeText(getApplicationContext(),AppPreferencesHelper.getInstance().getCurrentUserPassword(),Toast.LENGTH_SHORT).show();
                return true;
            }
        });


        Preference icon = findPreference(AppPreferencesHelper.PREF_KEY_ICON);
        icon.setDefaultValue(AppPreferencesHelper.getInstance().getIcon());
        icon.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                Class res = R.drawable.class;
                Field field = null;
                int drawableId=0;
                try {
                    field = res.getField(String.valueOf(newValue));
                    drawableId = field.getInt(null);
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                //Toast.makeText(getApplicationContext(),String.valueOf(drawableId),Toast.LENGTH_SHORT).show();
                AppPreferencesHelper.getInstance().setIcon(drawableId);
                Toast.makeText(getApplicationContext(),String.valueOf(AppPreferencesHelper.getInstance().getIcon()),Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        Preference showIcon = findPreference(AppPreferencesHelper.PREF_KEY_ICON_SHOW);
        showIcon.setDefaultValue(AppPreferencesHelper.getInstance().getIconShow());
        showIcon.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                AppPreferencesHelper.getInstance().setIconShow((boolean)newValue);
                Toast.makeText(getApplicationContext(),String.valueOf(AppPreferencesHelper.getInstance().getIconShow()),Toast.LENGTH_SHORT).show();
                return true;
            }
        });

    }

}
