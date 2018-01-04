package com.example.pi.ui.login;

/**
 * Created by
 * @author Miguel Rodriguez Jimenez
 * @date 05/01/18
 *
 */

public interface LoginContract {

    interface View {
        void onSuccess();
        void onUserEmptyError();
        void onPasswordEmptyError();
        void onPasswordError();
        void onCredentialsFail();
    }

    interface Presenter {
        void validateCredentials(String user, String password);
        void onDestroy();
    }

}
