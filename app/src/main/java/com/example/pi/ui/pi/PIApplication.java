package com.example.pi.ui.pi;

import android.app.Application;
import android.content.Context;
import com.example.pi.data.prefs.AppPreferencesHelper;

/**
 * Created by
 * @author Miguel Rodriguez Jimenez
 * @date 04/01/18
 *
 */

public class PIApplication extends Application {
    private AppPreferencesHelper appPreferencesHelper;
    private static PIApplication mContext;

    public PIApplication() {
        mContext = this;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appPreferencesHelper = AppPreferencesHelper.getInstance();
    }

    public AppPreferencesHelper getAppPreferencesHelper(){
        return appPreferencesHelper;
    }

    public static Context getContext() {
        return mContext;
    }
}
