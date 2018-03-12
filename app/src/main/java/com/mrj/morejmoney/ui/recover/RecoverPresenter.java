package com.mrj.morejmoney.ui.recover;

/**
 * Created by
 * @author Miguel Rodriguez Jimenez
 * @date 07/01/18
 *
 */

public class RecoverPresenter implements RecoverContract.Presenter, RecoverInteractorInterface.OnRecoverFinishedListener {

    private RecoverContract.View view;
    private RecoverInteractorInterface interactor;

    public RecoverPresenter(RecoverContract.View view){
        this.view = view;
        this.interactor = new RecoverInteractor();
    }

    /* implements RecoverContract.Presenter */
    @Override
    public void validateCredentials(String name, String email) {
        interactor.validateCredentials(name,email,this);
    }

    @Override
    public void onDestroy() {
        view = null;
        interactor = null;
    }
    /* implements RecoverContract.Presenter */

    /* implements RecoverInteractorInterface.OnRecoverFinishedListener */
    @Override
    public void onUserEmptyError() {
        view.onUserEmptyError();
    }

    @Override
    public void onEmailEmptyError() {
        view.onEmailEmptyError();
    }

    @Override
    public void onEmailError() {
        view.onEmailError();
    }

    @Override
    public void onSuccess() {
        view.onSuccess();
    }
    /* implements RecoverInteractorInterface.OnRecoverFinishedListener */
}
