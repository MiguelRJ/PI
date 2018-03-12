package com.mrj.morejmoney.ui.prefs;

import android.os.Bundle;
import android.support.v7.preference.Preference;

import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v4.app.Fragment;
import android.widget.Toast;
import com.mrj.morejmoney.R;
import com.mrj.morejmoney.data.prefs.AppPreferencesHelper;

import java.lang.reflect.Field;

/**
 * Created by Miguel on 19/11/2017.
 */

public class AccountSettingView extends PreferenceFragmentCompat {

    public static final String TAG = "AccountSettingView";

    private AccountSettingView accountSettingView;

    public static Fragment newInstance(Bundle bundle){
        AccountSettingView accountSettingView = new AccountSettingView();
        if (bundle != null) {
            accountSettingView.setArguments(bundle);
        }
        return accountSettingView;
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.settings);

        Preference remember = findPreference(AppPreferencesHelper.PREF_KEY_CURRENT_REMEMBER);
        remember.setDefaultValue(AppPreferencesHelper.getInstance().getCurrentRemember());
        remember.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                AppPreferencesHelper.getInstance().setCurrentRemember((boolean)newValue);
                Toast.makeText(getActivity(),String.valueOf(AppPreferencesHelper.getInstance().getCurrentRemember()),Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        Preference user = findPreference(AppPreferencesHelper.PREF_KEY_CURRENT_USER_NAME);
        user.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                Toast.makeText(getActivity(),AppPreferencesHelper.getInstance().getCurrentUserName(),Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        Preference password = findPreference(AppPreferencesHelper.PREF_KEY_CURRENT_USER_PASSWORD);
        password.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                Toast.makeText(getActivity(),AppPreferencesHelper.getInstance().getCurrentUserPassword(),Toast.LENGTH_SHORT).show();
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
                Toast.makeText(getActivity(),String.valueOf(AppPreferencesHelper.getInstance().getIcon()),Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        Preference showIcon = findPreference(AppPreferencesHelper.PREF_KEY_ICON_SHOW);
        showIcon.setDefaultValue(AppPreferencesHelper.getInstance().getIconShow());
        showIcon.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                AppPreferencesHelper.getInstance().setIconShow((boolean)newValue);
                Toast.makeText(getActivity(),String.valueOf(AppPreferencesHelper.getInstance().getIconShow()),Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }

}
