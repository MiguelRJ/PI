package com.mrj.morejmoney.ui.recover;

import android.text.TextUtils;

import com.mrj.morejmoney.data.repository.UserRepository;
import com.mrj.morejmoney.data.prefs.AppPreferencesHelper;
import com.mrj.morejmoney.ui.utils.CommonUtils;

/**
 * Created by
 * @author Miguel Rodriguez Jimenez
 * @date 07/01/18
 *
 */

public class RecoverInteractor implements RecoverInteractorInterface {

    @Override
    public void validateCredentials(String user, String email, OnRecoverFinishedListener listener) {

        if (TextUtils.isEmpty(user)){
            listener.onUserEmptyError();
        } else if (TextUtils.isEmpty(email)){
            listener.onEmailEmptyError();
        } else if (!CommonUtils.isEmailValid(email)){
            listener.onEmailError();
        } else if (UserRepository.getInstance().isUserAlreadySignIn(user) && UserRepository.getInstance().isEmailAlreadySignIn(email)){
            AppPreferencesHelper.getInstance().setCurrentUserPassword(UserRepository.getInstance().getPasswordBy(user));
            listener.onSuccess();
        } else {
            AppPreferencesHelper.getInstance().setCurrentUserPassword("null");
            listener.onSuccess();
        }
    }

}
