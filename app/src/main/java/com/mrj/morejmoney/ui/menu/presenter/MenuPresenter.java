package com.mrj.morejmoney.ui.menu.presenter;

import android.util.Log;

import com.mrj.morejmoney.data.model.PiggyBank;
import com.mrj.morejmoney.data.model.Transaction;
import com.mrj.morejmoney.ui.menu.contract.MenuContract;
import com.mrj.morejmoney.ui.menu.interactor.MenuInteractor;
import com.mrj.morejmoney.ui.menu.interactor.MenuInteractorInterface;

/**
 * Created by Miguel on 25/02/2018.
 */

public class MenuPresenter implements MenuContract.Presenter, MenuInteractorInterface.OnLoadFinishedListener {

    public static final int DELETE = 1;
    public static final int DELETE_ALL = 2;
    public static final int DELETE_TRANSACTION=3;

    private MenuContract.View view;
    private MenuInteractorInterface interactor;

    public MenuPresenter(MenuContract.View view){
        this.view = view;
        this.interactor = new MenuInteractor(this);
    }

    @Override
    public void onDeleted() {
        view.onDeleted();
    }

    @Override
    public boolean existsAnyTransactionWithPiggyBankID(int id) {
        return interactor.existsAnyTransactionWithPiggyBankID(id);
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
    public void OnDestroy() {
        view = null;
        interactor = null;
    }

    @Override
    public void options(int i, Object o) {
        switch (i) {
            case DELETE:
                interactor.deletePiggyBank( (PiggyBank)o );
                break;
            case DELETE_ALL:
                interactor.deleteAllTransactionsWithPiggyBankID( ((PiggyBank)o).getId() );
                break;
            case DELETE_TRANSACTION:
                interactor.deleteTransaction( (Transaction)o );
                break;
            default:
                Log.e("Error option","Option "+i+" not found");
                break;
        }
    }
}
