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
        void onUserDuplicated();
        void onEmailDuplicated();
        void onUserEmptyError();
        void onPasswordEmptyError();
        void onPasswordError();
    }
    interface Presenter {
        void validateCredentials(String user, String password, String email, String name);
        void onDestroy();
    }
}
