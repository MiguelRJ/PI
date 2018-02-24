package com.example.pi.ui.piggybank.presenter;

import android.util.Log;

import com.example.pi.data.model.PiggyBank;
import com.example.pi.ui.piggybank.contract.ListPiggyBankContract;
import com.example.pi.ui.piggybank.interactor.ListPiggyBankInteractor;
import com.example.pi.ui.piggybank.interactor.ListPiggyBankInteractorInterface;

import java.util.List;

/**
 * Created by
 * @author Miguel Rodriguez Jimenez
 * @date 04/01/18
 *      implements ListPiggyBankContract.Presenter
 *      implements ListPiggyBankInteractorInterface.OnLoadFinishedListener
 *      view, interactor
 *      public ListPiggyBankPresenter(ListPiggyBankContract.View view)
 */

public class ListPiggyBankPresenter implements ListPiggyBankContract.Presenter,ListPiggyBankInteractorInterface.OnLoadFinishedListener {

    public static final int DELETE = 1;

    private ListPiggyBankContract.View view;
    private ListPiggyBankInteractorInterface interactor;

    public ListPiggyBankPresenter(ListPiggyBankContract.View view){
        this.view = view;
        this.interactor = new ListPiggyBankInteractor(this);
    }

    /* implements ListPiggyBankContract.Presenter */
    @Override
    public void OnDestroy() {
        view = null;
        interactor = null;
    }

    @Override
    public void options(int i, Object o) {
        switch (i) {
            case DELETE:
                interactor.deletePiggyBank((PiggyBank) o);
                break;
            default:
                Log.e("Error option","Option "+i+" not found");
                break;
        }
    }

    @Override
    public void loadPiggyBank() {
        interactor.loadPiggyBank();
    }

    @Override
    public void onDeletedPiggyBank() {
        view.onDeletedPiggyBank();
    }
    /* implements ListPiggyBankContract.Presenter */

    /* ListPiggyBankInteractorInterface.OnLoadFinishedListener */
    @Override
    public void onSucces(List<PiggyBank> list) {
        view.showPiggyBank(list);
    }

    @Override
    public void showProgress() {
        view.showProgress();
    }

    @Override
    public void dismissProgress() {
        view.dismissProgress();
    }
    /* ListPiggyBankInteractorInterface.OnLoadFinishedListener */

}
