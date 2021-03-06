package com.mrj.morejmoney.ui.piggybank.presenter;

import android.util.Log;

import com.mrj.morejmoney.data.model.PiggyBank;
import com.mrj.morejmoney.ui.piggybank.contract.ListPiggyBankContract;
import com.mrj.morejmoney.ui.piggybank.interactor.ListPiggyBankInteractor;
import com.mrj.morejmoney.ui.piggybank.interactor.ListPiggyBankInteractorInterface;

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
    public static final int DELETE_ALL = 2;

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
            case DELETE_ALL:
                interactor.deleteAllTransactionsWithPiggyBankID( ((PiggyBank)o).getId() );
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

    @Override
    public boolean existsAnyTransactionWithPiggyBankID(int id) {
        return interactor.existsAnyTransactionWithPiggyBankID(id);
    }
    /* implements ListPiggyBankContract.Presenter */

    /* ListPiggyBankInteractorInterface.OnLoadFinishedListener */
    @Override
    public void onSucces(List<PiggyBank> list) {
        view.showPiggyBank(list);
    }

    @Override
    public void onSucces() {
        view.onSuccess();
    }

    @Override
    public void onError() {
        view.onError();
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
