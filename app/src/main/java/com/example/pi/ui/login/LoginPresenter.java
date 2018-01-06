package com.example.pi.ui.login;

/**
 * Created by Miguel on 04/01/2018.
 */

public class LoginPresenter implements LoginContract.Presenter, LoginInteractorInterface.OnLoginFinishedListener {

    private LoginContract.View view;
    private LoginInteractor interactor;

    public LoginPresenter(LoginContract.View view){
        this.view = view;
        interactor = new LoginInteractor();
    }

    /* implements LoginContract.Presenter */
    @Override
    public void validateCredentials(String user, String password, boolean remember) {
        interactor.validateCredentials(user,password,remember,this);
    }

    @Override
    public void onDestroy() {
        view = null;
        interactor = null;
    }
    /* implements LoginContract.Presenter */

    /* implements LoginInteractorInterface.OnLoginFinishedListener */
    @Override
    public void onUserEmptyError() {
        view.onUserEmptyError();
    }

    @Override
    public void onPasswordEmptyError() {
        view.onPasswordEmptyError();
    }

    @Override
    public void onPasswordError() {
        view.onPasswordError();
    }

    @Override
    public void onCredentialsFail() {
        view.onCredentialsFail();
    }

    @Override
    public void onSuccess() {
        view.onSuccess();
    }
    /* implements LoginInteractorInterface.OnLoginFinishedListener */

}
