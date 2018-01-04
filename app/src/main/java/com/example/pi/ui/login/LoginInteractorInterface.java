package com.example.pi.ui.login;

/**
 * Created by
 * @author Miguel Rodriguez Jimenez
 * @date 05/01/18
 *
 */

public interface LoginInteractorInterface {

    void validateCredentials(String user, String password, LoginInteractorInterface.OnLoginFinishedListener listener);

    interface OnLoginFinishedListener{
        void onUserEmptyError();
        void onPasswordEmptyError();
        void onPasswordError();
        void onCredentialsFail();
        void onSuccess();
    }

}
