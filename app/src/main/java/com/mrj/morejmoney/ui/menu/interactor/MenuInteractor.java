package com.mrj.morejmoney.ui.menu.interactor;

import com.mrj.morejmoney.data.model.PiggyBank;
import com.mrj.morejmoney.data.model.Transaction;
import com.mrj.morejmoney.data.repository.PiggyBankRepository;
import com.mrj.morejmoney.data.repository.TransactionRepository;

/**
 * Created by Miguel on 25/02/2018.
 */

public class MenuInteractor implements MenuInteractorInterface {

    private MenuInteractorInterface.OnLoadFinishedListener listener;

    public MenuInteractor(MenuInteractorInterface.OnLoadFinishedListener listener){
        this.listener = listener;
    }

    @Override
    public void deletePiggyBank(PiggyBank piggyBank) {
        PiggyBankRepository.getInstance().delete(piggyBank);
        listener.onDeleted();
    }

    @Override
    public void deleteTransaction(Transaction transaction) {
        TransactionRepository.getInstance().delete(transaction);
        listener.onDeleted();
    }

    @Override
    public boolean existsAnyTransactionWithPiggyBankID(int id) {
        return TransactionRepository.getInstance().exists(id);
    }

    @Override
    public void deleteAllTransactionsWithPiggyBankID(int id) {
        TransactionRepository.getInstance().deleteAll(id, listener);
    }
}
