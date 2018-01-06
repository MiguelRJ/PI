package com.example.pi.ui.transaction.interactor;

import android.text.TextUtils;

import com.example.pi.data.db.model.Transaction;

/**
 * Created by
 * @author Miguel Rodriguez Jimenez
 * @date 06/01/18
 *
 */

public class AddTransactionInteractor implements AddTransactionInteractorInterface{

    @Override
    public void validateTransaction(Transaction transaction , AddTransactionInteractorInterface.OnAddTransactionListener listener) {
        if (transaction.getAmount().isNaN()){
            listener.onAmountEmptyError();
        } else if (true){ // UserRepository validate credentials User Pass


        }
    }
}
