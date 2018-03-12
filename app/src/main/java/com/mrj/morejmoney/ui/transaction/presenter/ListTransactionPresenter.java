package com.mrj.morejmoney.ui.transaction.presenter;

import com.mrj.morejmoney.data.model.Transaction;
import com.mrj.morejmoney.ui.transaction.contract.ListTransactionContract;
import com.mrj.morejmoney.ui.transaction.interactor.ListTransactionInteractor;
import com.mrj.morejmoney.ui.transaction.interactor.ListTransactionInteractorInterface;

import java.util.HashMap;
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


    @Override
    public String nameSelecteds() {
        return view.nameSelecteds();
    }
    /* implements ListTransactionContract.Presenter */

    /* Gestion del multichoicelistener */
    HashMap<Integer, Boolean> selection  = new HashMap<>();

    @Override
    public void setNewSelection(int position) {
        selection.put(position,true);
    }

    @Override
    public void removeSelection(int position) {
        selection.remove(position);
    }

    @Override
    public void deleteSelection() {
        for (Integer position : selection.keySet()){
            interactor.deleteTransaction(view.getTransaction(position));
        }
    }

    @Override
    public void clearSelection() {
        selection.clear();
    }

    @Override
    public boolean isPositionChecked(int position) {
        return selection.get(position)==null?false:true;
    }
    /* Gestion del multichoicelistener */

    /* implements ListTransactionInteractorInterface.OnLoadFinisehdListener */
    @Override
    public void onSucces(List<Transaction> list) {
        view.showTransaction(list);
    }

    @Override
    public void showProgress() {
        view.showProgress();
    }

    @Override
    public void dismissProgress() {
        view.dismissProgress();
    }
    /* implements ListTransactionInteractorInterface.OnLoadFinisehdListener */
}
