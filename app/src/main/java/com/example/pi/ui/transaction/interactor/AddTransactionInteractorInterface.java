package com.example.pi.ui.transaction.interactor;

import com.example.pi.data.model.Transaction;

/**
 * Created by
 * @author Miguel Rodriguez Jimenez
 * @date 06/01/18
 *
 */

public interface AddTransactionInteractorInterface {

    interface OnAddTransactionListener{
        void onSuccess();
        void onAmountEmptyError();
    }

    void validateTransaction(Transaction transaction, AddTransactionInteractorInterface.OnAddTransactionListener listener);
}
