package com.mrj.morejmoney.ui.transaction.interactor;

import android.os.AsyncTask;

import com.mrj.morejmoney.data.model.Transaction;
import com.mrj.morejmoney.data.repository.TransactionRepository;

import java.util.ArrayList;

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
        new AsyncTask<Void, Void, ArrayList<Transaction>>() {
            @Override
            protected void onPreExecute() {
                listener.showProgress();
            }

            @Override
            protected ArrayList<Transaction> doInBackground(Void... voids) {
                return TransactionRepository.getInstance().getTransactions();
            }

            @Override
            protected void onPostExecute(ArrayList<Transaction> transactions) {
                listener.dismissProgress();
                listener.onSucces(transactions);
            }
        }.execute();
    }

    @Override
    public void deleteTransaction(Transaction transaction) {
        TransactionRepository.getInstance().delete(transaction);
    }

}
