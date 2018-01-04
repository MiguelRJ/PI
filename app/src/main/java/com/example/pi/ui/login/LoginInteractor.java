package com.example.pi.ui.login;

import android.text.TextUtils;

import com.example.pi.data.db.repository.UserRepository;
import com.example.pi.data.prefs.AppPreferencesHelper;
import com.example.pi.ui.pi.PIApplication;
import com.example.pi.ui.utils.CommonUtils;

/**
 * Created by Miguel on 04/01/2018.
 */

public class LoginInteractor implements LoginInteractorInterface {

    @Override
    public void validateCredentials(String user, String password, OnLoginFinishedListener listener) {

        if(TextUtils.isEmpty(user)) {
            listener.onUserEmptyError();
        }else if (TextUtils.isEmpty(password)) {
            listener.onPasswordEmptyError();
        }else if (!CommonUtils.isPasswordValid(password)) {
            listener.onPasswordError();
        } else if (UserRepository.getInstance().validateCredentials(user,password)){
            AppPreferencesHelper.getInstance().setCurrentUserId(
                    UserRepository.getInstance().getUserIdBy(user)
            );
            AppPreferencesHelper.getInstance().setCurrentUserName(user);
            AppPreferencesHelper.getInstance().setCurrentUserPassword(password);
            listener.onSuccess();
        } else {
            listener.onCredentialsFail();
        }

    }
}
