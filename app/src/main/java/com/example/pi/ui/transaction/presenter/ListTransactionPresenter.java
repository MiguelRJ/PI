package com.example.pi.ui.transaction.presenter;

import com.example.pi.data.db.model.Transaction;
import com.example.pi.ui.transaction.contract.ListTransactionContract;
import com.example.pi.ui.transaction.interactor.ListTransactionInteractor;
import com.example.pi.ui.transaction.interactor.ListTransactionInteractorInterface;

import java.util.List;

/**
 * Created by
 * @author Miguel Rodriguez Jimenez
 * @date 05/01/18
 *
 */

public class ListTransactionPresenter implements ListTransactionContract.Presenter, ListTransactionInteractorInterface.OnLoadFinisehdListener {

    private ListTransactionContract.View view;
    private ListTransactionInteractorInterface interactor;

    public ListTransactionPresenter (ListTransactionContract.View view){
        this.view = view;
        this.interactor = new ListTransactionInteractor(this);
    }

    /* implements ListTransactionContract.Presenter */
    @Override
    public void OnDestroy() {
        view = null;
        interactor = null;
    }

    @Override
    public void options(int i, Object o) {

    }

    @Override
    public void loadTransaction() {
        interactor.loadTransaction();
    }
    /* implements ListTransactionContract.Presenter */

    /* implements ListTransactionInteractorInterface.OnLoadFinisehdListener */
    @Override
    public void onSucces(List<Transaction> list) {
        view.showTransaction(list);
    }
    /* implements ListTransactionInteractorInterface.OnLoadFinisehdListener */
}
