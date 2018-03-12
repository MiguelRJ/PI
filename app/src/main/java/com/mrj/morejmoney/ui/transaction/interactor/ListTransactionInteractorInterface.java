package com.mrj.morejmoney.ui.transaction.interactor;

import com.mrj.morejmoney.data.model.Transaction;

import java.util.List;

/**
 * Created by
 * @author Miguel Rodriguez Jimenez
 * @date 06/01/18
 *
 */

public interface ListTransactionInteractorInterface {

    void deleteTransaction(Transaction transaction);

    interface OnLoadFinisehdListener{
        void onSucces(List<Transaction> list);
        void showProgress();
        void dismissProgress();
    }

    void loadTransaction();
}
