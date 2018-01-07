package com.example.pi.ui.signin;

/**
 * Created by
 * @author Miguel Rodriguez Jimenez
 * @date 05/01/18
 *
 */

public class SigninPresenter implements SigninContract.Presenter, SigninInteractorInterface.OnSigninFinishedListener {

    private SigninContract.View view;
    private SigninInteractorInterface interactor;

    public SigninPresenter(SigninContract.View view){
        this.view = view;
        this.interactor = new SigninInteractor();
    }

    /* implements SigninContract.Presenter */
    @Override
    public void validateCredentials(String user, String password, String email, String name) {
        interactor.validateCredentials(user,password,email,name,this);
    }

    @Override
    public void onDestroy() {
        view = null;
        interactor = null;
    }
    /* implements SigninContract.Presenter */

    /* implements SigninInteractorInterface.OnLoginFinishedListener */
    @Override
    public void onUserEmptyError() {
        view.onUserEmptyError();
    }

    @Override
    public void onPasswordEmptyError() {
        view.onPasswordEmptyError();
    }

    @Override
    public void onEmailEmptyError() {
        view.onEmailEmptyError();
    }

    @Override
    public void onPasswordError() {
        view.onPasswordError();
    }

    @Override
    public void onEmailError() {
        view.onEmailError();
    }

    @Override
    public void onUserDuplicated() {
        view.onUserDuplicated();
    }

    @Override
    public void onEmailDuplicated() {
        view.onEmailDuplicated();
    }

    @Override
    public void onSuccess() {
        view.onSuccess();
    }
    /* implements SigninInteractorInterface.OnLoginFinishedListener */

}
