package com.example.pi.ui.transaction.interactor;

import com.example.pi.data.db.model.Transaction;

/**
 * Created by
 * @author Miguel Rodriguez Jimenez
 * @date 06/01/18
 *
 */

public interface AddTransactionInteractorInterface {

    interface OnAddTransactionListener{
        void onSuccess();
    }

    void validateTransaction(Transaction transaction);
}
