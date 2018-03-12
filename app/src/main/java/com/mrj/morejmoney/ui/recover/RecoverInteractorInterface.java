package com.mrj.morejmoney.ui.recover;

/**
 * Created by
 * @author Miguel Rodriguez Jimenez
 * @date 07/01/18
 *
 */

public interface RecoverInteractorInterface {

    void validateCredentials(String user, String email, RecoverInteractorInterface.OnRecoverFinishedListener listener);

    interface OnRecoverFinishedListener {
        void onUserEmptyError();
        void onEmailEmptyError();
        void onEmailError();
        //void onUserDuplicated();
        //void onEmailDuplicated();
        void onSuccess();
    }
}
