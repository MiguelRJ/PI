package com.example.pi.ui.login;

import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.example.pi.data.db.model.PiggyBank;
import com.example.pi.data.db.repository.PiggyBankRepository;
import com.example.pi.data.db.repository.TransactionRepository;
import com.example.pi.data.db.repository.UserRepository;
import com.example.pi.data.prefs.AppPreferencesHelper;
import com.example.pi.ui.pi.PIApplication;
import com.example.pi.ui.utils.CommonUtils;

/**
 * Created by Miguel on 04/01/2018.
 */

public class LoginInteractor implements LoginInteractorInterface {

    @Override
    public void validateCredentials(String user, String password, boolean remember, OnLoginFinishedListener listener) {

        if (TextUtils.isEmpty(user)) {
            listener.onUserEmptyError();
        } else if (TextUtils.isEmpty(password)) {
            listener.onPasswordEmptyError();
        } else if (!CommonUtils.isPasswordValid(password)) {
            listener.onPasswordError();
        } else if (UserRepository.getInstance().validateCredentials(user,password)){
            AppPreferencesHelper.getInstance().setCurrentUserId(
                    UserRepository.getInstance().getUserIdBy(user)
            );
            PiggyBankRepository.getInstance().resetArray();
            PiggyBankRepository.getInstance().initialize();
            TransactionRepository.getInstance().resetArray();
            TransactionRepository.getInstance().initialize();
            AppPreferencesHelper.getInstance().setCurrentUserName(user);
            AppPreferencesHelper.getInstance().setCurrentUserPassword(password);
            AppPreferencesHelper.getInstance().setCurrentRemember(remember);
            listener.onSuccess();
        } else {
            listener.onCredentialsFail();
        }

    }
}
