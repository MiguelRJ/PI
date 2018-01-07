package com.example.pi.ui.recover;

/**
 * Created by
 * @author Miguel Rodriguez Jimenez
 * @date 07/01/18
 *
 */

public interface RecoverContract {

    interface View {
        void onUserEmptyError();
        void onEmailEmptyError();
        void onEmailError();
        //void onUserDuplicated();
        //void onEmailDuplicated();
        void onSuccess();
    }

    interface Presenter {
        void validateCredentials(String name, String email);
        void onDestroy();
    }

}
