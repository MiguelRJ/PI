package com.mrj.morejmoney.ui.signin;

/**
 * Created by
 * @author Miguel Rodriguez Jimenez
 * @date 05/01/18
 *
 */

public interface SigninInteractorInterface {

    void validateCredentials(String user, String password, String email, String name, SigninInteractorInterface.OnSigninFinishedListener listener);

    interface OnSigninFinishedListener{
        void onUserEmptyError();
        void onPasswordEmptyError();
        void onEmailEmptyError();
        void onPasswordError();
        void onEmailError();
        void onUserDuplicated();
        void onEmailDuplicated();
        void onSuccess();
    }
}
