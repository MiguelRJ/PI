package com.example.pi.ui.transaction.presenter;

import com.example.pi.data.model.PiggyBank;
import com.example.pi.data.model.Transaction;
import com.example.pi.ui.transaction.contract.AddTransactionContract;
import com.example.pi.ui.transaction.interactor.AddTransactionInteractor;
import com.example.pi.ui.transaction.interactor.AddTransactionInteractorInterface;

import java.util.ArrayList;

/**
 * Created by
 * @author Miguel Rodriguez Jimenez
 * @date 06/01/18
 *
 */

public class AddTransactionPresenter implements AddTransactionContract.Presenter, AddTransactionInteractorInterface.OnAddTransactionListener {

    private AddTransactionContract.View view;
    private AddTransactionInteractorInterface interactor;

    public AddTransactionPresenter (AddTransactionContract.View view){
        this.view = view;
        interactor = new AddTransactionInteractor();
    }

    /* implements AddTransactionContract.Presenter */
    @Override
    public void OnDestroy() {
        view = null;
        interactor = null;
    }

    @Override
    public void options(int i, Object o) {

    }

    @Override
    public void validateTransaction(Transaction transaction) {
        interactor.validateTransaction(transaction,this);
    }

    @Override
    public void loadPiggyBank() {
        interactor.loadPiggyBank(this);
    }
    /* implements AddTransactionContract.Presenter */

    /* implements AddTransactionInteractorInterface.OnAddTransactionListener */
    @Override
    public void onSuccess() {
        view.showOnSuccess();
    }

    @Override
    public void onAmountEmptyError() {
        view.onAmountEmptyError();
    }

    @Override
    public void showPiggyBankOnSpinner(ArrayList<PiggyBank> piggyBanks) {
        view.showPiggyBankOnSpinner(piggyBanks);
    }
    /* implements AddTransactionInteractorInterface.OnAddTransactionListener */
}
