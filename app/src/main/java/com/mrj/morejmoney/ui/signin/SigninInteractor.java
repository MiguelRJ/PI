package com.mrj.morejmoney.ui.signin;

import android.text.TextUtils;

import com.mrj.morejmoney.data.model.User;
import com.mrj.morejmoney.data.repository.UserRepository;
import com.mrj.morejmoney.ui.utils.CommonUtils;

/**
 * Created by
 * @author Miguel Rodriguez Jimenez
 * @date 05/01/18
 *
 */

public class SigninInteractor implements SigninInteractorInterface {

    @Override
    public void validateCredentials(String user, String password, String email, String name, OnSigninFinishedListener listener) {
        if(TextUtils.isEmpty(user)) {
            listener.onUserEmptyError();
        } else if (TextUtils.isEmpty(password)) {
            listener.onPasswordEmptyError();
        } else if (TextUtils.isEmpty(email)) {
            listener.onEmailEmptyError();
        } else if (!CommonUtils.isPasswordValid(password)) {
            listener.onPasswordError();
        } else if (!CommonUtils.isEmailValid(email)) {
            listener.onEmailError();
        } else if (UserRepository.getInstance().isUserAlreadySignIn(user)){
            listener.onUserDuplicated();
        } else if (UserRepository.getInstance().isEmailAlreadySignIn(email)){
            listener.onEmailDuplicated();
        } else {
            int lastId = UserRepository.getInstance().getLastId();
            UserRepository.getInstance().addUser(new User(lastId+1,user,email,password,name,null,null,0,null,null,false));
            listener.onSuccess();
        }
    }
}
