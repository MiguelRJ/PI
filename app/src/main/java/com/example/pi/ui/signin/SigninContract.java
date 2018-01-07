package com.example.pi.ui.signin;

/**
 * Created by
 * @author Miguel Rodriguez Jimenez
 * @date 05/01/18
 *
 */

public interface SigninContract {
    interface View {
        void onSuccess();
        void onUserEmptyError();
        void onPasswordEmptyError();
        void onEmailEmptyError();
        void onPasswordError();
        void onEmailError();
        void onUserDuplicated();
        void onEmailDuplicated();
    }
    interface Presenter {
        void validateCredentials(String user, String password, String email, String name);
        void onDestroy();
    }
}
