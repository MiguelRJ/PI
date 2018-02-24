package com.example.pi.ui.transaction.interactor;

import com.example.pi.data.model.Transaction;
import com.example.pi.data.repository.TransactionRepository;

/**
 * Created by
 * @author Miguel Rodriguez Jimenez
 * @date 05/01/18
 *
 */

public class ListTransactionInteractor implements ListTransactionInteractorInterface {

    private ListTransactionInteractorInterface.OnLoadFinisehdListener listener;

    public ListTransactionInteractor(ListTransactionInteractorInterface.OnLoadFinisehdListener listener){
        this.listener = listener;
    }

    @Override
    public void loadTransaction() {
        listener.onSucces(TransactionRepository.getInstance().getTransactions());
    }

    @Override
    public void deleteTransaction(Transaction transaction) {
        TransactionRepository.getInstance().deleteTransaction(transaction);
    }

}
