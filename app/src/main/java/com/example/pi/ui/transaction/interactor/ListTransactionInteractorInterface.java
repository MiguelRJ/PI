package com.example.pi.ui.transaction.interactor;

import com.example.pi.data.db.model.Transaction;

import java.util.List;

/**
 * Created by
 * @author Miguel Rodriguez Jimenez
 * @date 06/01/18
 *
 */

public interface ListTransactionInteractorInterface {

    interface OnLoadFinisehdListener{
        void onSucces(List<Transaction> list);
    }

    void loadTransaction();
}
